package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.ShopingcartItem;
import duorou.ShoppingCart;

public class UpdateServlet extends DBServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));//商品id号
		int cartnum=Integer.parseInt(request.getParameter("cartnum"));//商品数量
//		System.out.println(cartnum);
		String function=request.getParameter("function");
		int number=0;
		int whole=0;
		int number2=0;
//		System.out.println("dfgh");
//		System.out.println(function);
		if(function.equals("min")){
			number=cartnum-1;
			number2=number;
			out.write(""+number2+"");
		}
		if(function.equals("add")){
			number=cartnum+1;
//			System.out.println("sim:"+number);
			whole=goodAmount(id);
//			System.out.println("whole:"+whole);
			if(number>whole){
//				System.out.println("tgh");
				number=whole;
			}
			out.write(number);
		}
		ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
		
		Map<Integer, ShopingcartItem> items=cart.getItems();
		
		ShopingcartItem item=items.get(id);
		item.setQuantity(number);
		//System.out.println(123);
//		cart.getItems().get(id).setQuantity(number);
		
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
		
	}
	
	//根据id号找出商品库存
	public int goodAmount(int id){
		conn=getConnection();
		String sql="select goods_amount from goods_info where goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			res=ps.executeQuery();
			while(res.next()){
				return res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
