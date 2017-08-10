<%@page import="duorou.Goods_info"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/content.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/show_details.css">
    <style type="text/css">
        #binfen{
            color:#77f93e;
            font-weight: bold;
            border-bottom: 4px solid #77f93e;
        } 
    </style>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/cartNum.js"></script>
    <script type="text/javascript">
    function addCart(){
    	var form = document.getElementById("form");
    	var p=document.getElementById("text_box").value;
    	//alert(p)
    	form.action="addCart?number="+p;
		form.submit();
    }
    function buyGoods(){
    	var form = document.getElementById("form");
    	var t=document.getElementById("text_box").value;
    	form.action="buyGoods?method=single&number="+t;
		form.submit();
    }
    function addcheck(amount){
    	var num=document.getElementById("text_box");//数量
    	var tt=document.getElementById("num");
    	var hehe=tt.getElementsByTagName("span");
    	var hh=hehe[0].innerText;
    	alert(hh)
    	//alert(num+","+amount)//num:用户想要购买的==amount库存总量
    	var flag=true;
    	if(num.value>amount+1){
    		flag=false;
    		//alert(amount)
    	}
    	if(!flag){
    		alert("商品数量已经超出库存总量")
    		num.innerHTML=amount;
    	}
    }
	</script>
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
    <%
		Object obj=session.getAttribute("goods_info");
		if(obj==null){
			return;
		}else{
			Goods_info goods_info=new Goods_info();
			goods_info=(Goods_info)obj; 
			int goods_id=goods_info.getGoods_id();
			session.setAttribute("goods_id", goods_id);
	%>
    <div class="show">
        <div id="magnifier">
            <img src="<%=goods_info.getPic_link() %>" width="100%" height="100%"   onload="return imgzoom(this,600);" onclick="javascript:window.open(this.src);" style="cursor:pointer;"/>
            <div id="asd"></div>
        </div>
            <div id="mag"><img id="magnifierImg"  onload="return imgzoom(this,600);" onclick="javascript:window.open(this.src);" style="cursor:pointer;"/></div>
 	<form id="form" action="" method="post"> 
 	<input type="hidden" name="goods_id" value="${sessionScope.goods_id }">    
        <div class="goods_con">
            <div class="goods_name">
                <a href="#"><%=goods_info.getGoods_name() %>&nbsp;<%=goods_info.getEnglish_name() %></a>
            </div>
            <div class="goods_price">价&nbsp;&nbsp;&nbsp;&nbsp;格：<span><%=goods_info.getSingle_price() %></span></div>
            <div class="goods_birth">所在地：<span><%=goods_info.getLocation() %></span></div>
            <div class="goods_state">状&nbsp;&nbsp;&nbsp;&nbsp;态：<span><%=goods_info.getGoods_state() %></span></div>
            <div class="goods_num" id="num">数&nbsp;&nbsp;&nbsp;&nbsp;量：
                   <input id="min" name="" type="button" value="-" class="min"/>
                    <input id="text_box" name="number" type="text" value="1" class="num" 
                    onkeypress="number()" onkeyup="filterInput()" 
                    onchange="filterInput()" onbeforepaste="filterPaste()" 
                    onpaste="return false" style="ime-mode: disabled"/> 
                    <input id="add" name="" type="button" value="+" class="add" 
                    <%-- onclick="addcheck(<%=goods_info.getGoods_amount()%> --%>)"/> 
               （库存<span class="amount"><%=goods_info.getGoods_amount() %></span>件）</div>
            <a href="javascript:buyGoods();">
            	<input type="button" value="立即购买" class="btnBuy" onclick="">
            </a>
            <a href="javascript:addCart();">
            	<input type="button" value="加入购物车" class="btnCart" >
            </a>
        </div>
      </form> 
        <div class="goods_des">
            <div class="goods_desTitle">商品描述：</div>
            <div class="goods_desCon"><p>&nbsp;&nbsp;&nbsp;&nbsp;<%=goods_info.getDescription() %></p></div>
        </div>  
    </div>
    
    <%
		}
	%>
<!--footer-->
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

