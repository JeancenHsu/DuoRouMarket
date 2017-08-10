package servlet;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBServlet extends HttpServlet{
	static Connection conn;
	Statement stmt;
	static PreparedStatement ps;
	static ResultSet res;
	CallableStatement cstmt;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/duorou?useUnicode=true&characterEncoding=gbk";
			String user = "u1";
			String password = "abc";
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
//		driver = config.getInitParameter("driver");
//		System.out.print(driver);
//		url = config.getInitParameter("url");
//		user = config.getInitParameter("user");
//		password = config.getInitParameter("password");
		getConnection();
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		super.service(arg0, arg1);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
//			if(stmt!=null&&!stmt.isClosed()){
//				stmt.close();
//			}
			if(conn!=null&&conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		super.destroy();
	}
}

