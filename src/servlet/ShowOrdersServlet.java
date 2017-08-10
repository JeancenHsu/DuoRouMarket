package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.DBConnection;
import duorou.Order_info;
import duorou.User_info;

public class ShowOrdersServlet extends DBServlet1 {
	int DATA_PER_PAGE = 15;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cur = (String)request.getParameter("cur");
		
		ArrayList<Order_info> list = new ArrayList<Order_info>();
		list = getAllOrders(Integer.parseInt(cur));
		
		int totalPage = getTotalPage();
		
		request.setAttribute("orders", list);
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("show_orders.jsp").forward(request,response);
		
	}

	public int getTotalPage(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			sql = "select count(*) from order_info";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
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
	
	
	public ArrayList<Order_info> getAllOrders(int cur){
		ArrayList<Order_info> list = new ArrayList<Order_info>();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select order_id,user_id,user_rec_id,start_time,finish_time,status,whole_number,whole_price from order_info limit ?,?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cur - 1) * DATA_PER_PAGE);
			pstmt.setInt(2, DATA_PER_PAGE);
			
			res = pstmt.executeQuery();
			
			while(res.next()){
				Order_info orders = new Order_info();
				orders.setOrder_id(res.getString(1));
				orders.setUser_id(res.getInt(2));
				orders.setUser_rec_id(res.getInt(3));
				orders.setStart_time(res.getTimestamp(4));
				orders.setFinish_time(res.getTimestamp(5));
				orders.setStatus(res.getString(6));
				orders.setWhole_number(res.getInt(7));
				orders.setWhole_price(res.getInt(8));
				
				
				list.add(orders);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
