package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DBConnection /*extends HttpServlet*/{
	Context ctx;
	String Name;
	DataSource ds;
	protected static Connection conn;
	
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//		super.init();
//		try {
//			ctx = new InitialContext();
//			Name="java:/comp/env/jdbc/db";
//			ds=(DataSource) ctx.lookup(Name);
//			conn=ds.getConnection();
//			System.out.println("success");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			System.out.println("error");
//			e.printStackTrace();
//		}
//	}
	public static Connection getConnection(){
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("error!");
		}
		return conn;
	}
}
