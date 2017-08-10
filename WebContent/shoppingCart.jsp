<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>肉萌萌——养"肉"小车</title>
<link rel="stylesheet" href="css/shoppingCart.css" type="text/css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/cartNum.js"></script>
<link rel="stylesheet" href="css/demo.css"/>
<script type="text/javascript" src="js/demo.js"></script>
<style type="text/css">
    #yangzhi{
        color:#77f93e;
        font-weight: bold;
        border-bottom: 4px solid #77f93e;
</style>
<script type="text/javascript">
	var add;
	var reduce;
	//点击 + 或者 - 商品数量 价格随之变化
	function changeMin(id){
		//alert(id)
		var cartnum=document.getElementById("text_box").value;//数量
		//alert(cartnum)
		//alert(quantity)
		if(cartnum<=1){
			alert("商品数量至少为1")
			cartnum=2;
		}
		window.location="updateCart?function=min&id="+id+"&cartnum="+cartnum;
	}
	
	//批量删除之前 先判断是否选中了多选框 选中 删除 未选中提示用户选择
	function deleteSome(){
		//alert("deleteSome")
		var pid = document.getElementsByName("pid");
		var form = document.getElementById("form");
		var num=0;
		for(var i=0;i<pid.length;i++){
			if(pid[i].checked){
				num++;
			}
		}
		if(num>0){
			if(confirm('您确定删除所选商品吗？')){
				form.action="dalesome";
				form.submit();
				return true;
			}else{
				return false;
			}
		}else{
			alert("请选择您要删除的商品！");
		}
	}
	
	function deleteOne(id){
		var form = document.getElementById("form");
		if(confirm('您确定删除此商品吗？')){
			form.action="deleteCart?id="+id;
			form.submit();
			return true;
		}else{
			return false;
		}
	}
	
	function buyGoods(){
		var form=document.getElementById("form");
		//查看已选件数是否为0==如果为0 不能提交订单
		var upriceTotal=document.getElementById("upriceTotal");
		var m=upriceTotal.getElementsByTagName("span");
		var priceTotal=m[0].innerText;
		
		var d = document.getElementById("selected");
		var c=d.getElementsByTagName("span");
		var number=c[0].innerText;
		
		//alert("ddd")
		if(number==0){
			alert("您还未选择商品！")
		}else{
			form.action="buyGoods?method=some&priceTotal="+priceTotal+"&number="+number;
			form.submit();
		}
	}
</script>
<script type="text/javascript">
	var xmlHttpRequest;
	var name;
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
					var count=document.getElementById(""+add+"").value;//此商品数量
					
					count=xmlHttpRequest.responseText;
					//count=xmlHttpRequest.responseText;
			}
		}
	}
	
	$(function(){
		$('.add').click(function(){
			//获取各个商品 当前数量 ==id号标志哪个商品
			add=this.name;
			var count=document.getElementById(""+add+"").value;//此商品数量
			initXMLHttpRequest();
			fSend("POST", "updateCart", "function=add&id="+add+"&cartnum="+count, fCallback);
		});
	})
	
	$(function(){
		$('.reduce').click(function(){
			//获取各个商品 当前数量 ==id号标志哪个商品
			reduce=this.name;
			var count=document.getElementById(""+reduce+"").value;//此商品数量
			initXMLHttpRequest();
			fSend("POST", "updateCart", "function=min&id="+reduce+"&cartnum="+count, fCallback);
		});
	})
</script>
</head>
<body>
<%
Object obj2 = session.getAttribute("user_id");
if(obj2 == null){
	request.getRequestDispatcher("login.jsp").forward(request, response);
	return;
}
%>
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
        <p>我的购物车</p>
    </div>
    <form id="form" method="post" action="">
    <div class="catbox">
  <table id="cartTable">
    <thead>
      <tr>
        <th>
        	<label>
        		<input class="check-all check" type="checkbox"/>&nbsp;&nbsp;全选
        	</label>
        </th>
        <th>商品</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach var="entry" items="${cart.items }">
      <tr>
        <td class="checkbox"><input name="pid" value="${entry.key }" class="check-one check" type="checkbox"/></td>
        <td class="goods"><img src="${entry.value.goods_info.pic_link }" alt=""/><span>
        ${entry.value.goods_info.goods_name}<br/>
        	（${entry.value.goods_info.english_name}） </span></td>
        <td class="price">${entry.value.goods_info.single_price}</td>
        <td class="count" id="testCount">
        <!-- <span class="reduce"></span> -->
        <input type="button" name="${entry.key }" value="-" class="reduce">
          <input class="count-input" type="text" name="${entry.key }" id="${entry.key }" value="${entry.value.quantity }"/>
          <!-- <span class="add">
          	 +
          </span> -->
          <input type="button" name="${entry.key }" value="+" class="add">
         </td>
        <td class="subtotal">${entry.value.price}</td>
        <td class="operation">
        	<a href="javascript:deleteOne(${entry.key })">
        		<span class="">删除</span>
        	</a>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <div class="foot" id="foot">
    <label class="fl select-all"><input type="checkbox" class="check-all check"/>&nbsp;&nbsp;全选</label>
    <a href="javascript:deleteSome();" class="fl delete" id="deleteAll">删除</a>
    <div class="fr closing" onclick="getTotal();">
    	<a href="javascript:buyGoods();">结 算</a>
    </div>
    <input type="hidden" id="cartTotalPrice" />
    <div class="fr total" id="upriceTotal">合计：￥<span id="priceTotal">0.00</span></div>
    <div class="fr selected" id="selected">已选商品<span id="selectedTotal">0</span>件<span class="arrow up">︽</span><span class="arrow down">︾</span></div>
    <div class="selected-view">
      <div id="selectedViewList" class="clearfix">
        <div><img src="images/1.jpg"><span>取消选择</span></div>
      </div>
      <span class="arrow">◆<span>◆</span></span> </div>
  </div>
</div>
</form>

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