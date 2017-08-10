package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.DBConnection;
import duorou.Cart_info;
import duorou.Cate_info;
import duorou.Goods_info;

public class Operation extends DBConnection{
	PreparedStatement ps;
	ResultSet res;
	ArrayList<Integer> idlist;
	ArrayList<Integer> cateIdlist;
	ArrayList<Goods_info> goodslist;
	Map<Integer, Goods_info> goods;
	//��������Ʒ������ȡ����
	public ArrayList<Integer> findCateID(){
		cateIdlist=new ArrayList<Integer>();
		conn=DBConnection.getConnection();
		String sql="select cate_id from cate_info";
		try {
			ps=conn.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()){
				cateIdlist.add(res.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cateIdlist;
	}
	
	//������Ʒ������ȡ����Ʒ����
	public Cate_info findCateName(int cateId){
		Cate_info cate_info=new Cate_info();
		conn=DBConnection.getConnection();
		String sql="select cate_name from cate_info where cate_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cateId);
			res=ps.executeQuery();
			while(res.next()){
				cate_info.setCate_name(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cate_info;
	}

	public ArrayList<Goods_info> findImagesAndName(int cateId){
		conn=DBConnection.getConnection();
		goodslist=new ArrayList<Goods_info>();
		try {
			String sql="select goods_id,pic_link,goods_name,description from goods_info where cate_id=? and goods_state='������'";
			ps=conn.prepareStatement(sql);
//			ps.setInt(1, id);
			ps.setInt(1, cateId);
			res=ps.executeQuery();
			while(res.next()){
				Goods_info goods=new Goods_info();
				goods.setGoods_id(res.getInt(1));
				goods.setPic_link(res.getString(2));
				goods.setGoods_name(res.getString(3));
				goods.setDescription(res.getString(4));
				goodslist.add(goods);
//				System.out.println(goodslist.size());
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodslist;
	}
	
	//ȡ����Ʒ��Ϣ�浽map��������
	public Map<Integer, Goods_info> findGoods(){
		goods=new HashMap<Integer, Goods_info>();
		conn=DBConnection.getConnection();
		String sql="select goods_id,goods_name,single_price,location from goods_info where goods_state='������'";
		try {
			ps=conn.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()){
				Goods_info goods_info=new Goods_info();
				int goods_id=res.getInt(1);
				goods_info.setGoods_name(res.getString(2));
				goods_info.setSingle_price(res.getDouble(3));
				goods_info.setLocation(res.getString(4));
				goods.put(goods_id, goods_info);
//				System.out.println(goods.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}
	//����goods_id������Ʒ
	public Goods_info find(int goods_id){
		System.out.println(goods.get(goods_id));
		return goods.get(goods_id);
	}
	public static void main(String[] args) {
		Operation db=new Operation();
		db.findImagesAndName(2);
		db.findGoods();
		db.find(3);
	}
}
