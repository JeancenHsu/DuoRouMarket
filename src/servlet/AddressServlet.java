package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.Rec_info;

public class AddressServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Rec_info rec_info;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		ServletContext application=request.getServletContext();
		BuyGoodsServlet buy=new BuyGoodsServlet();
		Object userId=session.getAttribute("user_id");
		int user_id=0;
		if(userId==null){
			return;
		}else{
			user_id=(int) userId;
		}
		String method=request.getParameter("method");
//		System.out.println(method);
		int user_rec_id=0;
		int rec_id=0;
		if(method.equals("modify")){
			user_rec_id=Integer.parseInt(request.getParameter("user_rec_id"));
//			System.out.println("haha:"+user_rec_id);
			session.setAttribute("user_rec_id", user_rec_id);
			session.setAttribute("modifyRec", findRecById(user_id, user_rec_id));
			request.getRequestDispatcher("modify_address.jsp").forward(request, response);
		}
		if(method.equals("updateModify")){
//			System.out.println("updateModify");
			Object recId=session.getAttribute("user_rec_id");
			rec_id=Integer.parseInt(request.getParameter("id"));
			String username=request.getParameter("name");
			String phone=request.getParameter("phone");
			String country=request.getParameter("country");
			String city=request.getParameter("city");
			String address=request.getParameter("address");
			int code=Integer.parseInt(request.getParameter("code"));
//			System.out.println("isorno");
			if(updateRec(user_id, rec_id, username, phone, country, city, address, code)){
				application.setAttribute("rec", buy.findRecInfo(user_id));
				out.write(""+0+"");
//				System.out.println("success");
//				request.getRequestDispatcher("submit_order.jsp").forward(request, response);
			}
		}
		if(method.equals("delete")){
			user_rec_id=Integer.parseInt(request.getParameter("user_rec_id"));
			if(deleteRec(user_id, user_rec_id)){
				application.setAttribute("rec", buy.findRecInfo(user_id));
				request.getRequestDispatcher("manage_address.jsp").forward(request, response);
			}
		}
	}
	public Rec_info findRecById(int user_id,int user_rec_id){
		rec_info=new Rec_info();
		String sql="select rec_name,rec_phone,province,city,address,code,user_rec_id from rec_info where user_id=? and user_rec_id=?";
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, user_rec_id);
			res=ps.executeQuery();
			while(res.next()){
				rec_info.setRec_name(res.getString(1));
				rec_info.setRec_phone(res.getString(2));
				rec_info.setProvince(res.getString(3));
				rec_info.setCity(res.getString(4));
				rec_info.setAddress(res.getString(5));
				rec_info.setCode(res.getInt(6));
				rec_info.setUser_rec_id(res.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rec_info;
	}
	
	public boolean updateRec(int user_id,int rec_id,String username,String phone,String country,String city,String address,int code){
		String sql="update rec_info set rec_name=?,rec_phone=?,province=?,city=?,address=?,code=? where user_id=? and user_rec_id=?";
		conn=getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, phone);
			ps.setString(3, country);
			ps.setString(4, city);
			ps.setString(5, address);
			ps.setInt(6, code);
			ps.setInt(7, user_id);
			ps.setInt(8, rec_id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteRec(int user_id,int user_rec_id){
		conn=getConnection();
		String sql="delete from rec_info where user_id=? and user_rec_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, user_rec_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