<script type="text/javascript">
// JavaScript Document
function getEventObject(W3CEvent) {   //事件标准化函数
 return W3CEvent || window.event;
}
function getPointerPosition(e) {   //兼容浏览器的鼠标x,y获得函数
 e = e || getEventObject(e);
 var x = e.pageX || (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
 var y = e.pageY || (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
 return { 'x':x,'y':y };
}
function setOpacity(elem,level) {   //兼容浏览器设置透明值
 if(elem.filters) {
  elem.style.filter = 'alpha(opacity=' + level * 100 + ')';
 } else {
  elem.style.opacity = level;
 }
}
function css(elem,prop) {    //css设置函数,可以方便设置css值,并且兼容设置透明值
 for(var i in prop) {
  if(i == 'opacity') {
   setOpacity(elem,prop[i]);
  } else {
   elem.style[i] = prop[i];
  }
 }
 return elem;
}
var magnifier = {
 m : null,
 init:function(magni){
  var m = this.m = magni || {
   cont : null,  //装载原始图像的div
   img : null,   //放大的图像
   mag : null,   //放大框
   scale : 5   //比例值,设置的值越大放大越大,但是这里有个问题就是如果不可以整除时,会产生些很小的白边,目前不知道如何解决
  }
 
  css(m.img,{
   'position' : 'absolute',
   'width' : (m.cont.clientWidth * m.scale) + 'px',    //原始图像的宽*比例值
   'height' : (m.cont.clientHeight * m.scale) + 'px'    //原始图像的高*比例值
   })
  css(m.mag,{
   'display' : 'none',
   'width' : m.cont.clientWidth + 'px',   //m.cont为原始图像,和原始图像等宽
   'height' : m.cont.clientHeight + 'px',
   'position' : 'absolute',
   'left' : m.cont.offsetLeft + m.cont.offsetWidth + 10 + 'px',  //放大框的位置为原始图像的右方远10px
   'top' : m.cont.offsetTop + 'px'
   })
  var borderWid = m.cont.getElementsByTagName('div')[0].offsetWidth - m.cont.getElementsByTagName('div')[0].clientWidth;  //获取border的宽
  css(m.cont.getElementsByTagName('div')[0],{   //m.cont.getElementsByTagName('div')[0]为浏览框
   'display' : 'none',        //开始设置为不可见
   'width' : m.cont.clientWidth / m.scale - borderWid + 'px',   //原始图片的宽/比例值 - border的宽度
   'height' : m.cont.clientHeight / m.scale - borderWid + 'px',  //原始图片的高/比例值 - border的宽度
   'opacity' : 0.5     //设置透明度
   })

  m.img.src = m.cont.getElementsByTagName('img')[0].src;   //让原始图像的src值给予放大图像
  m.cont.onmouseover = magnifier.start;
 },
 start:function(e){
  if(document.all){    //只在IE下执行,主要避免IE6的select无法覆盖
   magnifier.createIframe(magnifier.m.img);
  }
  this.onmousemove = magnifier.move;  //this指向m.cont
  this.onmouseout = magnifier.end;
 },
 move:function(e){
  var pos = getPointerPosition(e);  //事件标准化
  this.getElementsByTagName('div')[0].style.display = '';
  css(this.getElementsByTagName('div')[0],{
   'top' : Math.min(Math.max(pos.y - this.offsetTop - parseInt(this.getElementsByTagName('div')[0].style.height) / 2,0),this.clientHeight - this.getElementsByTagName('div')[0].offsetHeight) + 'px',
   'left' : Math.min(Math.max(pos.x - this.offsetLeft - parseInt(this.getElementsByTagName('div')[0].style.width) / 2,0),this.clientWidth - this.getElementsByTagName('div')[0].offsetWidth) + 'px'   //left=鼠标x - this.offsetLeft - 浏览框宽/2,Math.max和Math.min让浏览框不会超出图像
   })
  magnifier.m.mag.style.display = '';
  css(magnifier.m.img,{
   'top' : - (parseInt(this.getElementsByTagName('div')[0].style.top) * magnifier.m.scale) + 'px',
   'left' : - (parseInt(this.getElementsByTagName('div')[0].style.left) * magnifier.m.scale) + 'px'
   })
 },

 end:function(e){
  this.getElementsByTagName('div')[0].style.display = 'none';
  magnifier.removeIframe(magnifier.m.img);  //销毁iframe
  magnifier.m.mag.style.display = 'none';
 },
 createIframe:function(elem){
  var layer = document.createElement('iframe');
  layer.tabIndex = '-1';
  layer.src = 'javascript:false;';
  elem.parentNode.appendChild(layer);

  layer.style.width = elem.offsetWidth + 'px';
  layer.style.height = elem.offsetHeight + 'px';
 },
 removeIframe:function(elem){
  var layers = elem.parentNode.getElementsByTagName('iframe');
  while(layers.length >0){
   layers[0].parentNode.removeChild(layers[0]);
  }
 }
}
window.onload = function(){
 magnifier.init({
       cont : document.getElementById('magnifier'),
       img : document.getElementById('magnifierImg'),
       mag : document.getElementById('mag'),
       scale : 3
       });
}

</script>