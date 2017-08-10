package method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import duorou.Cate_info;
import duorou.Goods_info;
import duorou.User_info;

import method.DBConnection;


public class DBOperator {
	Connection conn;
	PreparedStatement ps;
	ResultSet res;
	public ArrayList<Cate_info> getAllCates(){
		conn=DBConnection.getConnection();
		ArrayList<Cate_info> list = new ArrayList<Cate_info>();
		String sql="select cate_id,cate_name,cate_description,cate_image from cate_info";
		try {
			ps=conn.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()){
				Cate_info cate = new Cate_info();
				cate.setCate_id(res.getInt(1));
				cate.setCate_name(res.getString(2));
				cate.setCate_description(res.getString(3));
				cate.setCate_image(res.getString(4));
				list.add(cate);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public Cate_info getCateByCateId(int cate_id){
		conn=DBConnection.getConnection();
		Cate_info cate = new Cate_info();
		String sql="select cate_id,cate_name,cate_description,cate_image from cate_info where cate_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cate_id);
			res=ps.executeQuery();
			if(res.next()){
				cate.setCate_id(res.getInt(1));
				cate.setCate_name(res.getString(2));
				cate.setCate_description(res.getString(3));
				cate.setCate_image(res.getString(4));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cate;
	}
	
	public User_info getUserByUserId(int user_id){
		conn=DBConnection.getConnection();
		User_info user = new User_info();
		String sql="select user_id,user_name,user_phone,register_time,last_login_time,password from user_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			if(res.next()){
				user.setUser_id(res.getInt(1));
				user.setUser_name(res.getString(2));
				user.setUser_phone(res.getString(3));
				user.setRegister_time(res.getTimestamp(4));
				user.setLast_login_time(res.getTimestamp(5));
				user.setPassword(res.getString(6));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
}

