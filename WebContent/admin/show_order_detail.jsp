<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
        
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <title>查看订单详情--多肉商城后台管理系统</title>
    <link rel="stylesheet" href="admin/css/admin_head.css">
    <link rel="stylesheet" href="admin/css/show_order_detail.css">
    <link rel="stylesheet" href="admin/css/footer.css">
    
</head>
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
                <div class="content-right-title">查看订单详情</div>
                <c:set var="order" value="${requestScope.order }" />
                <c:set var="receive" value="${requestScope.rec }" />
                <c:set var="user" value="${requestScope.user }" />
                <c:set var="goods_map" value="${requestScope.goods }" />
                <form>
                    <div class="content-right-form">
                        <div class="form-1">
                            <span>订单编号：</span>
                            <div class="content-text"><span>${order.order_id }</span></div>
                            <br>
                        </div>
                        <div class="form-1">
                            <span>用户编号：</span>
                            <div class="content-text"><span>${order.user_id }</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                            
                            <span>用户名：</span>
                            <div class="content-text"><span>${user.user_name }</span>&nbsp;&nbsp;</div>
                            &nbsp;&nbsp;
                            <br>
                        </div>
                        
                        <div class="form-1">
                            <span>收件人姓名：</span>
                            <div class="content-text"><span>${rec.rec_name }</span></div>
                            <br>
                        </div>
                        <div class="form-1">
                            <span>收件地址：</span>
                            <div class="content-text"><span>${rec.province }省 ${rec.city }市 ${rec.address }</span></div>
                            <br>
                        </div>
                        <div class="form-1">
                            <span>收件电话：</span>
                            <div class="content-text"><span>${rec.rec_phone }</span></div>
                            <br>
                        </div>
                        
                        <div class="form-1">
                            <span>订单开始时间：</span>
                            <div class="content-text"><span>${order.start_time }</span></div>
                            <br>
                        </div>
                        
                        <div class="form-1">
                            <span>订单完成时间：</span>
                            <div class="content-text"><span>${order.finish_time }</span></div>
                            <br>
                        </div>
                        
                        <div class="form-1">
                            <span>订单详情列表：</span>
                            <br>
                        </div>


                <div class="table">
                    <div class="table-head">
                        <div  class="table-data-1">商品编号</div>
                        <div  class="table-data-1">商品名称</div>
                        <div  class="table-data-1">所属种类</div>
                        <div  class="table-data-1">单价</div>
                        <div  class="table-data-2">数量</div>
                        <div  class="table-data-2">总价</div>   
                    </div>
                    
                        <c:forEach items="${requestScope.details}" var="detail" varStatus="varStatus">                     	
                        	
                        	<c:if test="${varStatus.index%2==0 }">
                        		<div class="table-row0">
			                        <div  class="table-data-1">${detail.goods_id }</div>
			                        <div  class="table-data-1">${goods_map[detail.goods_id].goods_name }</div>
			                        <div  class="table-data-1">${goods_map[detail.goods_id].cate_name }</div>
			                        <div  class="table-data-1">${goods_map[detail.goods_id].single_price }</div>
			                        <div  class="table-data-2">${detail.quantity }</div>
			                        <div  class="table-data-2">${detail.price }</div>
			                    </div>
					    	</c:if>
    						<c:if test="${varStatus.index%2==1 }">
    							<div class="table-row1">
			                        <div  class="table-data-1">${detail.goods_id }</div>
			                        <div  class="table-data-1">${goods_map[detail.goods_id].goods_name }</div>
			                        <div  class="table-data-1">${goods_map[detail.goods_id].cate_name }</div>
			                        <div  class="table-data-1">${goods_map[detail.goods_id].single_price }</div>
			                        <div  class="table-data-2">${detail.quantity }</div>
			                        <div  class="table-data-2">${detail.price }</div>
                    			</div>
    						</c:if>			    	
					    	
						</c:forEach>
                       </div>
                        
                    </div>
                
                <div class="pages">
                	<c:if test="${param.cur == 1}">
						<div class="before"><a style="color:darkgrey;">首页</a></div>
                    	<div class="before"><a style="color:darkgrey;">上一页</a></div>
					</c:if>
                	
                	<c:if test="${param.cur != 1}">
						<div class="before"><a href="admin/ShowOrderDetail?cur=1&order_id=${order.order_id }&user_id=${order.user_id }&user_rec_id=${order.user_rec_id }">首页</a></div>
						<div class="before"><a href="admin/ShowOrderDetail?cur=${param.cur - 1}&order_id=${order.order_id }&user_id=${order.user_id }&user_rec_id=${order.user_rec_id }">上一页</a></div>
					</c:if>
					
					<div class="page-nums">
                        <span>当前第${param.cur}页       总共${requestScope.totalPage}页</span>
                    </div>
					
					<c:if test="${param.cur == requestScope.totalPage}">
						<div class="before"><a style="color:darkgrey;">下一页</a></div>
						<div class="after"><a style="color:darkgrey;">尾页</a></div>
					</c:if>
					
					<c:if test="${param.cur != requestScope.totalPage}">
						<div class="before"><a href="admin/ShowOrderDetail?cur=${param.cur + 1}&order_id=${order.order_id }&user_id=${order.user_id }&user_rec_id=${order.user_rec_id }">下一页</a></div>
						<div class="after"><a href="admin/ShowOrderDetail?cur=${requestScope.totalPage}&order_id=${order.order_id }&user_id=${order.user_id }&user_rec_id=${order.user_rec_id }">尾页</a></div>
						
					</c:if> 
                </div>
                
                <div class="form-btns">
                            <div class="form-btn-submit">
                            <input type="button" value="返回" onclick="JavaScript:self.location=document.referrer;;">
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
