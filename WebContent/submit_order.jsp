<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>肉萌萌——订单确认</title>
<link rel="stylesheet" href="css/submit_order.css" type="text/css">
<script src="js/jquery.js"></script> 
<script type="text/javascript" src="js/jquery-1.7.min.js"></script>
<script>
	$(function(){
	    $("#address ul a li").click(function(){
	        $("#address ul a li span").removeClass("a");
	        $(this).find("span").addClass("a");
	    });
	});
	function change(i){    
	}
	$(document).ready(function () {
		var ans = document.getElementsByName("address");
		ans[0].checked=true;
		ans[0].onclick=setA;
	});
    /* //给单选按钮绑定事件
     window.onload=function(){
        var ans = document.getElementsByName("address");
        for(var i=0;i<ans.length;i++){
	         if(ans[i].checked==true){
	        	 alert(222)
	               ord.innerHTML=ans[i].value;
	               alert(ans[i].value)
	         }
     	}
    } */
    //当值改变时把当前选项呈现在指定区域
     function setA(){
         var ans = document.getElementsByName("address");
         var ord = document.getElementById("answerOrder");
         for(var i=0;i<ans.length;i++){
	         if(ans[i].checked==true){
	               ord.innerHTML=ans[i].value;
	               alert(ans[i].value)
	              /*  var addressRe=document.getElementById("addressRe");
	       			var m=addressRe.getElementsByTagName("span");
	       			var user_rec_id=m[i].innerText;
	              	alert(user_rec_id) */
	          }
         }
 	}

	function submitOrder(){
 		var user_rec_id;
 		var ans = document.getElementsByName("address");
        for(var i=0;i<ans.length;i++){
	         if(ans[i].checked==true){
	             user_rec_id=ans[i].value;
	         }
     	}
		var form=document.getElementById("form");
		form.action="submitOrder?user_rec_id="+user_rec_id;
		form.submit();
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
    <div class="confirm_address">
        <p class="title">选择收货地址</p>
        <div class="address" id = "address">
          <ul>
	          <c:forEach var="rec" items="${applicationScope.rec }">
	               <a class="m">
	                   <li onclick = "change(this);">
	                        <input type="radio" name="address" id="address1_span" value="${rec.user_rec_id }" checked>
	                        <label for="address1_span" id="addressRe">
	                            <span class="detailed_address">${rec.province }省&nbsp;${rec.city }市&nbsp;${rec.address }&nbsp;&nbsp;${rec.rec_name }（收）&nbsp;${rec.rec_phone }</span>
	                        </label>
	                   </li>
	               </a>
	          </c:forEach>
         </ul>
        </div>
        <div class="mannage">
            <a href="manage_address.jsp">重新管理地址</a>
        </div>
    </div>
    <div class="confirm_info">
        <p>确认订单信息</p>
    </div>
  
    <div class="catbox">
    <form id="form" action="" method="post">
  <table id="cartTable">
    <thead>
      <tr>
        <th>商品宝贝</th>
        <th>商品属性</th>
        <th>单价</th>
        <th>数量</th>
        <th>小计</th>
      </tr>
    </thead>
    
    <tbody>
    <c:forEach var="entry" items="${Buygoods.items }">
      <tr>
        <td class="goods">
           <a href="show_details.html">
               <img src="${entry.value.goods_info.pic_link }" alt=""/>
           </a>
        </td> 
        <td class="goos_des">
            <div class="goods_name">名称：${entry.value.goods_info.goods_name}（${entry.value.goods_info.english_name}）</div><br><br>
            <div class="goods_birth">产地：${entry.value.goods_info.location}</div>
        </td>    
        <td class="price">￥${entry.value.goods_info.single_price}</td>
        <td class="count">${entry.value.quantity }</td>
        <td><div class="subtotal">￥${entry.value.price}</div></td>
      </tr>
      </c:forEach>    
    </tbody>
  </table>
  
  <div class="foot">
        <div class="bottom_price">
            <b>实际付款：</b><span>￥${sessionScope.totalPrice }</span>
        </div>
        <!-- <div class="bottom_address" id="add3">
            <b>收货信息：&nbsp;&nbsp;</b>
            <p id="answerOrder"></p>
        </div> -->
        <!-- <div class="bottom_name" id="bRecPerson">
            
        </div> -->
        <div class="back">
           <a href="shoppingCart.jsp">
               <input type="button" value="<<&nbsp;&nbsp;返回购物车">
           </a>
        </div>
        <div class="go">
           <a href="javascript:submitOrder();">
               <input type="button" value="提交订单">
           </a>
        </div>
  </div>
  </form>
  <!--footer-->
    <div class="moto">
        <div class="moto-Con">
            <a>从 现 在 起 ，你 是 它 们 的 主 人。</a>
        </div>
    </div>
    <div class="bottom" width=100%>
        <a>Copyright  ©爱多肉 All Rights Reserved.  Designed by 14卓工班 小仙女.</a>
        <br>
        <a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">联系我们</a>
    </div>
</div>
    
    
</body> 
</html>