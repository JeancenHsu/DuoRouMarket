package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;
import duorou.Cate_info;
import duorou.Goods_info;

public class ListGoodsServlet extends DBServlet{
	//先取出商品种类 根据商品种类取商品
	ArrayList<Cate_info> listCate;
	ArrayList<Goods_info> listGoods;
	ArrayList<Goods_info> tem;
	ArrayList<Goods_info> temAll;
	ArrayList<Goods_info> listName;
	Cate_info cate_info;
	ArrayList<Integer> cateIdlist;
	static Map<Integer, Cate_info> catelist;
	static Map<Integer, Goods_info> good;
	static Map<Integer,Goods_info> allgoods;
	Goods_info goods_info;
	int cate_id;
	static int goods_id;
	int idInt=0;
	static int goods_amount=0;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//查询所有商品种类
		request.getRequestDispatcher("shangcheng.jsp").forward(request, response);
		return;
	}
	
	public Map<Integer, Cate_info> findCate(){
		catelist=new HashMap<Integer, Cate_info>();
		conn=getConnection();
		String sql="select cate_id,cate_name,cate_description,cate_image from cate_info";
		try {
			ps=conn.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()){
				Cate_info cate_info=new Cate_info();
				cate_info.setCate_id(res.getInt(1));
				cate_id=cate_info.getCate_id();
				cate_info.setCate_name(res.getString(2));
				cate_info.setCate_description(res.getString(3));
				cate_info.setCate_image(res.getString(4));
				catelist.put(cate_id, cate_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return catelist;
	}
	public Map<Integer, Goods_info> findGoodsName(int cateId){
		good=new HashMap<Integer, Goods_info>();
		conn=DBConnection.getConnection();
		String sql="select goods_id,goods_name,single_price,location,pic_link,description,goods_amount,english_name,cate_id from goods_info where goods_state='热卖中' and cate_id=? limit 0,4";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateId);
			res=ps.executeQuery();
			while(res.next()){
				Goods_info goods_info=new Goods_info();
				goods_info.setGoods_id(res.getInt(1));
				goods_id=goods_info.getGoods_id();
				goods_info.setGoods_name(res.getString(2));
				goods_info.setSingle_price(res.getDouble(3));
				goods_info.setLocation(res.getString(4));
				goods_info.setPic_link(res.getString(5));
				goods_info.setDescription(res.getString(6));
				goods_info.setGoods_amount(res.getInt(7));
				goods_info.setEnglish_name(res.getString(8));
				goods_info.setCate_id(res.getInt(9));
				good.put(goods_id, goods_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return good;
	}
	
	//所有商品 
	public static Map<Integer, Goods_info> allGoods(){
//		System.out.println(cateId);
		allgoods=new HashMap<Integer, Goods_info>();
		conn=DBConnection.getConnection();
		String sql="select goods_id,goods_name,single_price,location,pic_link,description,goods_amount,english_name,cate_id from goods_info where goods_state='热卖中'";
		try {
			ps=conn.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()){
				Goods_info goods_info=new Goods_info();
				goods_info.setGoods_id(res.getInt(1));
				goods_id=goods_info.getGoods_id();
				goods_info.setGoods_name(res.getString(2));
				goods_info.setSingle_price(res.getDouble(3));
				goods_info.setLocation(res.getString(4));
				goods_info.setPic_link(res.getString(5));
				goods_info.setDescription(res.getString(6));
				goods_info.setGoods_amount(res.getInt(7));
				goods_amount=goods_info.getGoods_amount();
//				System.out.println("aaaa:"+goods_amount);
				goods_info.setEnglish_name(res.getString(8));
				goods_info.setCate_id(res.getInt(9));
				allgoods.put(goods_id, goods_info);
//				System.out.println("sdfgh:"+allgoods.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allgoods;
	}
	
//	public static Collection<Cate_info> getCate(){
//		return cate.values();
//	}
	//获取所有商品
	public static Collection<Goods_info> getAll(){
//		System.out.println(666+":"+good.values());
		return good.values();
	}
	
	//根据id号查找商品
//	public static Goods_info find(int goods_id){
//		System.out.println(100+":"+good.get(goods_id));
//		return good.get(goods_id);
//	}
	
	public static  Goods_info findAll(int goods_id){
		return allgoods.get(goods_id);
	}
}
