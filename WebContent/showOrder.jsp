<%@page import="duorou.Order_details"%>
<%@page import="duorou.Rec_info"%>
<%@page import="duorou.Order_info"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlet.ShowOrderServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>肉萌萌——个人订单</title>
<link rel="stylesheet" href="css/showOrder.css" type="text/css">
<style type="text/css">
    #order{
        color:#77f93e;
        font-weight: bold;
        border-bottom: 4px solid #77f93e;
</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	var xmlHttpRequest;
	var order_id;
	function initXMLHttpRequest() {
		if (xmlHttpRequest) {
			return;
		}
		if (window.ActiveXObject) {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttpRequest = new XMLHttpRequest();
		}
	}
	function fSend(method, url, data, callback) {
		if (method == "GET") {
			//设置提交的页面
			xmlHttpRequest.open(method, url + "?" + data, true);
			//设置回调函数(函数不加括号!)
			xmlHttpRequest.onreadystatechange = callback;
			//发送出去
			xmlHttpRequest.send();	
		} else if (method == "POST") {
			//alert("post");
			xmlHttpRequest.open(method, url, true);
			xmlHttpRequest.onreadystatechange = callback;
			//POST方式多加一句话
			xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttpRequest.send(data);	
		}
	}
	
	function fCallback() {
		//alert(xmlHttpRequest.readyState)
		if (xmlHttpRequest.readyState == 4) {//4是后台给我们的反馈
			if (xmlHttpRequest.status == 200) {//200代表后台正常处理成功
				
			}
		}
	}
	
	$(function(){
		$('.btn').click(function(){
			order_id=this.name;
			//alert(order_id)
			initXMLHttpRequest();
			fSend("GET", "showOrder", "order_id="+order_id, fCallback);
		});
	})
</script>

</head>
<body>
    <!--header-->
    <div class="head">
		<div class="head-Con">
			<img src="images/logo.png" alt="logo">
			<div class="nav">
				<a href="index.jsp" class="a1" id="shouye">首页</a>
                <a href="shangcheng.jsp" class="a2" id="binfen">鲜“肉”商城</a>
                <a href="shoppingCart.jsp" class="a2" id="yangzhi">养“肉”小车</a>
               <c:choose>
                	<c:when test="${sessionScope.user_name == null}">
                		<a href="login.jsp" class="a1" id="login">登录</a>
                		<a href="signin.jsp" class="a1" id="signin">注册</a>
                	</c:when>
                	<c:otherwise>
                		<c:choose>
                			<c:when test="${sessionScope.isadmin }">
		                		<a href="admin/add_cate.jsp" class="a1">后台</a>
		                		<a href="exit" class="a1">退出</a>
		                	</c:when>
	                		<c:otherwise>
		                		<a href="showOrder" class="a1">${sessionScope.user_name }</a>
		                		<a href="exit" class="a1">退出</a>
	                		</c:otherwise>
                		</c:choose>
                	</c:otherwise>
                </c:choose>
			</div>
		</div>
		<div id="search_box">
			<form id="search_form" method="get" action="searchGoods">
				<input type="text" id="s" name="search" placeholder="Search" class="swap_value" />
				<input type="submit" id="go" alt="Search" title="Search" value=""/>
			</form>
		</div>
	</div>
    
    <div class="cart_title">
        <p>我的订单</p>
    </div>
    <div class="catbox">
  <table id="cartTable">
    <thead>
      <tr>
        <th>订单编号</th>
        <th>收件人</th>
        <th>总数量</th>
        <th>总价格</th>
        <th>订单状态</th>
        <th>确认收货</th>
      </tr>
    </thead>
    <tbody>
     <%
     	Object obj=session.getAttribute("user_id");
		int user_id=0;
		String rec_name="";
		if(obj==null){
			return;
		}else{
			user_id=(Integer)obj;
		}
     	ShowOrderServlet show=new ShowOrderServlet();
     	int i=0;
     	int j=0;
     	String name="";
     	int user_rec_id=0;
     	String order_id="";
     	int goods_id=0;
     	ArrayList<Order_info> orderList=show.findOrder(user_id);
     	ArrayList<Rec_info> recList;
     	ArrayList<Order_details> detailsList;
     	Order_info order=new Order_info();
     	Order_details details=new Order_details();
     	Rec_info rec=new Rec_info();
     	while(i<orderList.size()){
     		order=orderList.get(i);
     		user_rec_id=order.getUser_rec_id();
     		order_id=order.getOrder_id();
     		recList=show.findRecName(user_rec_id, user_id);
     		if(j<recList.size()){
     			rec=recList.get(j);
     			detailsList=show.findOrderDetials(order_id);
     %>
      <tr>
        <td class=""><%=order.getOrder_id() %></td>
        <td>
	      	<%=rec.getRec_name() %>
      	</td>
        <td class=""><%=order.getWhole_number() %></td>
        <td class=""><%=order.getWhole_price() %></td>
        <td class=""><%=order.getStatus() %></td>
        
        <%
        	String status="";
        	status=order.getStatus();
        	if(status.equals("配送中")){
        %>
        <td><input type="button" name="<%=order.getOrder_id() %>" class="btn green" value="确认收货"></td>
        
        <%}
        	else{
        	%>
        <td><input type="button" name="<%=order.getOrder_id() %>" class="alreadybtn gray" value="已确认收货"></td>
      </tr>
      <%} %>
      <%
      
      } 
     	i++;}
      %>
      <%
      	
      %>
    </tbody>
  </table>
</div>

	<div class="moto">
        <div class="moto-Con">
            <a>从 现 在 起 ，你 是 它 们 的 主 人。</a>
        </div>
    </div>
    <div class="bottom" width=100%>
        <a>Copyright  ©爱多肉 All Rights Reserved.  Designed by 14卓工小仙女.</a>
        <br>
        <a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">联系我们</a>
    </div>
</body>
</html>