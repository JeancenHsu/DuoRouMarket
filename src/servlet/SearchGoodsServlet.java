package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duorou.Goods_info;

public class SearchGoodsServlet extends DBServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String searchContent = null;
	ArrayList<Integer> goods_id;
	ArrayList<Goods_info> goods;
	ArrayList<Goods_info> test;
	ArrayList<Goods_info> temp;
	Goods_info goods_info;
	int id=0;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","text/html; charset=UTF-8");
		Object obj = request.getParameter("search");
		if(obj==null||obj.equals("")){
			request.getRequestDispatcher("search_failure.jsp").forward(request, response);
			return;
		}else{
			temp=new ArrayList<Goods_info>();
			searchContent = (String) obj;
			searchContent=new String(searchContent.getBytes("iso-8859-1"),"UTF-8");
//			System.out.println("sdfg:"+searchContent);
			goods_id=search(searchContent);
//			System.out.println("fghj:"+goods_id.size());
			if(goods_id.size()==0){
//				System.out.println("fghj");
				request.getRequestDispatcher("search_failure.jsp").forward(request, response);
				return;
			}
			int i=0;
//			System.out.println("size:"+goods_id.size());
			while(i<goods_id.size()){
				id=goods_id.get(i);//6
//				System.out.println("swdfgh:"+id);
				test=searchGoods(id);
				temp.addAll(test);
//				System.out.println("sdfghjsize:"+temp.size());
				request.setAttribute("goodsid", temp);
				i++;
			}
			request.getRequestDispatcher("search.jsp").forward(request, response);
		}
	}
	//ËÑË÷
	public ArrayList<Integer> search(String searchContent){
		goods_id=new ArrayList<Integer>();
		conn=getConnection();
		String sql="select goods_id from goods_info where goods_name like ? or cate_id in(select cate_id from cate_info where cate_name like ?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%" + searchContent + "%");
			ps.setString(2, "%" + searchContent + "%");
			res=ps.executeQuery();
			while(res.next()){
				goods_id.add(res.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods_id;
	}
	
	public ArrayList<Goods_info> searchGoods(int goods_id){
		goods=new ArrayList<Goods_info>();
		goods_info=new Goods_info();
		conn=getConnection();
		String sql="select goods_name,english_name,single_price,pic_link,goods_id from goods_info where goods_id=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, goods_id);
			res=ps.executeQuery();
			while(res.next()){
				goods_info.setGoods_name(res.getString(1));
				goods_info.setEnglish_name(res.getString(2));
				goods_info.setSingle_price(res.getDouble(3));
				goods_info.setPic_link(res.getString(4));
				goods_info.setGoods_id(res.getInt(5));
				goods.add(goods_info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}
}
