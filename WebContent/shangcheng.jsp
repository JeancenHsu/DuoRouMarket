<%@page import="java.util.Set"%>
<%@page import="duorou.Cate_info"%>
<%@page import="java.util.Map"%>
<%@page import="servlet.ListGoodsServlet"%>
<%@page import="java.util.Iterator"%>
<%@page import="duorou.Goods_info"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>肉萌萌——鲜"肉"商城</title>
<link rel="stylesheet" href="css/shangcheng.css" type="text/css">
<script type="text/javascript" src="js/jquery.min_menu.js"></script>
<script type="text/javascript" src="js/shangcheng.js"></script>
<script type="text/javascript" src="js/jquery.min_totop.js"></script>
<style type="text/css">
#binfen {
	color: #77f93e;
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
	<!--二级菜单-->
	<div class="nav-wrap">
		<div class="nav">
			<div class="goods">
				<div>
					<h2>
						<a>全部商品分类</a>
					</h2>
				</div>
				<div class="all-goods">
				<%
				//System.out.println("user_id88:"+session.getAttribute("user_id"));
					Map<Integer, Cate_info> mapCate;
					Map<Integer, Goods_info> mapGoods;
					Cate_info cate=new Cate_info();
					Goods_info goods=new Goods_info();
					ListGoodsServlet list=new ListGoodsServlet();
					mapCate=list.findCate();
					int cateId=0;
					int goodsId=0;
					Set<Integer> set=mapCate.keySet();
					Iterator it=set.iterator();
					while(it.hasNext()){
						cateId=(Integer)it.next();
						cate=mapCate.get(cateId);
				%>
				<%-- <c:forEach var="cate" items="${requestScope.cate}"> --%>
					<div class="item">
						<div class="product">
							<h3>
								<a href="goodsCate?cate_id=<%=cate.getCate_id()%>"><%=cate.getCate_name()%></a>
							</h3>
							<s style="display: block;"></s>
						</div>
						<div class="product-wrap posone">
							<div class="cf">
								<div class="fl wd252 pr20">
									<h2>
										<a href="goodsCate?cate_id=<%=cate.getCate_id()%>">
											<%=cate.getCate_name()%>
										</a>
									</h2>
									<p class="lh30"><%=cate.getCate_description() %></p>
									<ul class="cf">
            						<%-- <c:forEach var="goods" items="${requestScope.goods}"> --%>
            						<%
            							mapGoods=list.findGoodsName(cateId);
	            						Set<Integer> set2=mapGoods.keySet();
	            						Iterator it2=set2.iterator();
	            						while(it2.hasNext()){
	            							goodsId=(Integer)it2.next();
	            							goods=mapGoods.get(goodsId);
            						%>
										<li>
											<a href="goodsInfo?goods_id=<%=goods.getGoods_id()%>">
												<%=goods.getGoods_name() %>
											</a>
										</li>
										<%} %>
									<%-- </c:forEach> --%>
									</ul>
								</div>
								<dl class="fl wd185 pl20 blee">
									<dd id="logoads" class="image">
										<img src="<%=cate.getCate_image()%>">
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<%} %>
				<%-- </c:forEach> --%>
				</div>
			</div>
		</div>
	</div>

	<!--返回顶部-->
	<div id="goTop" class="goTop">
		<p>返回顶部</p>
	</div>
<script type="text/javascript">
    $(window).scroll(function(){
        var sc=$(window).scrollTop();
        var rwidth=$(window).width()+$(document).scrollLeft();
        var rheight=$(window).height()+$(document).scrollTop();
        if(sc>0){
            $("#goTop").css("display","block");
            $("#goTop").css("left",(rwidth-80)+"px");
            $("#goTop").css("top",(rheight-120)+"px");
        }else{
            $("#goTop").css("display","none");
        }
    });
    $("#goTop").click(function(){
        $('body,html').animate({scrollTop:0},300);
    });
</script>

<%
	Map<Integer, Cate_info> mapCate2;
	Map<Integer, Goods_info> mapGoods2;
	Cate_info cate2=new Cate_info();
	Goods_info goods2=new Goods_info();
	ListGoodsServlet list2=new ListGoodsServlet();
	mapCate2=list2.findCate();
	int cateId2=0;
	int goodsId2=0;
	Set<Integer> set3=mapCate2.keySet();
	Iterator it3=set3.iterator();
	while(it3.hasNext()){
		cateId2=(Integer)it3.next();
		cate2=mapCate2.get(cateId2);
%>
<div class="content01">
        <div class="binfen">
            <div class="binfen-top">
                <a class="title"><%=cate2.getCate_name() %></a>
            </div>
            <div class="binfen-bottom">
            <%
        		mapGoods2=list2.findGoodsName(cateId2);
         		Set<Integer> set4=mapGoods2.keySet();
         		Iterator it4=set4.iterator();
         		while(it4.hasNext()){
         			goodsId2=(Integer)it4.next();
         			goods2=mapGoods2.get(goodsId2);
            %>
            <%-- <c:forEach var="goods" items="${requestScope.goods}"> --%>
                <div class="binfen-bottom-in">
                    <a href="goodsInfo?goods_id=<%=goods2.getGoods_id()%>">
                    	<img class="pic01" src="<%=goods2.getPic_link()%>">
                    </a>
                    <div class="content01-title">
                        <a href="goodsInfo?goods_id=<%=goods2.getGoods_id()%>">
                        	<%=goods2.getGoods_name() %><br> <%=goods2.getEnglish_name() %>
                        </a>
                    </div>
                    <div class="content01-article">
                        <p>￥<%=goods2.getSingle_price() %></p>
                        <a href="goodsInfo?goods_id=<%=goods2.getGoods_id()%>">
                            <input type="button" class="button green" value="查&nbsp;看&nbsp;详&nbsp;情">
                        </a>
                    </div>
                </div>
            <%} %>
            <%-- </c:forEach> --%>
            </div>
        </div>
</div>
<%-- </c:forEach> --%>
<%} %>
	<!--footer-->
	<div class="moto">
		<div class="moto-Con">
			<a>从 现 在 起 ，你 是 它 们 的 主 人。</a>
		</div>
	</div>
	<div class="bottom" width=100%>
		<a>Copyright ©爱多肉 All Rights Reserved. Designed by 14卓工班小仙女.</a> <br>
		<a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
			href="#">联系我们</a>
	</div>
</body>
</html>