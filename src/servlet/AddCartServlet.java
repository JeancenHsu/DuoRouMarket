package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import duorou.Goods_info;
import duorou.ShopingcartItem;
import duorou.ShoppingCart;

public class AddCartServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int number=0;
	//添加商品进入购物车
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		ShoppingCart cart;
		
		 Object obj2 = session.getAttribute("user_id");
		 if(obj2 == null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		 }
		
		cart =(ShoppingCart) session.getAttribute("cart");
		//获取用户添加数量
		int goods_id=0;
		String num=request.getParameter("number");
		if(num==null){
			return;
		}else{
			number=Integer.parseInt(num);
//			System.out.println("number:"+number);
		}
		request.setAttribute("goodsNum", number);
		Goods_info goods_info;
		double single_price=0;
		double price=0;
		//获取商品id号
		Object obj=session.getAttribute("goods_id");
		if(obj==null){
			return;
		}else{
			goods_id=(int) obj;
			//根据商品id去查找商品
			ListGoodsServlet.allGoods();
			goods_info=ListGoodsServlet.findAll(goods_id);
			session.setAttribute("goodsAddToCart", goods_info);
			single_price=goods_info.getSingle_price();
			price=single_price*number;
			request.setAttribute("pri", price);
		}
		//获取购物车
		Map<Integer, ShopingcartItem> cart2=cart.getItems();
		ShopingcartItem item=new ShopingcartItem(); 
		Set<Integer> set=cart2.keySet();
		Iterator it=set.iterator();
		String goods_name="";
		while(it.hasNext()){
			goods_id=(int) it.next();
			item=cart2.get(goods_id);
			goods_name=item.getGoods_info().getGoods_name();
//			System.out.println("rty:"+goods_name);
		}
	    if(goods_name==null||goods_name.equals("")){
	        //首次购物
//	    	System.out.println("cartnull111");
	        cart=new ShoppingCart();
//	        goodsInCart(goods_info, cart,session);
	        session.setAttribute("cart", cart);
	    }/*else{*/
	    	//将商品添加到购物车
		    goodsInCart(goods_info, cart,session);
//		    session.setAttribute("cart", cart);	
	    /*}*/
	   
	    request.getRequestDispatcher("addToCart.jsp").forward(request, response);
	}
	private void goodsInCart(Goods_info goods_info,ShoppingCart cart,HttpSession session){
		//判断是否买过
		Map<Integer, ShopingcartItem> items=cart.getItems();
		ShopingcartItem item=items.get(goods_info.getGoods_id());
		boolean flag=true;
		if(item==null){
			//此商品未加入过购物车过 创建新条目
//			System.out.println("fghj");
			item=new ShopingcartItem();
			item.setGoods_info(goods_info);
			item.setQuantity(number);
//			System.out.println("dfgh:"+item.getQuantity());
			items.put(goods_info.getGoods_id(), item);
//			session.setAttribute("cart", cart);
			flag=false;
		}
		//已加入过 数量加number
		if(flag){
			item.setQuantity(item.getQuantity()+number);
//			session.setAttribute("cart", cart);
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
