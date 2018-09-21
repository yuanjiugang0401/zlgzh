<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>首页</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/index.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/footer.css">
		<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/popbox.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/autosize.js"></script>
	</head>
	<body>
		<ul class="list">
		 <c:forEach items="${goodsList}" var="zlGoods">
			<li class="item">
				<img src="${fn:split(zlGoods.goodsPic,"|")[0]}">
				<div class="item-text">
					<p>${zlGoods.goodsName}</p>
					<span>¥${zlGoods.goodsPrice}</span>
					<div class="cart">
						<a href="javascript:;" onclick="addcart('${zlGoods.id}','${zlGoods.goodsName}','${fn:split(zlGoods.goodsPic,'|')[0]}','${zlGoods.goodsSpe}','${zlGoods.goodsPrice}')"><i class="iconfont icon-gouwuche"></i></a>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
		<script type="text/javascript">
		function addcart(goods_id,goods_name,goods_pic,goods_spe,goods_price){
			$.ajax({
				url:'${ctx}/page/cartInsert',
				type:'post',
				data:{
					'goodsId':goods_id,
					'goodsName':goods_name,
					'goodsPic':goods_pic,
					'goodsSpe':goods_spe,
					'goodsPrice':goods_price,
					'goodsNum':1
				},
				success:function(rs){
					if(rs.rs_code==1){
						$('#cart_num').text(rs.cart_num);
						Popbox.box("已加入购物车！");
					}
					else if(rs.rs_code==1005){
						Popbox.box("登录已失效，重新登录中，请稍后...");
						setTimeout('window.location.href=history.go(-1)',2000);
					}
					else{
						Popbox.box("加入购物车失败！");
					}
				}
			})
	    }
		</script>
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>