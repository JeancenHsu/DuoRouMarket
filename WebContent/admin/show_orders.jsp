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

    <title>查看所有订单--多肉商城后台管理系统</title>
    <link rel="stylesheet" href="admin/css/admin_head.css">
    <link rel="stylesheet" href="admin/css/show_orders.css">
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
                    <a href="admin/ShowUsers?cur=1">&nbsp;管理用户</a><br>
                    <a href="admin/ShowOrders?cur=1">●订单管理</a><br>
                    
                </div>
            </div>
            <div class="content-right">
                <div class="content-right-title">查看所有订单</div>
               <form action="AdminServlet?method=doDelete&table=order_info" method="post">
                <div class="table">
                    <div class="table-head">
                        <div  class="table-data-checkbox">
                            <input type="checkbox" name="flag" id="all" onclick="selectAll('cb')">
                        </div>
                        <div  class="table-data-3">订单编号</div>
                        <div  class="table-data-2">用户编号</div>
                        <div  class="table-data-2">商品数量</div>
                        <div  class="table-data-2">订单总价</div>
                        <div  class="table-data-4">开始时间</div>
                        <div  class="table-data-2">订单状态 </div>
                        <div  class="table-data-2">查看详情</div>
                        
                    </div>
                        <c:forEach items="${requestScope.orders}" var="order" varStatus="varStatus">
                        	<c:if test="${varStatus.index%2==0 }">
                        		<div class="table-row0">
			                        <div  class="table-data-checkbox">
			                            <input type="checkbox" name="cb" onclick="other()" value="${order.order_id }">
			                        </div>
			                        <div  class="table-data-3">${order.order_id }</div>
			                        <div  class="table-data-2">${order.user_id }</div>
			                        <div  class="table-data-2">${order.whole_number }</div>
			                        <div  class="table-data-2">${order.whole_price }</div>
			                        <div  class="table-data-4">${order.start_time }</div>
			                        <div  class="table-data-2">${order.status }</div>
			                        <div  class="table-data-2"><a href="admin/ShowOrderDetail?cur=1&order_id=${order.order_id }&user_id=${order.user_id }&user_rec_id=${order.user_rec_id }">查看</a></div>
			                    </div>
					    	</c:if>
    						<c:if test="${varStatus.index%2==1 }">
    							<div class="table-row1">
			                        <div  class="table-data-checkbox">
			                            <input type="checkbox" name="cb" onclick="other()" value="${order.order_id }">
			                        </div>
			                        <div  class="table-data-3">${order.order_id }</div>
			                        <div  class="table-data-2">${order.user_id }</div>
			                        <div  class="table-data-2">${order.whole_number }</div>
			                        <div  class="table-data-2">${order.whole_price }</div>
			                        <div  class="table-data-4">${order.start_time }</div>
			                        <div  class="table-data-2">${order.status }</div>
			                        <div  class="table-data-2"><a href="admin/ShowOrderDetail?cur=1&order_id=${order.order_id }&user_id=${order.user_id }&user_rec_id=${order.user_rec_id }">查看</a></div>
			                    </div>
    						</c:if>
						</c:forEach>
                 
                 </div>
                
                 <div class="form-btns">
                            <div class="form-btn-submit">
                                <input type="submit" value="删除订单">
                            </div>
                </div>
               </form>
                <div class="pages">
                	<c:if test="${param.cur == 1}">
						<div class="before"><a style="color:darkgrey;">首页</a></div>
                    	<div class="before"><a style="color:darkgrey;">上一页</a></div>
					</c:if>
                	
                	<c:if test="${param.cur != 1}">
						<div class="before"><a href="admin/ShowGoods?cur=1">首页</a></div>
						<div class="before"><a href="admin/ShowGoods?cur=${param.cur - 1}">上一页</a></div>
					</c:if>
					
					<div class="page-nums">
                        <span>当前第${param.cur}页       总共${requestScope.totalPage}页</span>
                    </div>
					
					<c:if test="${param.cur == requestScope.totalPage}">
						<div class="before"><a style="color:darkgrey;">下一页</a></div>
						<div class="after"><a style="color:darkgrey;">尾页</a></div>
					</c:if>
					
					<c:if test="${param.cur != requestScope.totalPage}">
						<div class="before"><a href="admin/ShowGoods?cur=${param.cur + 1}">下一页</a></div>
						<div class="after"><a href="admin/ShowGoods?cur=${requestScope.totalPage}">尾页</a></div>
						
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
