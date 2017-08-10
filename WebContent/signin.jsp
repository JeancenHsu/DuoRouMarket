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
    <title>My JSP 'signin.jsp' starting page</title>
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
    <script type="text/javascript">
		function check() {
			if (registerform.userphone.value == "") {
				alert("手机号不能为空!");
				registerform.userphone.focus();
				return false;
			} else if (!(/^1[0-9][0-9]\d{4,8}$/
					.test(registerform.userphone.value))) {
				alert("请输入正确的手机号码");
				document.registerform.userphone.focus();
				return false;
			}
			
			if (registerform.password.value == "") {
				alert("密码不能为空!");
				registerform.password.focus();
				return false;
			}
			
			if (registerform.password.value.length < 6) {
				alert("密码长度不能小于6位!");
				registerform.password.focus();
				return false;
			}
			
			if (registerform.repassword.value != registerform.password.value) {
				alert("两次输入密码不一致，请重新输入");
				document.registerform.password.focus();
				return false;
			}
			
			if (!registerform.protocol.checked) {
				alert("请同意网站服务条款！");
				return false;
			}
			
			loginform.submit();
		}
	</script>
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
	</div>
    
    <div class="blank"></div>
 	
    <div class="zhuce">
        <div class="zhuce-Con">
            <div class="zhuce-head">
                <div class="zhuce-head-Con">
                    <div class="zhuce-head-left"><a>1.设置用户名</a></div>
                    <div class="zhuce-head-mid"><a>2.设置用户详情</a></div>
                    <div class="zhuce-head-right"><a>3.注册成功</a></div>
                </div>
            </div>
            <div class="zhuce-body">
                <div class="zhuce-notice">
                    <a>请使用实名登记的移动电话号码进行注册！</a>
                </div>
                
                <form action="/CFleshiness/register" name="registerform" method="post" onSubmit="return check();">
                    <div class="login-input">
                        <div class="login-textbox">
                            <div class="signin-img"></div>
                            <input class="input-txt" type="text" name="userphone" style="width:408px;height:42px;border:none;font-size: 16px;" placeholder="中国大陆手机号">
                        </div>
                        <div class="login-textbox">
                            <div class="login-pwdimg"></div>
                            <input class="input-txt" type="password" name="password" style="width:408px;height:42px;border:none;font-size: 16px;" placeholder="请设置密码">
                        </div>
                        <div class="login-textbox">
                            <div class="login-pwdimg"></div>
                            <input class="input-txt" type="password" name="repassword" style="width:408px;height:42px;border:none;font-size: 16px;" placeholder="请再次输入密码">
                        </div>
                        
                    </div>
                    
                    <div class="login-checkbox">
                            <input class="login-checkbox-Con" type="checkbox" value="" name="" checked>同意<a href="#">《网站服务协议》</a>
                    </div>
                    <div style="height:30px"></div>
                    <div class="login-btn">
                        <a href="signin2.jsp"><input type="submit" value="同意协议并注册"></a>
                    </div>
                </form>
                        
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