package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.DBConnection;

import duorou.Cate_info;

public class ShowCatesServlet extends DBServlet1 {
	
	int DATA_PER_PAGE = 15;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String cur = (String)request.getParameter("cur");
		
		ArrayList<Cate_info> list = new ArrayList<Cate_info>();
		list = getAllCates(Integer.parseInt(cur));
		
		int totalPage = getTotalPage();
		
		request.setAttribute("cates", list);
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("show_cates.jsp").forward(request,response);
		
	}
	
	public int getTotalPage(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			sql = "select count(*) from cate_info";
			
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
	
	
	public ArrayList<Cate_info> getAllCates(int cur){
		ArrayList<Cate_info> list = new ArrayList<Cate_info>();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select cate_id,cate_name,cate_description,cate_image from cate_info where 1 limit ?,?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cur - 1) * DATA_PER_PAGE);
			pstmt.setInt(2, DATA_PER_PAGE);
			
			res = pstmt.executeQuery();
			
			while(res.next()){
				Cate_info cate = new Cate_info();
				cate.setCate_id(res.getInt(1));
				cate.setCate_name(res.getString(2));
				cate.setCate_description(res.getString(3));
				cate.setCate_image(res.getString(4));
				list.add(cate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
