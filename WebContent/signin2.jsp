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
    
    <title>My JSP 'signin2.jsp' starting page</title>
    
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
     <link rel="stylesheet" href="css/register.css">
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
    <script type="text/javascript" src="js/jquery-1.7.min.js"></script>
    <script type="text/javascript">
    var xmlHttpRequest;
    var username;
    var country;
    var city;
    var address;
    var code;
    
    function initXMLHttpRequest() {
		if (xmlHttpRequest) {
			return;
		}
		if (window.ActiveXObject) {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttpRequest = new XMLHttpRequest();
		}
	}
    
    function fSend(method, url, data, callback) {
		if (method == "POST") {
			xmlHttpRequest.open(method, url, true);
			xmlHttpRequest.onreadystatechange = callback;
			xmlHttpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttpRequest.send(data);	
		}
	}
	
	function register(){
		username = document.getElementById("username").value;
    	country = document.getElementById("country").value;
    	city = document.getElementById("city").value;
    	address = document.getElementById("address").value;
    	code = document.getElementById("code").value;
    	
    	if (username == "") {
			alert("收件人不能为空!");
			username.focus();
			return false;
		} 
		
		if (country == "") {
			alert("省不能为空!");
			country.focus();
			return false;
		}
		
		if (city == "") {
			alert("城市不能为空!");
			city.focus();
			return false;
		}
		
		if (address == "") {
			alert("收货地址不能为空!");
			address.focus();
			return false;
		}
		
		if (code == "") {
			alert("邮政编码不能为空!");
			code.focus();
			return false;
		}
		
		if (code.length != 6) {
			alert("请输入6位邮政编码!");
			code.focus();
			return false;
		}
    	
		initXMLHttpRequest();
		fSend("POST", "register2", "username="+username+"&province="+country+"&city="+city+"&address="+address+"&code="+code, fCallback);
	}
    
    function fCallback() {
		if (xmlHttpRequest.readyState == 4) {//4是后台给我们的反馈
			if (xmlHttpRequest.status == 200) {//200代表后台正常处理成功
				if(xmlHttpRequest.responseText == 0){
					window.location.href='signin3.jsp'
				}
			}
		}
	}
    
    </script>
   
    <script type="text/javascript"> 
        function showCity( ) 
        {   
            var ctryName=document.myform.country.value;  
            var ctryType = new Array( );   
            ctryType['安徽']=['合肥', '芜湖', '蚌埠', '淮南', '马鞍山', '淮北','铜陵','安庆','黄山', '滁州', '阜阳', '宿州', '六安', '亳州', '池州', '宣城'];  
            ctryType['北京']=['东城', '西城', '朝阳', '丰台', '石景山', '海淀', '门头沟', '房山', '通州', '顺义', '昌平', '大兴', '怀柔', '平谷', '密云', '延庆']; 
            ctryType['重庆']=['万州', '涪陵', '渝中', '大渡口', '江北', '沙坪坝', '九龙坡', '南岸', '北碚', '万盛', '双桥', '渝北', '巴南', '黔江', '长寿', '綦江', '潼南', '铜梁', '大足', '荣昌', '璧山', '梁平', '城口', '丰都', '垫江', '武隆', '忠县', '开县', '云阳', '奉节', '巫山', '巫溪', '石柱', '秀山', '酉阳', '彭水', '江津', '合川', '永川', '南川'];    
            ctryType['福建']=['福州', '厦门', '莆田', '三明', '泉州', '漳州', '南平', '龙岩','宁德']; 
            ctryType['甘肃']=['兰州', '嘉峪关', '金昌', '白银', '天水', '武威', '张掖', '平凉', '酒泉', '庆阳', '定西', '陇南', '临夏', '甘南'];
            ctryType['广东']=['广州', '韶关', '深圳', '珠海', '汕头', '佛山', '江门', '湛江', '茂名', '肇庆', '惠州', '梅州', '汕尾', '河源', '阳江', '清远', '东莞', '中山', '潮州', '揭阳', '云浮'];
            ctryType['广西']=['南宁', '柳州', '桂林', '梧州', '北海', '防城港', '钦州', '贵港', '玉林', '百色', '贺州', '河池', '来宾', '崇左'];
            ctryType['贵州']=['贵阳', '六盘水', '遵义', '安顺', '铜仁', '黔西南', '毕节', '黔东南', '黔南'];
            ctryType['海南']=['海口', '三亚', '三沙', '五指山', '琼海', '儋州', '文昌', '澄迈', '临高', '白沙', '昌江', '乐东', '陵水', '保亭', '琼中'];
            ctryType['河北']=['石家庄', '唐山', '秦皇岛', '邯郸', '邢台', '保定', '张家口', '承德', '沧州', '廊坊', '衡水'];
            ctryType['黑龙江']=['哈尔滨', '齐齐哈尔', '鸡西', '鹤岗', '双鸭山', '大庆', '伊春', '佳木斯', '七台河', '牡丹江', '黑河', '绥化', '大兴安岭'];
            ctryType['河南']=['郑州', '开封', '洛阳', '平顶山', '安阳', '鹤壁', '新乡', '焦作', '济源', '濮阳', '许昌', '漯河', '三门峡', '南阳', '商丘', '信阳', '周口', '驻马店'];   
            ctryType['湖北']=['武汉', '黄石', '十堰', '宜昌', '襄阳', '鄂州', '荆门', '孝感', '荆州', '黄冈', '咸宁', '随州', '恩施', '仙桃', '潜江', '天门', '神农架'];   
            ctryType['湖南']=['长沙', '株洲', '湘潭', '衡阳', '邵阳', '岳阳', '常德', '张家界', '益阳', '郴州', '永州', '怀化', '娄底', '湘西'];   
            ctryType['江苏']=['南京', '无锡', '徐州', '常州', '苏州', '南通', '连云港', '淮安', '盐城', '扬州', '镇江', '泰州', '宿迁']; 
            ctryType['江西']=['南昌', '景德镇', '萍乡', '九江', '新余', '鹰潭', '赣州', '吉安', '宜春', '抚州', '上饶'];
            ctryType['吉林']=['长春', '吉林', '四平', '辽源', '通化', '白山', '松原', '白城', '延边朝鲜族'];
            ctryType['辽宁']=['沈阳', '大连', '鞍山', '抚顺', '本溪', '丹东', '锦州', '营口', '阜新', '辽阳', '盘锦', '铁岭', '朝阳', '葫芦岛'];
            ctryType['内蒙古']=['呼和浩特', '包头', '乌海', '赤峰', '通辽', '鄂尔多斯', '呼伦贝尔', '巴彦淖尔', '乌兰察布', '兴安', '锡林郭勒', '阿拉善'];
            ctryType['宁夏']=['银川', '石嘴山', '吴忠', '固原', '中卫'];
            ctryType['青海']=['西宁', '海东', '海北', '黄南', '海南藏族', '果洛', '玉树', '海西'];
            ctryType['山东']=['济南', '青岛', '淄博', '枣庄', '东营', '烟台', '潍坊', '济宁', '泰安', '威海', '日照', '莱芜', '临沂', '德州', '聊城', '滨州', '菏泽'];
            ctryType['上海']=['黄浦', '徐汇', '长宁', '静安', '普陀', '虹口', '杨浦', '闵行', '宝山', '嘉定', '浦东', '金山', '松江', '青浦', '奉贤', '崇明'];
            ctryType['山西']=['太原', '大同', '阳泉', '长治', '晋城', '朔州', '晋中', '运城', '忻州', '临汾', '吕梁'];
            ctryType['陕西']=['西安', '铜川', '宝鸡', '咸阳', '渭南', '延安', '汉中', '榆林', '安康', '商洛'];
            ctryType['四川']=['成都', '自贡', '攀枝花', '泸州', '德阳', '绵阳', '广元', '遂宁', '内江', '乐山', '南充', '眉山', '宜宾', '广安', '达州', '雅安', '巴中', '资阳', '阿坝', '甘孜', '凉山'];
            ctryType['天津']=['和平', '河东', '河西', '南开', '河北', '红桥', '东丽', '西青', '津南', '北辰', '武清', '宝坻', '滨海', '宁河', '静海', '蓟县'];
            ctryType['新疆']=['乌鲁木齐', '克拉玛依', '吐鲁番', '哈密', '昌吉', '博尔塔拉', '巴音郭楞', '阿克苏', '克孜勒苏柯尔克孜', '喀什', '和田', '伊犁', '塔城', '阿勒泰', '石河子', '阿拉尔', '图木舒克', '五家渠'];
            ctryType['西藏']=['拉萨', '昌都', '山南', '日喀则', '那曲', '阿里', '林芝'];
            ctryType['云南']=['昆明', '曲靖', '玉溪', '保山', '昭通', '丽江', '普洱', '临沧', '楚雄', '红河', '文山', '西双版纳', '大理', '德宏', '怒江', '迪庆'];
            ctryType['浙江']=['杭州', '宁波', '温州', '嘉兴', '湖州', '绍兴', '金华', '衢州', '舟山', '台州', '丽水'];

            document.myform.cityList.options.length=0; 
            for (var j=0;j<ctryType[ctryName].length;j++)
            {  
                document.myform.cityList.options.add(new Option(ctryType[ctryName][j], ctryType[ctryName][j])); 
            }    
            document.myform.cityList.selectedIndex=0;  
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
                    <div class="zhuce-head-left1"><a>1.设置用户名</a></div>
                    <div class="zhuce-head-mid1"><a>2.设置账户信息</a></div>
                    <div class="zhuce-head-right1"><a>3.注册成功</a></div>
                </div>
            </div>
            <div class="zhuce-body">
            <div class="login-textbox">
                            <div class="txt-img"></div>
                            <input class="input-txt" id="username" name="username" type="text" style="width:408px;height:42px;border:none;font-size: 16px;float:left" placeholder="用户名">
                        </div>
                    <div class="login-input">
                        <div class="login-textbox">
                            <div class="txt-img"></div>
                            <div class="province">省份：
                            </div>
                            <div class="select_province">
                                <FORM action="" method="get" name="myform" >  
                             
                                <select name="country" id="country" onChange="showCity()" class="select">  
                                    <option>--请选择省--</option>   
                                    <option value="安徽">安徽</option>   
                                    <option value="北京">北京</option>    
                                    <option value="重庆">重庆</option> 
                                    <option value="福建">福建</option>
                                    <option value="甘肃">甘肃</option>
                                    <option value="广东">广东</option>
                                    <option value="广西">广西</option>
                                    <option value="贵州">贵州</option>
                                    <option value="海南">海南</option>
                                    <option value="河北">河北</option>
                                    <option value="黑龙江">黑龙江</option>
                                    <option value="河南">河南</option>
                                    <option value="湖北">湖北</option>
                                    <option value="湖南">湖南</option>
                                    <option value="江苏">江苏</option>
                                    <option value="江西">江西</option>
                                    <option value="吉林">吉林</option>  
                                    <option value="辽宁">辽宁</option> 
                                    <option value="内蒙古">内蒙古</option> 
                                    <option value="宁夏">宁夏</option> 
                                    <option value="青海">青海</option> 
                                    <option value="山东">山东</option> 
                                    <option value="上海">上海</option> 
                                    <option value="山西">山西</option> 
                                    <option value="陕西">陕西</option> 
                                    <option value="四川">四川</option> 
                                    <option value="天津">天津</option>
                                    <option value="新疆">新疆</option> 
                                    <option value="西藏">西藏</option>
                                    <option value="云南">云南</option> 
                                    <option value="浙江">浙江</option>  
                               </select>       
                            </div>
                        </div>
                        <div class="login-textbox">
                            <div class="txt-img"></div>
                            <div class="city">
                                市区:
                            </div>
                            <div class="select_city">
                                <select name="cityList" id="city" class="select">
                                           <option>--请选择所在城市或地区--</option>
                                        </select>
                            </div>
                        </div>
                        <div class="login-textbox">
                            <div class="txt-img"></div>
                            <input class="input-txt" id="address" name="address" type="text" style="width:408px;height:42px;border:none;font-size: 16px;float:left" placeholder="具体地址">
                        </div>
                         <div class="login-textbox">
                            <div class="txt-img"></div>
                            <input class="input-txt" id="code" name="code" type="text" style="width:408px;height:42px;border:none;font-size: 16px;float:left" placeholder="邮政编码">
                        </div>
                    <div>
                    <div style="height:50px"></div>
                    <div class="login-btn">
                        <!-- <a href="signin3.jsp"> -->
                        <input type="button" id="button" class="button" value="提  交" onclick="register()"><!-- </a> -->
                    </div>      
            </div>
        </div>
    </div>
</div>
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
