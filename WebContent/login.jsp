<%@page import="servlet.DBServlet"%>
<%@page import="duorou.User_info"%>
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
    <title>My JSP 'login.jsp' starting page</title>
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
        #login{
            color:#77f93e;
            font-weight: bold;
            border-bottom: 4px solid #77f93e;
        }    
    </style>
 
  </head>
  
  <body>
    <div class="blank"></div>
    <div class="head">
        <div class="head-Con">
            <a href="#">
                <img src="images/logo.png" alt="logo">
            </a>
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
    </div>
    <div class="login-all">
        <div class="login-content">
            <div class="login-left"></div>
            <div class="login-right">
                <div class="login-right-box">
                    <div class="login-title">
                        <a>欢迎登录</a>
                    </div>
                    <form name="loginform" action="/CFleshiness/login" method="post">
                    <div class="login-input">
                        <div class="login-textbox">
                            <div class="login-userimg"></div>
                            <input class="input-txt" type="text" name="userphone" 
                            value="${sessionScope.phoneInCookie }"
                            style="width:294px;height:40px;border:none;font-size: 16px;" placeholder="手机">
                        </div>
                        <div class="login-textbox">
                            <div class="login-pwdimg"></div>
                            <input class="input-txt" type="password" name="password" 
                            value="${sessionScope.passwordInCookie }" 
                            style="width:294px;height:40px;border:none;font-size: 16px;" placeholder="密码">
                        </div>
                        <div class="login-checkbox">
                            <input class="login-checkbox-Con" type="checkbox" name="check" checked>记住密码
                        </div>
                        <div class="login-btn">
                            <input type="submit" value="登 录">
                        </div>
                    </div>
                    </form>
                    
                    <a href="signin.jsp" style="color:#8fba9a;margin:10px 24px;font-size:12px;">免费注册>></a>
                    <div class="login-other">
    
                    </div>
                </div>
            </div>
        </div>
    </div>
    
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
