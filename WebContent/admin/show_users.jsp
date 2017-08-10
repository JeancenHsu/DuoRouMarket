<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
        
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <title>查看所有用户--多肉商城后台管理系统</title>
    <link rel="stylesheet" href="admin/css/admin_head.css">
    <link rel="stylesheet" href="admin/css/show_users.css">
    <link rel="stylesheet" href="admin/css/footer.css">
    
    
</head>

<script type="text/javascript">
	function selectAll(obj){
		var all = document.getElementById("all");
		var flag = document.getElementsByName(obj);
		if(all.checked){
			for(var i=0;i<flag.length;i++){
				flag[i].checked=true;
			}
		}else{
			for(var i=0;i<flag.length;i++){
				flag[i].checked=false;
			}
		}
	}
	
	function other(){
		//只要有一个不选中，全选就不选中
		var cbs = document.getElementsByName("cb");
		var flag=true;
		for(var i=0;i<cbs.length;i++){
			if(!cbs[i].checked){
				flag=false;
				break;
			}
		}
		if(flag){
			document.getElementById("all").checked=true;
		}else{
			document.getElementById("all").checked=false;
		}
	}
	
</script>

<body>



    <div class="head">
        <div class="head-Con">
            <a href="#">
                <img class="head-img" src="admin/images/logo.png" alt="logo">
            </a>
            <span class="span-head">商城后台管理系统</span>
            <div class="nav">
                <a href="exit" class="a2" id="signin">&nbsp;&nbsp;退出&nbsp;&nbsp;</a>
                <a href="shangcheng.jsp" class="a2" id="binfen">多肉商城</a>
                <a href="index.jsp" class="a2" id="shouye">网站首页</a>
            </div>
        </div>
    </div>
    <div class="body">
        <div class="content">
            <div class="content-left">
                <span>商城管理系统主要功能</span><br>
                <div class="content-left-list">
                    <a href="admin/add_cate.jsp">&nbsp;增加商品分类</a><br>
                    <a href="admin/AddGoods">&nbsp;增加新商品</a><br>
                    <a href="admin/ShowCates?cur=1">&nbsp;查看/编辑/删除商品分类</a><br>
                    <a href="admin/ShowGoods?cur=1">&nbsp;查看/编辑/删除商品</a><br>
                    <a href="admin/ShowUsers?cur=1">●用户管理</a><br>
                    <a href="admin/ShowOrders?cur=1">&nbsp;订单管理</a>
                </div>
            </div>
            <div class="content-right">
                <div class="content-right-title">查看所有用户</div>
               <form action="AdminServlet?method=doDelete&table=user_info" method="post">
                <div class="table">
                    <div class="table-head">
                        <div  class="table-data-checkbox">
                            <input type="checkbox" name="flag" id="all" onclick="selectAll('cb')">
                        </div>
                        <div  class="table-data-1">用户编号</div>
                        <div  class="table-data-1">用户名</div>
                        <div  class="table-data-1">手机号</div>
                        <div  class="table-data-3">注册时间</div>
                        <div  class="table-data-3">上次登录时间</div>
                        <div  class="table-data-2">编辑</div>
                        
                    </div>
                        <c:forEach items="${requestScope.users}" var="user" varStatus="varStatus">
                        	<c:if test="${varStatus.index%2==0 }">
                        		<div class="table-row0">
			                        <div  class="table-data-checkbox">
			                            <input type="checkbox" name="cb" onclick="other()" value="${user.user_id }">
			                        </div>
			                        <div  class="table-data-1">${user.user_id }</div>
			                        <div  class="table-data-1">${user.user_name }</div>
			                        <div  class="table-data-1">${user.user_phone }</div>
			                        <div  class="table-data-3">${user.register_time }</div>
			                        <div  class="table-data-3">${user.last_login_time }</div>
			                        <div  class="table-data-2"><a href="admin/edit_user.jsp?user_id=${user.user_id }&user_name=${user.user_name }&user_phone=${user.user_phone }">编辑</a></div>
			                    </div>
					    	</c:if>
    						<c:if test="${varStatus.index%2==1 }">
    							<div class="table-row1">
			                        <div  class="table-data-checkbox">
			                            <input type="checkbox" name="cb" onclick="other()" value="${user.user_id }">
			                        </div>
			                        <div  class="table-data-1">${user.user_id }</div>
			                        <div  class="table-data-1">${user.user_name }</div>
			                        <div  class="table-data-1">${user.user_phone }</div>
			                        <div  class="table-data-3">${user.register_time }</div>
			                        <div  class="table-data-3">${user.last_login_time }</div>
			                        <div  class="table-data-2"><a href="admin/edit_user.jsp?user_id=${user.user_id }&user_name=${user.user_name }&user_phone=${user.user_phone }">编辑</a></div>
			               		</div>
    						</c:if>
						</c:forEach>
                 
                 </div>
                
                 <div class="form-btns">
                            <div class="form-btn-submit">
                                <input type="submit" value="删除用户">
                            </div>
                </div>
               </form>
                <div class="pages">
                	<c:if test="${param.cur == 1}">
						<div class="before"><a style="color:darkgrey;">首页</a></div>
                    	<div class="before"><a style="color:darkgrey;">上一页</a></div>
					</c:if>
                	
                	<c:if test="${param.cur != 1}">
						<div class="before"><a href="admin/ShowUsers?cur=1">首页</a></div>
						<div class="before"><a href="admin/ShowUsers?cur=${param.cur - 1}">上一页</a></div>
					</c:if>
					
					<div class="page-nums">
                        <span>当前第${param.cur}页       总共${requestScope.totalPage}页</span>
                    </div>
					
					<c:if test="${param.cur == requestScope.totalPage}">
						<div class="before"><a style="color:darkgrey;">下一页</a></div>
						<div class="after"><a style="color:darkgrey;">尾页</a></div>
					</c:if>
					
					<c:if test="${param.cur != requestScope.totalPage}">
						<div class="before"><a href="admin/ShowUsers?cur=${param.cur + 1}">下一页</a></div>
						<div class="after"><a href="admin/ShowUsers?cur=${requestScope.totalPage}">尾页</a></div>
						
					</c:if> 
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
