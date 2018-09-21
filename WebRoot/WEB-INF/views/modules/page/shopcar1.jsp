<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>购物车</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/global.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/shopcar.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/footer.css">
		<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/popbox.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/autosize.js"></script>
	</head>
		<body>
		<header class="header">
			<div class="header-img">
				<img src="${ctxStatic}/images/icon-back.png">
			</div>
			<div class="header-title">购物车</div>
		</header>
		<div class="list">
		<c:forEach items="${cartList}" var="zlCart" varStatus="s">
			<div class="item">
				<div class="item-t">
					<a href="javascript:void(0)" onclick="del('${zlCart.goodsId}')"><img src="${ctxStatic}/images/icon-dele.png"></a>
				</div>
				<div class="item-cont">
					<div class="item-cont-l">
						<img src="${zlCart.goodsPic}">
					</div>
					<div class="item-cont-r">
						<h3 class="ellipsis">${zlCart.goodsName}</h3>
						<p>规格：${zlCart.goodsSpe}</p>
						<div class="price-calc">
							<div class="price">
								￥<em>${zlCart.goodsPrice}</em>
							</div>
							<div class="calc">
								<a href="javascript:void(0)" onclick="min('${zlCart.goodsId}','${zlCart.goodsPrice}','${s.count}')"><i>-</i></a>
								<input id="goods_num${s.count}" value="${zlCart.goodsNum}" type="text" readonly="readonly"/>
								<a href="javascript:void(0)" onclick="plus('${zlCart.goodsId}','${zlCart.goodsPrice}','${s.count}')"><i>+</i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<input type="hidden" value="${tnum}" id='tnum1'>
    <input type="hidden" value="${tprice}" id='tprice1'>
		<div class="settle">
			<div class="set-l">
				<p>合计：<i>￥</i><em id="em_price">${tprice}</em></p>
			</div>
			<a class="set-link" href="">
				<p>去结算</p>
			</a>
		</div>
	<jsp:include page="footer1.jsp"></jsp:include>
	<script type="text/javascript">
	 //点加号
	 function plus(goods_id,goods_price,sort){
		 //获取单个商品总数
		 var goods_num1=$('#goods_num'+sort).val();
		 //将单个商品总数转为整数型
		 var goods_num=parseInt(goods_num1)+1;
		 //获取所有商品总数
		 var tnum1 = $('#tnum1').val();
		 //获取所有商品总价格
	     var tprice1 = $('#tprice1').val();
	     var tnum = parseInt(tnum1)+1;
	     var cart_num = parseInt(cart_num)+1;
	     var tprice=(parseFloat(tprice1)+parseFloat(goods_price)).toFixed(2);
	     $.ajax({
	    		url:'${ctx}/page/cartUpdate',
	    		type:'post',
	    		//data:'goods_id='+goods_id+'&goods_price='+goods_price+'&goods_num='+goods_num+'&s=1',
	    		data:{
					'goodsId':goods_id,
					'goodsPrice':goods_price,
					'goodsNum':goods_num,
					's':1
				},
	    		success:function(rs){
	    				if(rs.rs_code==1){
	    				$('#cart_num').text(rs.cart_num)
	    				$('#tnum1').val(tnum);
	    		    	$('#tprice1').val(tprice);
	    		    	$('#goods_num'+sort).val(goods_num);
	    		    	$('#em_price').text(tprice);
	    			}else if(rs.rs_code==1005){
	    				Popbox.box("登录已失效，重新登录中，请稍后...");
						setTimeout('window.location.href=history.go(-1)',2000);
					}
	    		}
	    	})
	 }
	 //减号事件
	 function min(goods_id,goods_price,sort){
		var goods_num1=$('#goods_num'+sort).val();
	    if(parseInt(goods_num1)==1||parseInt(goods_num1)<1){
		    return;
	    }
	    var goods_num=parseInt(goods_num1)-1;
	    var goods_total  = goods_num*goods_price;
		var tnum1 = $('#tnum1').val();
    	var tprice1 = $('#tprice1').val();
    	var tnum = parseInt(tnum1)-1;
    	var tprice = (parseFloat(tprice1)-parseFloat(goods_price)).toFixed(2);
	   	$.ajax({
	    		url:'${ctx}/page/cartUpdate',
	    		type:'post',
	    		//data:'goods_id='+goods_id+'&goods_price='+goods_price+'&goods_num='+goods_num+'&s=1',
	    		data:{
					'goodsId':goods_id,
					'goodsPrice':goods_price,
					'goodsNum':goods_num,
					's':0
				},
	    		success:function(rs){
	    				if(rs.rs_code==1){
	    				$('#cart_num').text(rs.cart_num)
	    				$('#tnum1').val(tnum);
	    		    	$('#tprice1').val(tprice);
	    		    	$('#goods_num'+sort).val(goods_num);
	    		    	$('#em_price').text(tprice);
	    			}else if(rs.rs_code==1005){
	    				Popbox.box("登录已失效，重新登录中，请稍后...");
						setTimeout('window.location.href=history.go(-1)',2000);
					}
	    		}
	    })
	}
	//删除事件
	 function del(goods_id){
	    	cart_num = parseInt(cart_num)-1;
	    	layer.confirm('确定要删除？', {
	    		 skin: 'layui-layer-molv',
	             btn: ['确定','取消'] //按钮
	    		}, function(){
	    			$.ajax({
	    	    		url:'${ctx}/page/cartDel',
	    	    		type:'post',
	    	    		data:{
	    					'goodsId':goods_id
	    				},
	    	    		success:function(rs){
	    					if(rs.rs_code==1){
	    					$('#cart_num').text(rs.cart_num);
	    	    				location.reload();
	    	    			}
	    					else if(rs.rs_code==1005){
	    						Popbox.box("登录已失效，重新登录中，请稍后...");
	    						setTimeout('window.location.href=history.go(-1)',2000);
	    					}else{
	    						Popbox.box('系统故障');
	    	    			}
	    	    		}
	    	    	})
	    		}, function(){
		    		
	    	});
	   }
	</script>
	</body>
</html>