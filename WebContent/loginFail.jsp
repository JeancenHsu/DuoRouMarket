<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'signin3.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/banner.css">
    <link rel="stylesheet" href="css/content.css">
    <link rel="stylesheet" href="css/footer.css">
    <style type="text/css">
        #signin{
            color:#77f93e;
            font-weight: bold;
            border-bottom: 4px solid #77f93e;
        }
        
        .content03{
            margin-top: 0;
        }
    </style>
</head>
<body>
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
    
    <div class="blank"></div>
    
    <div class="zhuce">
        <div class="zhuce-Con">
            <div class="zhuce-head">
                <div class="zhuce-success-img"></div>
                <div class="zhuce-sucess-txt">
         
				<c:choose>
                	<c:when test="${sessionScope.errorMsg == null}">
                		<a>未知错误</a>
                	</c:when>
                	<c:otherwise>
                		<a>${sessionScope.errorMsg }</a>
                	</c:otherwise>
                </c:choose>
                    <a>登录失败！</a>
                </div>
                <a href="login.jsp" style="display:block;margin:35px auto; text-align:center;color:#8fba9a;font-size:16px;">重新登录</a>
            </div>
        </div>
    </div>
    <div class="moto">
        <div class="moto-Con">
            <span>从 现 在 起 ，你 是 它 们 的 主 人。</span>
        </div>
    </div>
    <div class="bottom" width=100%>
        <span>Copyright  ©爱多肉 All Rights Reserved.  Designed by 14卓工小仙女.</span>
        <br>
        <a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">联系我们</a>
    </div>                                   

</body>
</html>