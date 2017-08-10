package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.DBConnection;
import method.DBOperator;
import duorou.Cate_info;
import duorou.Goods_info;

public class ShowGoodsServlet extends DBServlet1 {
	int DATA_PER_PAGE = 16;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cur = (String)request.getParameter("cur");
		
		ArrayList<Goods_info> list = new ArrayList<Goods_info>();
		list = getAllGoods(Integer.parseInt(cur));
		
		int totalPage = getTotalPage();
		
		request.setAttribute("goods", list);
		request.setAttribute("totalPage", totalPage);
		
		request.getRequestDispatcher("show_goods.jsp").forward(request,response);
		
	}

	public int getTotalPage(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			sql = "select count(*) from goods_info";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
			count = (int)Math.ceil((count + 1.0 - 1.0) / DATA_PER_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public ArrayList<Goods_info> getAllGoods(int cur){
		ArrayList<Goods_info> list = new ArrayList<Goods_info>();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "";
		
		try {
			sql = "select goods_id,cate_id,goods_name,pic_link,description,single_price,goods_amount,goods_state,location,english_name,province,city from goods_info where 1 limit ?,?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cur - 1) * DATA_PER_PAGE);
			pstmt.setInt(2, DATA_PER_PAGE);
			
			res = pstmt.executeQuery();
			
			while(res.next()){
				Goods_info goods = new Goods_info();
				goods.setGoods_id(res.getInt(1));
				goods.setCate_id(res.getInt(2));
				goods.setGoods_name(res.getString(3));
				goods.setPic_link(res.getString(4));
				goods.setDescription(res.getString(5));
				goods.setSingle_price(res.getDouble(6));
				goods.setGoods_amount(res.getInt(7));
				goods.setGoods_state(res.getString(8));
				goods.setLocation(res.getString(9));
				goods.setEnglish_name(res.getString(10));
				goods.setLocation_province(res.getString(11));
				goods.setLocation_city(res.getString(12));
				
				Cate_info cate = new Cate_info();
				DBOperator op = new DBOperator();
				cate = op.getCateByCateId(goods.getCate_id());
				goods.setCate_name(cate.getCate_name());
				
				list.add(goods);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
