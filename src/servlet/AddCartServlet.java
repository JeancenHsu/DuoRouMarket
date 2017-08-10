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
	//�����Ʒ���빺�ﳵ
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
		//��ȡ�û��������
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
		//��ȡ��Ʒid��
		Object obj=session.getAttribute("goods_id");
		if(obj==null){
			return;
		}else{
			goods_id=(int) obj;
			//������Ʒidȥ������Ʒ
			ListGoodsServlet.allGoods();
			goods_info=ListGoodsServlet.findAll(goods_id);
			session.setAttribute("goodsAddToCart", goods_info);
			single_price=goods_info.getSingle_price();
			price=single_price*number;
			request.setAttribute("pri", price);
		}
		//��ȡ���ﳵ
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
	        //�״ι���
//	    	System.out.println("cartnull111");
	        cart=new ShoppingCart();
//	        goodsInCart(goods_info, cart,session);
	        session.setAttribute("cart", cart);
	    }/*else{*/
	    	//����Ʒ��ӵ����ﳵ
		    goodsInCart(goods_info, cart,session);
//		    session.setAttribute("cart", cart);	
	    /*}*/
	   
	    request.getRequestDispatcher("addToCart.jsp").forward(request, response);
	}
	private void goodsInCart(Goods_info goods_info,ShoppingCart cart,HttpSession session){
		//�ж��Ƿ����
		Map<Integer, ShopingcartItem> items=cart.getItems();
		ShopingcartItem item=items.get(goods_info.getGoods_id());
		boolean flag=true;
		if(item==null){
			//����Ʒδ��������ﳵ�� ��������Ŀ
//			System.out.println("fghj");
			item=new ShopingcartItem();
			item.setGoods_info(goods_info);
			item.setQuantity(number);
//			System.out.println("dfgh:"+item.getQuantity());
			items.put(goods_info.getGoods_id(), item);
//			session.setAttribute("cart", cart);
			flag=false;
		}
		//�Ѽ���� ������number
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
