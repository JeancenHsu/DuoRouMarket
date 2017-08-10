package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.Goods_info;
import duorou.ShopingcartItem;
import duorou.ShoppingCart;

public class SubmitOrderServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("submit");
		HttpSession session=request.getSession();
		Object obj=session.getAttribute("Buygoods");
		if(obj==null){
			return;
		}
//		ShoppingCart buyGoods=(ShoppingCart) obj;
		
		//�ȶ����� --����Ψһ�Ķ������ ==��ȡ�û���� ==��ȡ��Ʒ��� ==��ȡ�ռ��˱��
		
		//����Ψһ�Ķ������
		Date d = new Date();
		long time = d.getTime();
		String t=String.valueOf(time);
		String order_id = (String) (session.getId().subSequence(10, 20)+t);
//		System.out.println(id);
		//��ȡ�û�id��
		Object objUser=session.getAttribute("user_id");
		int user_id=0;
		if(objUser==null){
			return;
		}else{
			user_id=(int) objUser;
		}
		//��ȡ�ռ��˱��
		int user_rec_id=Integer.parseInt(request.getParameter("user_rec_id"));
		//��ȡ�����ܼ�
		double totalPrice;
		Object objTotalPrice=session.getAttribute("totalPrice");
		if(objTotalPrice==null){
			return;
		}else{
			totalPrice=(double) objTotalPrice;
		}
		//��ȡ����������
		int number;
		Object objNumber=session.getAttribute("num");
		if(objNumber==null){
			return;
		}else{
			number=(int) objNumber;
		}
	    Timestamp stTime=currentTime();
		String status="������";
		
		if(isOrder(order_id, user_id, user_rec_id, stTime, status, number, totalPrice)){
//			System.out.println("ok");
			Object buyGoodsObject=session.getAttribute("Buygoods");
			ShoppingCart buygoods;
			Map<Integer, ShopingcartItem> item;
			ShopingcartItem items;
			Goods_info goods_info=new Goods_info();
			int goodsId=0;
			int quantity=0;
			double price=0;
			int goods_amount=0;
			if(buyGoodsObject==null){
				return;
			}else{
				buygoods=(ShoppingCart) buyGoodsObject;
				item=buygoods.getItems();
				Set<Integer> set=item.keySet();
				Iterator it=set.iterator();
				while(it.hasNext()){
					goodsId=(Integer)it.next();
					items=item.get(goodsId);
					quantity=items.getQuantity();
					price=items.getPrice();
					goods_info=items.getGoods_info();
					goodsId=goods_info.getGoods_id();
					goods_amount=goods_info.getGoods_amount();
					orderDetails(order_id, user_id, goodsId, quantity, price);
					updateQuantity(goodsId, quantity, goods_amount);
				}
			}
		}
		request.getRequestDispatcher("submit_success.jsp").forward(request, response);
	}
	
	public Timestamp currentTime(){
		Timestamp startTime = null;
		conn=getConnection();
		String sql="select current_timestamp()";
		try {
			ps=conn.prepareStatement(sql);
			res=ps.executeQuery();
			while(res.next()){
				return res.getTimestamp(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return startTime;
	}
	//������
	public boolean isOrder(String order_id,int user_id,int user_rec_id,Timestamp stTime,String status,int number,double totalPrice){
		conn=getConnection();
		String sql="insert into order_info(order_id,user_id,user_rec_id,start_time,status,whole_number,whole_price) values(?,?,?,?,?,?,?)";
		try {
//			conn.setAutoCommit(false);//�����ݿ��Զ��ύ���ܹر�
			ps=conn.prepareStatement(sql);
			ps.setString(1, order_id);
			ps.setInt(2, user_id);
			ps.setInt(3, user_rec_id);
			ps.setTimestamp(4, stTime);
			ps.setString(5, status);
			ps.setInt(6, number);
			ps.setDouble(7, totalPrice);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//������ϸ�� ==һ����������Ӧ�� ������Ʒ�� ��Ʒ��� �����Ʒ���� �ܼ�
	public void orderDetails(String order_id,int user_id,int goods_id,int quantity,double price){
		conn=getConnection();
		String sql="insert into order_details(order_id,user_id,goods_id,quantity,price) values(?,?,?,?,?)";
		try {
//			conn.setAutoCommit(false);//�����ݿ��Զ��ύ���ܹر�
			ps=conn.prepareStatement(sql);
			ps.setString(1, order_id);
			ps.setInt(2, user_id);
			ps.setInt(3, goods_id);
			ps.setInt(4, quantity);
			ps.setDouble(5, price);
			ps.executeUpdate();
			//System.out.println("heheaa");
//			conn.commit();
//			System.out.println("�ύ�ɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�����ύ��� ����Ʒ���ж�Ӧ��Ʒ��������
	public void updateQuantity(int goodsId,int quantity,int goods_amount){
		conn=getConnection();
		int num=goods_amount-quantity;
		System.out.println("goods_amount:"+goods_amount+"quantity:"+quantity+"num:"+num);
		String sql="update goods_info set goods_amount=? where goods_id=?";
		try {
//			conn.setAutoCommit(false);//�����ݿ��Զ��ύ���ܹر�
			ps=conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setInt(2, goodsId);
			ps.executeUpdate();
//			conn.commit();
//			System.out.println("�ύ�ɹ�");
		} catch (SQLException e) {
//			try {
////				conn.rollback();
//				System.out.println("�ύʧ��");
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		}
	}
}
