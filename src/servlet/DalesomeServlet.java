package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.ShoppingCart;

public class DalesomeServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String pids[]=request.getParameterValues("pid");
		
		ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
		for(String pid:pids){
			cart.getItems().remove(Integer.parseInt(pid));
			session.setAttribute("cart", cart);
		}
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
	}
}
