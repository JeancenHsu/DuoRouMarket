package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.Goods_info;

public class GoodsInfoServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int goods_amount = 0;//商品数量
	Map<Integer, Goods_info> goods;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		
		HttpSession session=request.getSession();
//		PrintWriter out=response.getWriter();
		String goods=request.getParameter("goods_id");
		//System.out.println(goods);
		if(goods==null){
			return;
		}else{
			int goods_id=Integer.parseInt(goods);
			//System.out.println(goods_id);
			Goods_info goods_info2=new Goods_info();
			goods_info2=findGoodsAll(goods_id);
			session.setAttribute("goods_info", goods_info2);
			//out.write(goods_amount);
			request.getRequestDispatcher("show_details.jsp").forward(request, response);
		}
	}	
	
	//根据goods_id取出商品的详细信息 包括商品图片、名称、描述、价格
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
				goods_info.setGoods_amount(res.getInt(5));
				goods_info.setGoods_state(res.getString(6));
				goods_info.setLocation(res.getString(7));
				goods_info.setGoods_id(res.getInt(8));
				goods_info.setEnglish_name(res.getString(9));
				goods_amount=goods_info.getGoods_amount();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods_info;
	}
}
