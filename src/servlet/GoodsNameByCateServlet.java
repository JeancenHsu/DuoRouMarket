package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import duorou.Goods_info;

public class GoodsNameByCateServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Goods_info> listName;
	Goods_info goods_info;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cate_id=Integer.parseInt(request.getParameter("cate_id"));
		request.setAttribute("name", findGoodsName(cate_id));
		
		request.getRequestDispatcher("listGoods").forward(request, response);
		return;
	}
	
	public ArrayList<Goods_info> findGoodsName(int cate_id){
		listName=new ArrayList<Goods_info>();
		conn=getConnection();
		String sql="select goods_name from goods_info where cate_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cate_id);
			res=ps.executeQuery();
			while(res.next()){
				goods_info=new Goods_info();
				goods_info.setGoods_name(res.getString(1));
				listName.add(goods_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listName;
	}
}
