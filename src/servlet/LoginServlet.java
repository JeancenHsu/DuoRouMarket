package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import com.sun.security.auth.UserPrincipal;

import sun.awt.RepaintArea;
import util.DBConnection;
import duorou.Goods_info;
import duorou.ShopingcartItem;
import duorou.ShoppingCart;
import duorou.User_info;

public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conn;
	PreparedStatement ps,ps1,ps2,ps3,ps4;
	ResultSet rs,rs1,rs2,rs3;
	Statement stmt;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	PrintWriter out;
	ServletContext application;
	int tempId=0;
	
	Map<Integer, ShopingcartItem> shoppingcartItemMap;
	ArrayList<Goods_info> idList;
	ArrayList<Integer> numList;
	int goods_id=0;
	ShopingcartItem shoppingcartItem;
	Goods_info goods_info;
	int user_id=0;
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request = req;
		response = resp;
		session = request.getSession();
		out = response.getWriter();
		
		String operation = getOperation();
		if(operation != null){
			if(operation.equals("exit")){
				exit();
				//取用户id
				Object objuser=session.getAttribute("user_id");
				if(objuser==null){
					return;
				}else{
					user_id=(int)objuser;
				}
				//将session里面的数据取出来 存回数据库
				Object obj=session.getAttribute("cart");
				if(obj==null){
					return;
				}else{
					ShoppingCart shoppingCart=(ShoppingCart) obj;
					shoppingcartItemMap=shoppingCart.getItems();
					//取键值对
					int quantity=0;
					Set<Integer> set=shoppingcartItemMap.keySet();
					Iterator it=set.iterator();
					idList=new ArrayList<Goods_info>();
					numList=new ArrayList<Integer>();
					while(it.hasNext()){
						goods_id=(int) it.next();
//						System.out.println("sdfg:"+goods_id);
						shoppingcartItem=shoppingcartItemMap.get(goods_id);
						quantity=shoppingcartItem.getQuantity();//数量
						numList.add(quantity);
//						double price=shoppingcartItem.getPrice();//单个商品总价
						goods_info=shoppingcartItem.getGoods_info();
						idList.add(goods_info);
						//先将这个人原来的购物车删除 更新新的购物车
						isClear(user_id);
					}
					String goods_name="";
					String english_name="";
					String pic_link="";
					double single_price=0;
					String location="";
					int number=0;
					int j=0;
					while(j<idList.size()){
						goods_info=idList.get(j);
						goods_id=goods_info.getGoods_id();
						goods_name=goods_info.getGoods_name();
						english_name=goods_info.getEnglish_name();
						pic_link=goods_info.getPic_link();
						single_price=goods_info.getSingle_price();
						number=numList.get(j);
						location=goods_info.getLocation();
						insertCart(user_id, goods_id, goods_name, english_name, pic_link, single_price, number, location);
						j++;
					}
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request = req;
		response = resp;
		session = request.getSession();
		String operation = getOperation();
		if(operation != null){
			if(operation.equals("login")){
				login();
			}else if(operation.equals("register")){
				register();
			}else if(operation.equals("register2")){
				addUserInfo();
			}else if(operation.equals("search")){
				search();
			}
		}
	}
	
	//获取数据库中所有用户名密码
	public List<User_info> read(){
		List<User_info> user = new ArrayList<User_info>();
		conn = DBConnection.getConnection();
		try{
			ps = conn.prepareStatement("select user_phone,password from user_info");
			rs = ps.executeQuery();
			while(rs.next()){
				User_info info = new User_info();
				info.setUser_phone(rs.getString(1));
				info.setPassword(rs.getString(2));
				user.add(info);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void login(){
		conn = DBConnection.getConnection();
		session = request.getSession();
		String phone = request.getParameter("userphone");
		String password = request.getParameter("password");
		String phoneInDb=null,pwdInDb=null;
		boolean flag = false,flag2 = true,flag3 = false;
		int status = 0;
		int user_id = 0;
		String user_name = null;
		session.setAttribute("status", status);
		application = getServletContext();
		@SuppressWarnings("unchecked")
		List<String> onlineuser = (List<String>)application.getAttribute("online");
		try {
			ps = conn.prepareStatement("select user_phone,password from user_info");
			rs = ps.executeQuery();
			while(rs.next()){
				phoneInDb = rs.getString(1);
				pwdInDb = rs.getString(2);
				if(phoneInDb.equals(phone)){
					flag = true;
					if(password.equals(pwdInDb)){
						if(onlineuser == null){
							onlineuser = new ArrayList<String>();
							onlineuser.add(phone);
						}else{
							for(int i=0; i<onlineuser.size(); i++){
								if(phone.equals(onlineuser.get(i))){
									flag2 = false;
									session.setAttribute("errorMsg", "重复登录!");
									response.sendRedirect("loginFail.jsp");
									return;
								}
							}
							if(flag2){
								stmt = conn.createStatement();
								rs = stmt.executeQuery("select current_timestamp()");
								Timestamp time;
								if(rs.next()){
									time = rs.getTimestamp(1);
									ps = conn.prepareStatement("update user_info set last_login_time=? where user_phone=?");
									ps.setTimestamp(1, time);
									ps.setString(2, phone);
									ps.executeUpdate();
								}
								ps.close();
								onlineuser.add(phone);
							}
						}
						application.setAttribute("online", onlineuser);
						String check[] = request.getParameterValues("check");
						if(check != null){
							if(check[0].equals("on")){
								flag3 = true;
								session.setAttribute("flagOfCookie", flag3);
							}
						}else{
							flag3 = false;	
							session.setAttribute("flagOfCookie", flag3);
						}
						if(flag3){
							phone=java.net.URLEncoder.encode(phone, "utf-8");
							Cookie cookie = new Cookie(phone, password);
							cookie.setMaxAge(604800);
							response.addCookie(cookie);
						}
						ps2 = conn.prepareStatement("select user_id,user_name from user_info where user_phone=?");
						ps2.setString(1, phone);
						rs2 = ps2.executeQuery();
						if(rs2.next()){
							user_id = rs2.getInt(1);
							user_name = rs2.getString(2);
						}
						session.setAttribute("user_id", user_id);
//						System.out.println("hhhh:"+session.getAttribute("user_id"));
						session.setAttribute("phone", phone); 
						session.setAttribute("user_name", user_name);
						session.setAttribute("last_user_phone", phone);
						getCookiePhoneAndPassword();
//						response.sendRedirect("");
//						System.out.println("user_id4:"+session.getAttribute("user_id"));
						try {
							request.getRequestDispatcher("index.jsp").forward(request, response);
						} catch (ServletException e) {
							e.printStackTrace();
						}
//						System.out.println("user_id3:"+session.getAttribute("user_id"));
						ps2.close();
					}else{
							session.setAttribute("errorMsg", "密码错误!");
							response.sendRedirect("loginFail.jsp");
						}
					}
				}
			if(!flag){
				if(adminLogin(phone, password)){
					session.setAttribute("isadmin", true);
					session.setAttribute("user_name", phone);
					session.setAttribute("phone", phone);
					try {
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					}
					return;
				}else{
					session.setAttribute("errorMsg", "用户名错误!");
					response.sendRedirect("loginFail.jsp");
					return;
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public boolean adminLogin(String phone,String password){
		boolean flag = false;
		conn = DBConnection.getConnection();
		try {
			ps = conn.prepareStatement("select adminname,adminpwd from admin");
			rs = ps.executeQuery();
			if(rs.next()){
				if(phone.equals(rs.getString(1)) && password.equals(rs.getString(2))){
					flag = true;
				}
			}
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
			return flag;
		}
	}

	//注册账号
	public void register(){
		conn = DBConnection.getConnection();
		session = request.getSession();
		String phone = request.getParameter("userphone");
		String password = request.getParameter("password");
		try {
			ps = conn.prepareStatement("select user_phone from user_info where user_phone=?");
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()){
				session.setAttribute("errorMsg", "该手机号已注册！");
				response.sendRedirect("signinFail.jsp");
				ps.close();
			}else{
				ps1 = conn.prepareStatement("insert into user_info(user_phone,password) values(?,?)");
				ps1.setString(1, phone);
				ps1.setString(2, password);
				int i = ps1.executeUpdate();
				if(i>0){
					ps2 = conn.prepareStatement("select user_id from user_info where user_phone=?");
					ps2.setString(1, phone);
					rs1 = ps2.executeQuery();
					if(rs1.next()){
						int user_id = rs1.getInt(1);
						session.setAttribute("user_id", user_id);
						tempId = user_id;
						//rec_info
						ps3 = conn.prepareStatement("insert into rec_info(user_id,user_rec_id,rec_phone) values(?,1,?)");
						ps3.setInt(1, user_id);
						ps3.setString(2, phone);
						ps3.executeUpdate();
						//写注册时间
						stmt = conn.createStatement();
						rs2 = stmt.executeQuery("select current_timestamp()");
						Timestamp time;
						if(rs2.next()){
							time = rs2.getTimestamp(1);
							ps4 = conn.prepareStatement("update user_info set register_time=? where user_id=?");
							ps4.setTimestamp(1, time);
							ps4.setInt(2, user_id);
							ps4.executeUpdate();
						}
					}
					response.sendRedirect("signin2.jsp");
					ps4.close();
					ps3.close();
				}else{
					session.setAttribute("erroMsg", "数据库写入失败！");
					response.sendRedirect("signinFail.jsp");
				}
				ps2.close();
				ps1.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addUserInfo(){
		conn = DBConnection.getConnection();
		session = request.getSession();
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		int user_id = tempId;
//		System.out.println(tempId+"vsdbs");
//		int user_id = Integer.parseInt(session.getAttribute("user_id").toString());
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
		int code = Integer.parseInt(request.getParameter("code"));
		try {
			ps2 = conn.prepareStatement("update user_info set user_name=? where user_id=?");
			ps2.setString(1, username);
			ps2.setInt(2, user_id);
			ps2.executeUpdate();
			
			ps = conn.prepareStatement("update rec_info set province=?,city=?,address=?,rec_name=?,code=? where user_id=?");
			ps.setString(1, province);
			ps.setString(2, city);
			ps.setString(3, address);
			ps.setString(4, username);
			ps.setInt(5, code);
			ps.setInt(6, user_id);
			int i = ps.executeUpdate();
			if(i>0){
				out.write("0");
		//		response.sendRedirect("signin3.jsp");
				ps.close();
			}else{
				session.setAttribute("errorMsg", "数据库写入失败！");
				response.sendRedirect("signinFail.jsp");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//退出登录
	public void exit(){
		session = request.getSession();
		application = getServletContext();
		Object obj2 = session.getAttribute("phone");
		Object obj1 = application.getAttribute("online");
		
		if(obj2 == null){
			return;
		}
		if(obj1 == null){
			session.setAttribute("phone", null);
			session.setAttribute("user_name", null);
			session.setAttribute("isadmin", null);
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			@SuppressWarnings("unchecked")
			ArrayList<String> online = (ArrayList<String>) obj1;
			String phone = (String) obj2;
			online.remove(phone);
			session.setAttribute("last_user_phone", phone);
			application.setAttribute("online", online);
			session.setAttribute("phone", null);
			session.setAttribute("user_name", null);
			session.setAttribute("isadmin", null);
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//先将这个人原来的购物车清除
	public void isClear(int user_id){
		conn=DBConnection.getConnection();
		String sql=" delete from cart_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCart(int user_id,int goods_id,String goods_name,String english_name,String pic_link,double single_price,int quantity,String location){
		conn=DBConnection.getConnection();
		String sql="insert into cart_info(user_id,goods_id,goods_name,english_name,pic_link,single_price,quantity,location) values(?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, goods_id);
			ps.setString(3, goods_name);
			ps.setString(4, english_name);
			ps.setString(5, pic_link);
			ps.setDouble(6, single_price);
			ps.setInt(7, quantity);
			ps.setString(8, location);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public String getOperation(){
		StringBuffer url = request.getRequestURL();
		String operation = null;
		if(url != null){
			int index = url.lastIndexOf("/");
			operation = url.substring(index+1);
		}
		return operation;
	}
	
	//设置cookie值
	public void getCookiePhoneAndPassword(){
	   session = request.getSession();
  	   String phone = null;
  	   String password = null;
  	   boolean flag = true;
  	   Object obj1 = session.getAttribute("last_user_phone");
  	   Object obj2 = session.getAttribute("flagOfCookie");
  	   //如果上次没有登录过
  	   if(obj1 == null){                   
  	   		session.setAttribute("user_name", null);
  	   		phone = "";
  	   		password = "";
  	   }else{
	  	   phone = (String)obj1;
//	  	   System.out.println("user_id:"+session.getAttribute("user_id"));
//	  	   System.out.println("phone:"+phone);
	       Cookie cookie[] = request.getCookies();
	       List<User_info> userinfo = read();
	       //最后一次登录客户的用户名密码
	       for(User_info u:userinfo){
			   if(u.getUser_phone().equals(phone)){
			       	password = u.getPassword();
			   }
		   }
	       //检查cookie中是否存在该用户
	       for(Cookie c:cookie){
				if(!c.getName().equals(phone)){
					flag = false;
				}else{
				 	flag = true;    //如果存在，则为true
					break;
				}
	       }
  	   }
  	   if(!flag){                   //如果cookie中不存在，则设为空
	   		phone = null;
       		password = null;
	   }
	   if(obj2 != null){             
		   String flagOfCookie = obj2.toString();
	  	   if(flagOfCookie.equals("false")){
	  	   		phone = "";
	       		password = "";
	  	   }
	   }
	   session.setAttribute("phoneInCookie", phone);
	   session.setAttribute("passwordInCookie", password);
//	   System.out.println("user_id2:"+session.getAttribute("user_id"));
	}
	
	//搜索
	public void search(){
		session = request.getSession();
		conn = DBConnection.getConnection();
		String searchContent = null;
		List<Integer> goods_id = new ArrayList<Integer>();
		Object obj = request.getParameter("search");
		if(obj == null || obj == ""){
			try {
				session.setAttribute("search", "");
				request.getRequestDispatcher("show.jsp").forward(request, response);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		searchContent = (String) obj;
		try {
			ps = conn.prepareStatement("select goods_id from goods_info where goods_name like ? or cate_id in(select cate_id from cate_info where cate_name like ?)");
			ps.setString(1, "%" + searchContent + "%");
			ps.setString(2, "%" + searchContent + "%");
			rs = ps.executeQuery();
			if(!rs.next()){
//				System.out.println("search is null");
				session.setAttribute("search", "");
			}else{
				goods_id.add(rs.getInt(1));
			}
			while(rs.next()){
				goods_id.add(rs.getInt(1));
				session.setAttribute("search", goods_id);
			}
			response.sendRedirect("show.jsp");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}

