<%@page import="servlet.GoodsCateServlet"%>
<%@page import="duorou.Goods_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>肉萌萌——查看更多</title>
<link rel="stylesheet" href="css/show_more.css" type="text/css">
<style type="text/css">
    #binfen{
        color:#77f93e;
        font-weight: bold;
        border-bottom: 4px solid #77f93e;
    }    
</style>
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
<!--常见多肉-->
	<div class="binfen-top">
        <a class="title">${requestScope.cateName.cate_name }</a>
    </div>
 	<div class="binfen-bottom">
 		<c:forEach var="p" items="${requestScope.cate}">
         	<div class="binfen-bottom-in">
	            <div class="binfen-bottom-in">
	                <a href="goodsInfo?goods_id=${p.goods_id }">
	                    <img class="pic01" src="${p.pic_link }">
	                </a>
	                <div class="content01-title">
	                    <a href="show_details.html">${p.goods_name }<br>（${p.english_name }）</a>
	                </div>
	                <div class="content01-article">
	                    <p>￥${p.single_price }</p>
	                    <a href="goodsInfo?goods_id=${p.goods_id }">
	                        <input type="button" class="button green" value="查&nbsp;看&nbsp;详&nbsp;情">
	                    </a>
	                </div>
	            </div>  
      	</div>
      </c:forEach>
  </div>
  <img src="images/footer.png" alt="" width="100%">
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