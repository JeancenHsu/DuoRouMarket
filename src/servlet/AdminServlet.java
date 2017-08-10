package servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import method.DBConnection;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AdminServlet extends DBServlet1 {
	HttpServletRequest request;
	HttpServletResponse response;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		request=req;
		response=resp;
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("method").equals("doCates")){
			doCates();
		}
		if(request.getParameter("method").equals("doDelete")){
			doDelete();
		}
		if(request.getParameter("method").equals("doGoods")){
			doGoods();
		}
		if(request.getParameter("method").equals("doUsers")){
			doUsers();
		}
		
	}
	
	public void doCates() throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cate_id = 0;
		String cate_name = null;
		String cate_description = null;
		String link = null;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			  List items = upload.parseRequest(request);
			  Iterator itr = items.iterator();
			  while (itr.hasNext()) {
				  FileItem item = (FileItem) itr.next();
				  if (item.isFormField()) { //如果不是文件
					 // System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
					  if("cate_id".equals(item.getFieldName())){
						  cate_id = Integer.parseInt(item.getString("UTF-8"));
					  }
					  if("cate_name".equals(item.getFieldName())){
						  cate_name = item.getString("UTF-8");
					  }
					  if("cate_description".equals(item.getFieldName())){
						  cate_description = item.getString("UTF-8");
					  }
				  } else { //如果是文件
					  if (item.getName() != null && !item.getName().equals("")) {
						  //System.out.println("上传文件的大小:" + item.getSize());
						  //System.out.println("上传文件的类型:" + item.getContentType());
						  // item.getName()返回上传文件在客户端的完整路径名称
						  //System.out.println("上传文件的名称:" + item.getName());
	
						  File tempFile = new File(item.getName());
						  //上传文件的保存路径
						  File file = new File(sc.getRealPath("/") + savePath + "/cate", tempFile.getName());
						  item.write(file);
						  
						 link = savePath + "/cate/" +tempFile.getName();
						  //request.setAttribute("upload.message", "上传文件成功！");
					  }else{
						  request.setAttribute("errorMsg", "没有选择上传文件！");
						  request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
						  //request.setAttribute("upload.message", "没有选择上传文件！");
					  }
				  }
			  }
		  }catch(FileUploadException e){
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		   request.setAttribute("errorMsg", "上传文件失败！");
		   request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		  // request.setAttribute("upload.message", "上传文件失败！");
		  }
		
		String sql = null;
		
		 if(cate_name != null && cate_description !=null && !cate_name.equals("") && !cate_description.equals("")){
			  if(request.getParameter("operate").equals("add")){
				  sql = "insert into cate_info(cate_name,cate_description,cate_image) values(?,?,?)";
			  }
			  if(request.getParameter("operate").equals("update")){
				  sql = "update cate_info set cate_name=?,cate_description=? where cate_id="+cate_id;
			  }
			  PreparedStatement pst;
			  try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, cate_name);
				pst.setString(2, cate_description);
				if(request.getParameter("operate").equals("add")){
					pst.setString(3, link);
				}
				
				int i = pst.executeUpdate();
				
				if(i>0){
					request.getRequestDispatcher("admin/success.html").forward(request, response);
				}else{
					request.setAttribute("errorMsg", "数据库写入失败！");
					request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 //表单存在空
			 request.setAttribute("errorMsg", "表单不得有空值！");
			request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		 }  
	}
	
	public void doGoods() throws ServletException, IOException{
		
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int goods_id=0;
		String goods_name = null;
		String english_name = null;
		int cate_id = 0;
		double single_price = 0;
		int goods_amount = 0;
		String pic_link = null;
		String location_province = null;
		String location_city = null;
		String location;
		String goods_state = null;
		String description = null;
		
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			  List items = upload.parseRequest(request);
			  Iterator itr = items.iterator();
			  while (itr.hasNext()) {
				  FileItem item = (FileItem) itr.next();
				  if (item.isFormField()) { //如果不是文件
					 // System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
					  if("goods_id".equals(item.getFieldName())){
						  goods_id = Integer.parseInt(item.getString("UTF-8"));;
					  }
					  if("goods_name".equals(item.getFieldName())){
						  goods_name = item.getString("UTF-8");
					  }
					  if("english_name".equals(item.getFieldName())){
						  english_name = item.getString("UTF-8");
					  }
					  if("goods_cate".equals(item.getFieldName())){
						  cate_id = Integer.parseInt(item.getString("UTF-8"));
					  }
					  if("single_price".equals(item.getFieldName())){
						  single_price = Double.parseDouble(item.getString("UTF-8"));
					  }
					  if("goods_amount".equals(item.getFieldName())){
						  goods_amount = Integer.parseInt(item.getString("UTF-8"));
					  }

					  if("location_province".equals(item.getFieldName())){
						  location_province = item.getString("UTF-8");
					  }
					  if("location_city".equals(item.getFieldName())){
						  location_city = item.getString("UTF-8");
					  }
					  if("goods_state".equals(item.getFieldName())){
						  goods_state = item.getString("UTF-8");
					  }
					  if("description".equals(item.getFieldName())){
						  description = item.getString("UTF-8");
					  }
					  
					  
				  } else { //如果是文件
					  if (item.getName() != null && !item.getName().equals("")) {
						  //System.out.println("上传文件的大小:" + item.getSize());
						  //System.out.println("上传文件的类型:" + item.getContentType());
						  // item.getName()返回上传文件在客户端的完整路径名称
						  //System.out.println("上传文件的名称:" + item.getName());
	
						  File tempFile = new File(item.getName());
						  //上传文件的保存路径
						  File file = new File(sc.getRealPath("/") + savePath + "/goods", tempFile.getName());
						  item.write(file);
						  
						 pic_link = savePath + "/goods/" +tempFile.getName();
						  //request.setAttribute("upload.message", "上传文件成功！");
						 
					  }else{
						  //request.setAttribute("upload.message", "没有选择上传文件！");
						  request.setAttribute("errorMsg", "没有选择上传文件！");
						  request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
					  }
				  }
			  }
		  }catch(FileUploadException e){
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		  // request.setAttribute("upload.message", "上传文件失败！");
		   request.setAttribute("errorMsg", "上传文件失败！");
		   request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		  }
		
		location = location_province  + location_city ;
		String sql = null;
		 if(goods_name != null && english_name !=null && cate_id != 0 && single_price != 0 && goods_amount != 0 && location_province != null && location_city != null && goods_state!=null && description!=null && !goods_name.equals("") && !english_name.equals("") && !location_province.equals("") && !location_city.equals("") && !goods_state.equals("") && !description.equals("")){
			 if(request.getParameter("operate").equals("add")){ 
				 sql = "insert into goods_info(cate_id,goods_name,description,single_price,goods_amount,goods_state,location,province,city,english_name,pic_link) values(?,?,?,?,?,?,?,?,?,?,?)";
			 }
			 if(request.getParameter("operate").equals("update")){
				 sql = "update goods_info set cate_id=?,goods_name=?,description=?,single_price=?,goods_amount=?,goods_state=?,location=?,province=?,city=?,english_name=? where goods_id="+goods_id;
			 }
			  PreparedStatement pst;
			  try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, cate_id);
				pst.setString(2, goods_name);
				pst.setString(3, description);
				pst.setDouble(4, single_price);
				pst.setInt(5, goods_amount);
				pst.setString(6, goods_state);
				pst.setString(7, location);
				pst.setString(8, location_province);
				pst.setString(9, location_province);
				pst.setString(10, english_name);
				if(request.getParameter("operate").equals("add")){
					pst.setString(11, pic_link);
				}
				
				int i =pst.executeUpdate();
				if(i>0){
					request.getRequestDispatcher("admin/success.html").forward(request, response);
				}else{
					request.setAttribute("errorMsg", "数据库写入失败！");
					request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 //表单存在空
			 request.setAttribute("errorMsg", "表单不得有空值！");
			 request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		 } 
	}

	public void doDelete() throws ServletException, IOException{
		System.out.println("ok");
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ss[] = request.getParameterValues("cb");
		int ids[]= new int[ss.length];
		String order_ids[] = new String[ss.length];
		
		conn=DBConnection.getConnection();
		PreparedStatement pst;
		String sql="";
		
		if(request.getParameter("table").equals("cate_info")){
			sql = "delete from cate_info where cate_id=?";
		}
		if(request.getParameter("table").equals("goods_info")){
			sql = "delete from goods_info where goods_id=?";
		}
		if(request.getParameter("table").equals("user_info")){
			sql = "delete from user_info where user_id=?";
		}
		if(request.getParameter("table").equals("order_info")){
			sql = "delete from order_info where order_id=?";
		}
		
		int sum=0;
		
		for(int i=0;i<ss.length;i++){
			if(!request.getParameter("table").equals("order_info")){
				ids[i]=Integer.parseInt(ss[i]);
			}else{
				order_ids[i]=ss[i];
			}
			try {
				pst=conn.prepareStatement(sql);
								
				if(!request.getParameter("table").equals("order_info")){
					pst.setInt(1, ids[i]);
				}else{
					pst.setString(1, order_ids[i]);
				}
				
				sum+=pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(sum>=ss.length){
			request.getRequestDispatcher("admin/success.html").forward(request, response);
		}else{
			request.setAttribute("errorMsg", "数据库删除指令执行失败！");
			request.getRequestDispatcher("admin/fail.jsp").forward(request, response);
		}
	}
	
	public void doUsers() throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int user_id = 0;
		String user_name = null;
		String password = null;
		String user_phone = null;
		
		if(request.getParameter("user_id")!=null){
			user_id=Integer.parseInt(request.getParameter("user_id"));
		}
		if(request.getParameter("user_name")!=null){
			user_name=request.getParameter("user_name");
		}
		if(request.getParameter("password")!=null){
			password=request.getParameter("password");
		}
		if(request.getParameter("user_phone")!=null){
			user_phone=request.getParameter("user_phone");
		}
		System.out.println(user_id);
		System.out.println(user_name);
		System.out.println(password);
		System.out.println(user_phone);
		String sql = null;
		
		 if(user_id != 0 && user_name !=null && password != null && user_phone != null){
//			  if(request.getParameter("operate").equals("add")){
//				  sql = "insert into cate_info(cate_name,cate_description,cate_image) values(?,?,?)";
//			  }
			  if(request.getParameter("operate").equals("update")){
				  sql = "update user_info set user_name=?,password=?,user_phone=? where user_id="+user_id;
			  }
			  PreparedStatement pst;
			  try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, user_name);
				pst.setString(2, password);
				pst.setString(3, user_phone);

				int i = pst.executeUpdate();
				
				if(i>0){
					request.getRequestDispatcher("admin/success.html").forward(request, response);
				}else{
					request.getRequestDispatcher("admin/fail.html").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 //表单存在空
			 request.getRequestDispatcher("admin/fail.html").forward(request, response);
		 }  
	}
	
}
