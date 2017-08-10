package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import duorou.Cate_info;
import duorou.Goods_info;

public class GoodsCateServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Goods_info goods_info;
	Cate_info cate_info;
	ArrayList<Goods_info> listcate;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session=request.getSession();
//		System.out.println("zzzzzzzx:"+request.getParameter("cate_id"));
		int cate_id=Integer.parseInt(request.getParameter("cate_id"));
		request.setAttribute("cate", findAll(cate_id));
		request.setAttribute("cateName", findCateName(cate_id));
		request.getRequestDispatcher("show_more.jsp").forward(request, response);
	}
	
	public Cate_info findCateName(int cate_id){
		cate_info=new Cate_info();
		conn=getConnection();
		String sql="select cate_name from cate_info where cate_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cate_id);
			res=ps.executeQuery();
			while(res.next()){
				cate_info.setCate_name(res.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cate_info;
	}
	//根据商品种类取出--这个种类的商品
	public ArrayList<Goods_info> findAll(int cate_id){
		listcate=new ArrayList<Goods_info>();
		conn=getConnection();
		String sql="select goods_id,goods_name,pic_link,single_price,english_name from goods_info where cate_id=? and goods_state='热卖中'";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cate_id);
			res=ps.executeQuery();
			while(res.next()){
				goods_info=new Goods_info();
				goods_info.setGoods_id(res.getInt(1));
				goods_info.setGoods_name(res.getString(2));
				goods_info.setPic_link(res.getString(3));
				goods_info.setSingle_price(res.getDouble(4));
				goods_info.setEnglish_name(res.getString(5));
				listcate.add(goods_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listcate;
	}
}
