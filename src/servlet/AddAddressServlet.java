package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddAddressServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		HttpSession session=request.getSession();
		ServletContext application=request.getServletContext();
		PrintWriter out = response.getWriter();
		Object userId=session.getAttribute("user_id");
		int user_id=0;
		if(userId==null){
			return;
		}else{
			user_id=(int) userId;
		}
		String username=request.getParameter("name");
		String phone=request.getParameter("phone");
		String country=request.getParameter("country");
		String city=request.getParameter("city");
		String address=request.getParameter("address");
		int code=Integer.parseInt(request.getParameter("code"));
		int user_rec_id=0;
		user_rec_id=maxUserRecId(user_id)+1;
		if(insertRec(username, code, phone, country, city, address, user_id, user_rec_id)){
			BuyGoodsServlet buyGoods=new BuyGoodsServlet();
			application.setAttribute("rec", buyGoods.findRecInfo(user_id));
			out.write(username);
			//request.getRequestDispatcher("manage_address.jsp").forward(request, response);
		}
	}
	
	public int maxUserRecId(int user_id){
		conn=getConnection();
		String sql="select max(user_rec_id) from rec_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				return res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean insertRec(String username,int code,String phone,String country,String city,String address,int user_id,int user_rec_id){
		conn=getConnection();
		String sql="insert into rec_info(rec_name,code,province,city,address,rec_phone,user_id,user_rec_id) values(?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, code);
			ps.setString(3, country);
			ps.setString(4, city);
			ps.setString(5, address);
			ps.setString(6, phone);
			ps.setInt(7, user_id);
			ps.setInt(8, user_rec_id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
