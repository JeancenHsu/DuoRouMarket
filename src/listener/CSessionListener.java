package listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import util.DBConnection;
import duorou.Goods_info;
import duorou.ShopingcartItem;
import duorou.ShoppingCart;

public class CSessionListener implements HttpSessionListener,HttpSessionAttributeListener{
	Map<Integer, ShopingcartItem> shoppingcartItemMap;
	ShopingcartItem shoppingcartItem;
	ShoppingCart shoppingcart;
	Goods_info goods_info;
	int goods_id=0;
	int user_id=0;
	Connection conn;
	PreparedStatement ps;
	ResultSet res;
	ArrayList<Goods_info> idList;
	ArrayList<Integer> numList;
//	HttpServletRequest request;
//	HttpSession session;
	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		HttpSession session=arg0.getSession();
//		System.out.println("user_id7:"+session.getAttribute("user_id"));
		Object objUser=session.getAttribute("user_id");
		if(objUser==null){
			return;
		}
		user_id=(int) objUser;
		//���û� �������ݿ��е� ���ﳵ ���س���
		session.setAttribute("cart", findCart(user_id));
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("close");
//		arg0.getSession().setAttribute("user_id", 1);
		//ֻҪ����������ʸ�վ�㣬Ȼ��5s��ˢ��֮��sessionDestroyed�ͻᱻ�Զ�������
//		arg0.getSession().setMaxInactiveInterval(5);//��СĬ�ϳ�ʱʱ�� 
		//ȡ�û�id
		Object objuser=arg0.getSession().getAttribute("user_id");
		if(objuser==null){
			return;
		}else{
			user_id=(int)objuser;
		}
		//��session���������ȡ���� ������ݿ�
		Object obj=arg0.getSession().getAttribute("cart");
		if(obj==null){
//			System.out.println("asdfgh");
			return;
		}else{
			ShoppingCart shoppingCart=(ShoppingCart) obj;
			shoppingcartItemMap=shoppingCart.getItems();
//			System.out.println("sessionhrhr:"+shoppingcartItemMap.size());
			//ȡ��ֵ��
			int quantity=0;
			Set<Integer> set=shoppingcartItemMap.keySet();
			Iterator it=set.iterator();
			idList=new ArrayList<Goods_info>();
			numList=new ArrayList<Integer>();
			while(it.hasNext()){
				goods_id=(int) it.next();
//				System.out.println("sdfg:"+goods_id);
				shoppingcartItem=shoppingcartItemMap.get(goods_id);
				quantity=shoppingcartItem.getQuantity();//����
				numList.add(quantity);
//				double price=shoppingcartItem.getPrice();//������Ʒ�ܼ�
				goods_info=shoppingcartItem.getGoods_info();
				idList.add(goods_info);
				//�Ƚ������ԭ���Ĺ��ﳵɾ�� �����µĹ��ﳵ
				isClear(user_id);
			}
			String goods_name="";
			String english_name="";
			String pic_link="";
			double single_price=0;
			String location="";
			int number=0;
			int j=0;
			while(j<idList.size()){
				goods_info=idList.get(j);
				goods_id=goods_info.getGoods_id();
				goods_name=goods_info.getGoods_name();
				english_name=goods_info.getEnglish_name();
				pic_link=goods_info.getPic_link();
				single_price=goods_info.getSingle_price();
				number=numList.get(j);
				location=goods_info.getLocation();
				insertCart(user_id, goods_id, goods_name, english_name, pic_link, single_price, number, location);
				j++;
			}
		}
	}
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
	}
	
	public ShoppingCart findCart(int user_id){
//		System.out.println("hehe:"+user_id);
		shoppingcartItemMap=new HashMap<Integer, ShopingcartItem>();
		shoppingcart=new ShoppingCart();
		conn=DBConnection.getConnection();
		String sql="select goods_id,goods_name,english_name,pic_link,single_price,quantity,location from cart_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Goods_info goods_info=new Goods_info();
				goods_info.setGoods_id(res.getInt(1));
				goods_id=goods_info.getGoods_id();
				goods_info.setGoods_name(res.getString(2));
				goods_info.setEnglish_name(res.getString(3));
				goods_info.setPic_link(res.getString(4));
				goods_info.setSingle_price(res.getDouble(5));
				goods_info.setLocation(res.getString(7));
//				System.out.println("sdfffff:"+goods_id);
				shoppingcartItem=new ShopingcartItem();
				shoppingcartItem.setGoods_info(goods_info);
				shoppingcartItem.setQuantity(res.getInt(6));
				shoppingcartItemMap.put(goods_id, shoppingcartItem);
				shoppingcart.setItems(shoppingcartItemMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shoppingcart;
	}
	
	//�Ƚ������ԭ���Ĺ��ﳵ���
	public void isClear(int user_id){
		conn=DBConnection.getConnection();
		String sql=" delete from cart_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//�Ȳ鿴���ݿ��� ����� ��û�� ����Ʒ
	public boolean isAdd(int user_id,int goods_id){
		conn=DBConnection.getConnection();
		String sql="select user_id,goods_id from cart_info where user_id=? and goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, goods_id);
			res=ps.executeQuery();
			while(res.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateCart(String goods_name,String english_name,String pic_link,double single_price,int quantity,String location,int user_id,int goods_id){
		conn=DBConnection.getConnection();
		String sql="update cart_info set goods_name=? , english_name=? , pic_link=? , single_price=? , quantity=?  , location=? where user_id=? and goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, goods_name);
			ps.setString(2, english_name);
			ps.setString(3, pic_link);
			ps.setDouble(4, single_price);
			ps.setInt(5, quantity);
			ps.setString(6, location);
			ps.setInt(7, user_id);
			ps.setInt(8, goods_id);
			ps.executeUpdate();
			System.out.println("�������ݿ�ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCart(int user_id,int goods_id,String goods_name,String english_name,String pic_link,double single_price,int quantity,String location){
		conn=DBConnection.getConnection();
		String sql="insert into cart_info(user_id,goods_id,goods_name,english_name,pic_link,single_price,quantity,location) values(?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, goods_id);
			ps.setString(3, goods_name);
			ps.setString(4, english_name);
			ps.setString(5, pic_link);
			ps.setDouble(6, single_price);
			ps.setInt(7, quantity);
			ps.setString(8, location);
			ps.executeUpdate();
//			System.out.println("�������ݳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
