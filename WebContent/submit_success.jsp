<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>肉萌萌——注册提交成功</title>
<link rel="stylesheet" href="css/submit_success.css">
</head>
<body>
	<div class="head">
		<div class="head-Con">
			<img src="images/logo.png" alt="logo">
			<div class="nav">
				<a href="index.jsp" class="a1" id="shouye">首页</a> <a
					href="shangcheng.jsp" class="a2" id="binfen">鲜“肉”商城</a> <a
					href="shoppingCart.jsp" class="a2" id="yangzhi">养“肉”小车</a>
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
				<input type="text" id="s" name="search" placeholder="Search"
					class="swap_value" /> <input type="submit" id="go" alt="Search"
					title="Search" value="" />
			</form>
		</div>
	</div>

	<div class="blank"></div>

	<div class="zhuce">
		<div class="zhuce-Con">
			<div class="zhuce-success-img"></div>
			<div class="zhuce-sucess-txt">
				<a>恭喜您订单提交成功！</a>
			</div>
		</div>
	</div>
	<div class="moto">
		<div class="moto-Con">
			<a>从 现 在 起 ，你 是 它 们 的 主 人。</a>
		</div>
	</div>
	<div class="bottom" width=100%>
		<a>Copyright ©爱多肉 All Rights Reserved. Designed by 14卓工班 小仙女.</a> <br>
		<a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页 </a>&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="#">联系我们</a>
	</div>

</body>
</html>