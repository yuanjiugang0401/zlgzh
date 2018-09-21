<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>支付</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/global.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/shopcar.css">
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
			<div class="header-title">订单结算</div>
		</header>
		<input type="hidden" value="${goodsId}" readonly="readonly" id="goods_id">
		<div class="address-box">
			<a href="${ctx}/page/orderAddressList?goodsId=${goodsId}">
				<div class="address">
					<div class="address-img">
						<img src="${ctxStatic}/images/icon_nav_city.png">
					</div>
				 <c:if test="${empty zlAddress}">
          			<h4 class="address-name"><span>点击添加收货地址</span></h4>
        		</c:if>
        		<c:if test="${!empty zlAddress}">
					<div class="address-text">
						<h4>${zlAddress.addrUser}&nbsp;&nbsp;${zlAddress.addrTel}</h4>
						<p>${zlAddress.addrCity}&nbsp;&nbsp;${zlAddress.addrName}</p>
					</div>
				</c:if>
				<div class="address-icon">
				<i class="iconfont icon-jiantou1"></i>
				</div>
				</div>
			</a>
		</div>
		<div class="list close">
			<c:forEach items="${cartList}" var="zlCart" varStatus="s">
				<div class="item">
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
									<p>数量：x${zlCart.goodsNum}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="lr-box">
			<div class="lr">
				<p>配送方式</p>
				<p>快递</p>
			</div>
		</div>
		<div class="lr-box">
			<div class="lr">
				<p>运费</p>
				<p>免运费</p>
			</div>
		</div>
		<div class="zf">
			<p>总金额：<i>￥</i><em>${totalPrice}</em></p>
			<a href="">微信支付</a>
		</div>
		<script type="text/javascript">
		function selectAddress(){
			 var goods_Id=$('#goods_id').val();
			 alert(goods_Id);
			 window.location.href = '${ctx}/page/addresslist?goodsId='+goods_id;
		}
		</script>
	</body>
</html>