package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.DBConnection;
import method.DBOperator;
import duorou.Cate_info;
import duorou.Goods_info;
import duorou.Order_details;
import duorou.Order_info;
import duorou.Rec_info;
import duorou.User_info;

public class ShowOrderDetailServlet extends DBServlet1 {
	int DATA_PER_PAGE = 5;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		int cur = Integer.parseInt(request.getParameter("cur"));
		String order_id = request.getParameter("order_id");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int user_rec_id = Integer.parseInt(request.getParameter("user_rec_id"));
		
		Order_info order = new Order_info();
		Rec_info rec = new Rec_info();
		User_info user = new User_info();
		ArrayList<Order_details> details = new ArrayList<Order_details>();
//		ArrayList<Goods_info> goods =new ArrayList<Goods_info>();
		
		Map<Integer, Goods_info> goods_detail = new HashMap<Integer, Goods_info>();
		
		
		order = getOrderByOrderId(order_id);
		details = getOrderDetailsByOrderId(cur, order_id);
		rec = getRecInfoByUserIdAndUserRecId(user_id, user_rec_id);
		user = getUserInfoByUserId(user_id);
		

		for(int i=0;i<details.size();i++){
			Order_details d = details.get(i);
			Goods_info goods_info = new Goods_info();
			
			int goods_id = d.getGoods_id();
			goods_info = getGoodsInfoByGoodsId(goods_id);
			
			goods_detail.put(goods_id, goods_info);
			
		}
		
		int totalPage = getTotalPageByOrderId(order_id);
		
		request.setAttribute("order", order);
		request.setAttribute("details", details);
		request.setAttribute("rec", rec);
		request.setAttribute("user", user);
		request.setAttribute("goods", goods_detail);
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("show_order_detail.jsp").forward(request,response);
		
	}

	public int getTotalPageByOrderId(String order_id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			sql = "select count(*) from order_details where order_id=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			count = (int)Math.ceil((count + 1.0 - 1.0) / DATA_PER_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Order_info getOrderByOrderId(String order_id){
		Order_info order = new Order_info();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select order_id,user_id,user_rec_id,start_time,finish_time,status,whole_number,whole_price from order_info where order_id=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order_id);
			
			res = pstmt.executeQuery();
			
			if(res.next()){
				order.setOrder_id(res.getString(1));
				order.setUser_id(res.getInt(2));
				order.setUser_rec_id(res.getInt(3));
				order.setStart_time(res.getTimestamp(4));
				order.setFinish_time(res.getTimestamp(5));
				order.setStatus(res.getString(6));
				order.setWhole_number(res.getInt(7));
				order.setWhole_price(res.getInt(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
		
	public ArrayList<Order_details> getOrderDetailsByOrderId(int cur,String order_id){
		ArrayList<Order_details> list = new ArrayList<Order_details>();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select order_id,user_id,goods_id,quantity,price from order_details where order_id=? limit ?,?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, order_id);
			pstmt.setInt(2, (cur - 1) * DATA_PER_PAGE);
			pstmt.setInt(3, DATA_PER_PAGE);
			
			res = pstmt.executeQuery();
			
			while(res.next()){
				Order_details details = new Order_details();
				details.setOrder_id(res.getString(1));
				details.setUser_id(res.getInt(2));
				details.setGoods_id(res.getInt(3));
				details.setQuantity(res.getInt(4));
				details.setPrice(res.getDouble(5));
				
				list.add(details);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Rec_info getRecInfoByUserIdAndUserRecId(int user_id,int user_rec_id){
		Rec_info receive = new Rec_info();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select user_id,rec_name,user_rec_id,province,city,address,rec_phone from rec_info where user_id=? and user_rec_id=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, user_rec_id);
			res = pstmt.executeQuery();
			
			if(res.next()){
				receive.setUser_id(res.getInt(1));
				receive.setRec_name(res.getString(2));
				receive.setUser_rec_id(res.getInt(3));
				receive.setProvince(res.getString(4));
				receive.setCity(res.getString(5));
				receive.setAddress(res.getString(6));
				receive.setRec_phone(res.getString(7));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return receive;
	}

	public Goods_info getGoodsInfoByGoodsId(int goods_id){
		Goods_info goods = new Goods_info();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select goods_id,cate_id,goods_name,pic_link,description,single_price,goods_amount,goods_state,location,english_name,province,city from goods_info where goods_id=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods_id);
			
			res = pstmt.executeQuery();
			
			if(res.next()){
				goods.setGoods_id(res.getInt(1));
				goods.setCate_id(res.getInt(2));
				goods.setGoods_name(res.getString(3));
				goods.setPic_link(res.getString(4));
				goods.setDescription(res.getString(5));
				goods.setSingle_price(res.getDouble(6));
				goods.setGoods_amount(res.getInt(7));
				goods.setGoods_state(res.getString(8));
				goods.setLocation(res.getString(9));
				goods.setEnglish_name(res.getString(10));
				goods.setLocation_province(res.getString(11));
				goods.setLocation_city(res.getString(12));
				
				Cate_info cate = new Cate_info();
				DBOperator op = new DBOperator();
				cate = op.getCateByCateId(goods.getCate_id());
				
				goods.setCate_name(cate.getCate_name());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goods;
	}

	public User_info getUserInfoByUserId(int user_id){
		User_info user = new User_info();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select user_id,user_name,user_phone,password,register_time,last_login_time from user_info where user_id=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			
			res = pstmt.executeQuery();
			
			if(res.next()){
				user.setUser_id(res.getInt(1));
				user.setUser_name(res.getString(2));
				user.setUser_phone(res.getString(3));
				user.setPassword(res.getString(4));
				user.setRegister_time(res.getTimestamp(5));
				user.setLast_login_time(res.getTimestamp(6));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
