<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加分类--多肉商城后台管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
   
    <link rel="stylesheet" href="admin/css/admin_head.css">
    <link rel="stylesheet" href="admin/css/add_cate.css">
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
                    <a href="admin/add_cate.jsp">●增加商品分类</a><br>
                    <a href="admin/AddGoods">&nbsp;增加新商品</a><br>
                    <a href="admin/ShowCates?cur=1">&nbsp;查看/编辑/删除商品分类</a><br>
                    <a href="admin/ShowGoods?cur=1">&nbsp;查看/编辑/删除商品</a><br>
                    <a href="admin/ShowUsers?cur=1">&nbsp;用户管理</a><br>
                     <a href="admin/ShowOrders?cur=1">&nbsp;订单管理</a>
                </div>
            </div>
            <div class="content-right">
                <div class="content-right-title">增加商品分类</div>
                <form action="AdminServlet?method=doCates&operate=add" method="post" enctype="multipart/form-data">
                    <div class="content-right-form">
                        <div class="form-1">
                            <span>分类名称：</span>
                            <input type="text" class="content-right-form-text" name="cate_name"><br>
                        </div>
                        <div>
                        <div class="form-1">
                            <span>分类图片：</span>
                            <input type="file" class="file-update" name="cate_images"><br>
                        </div>
                        </div>
                        <div class="form-1">
                            <span>分类描述：</span><br>
                            <textarea class="cate-description"  cols ="100" rows = "30" name="cate_description"></textarea><br>
                        </div>
                        <div class="form-btns">
                            <div class="form-btn-submit">
                                <input type="submit" value="确认添加">
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
