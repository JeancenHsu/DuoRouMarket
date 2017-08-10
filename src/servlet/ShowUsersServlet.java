package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.DBConnection;
import method.DBOperator;
import duorou.Cate_info;
import duorou.Goods_info;
import duorou.User_info;

public class ShowUsersServlet extends DBServlet1 {
	int DATA_PER_PAGE = 14;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cur = (String)request.getParameter("cur");
		
		ArrayList<User_info> list = new ArrayList<User_info>();
		list = getAllUsers(Integer.parseInt(cur));
		
		int totalPage = getTotalPage();
		
		request.setAttribute("users", list);
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("show_users.jsp").forward(request,response);
		
	}

	public int getTotalPage(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			sql = "select count(*) from user_info";
			
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
	
	
	public ArrayList<User_info> getAllUsers(int cur){
		ArrayList<User_info> list = new ArrayList<User_info>();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select user_id,user_name,user_phone,register_time,last_login_time from user_info limit ?,?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cur - 1) * DATA_PER_PAGE);
			pstmt.setInt(2, DATA_PER_PAGE);
			
			res = pstmt.executeQuery();
			
			while(res.next()){
				User_info users = new User_info();
				users.setUser_id(res.getInt(1));
				users.setUser_name(res.getString(2));
				users.setUser_phone(res.getString(3));
				users.setRegister_time(res.getTimestamp(4));
				users.setLast_login_time(res.getTimestamp(5));
				
				
				list.add(users);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
