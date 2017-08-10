<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>肉萌萌——首页</title>
    <link rel="stylesheet" href="css/content.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/banner.css">
    <link rel="stylesheet" href="css/footer.css">
    <style type="text/css">
        #shouye{
            color:#77f93e;
            font-weight: bold;
            border-bottom: 4px solid #77f93e;
        }    
    </style>
    <script src="js/jquery-1.7.min.js"></script>
	<script src="js/js.js"></script>
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
		<div id="search_box">
			<form id="search_form" method="get" action="searchGoods">
				<input type="text" id="s" name="search" placeholder="Search" class="swap_value" />
				<input type="submit" id="go" alt="Search" title="Search" value=""/>
			</form>
		</div>
	</div>
  	<%
  	System.out.println("user_id6:"+session.getAttribute("user_id"));
  	%>
<!--轮播banner -->
    <div class="ppt">
        <div class="ppt-con">
            <div class="li show"></div>
            <div class="li"></div>
            <div class="li"></div>            
        </div>
        <div class="cicles">
        </div>
    </div>
    
<!--肉色缤纷-->
    <div class="content01">
        <div class="binfen">
            <div class="binfen-top">
                <a class="title" href="shangcheng.jsp">“肉”色缤纷</a>
                <a class="more" href="shangcheng.jsp">查看更多 >></a>
            </div>
            <div class="binfen-bottom">
                <div class="binfen-bottom-in">
                    <a href="#"><img class="pic01" src="images/pic01-1.jpg"></a>
                    <div class="content01-title">
                        <a href="#">红宝石（Sedeveria pink ruby）</a>
                    </div>
                    <div class="content01-article">
                        <p>红宝石为景天属和拟石莲花属杂交的多肉植物。叶片细长匙状，前端较肥厚、斜尖，呈莲花状紧密排列，整体较包。红宝石叶片非常光滑，因此不管是秋冬季节变红或者夏天变绿的时候，颜色都比较醒目，看上去非常赏心悦目，因此也比较受欢迎。</p>
                    </div>
                </div>
                <div class="binfen-bottom-in">
                    <a href="#"><img class="pic01" src="images/pic01-2.jpg"></a>
                    <div class="content01-title">
                        <a href="#">熊童子（Cotyledon tomentosa）</a>
                    </div>
                    <div class="content01-article">
                        <p>熊童子为多年生肉质草本植物，植株多分枝，呈小灌木状，茎深褐色.叶表绿色，密生白色短毛。叶片肉质，匙形，密被白色绒毛，下部全缘，叶端具爪样齿，在阳光充足生长环境下，叶端齿会呈现红褐色，活像一只小熊的脚掌，很是可爱。</p>
                    </div>
                </div>
                <div class="binfen-bottom-in">
                    <a href="#"><img class="pic01" src="images/pic01-3.jpg"></a>
                    <div class="content01-title">
                        <a href="#">玉露（Haworthia cooperi）</a>
                    </div>
                    <div class="content01-article">
                        <p>玉露是龙舌兰目独尾草科多肉植物，是多肉植物中的“软叶系”品种。玉露植株玲珑小巧，种类丰富，叶色晶莹剔透，富于变化，如同有生命活中工艺品，非常可爱，是近年来人气较旺的小型多肉植物品种之一，有很多杂交品种。</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
<!--萌肉摄影-->
    <div class="content02">
        <div class="sheying">
            <div class="sheying-top">
                <a class="title" href="sheying.html">萌“肉”摄影</a>
                <a class="more" href="sheying.html">查看更多 >></a>
            </div>
            <div class="sheying-content">
                <div class="sheying-content-top">
                    <div class="sheying-left">
                        <img src="images/pic02-1.jpg">
                    </div>
                    <div class="sheying-mid">
                        <div class="sheying-mid-top">
                            <img src="images/pic02-2.jpg">
                        </div>
                        <div class="sheying-mid-bottom">
                            <img src="images/pic02-3.jpg">
                        </div>
                    </div>
                    <div class="sheying-right">
                        <img src="images/pic02-4.jpg">
                    </div>
                </div>
                <div class="sheying-bottom">
                    <img src="images/pic02-5.jpg">
                </div>
            </div>
        </div>
    </div>
    
<!--养肉攻略-->
    <div class="content03">
        <div class="yangzhi">
            <div class="yangzhi-top">
                <a class="title" href="yangzhi.html">养“肉”攻略</a>
                <a class="more" href="yangzhi.html">查看更多 >></a>
            </div>
            <div class="yangzhi-content1">
                <div class="yangzhi-content1-left">
                    <img src="images/pic03.png">
                </div>
                <div class="yangzhi-content1-right">
                    <div class="yangzhi-title">
                        <a>什么是叶插？如何进行叶插?</a>
                    </div>
                    <div class="yangzhi-article">
                        <a>
                        <p>叶插用于能自叶上发生不定芽及不定根种类，是一些多肉植物的主要繁殖方式。</p>
                        <p>（1）全叶插以完整叶片为插穗。依扦插位置分为两种。平置法：切去叶柄，将叶片平铺沙面上，以铁针或竹针固定于沙面上，下面与沙面紧接。另一方法为直插法，也称叶柄插法：将叶柄插入沙中，叶片立于沙面上，叶柄基部就发生不定芽。</p>
                        <p>（2）片叶插将一个叶片分切为数块，分别进行扦插，使每块叶片上形成不定芽。在各对侧脉下方自主脉处切开，再去除叶脉下方较薄部分，分别把每块叶片下端插入沙中，在主脉下端就可生出幼小植株。若叶片较长，可横切成5cm左右的小段，将下端插入沙中，自下端可生出幼株。</p>
                        </a>
                    </div>
                </div>
            </div>
            <div class="yangzhi-content2">
                <div class="yangzhi-content2-left">
                    <div class="yangzhi-title">
                        <a>刚买回的多肉植物如何上盆?</a>
                    </div>
                    <div class="yangzhi-article">
                        <a>
                            <p>1、时间：选者植株的生长期，9月-4月（30度-5度），是最佳时间段。</p>
                            <p>2、修根：修剪掉细小的根系和枯根，保留主根，主根中虚存着大量的养分，然后再放通风好散光处晾根3天，如果伤口大需要增加晾根时间。（夏季移植有风险，干土上盆，一周后的晴天上午少量浇水。）</p>
                            <p>3、上盆：采用湿土干种的方法上盆，摆放在通风良好、光照良好的环境里，5天后的晴天上午浇水，上午浇水的好处是植株表面的水分迅速挥发。浇水一定要求浇透，即看到盆地流出水。植株在浇水后的一周左右就能长出新根。</p>
                            <p>4、养护：根据植株对光照要求强弱选者合适的环境，由于秋季光照狠毒、很强，全日照种植环境还是需要继续遮荫60%。浇水的节奏需要自家的种植环境、土壤配比、花盆材质、通风量、天气等因素慢慢总结，不建议土壤表面铺一层观赏石，缺点是不利于观察表土的干湿度。</p>
                        </a>
                    </div>
                </div>
                <div class="yangzhi-content2-right">
                    <img src="images/pic03-2.png">
                </div>
            </div>
        </div>
    </div>

<!--footer-->
    <div class="moto">
        <div class="moto-Con">
            <span>从 现 在 起 ，你 是 它 们 的 主 人。</span>
        </div>
    </div>
    <div class="bottom" width=100%>
        <span>Copyright  ©爱多肉 All Rights Reserved.  Designed by 14卓工班小仙女.</span>
        <br>
        <a href="#">加入收藏</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">设为首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">联系我们</a>
    </div>                                   


</body>
</html>