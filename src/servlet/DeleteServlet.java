package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.ShopingcartItem;
import duorou.ShoppingCart;

public class DeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println("dfgh1111");
		HttpSession session=request.getSession();
		String id = request.getParameter("id"); 
		int goods_id=Integer.parseInt(id);
//		System.out.println("dfg:"+goods_id);
		//获得购物车
		ShoppingCart cart=(ShoppingCart)session.getAttribute("cart");
		//删除条目
		cart.getItems().remove(goods_id);
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
	}
}
