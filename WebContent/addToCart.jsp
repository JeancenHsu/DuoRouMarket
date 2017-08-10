<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>肉萌萌——养"肉"小车</title>
<link rel="stylesheet" href="css/addToCart.css" type="text/css">
<style type="text/css">
    #yangzhi{
        color:#77f93e;
        font-weight: bold;
        border-bottom: 4px solid #77f93e;
}    
</style>
<script type="text/javascript">
function addCart(){
	var form = document.getElementById("form");
	//var p=document.getElementById("text_box").value;
	//alert(p)
	var goods_num=document.getElementById("goods_num");
	var t=goods_num.getElementsByTagName("span");
	var number=t[0].innerText;
	alert(number)
	form.action="addCart?number="+number;
	form.submit();
}
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
        <p>我的购物车</p>
    </div>
<form id="form" action="" method="post">
    <div class="center">
        <div class="goods_img">
            <img src="${sessionScope.goodsAddToCart.pic_link }" alt="" width="250" height="188">
        </div>
        <div class="goods_name">
            已添加商品：<br><span>${sessionScope.goodsAddToCart.goods_name }
            		（${sessionScope.goodsAddToCart.english_name }）</span>
        </div>
        <div class="goods_num" id="goods_num">
            数量：<br><span>${requestScope.goodsNum }</span>
        </div>
        <div class="success">
            <img src="images/success.png" alt="">
            <div class="success_des">
                <span>已成功加入购物车</span>
            </div>
        </div>
        <div class="price">
            小计：<span>￥${requestScope.pri }</span>
        </div>
        <div class="cart_number">
            购物车里共有<span>${sessionScope.cart.whole_quantity }件</span>商品
        </div>
        <div class="back">
           <a href="goodsInfo?goods_id=${sessionScope.goodsAddToCart.goods_id }">
               <input type="button" value="<<&nbsp;&nbsp;返回商品详情">
           </a>
        </div>
        <div class="go">
           <a href="shoppingCart.jsp">
               <input type="button" value="去购物车结算&nbsp;&nbsp;>>">
           </a>
        </div>
    </div>
</form>

<!--footer-->
    <div class="moto">
        <div class="moto-Con">
            <a>从 现 在 起 ，你 是 它 们 的 主 人。</a>
        </div>
    </div>
    <div class="bottom" width=100%>
        <a>Copyright  ©爱多肉 All Rights Reserved.  Designed by 14卓工班小仙女.</a>
        <br>
        <a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">联系我们</a>
    </div> 
</body>
</html>