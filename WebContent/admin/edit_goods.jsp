<%@page import="duorou.Cate_info"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>编辑商品--多肉商城后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" href="admin/css/admin_head.css">
    <link rel="stylesheet" href="admin/css/add_goods.css">
    <link rel="stylesheet" href="admin/css/footer.css">
    
</head>
<jsp:useBean id="cate" class="duorou.Cate_info"></jsp:useBean>
<jsp:useBean id="operator" class="method.DBOperator"></jsp:useBean>

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
                    <a href="admin/ShowCates?cur=1">●查看/编辑/删除商品分类</a><br>
                    <a href="admin/ShowGoods?cur=1">&nbsp;查看/编辑/删除商品</a><br>
                    <a href="admin/ShowUsers?cur=1">&nbsp;管理用户</a><br>
                    <a href="admin/ShowOrders?cur=1">&nbsp;订单管理</a>
                </div>
            </div>
            <div class="content-right">
                <div class="content-right-title">编辑商品</div>
                <form action="AdminServlet?method=doGoods&operate=update" method="post" enctype="multipart/form-data">
                    <div class="content-right-form">
                        <div class="form-1">
                            <span>商品名称：</span>
                            <input type="hidden" name="goods_id" value="${param.goods_id }">
                            <input type="text" name="goods_name" class="content-right-form-text" value="${param.goods_name }"><br>
                        </div>
                        <div class="form-1">
                            <span>英文名称：</span>
                            <input type="text" name="english_name" class="content-right-form-text" value="${param.english_name }"><br>
                        </div>
                        <div class="form-1">
                            <span>商品种类：</span>
                            <select class="select-cate" name="goods_cate" >
                                <option value="${param.cate_id }">${param.cate_name }</option>
<%
					ArrayList<Cate_info> cates = operator.getAllCates();
					for(int i=0;i<cates.size();i++){
						cate = (Cate_info)cates.get(i);
%>
						<option value="<%=cate.getCate_id() %>"><%=cate.getCate_name() %></option>					
<%
					}
%>
                            </select><br>
                        </div>
                        <div class="form-1">
                            <span>商品单价：</span>
                            <input type="text" name="single_price" class="content-right-form-text" value="${param.single_price }"><span>元</span><br>
                        </div>
                        <div class="form-1">
                            <span>商品库存：</span>
                            <input type="text" name="goods_amount" class="content-right-form-text" value="${param.goods_amount }"><span>件</span><br>
                        </div>
                        
                        <div class="form-1">
                            <span>商品所在地：</span>
                            <select class="select-province" name="location_province">
                                <option value="${param.location_province }">${param.location_province }</option>
                            </select><span>省</span>
                            <select class="select-city" name="location_city">
                                <option value="${param.city }">${param.location_city }</option>
                            </select><span>市</span>
                            <br>
                        </div>
                        <div class="form-1">
                            <span>商品状态：</span>
                            <select class="select-state" name="goods_state">
                            	<option value="${param.goods_state }">${param.goods_state }</option>
                                <option value="热卖中">热卖中</option>
                                <option value="已下架">已下架</option>
                            </select><br>
                        </div>
                        <div class="form-1">
                            <span>商品描述：</span><br>
                            <textarea class="goods-description" name="description" cols ="100" rows = "30">${param.goods_description }</textarea><br>
                        </div>
                        
                        
                        <div class="form-btns">
                            <div class="form-btn-submit">
                                <input type="submit" value="确认修改">
                            </div>
                            <div class="form-btn-reset">
                                <input type="reset" value="重置">
                            </div>
                        </div>
                    </div>
                </form>
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
