package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.Goods_info;
import duorou.Order_details;
import duorou.Order_info;
import duorou.Rec_info;

public class ShowOrderServlet extends DBServlet{
	ArrayList<Order_info> orderList;
	ArrayList<Order_details> detailsList;
	ArrayList<Order_info> temList;
	ArrayList<Order_details> temDeList;
	ArrayList<Goods_info> goodsList;
	ArrayList<Goods_info> temGoodsList;
	ArrayList<Rec_info> recList;
	ArrayList<Rec_info> testList;
	Order_info order=new Order_info();
	Order_details details=new Order_details();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		String order_id=request.getParameter("order_id");
//		System.out.println("df:"+order_id);
		if(updateStatus(order_id)){
			out.write("success");
		}
//		Object obj=session.getAttribute("user_id");
//		int user_id=0;
//		String rec_name="";
//		if(obj==null){
//			return;
//		}else{
//			user_id=(int) obj;
//		}
//		int i=0;
//		int user_rec_id=0;
//		orderList=findOrder(user_id);
//		session.setAttribute("orderInfo", orderList);
//		testList=new ArrayList<Rec_info>();
//		//根据收件人id取出收件人名字
//		System.out.println("sdfg:"+orderList.size()); //3
//		while(i<orderList.size()){
//			System.out.println("i:"+i);
//			order=orderList.get(i);
//			user_rec_id=order.getUser_rec_id();
//			System.out.println("ghh:"+user_rec_id);//1
//			recList=findRecName(user_rec_id,user_id);
//			testList.addAll(recList);
//			System.out.println("fvgb:"+recList.size());
//			session.setAttribute("recName", testList);
//			i++;
//		}
//		int i=0;
//		int user_rec_id=0;
//		String order_id="";
//		int goods_id=0;
//		int j=0;
//		String test;
////		temList=new ArrayList<Order_info>();
//		temDeList=new ArrayList<Order_details>();
//		temGoodsList=new ArrayList<Goods_info>();
//		testList=new ArrayList<Rec_info>();
//		orderList=findOrder(user_id);
//		System.out.println("orderList:"+orderList.size());
//		//2==订单数目为2
//		while(i<orderList.size()){
//			order=orderList.get(i);
//			user_rec_id=order.getUser_rec_id();
//			recList=findRecName(user_rec_id);
//			order_id=order.getOrder_id();//2
//			System.out.println("order_id:"+order_id);
//			detailsList=findOrderDetials(order_id);//一条订单对应的购买商品
//			temDeList.addAll(detailsList);
//			//
//			if(j<temDeList.size()){
//				details=temDeList.get(j);
//				System.out.println("details:"+temDeList.size());
//				goods_id=details.getGoods_id();
//				System.out.println("good_id:"+goods_id);
//				goodsList=findGoodsName(goods_id);
//				temGoodsList.addAll(goodsList);
//				j++;
//			}
//			i++;
//		}
//		session.setAttribute("recName", recList);
//		session.setAttribute("details", temDeList);
//		session.setAttribute("orderInfo", orderList);
		request.getRequestDispatcher("showOrder.jsp").forward(request, response);
	}
	
	//更新订单状态
	public boolean updateStatus(String order_id){
		conn=getConnection();
		String sql="update order_info set status='已送达' where order_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, order_id);
			ps.executeUpdate();
//			System.out.println("更新数据库成功");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//取出所有订单
	public ArrayList<Order_info> findOrder(int user_id){
		orderList=new ArrayList<Order_info>();
		conn=getConnection();
		String sql="select order_id,status,whole_number,whole_price,user_rec_id from order_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Order_info order_info=new Order_info();
				order_info.setOrder_id(res.getString(1));
				order_info.setStatus(res.getString(2));
				order_info.setWhole_number(res.getInt(3));
				order_info.setWhole_price(res.getDouble(4));
				order_info.setUser_rec_id(res.getInt(5));
//				System.out.println("sdx:"+order_info.getUser_rec_id());
				orderList.add(order_info);
//				System.out.println("orderList2:"+orderList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	//根据收件人编号取出收件人
	public ArrayList<Rec_info> findRecName(int user_rec_id,int user_id){
		conn=getConnection();
		recList=new ArrayList<Rec_info>();
//		System.out.println("user_rec_idsss:"+user_rec_id);
		String sql="select rec_name from rec_info where user_rec_id=? and user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, user_rec_id);
			ps.setInt(2, user_id);
			res=ps.executeQuery();
			while(res.next()){
				Rec_info rec_info=new Rec_info();
				rec_info.setRec_name(res.getString(1));
				recList.add(rec_info);
//				System.out.println("recListqs:"+recList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recList;
	}
	
	//根据订单编号取出订单详情
	public ArrayList<Order_details> findOrderDetials(String order_id){
		detailsList=new ArrayList<Order_details>();
		conn=getConnection();
		String sql="select goods_id,quantity,price from order_details where order_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, order_id);
			res=ps.executeQuery();
			while(res.next()){
				Order_details order_details=new Order_details();
				order_details.setGoods_id(res.getInt(1));
				order_details.setQuantity(res.getInt(2));
				order_details.setPrice(res.getDouble(3));
				detailsList.add(order_details);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detailsList;
	}
	
	//根据商品编号取出商品名字
	public ArrayList<Goods_info> findGoodsName(int goods_id){
		conn=getConnection();
		goodsList=new ArrayList<Goods_info>();
		String sql="select goods_name from goods_info where goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goods_id);
			res=ps.executeQuery();
			while(res.next()){
				Goods_info goods_info=new Goods_info();
				goods_info.setGoods_name(res.getString(1));
				goodsList.add(goods_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodsList;
	}
}
