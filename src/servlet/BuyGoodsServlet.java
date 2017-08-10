package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.Goods_info;
import duorou.Rec_info;
import duorou.ShopingcartItem;
import duorou.ShoppingCart;

public class BuyGoodsServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//读取 用户 收件地址信息  购买商品信息 ==
	ArrayList<Rec_info> recList;
	ArrayList<Goods_info> goodsList;
	ArrayList<Goods_info> temp;
	Goods_info goods_info;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("doPost");
		HttpSession session=request.getSession();
		
		Object obj2 = session.getAttribute("user_id");
		 if(obj2 == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		 }
		
		ServletContext application=request.getServletContext();
		String method=request.getParameter("method");
//		System.out.println("method:"+method);
		int userid=0;
		Object userId=session.getAttribute("user_id");
		int goods_id=0;
		if(userId==null){
			return;
		}else{
			userid=(int) userId;
			application.setAttribute("rec", findRecInfo(userid));
		}
		if(method.equals("single")){
			Object obj=session.getAttribute("goods_id");
			if(obj==null){
				return;
			}else{
				Map<Integer, ShopingcartItem> map=new HashMap<Integer, ShopingcartItem>();
				ShopingcartItem item=new ShopingcartItem();
				ShoppingCart resCart=new ShoppingCart();
				int number=Integer.parseInt(request.getParameter("number"));
//				System.out.println("sdf:"+number);
				goods_id=(int) obj;//商品id号
				goodsList=findGoods(goods_id);
				int i=0;
				double totalPrice = 0;
				while(i<goodsList.size()){
					goods_info=goodsList.get(i);
					Double temprice=goods_info.getSingle_price();
					totalPrice=number*temprice;
					item.setGoods_info(goods_info);
					item.setQuantity(number);
					item.setPrice(totalPrice);
					map.put(goods_id, item);
					resCart.setItems(map);
					session.setAttribute("Buygoods", resCart);
					i++;
				}
				session.setAttribute("num", number);
				session.setAttribute("totalPrice", totalPrice);
			}
			request.getRequestDispatcher("submit_order.jsp").forward(request, response);
		}
		if(method.equals("some")){
			//获取总价
			double totalPrice=Double.parseDouble(request.getParameter("priceTotal"));
			session.setAttribute("totalPrice", totalPrice);
			int number=Integer.parseInt(request.getParameter("number"));
			session.setAttribute("num", number);
			//获取购物车项
			ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
			Map<Integer, ShopingcartItem> item;
			Map<Integer, ShopingcartItem> temGoods=new HashMap<Integer, ShopingcartItem>();
//			ShopingcartItem items;
			item=cart.getItems();
			Goods_info goods_info;
			int goodsId=0;
			Set<Integer> set=item.keySet();
			Iterator it=set.iterator();
			String pid[]=request.getParameterValues("pid");
			double price=0;
			int quantity=0;
			while(it.hasNext()){
				ShoppingCart tempCart = new ShoppingCart();
				ShopingcartItem items=new ShopingcartItem();
				goodsId=(Integer)it.next();//所有购物车中商品id号
				for(String id:pid){//用户勾选的checkbox对应的商品id号
					if(Integer.parseInt(id)==goodsId){
						//根据Integer.parseInt(id) 取商品
						items=item.get(Integer.parseInt(id));
						price=items.getPrice();
						quantity=items.getQuantity();
						goods_info=findGoodsAll(Integer.parseInt(id));
						items.setGoods_info(goods_info);
						temGoods.put(Integer.parseInt(id), items);
						tempCart.setItems(temGoods);
						session.setAttribute("Buygoods", tempCart);
					}
				}
			}
			Object obj=session.getAttribute("Buygoods");
			if(obj==null){return;}
			else{
				ShoppingCart buy=(ShoppingCart) obj;
				Map<Integer, ShopingcartItem> hehe=buy.getItems();
			}
			request.getRequestDispatcher("submit_order.jsp").forward(request, response);
		}
//		request.getRequestDispatcher("submit_order.jsp").forward(request, response);
	}
	
	public ArrayList<Rec_info> findRecInfo(int userid){
		recList=new ArrayList<Rec_info>();
		conn=getConnection();
		String sql="select province,city,address,rec_phone,rec_name,user_rec_id,code from rec_info where user_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, userid);
			res=ps.executeQuery();
			while(res.next()){
				Rec_info rec=new Rec_info();
				rec.setProvince(res.getString(1));
				rec.setCity(res.getString(2));
				rec.setAddress(res.getString(3));
				rec.setRec_phone(res.getString(4));
				rec.setRec_name(res.getString(5));
				rec.setUser_rec_id(res.getInt(6));
				rec.setCode(res.getInt(7));
				recList.add(rec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recList;
	}
	
	public ArrayList<Goods_info> findGoods(int goods_id){
		goodsList=new ArrayList<Goods_info>();
		conn=getConnection();
		String sql="select goods_name,pic_link,description,single_price,goods_amount,goods_state,location,goods_id,english_name from goods_info where goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goods_id);
			res=ps.executeQuery();
			while(res.next()){
				goods_info=new Goods_info();
				goods_info.setGoods_name(res.getString(1));
				goods_info.setPic_link(res.getString(2));
				goods_info.setDescription(res.getString(3));
				goods_info.setSingle_price(res.getDouble(4));
//				double single_price=goods_info.getSingle_price();//单价
				goods_info.setGoods_amount(res.getInt(5));
				goods_info.setGoods_state(res.getString(6));
				goods_info.setLocation(res.getString(7));
				goods_info.setGoods_id(res.getInt(8));
				goods_info.setEnglish_name(res.getString(9));
				goodsList.add(goods_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodsList;
	}
	public Goods_info findGoodsAll(int goods_id){
		Goods_info goods_info=new Goods_info();
		conn=getConnection();
		String sql="select goods_name,pic_link,description,single_price,goods_amount,goods_state,location,goods_id,english_name from goods_info where goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goods_id);
			res=ps.executeQuery();
			while(res.next()){
				goods_info.setGoods_name(res.getString(1));
				goods_info.setPic_link(res.getString(2));
				goods_info.setDescription(res.getString(3));
				goods_info.setSingle_price(res.getDouble(4));
//				double single_price=goods_info.getSingle_price();//单价
				goods_info.setGoods_amount(res.getInt(5));
				goods_info.setGoods_state(res.getString(6));
				goods_info.setLocation(res.getString(7));
				goods_info.setGoods_id(res.getInt(8));
				goods_info.setEnglish_name(res.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods_info;
	}
}


