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
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jquery-weui.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/global.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/shopcar.css">
		<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/popbox.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/jquery-weui.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/autosize.js"></script>
	</head>
		<body>
		<header class="header">
			<div class="header-img">
				<img src="${ctxStatic}/images/icon-back.png">
			</div>
			<div class="header-title">订单结算</div>
		</header>
		<!--商品-->
		<input type="hidden" value="${goodsId}" readonly="readonly" id="goods_id">
		<input type="hidden" value="${zlAddress.id}" readonly="readonly" id="addr_id">
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
		<div class="lr-box" onclick="show_pay_way();">
			<div class="lr">
				<p>支付方式</p>
				<span class="weui-cell__ft" style="font-size: 14px" id="pay_way_name">微信支付</span>
			</div>
		</div>
		<!-- 支付方式 -->
		<div id="show_pay_way" class='weui-popup__container popup-bottom' style="z-index: 600;">
			<div class="weui-popup__overlay" style="opacity: 1;"></div>
			<div class="weui-popup__modal">
				<div class="modal-content">
					<div class="weui-msg" style="padding-top: 0;">

						<div class="weui-cells weui-cells_radio">

							<label class="weui-cell weui-check__label" for="c1" >
								<div class="weui-cell__bd">
									<p>微信在线支付<span style="float: right;"><i>￥</i>${totalPrice}元</span></p>
								</div>
								<div class="weui-cell__ft">
									<input type="radio" <c:if test="${pay_way == 1}">checked</c:if> class="weui-check" name="radio1"
										   value=""  id="c1" onclick="window.location.href='javascript:pay_way_name(\'1\');'">
									<span class="weui-icon-checked"></span>
								</div>
							</label>
							<label class="weui-cell weui-check__label" for="c2" >
								<div class="weui-cell__bd">
									<p>账户余额支付<span style="float: right;"><i>￥</i>${totalPrice}元</span></p>
								</div>
								<div class="weui-cell__ft">
									<input type="radio" <c:if test="${pay_way == 2}">checked</c:if> class="weui-check" name="radio1"
										   value=""  id="c2" onclick="window.location.href='javascript:pay_way_name(\'2\');'">
									<span class="weui-icon-checked"></span>
								</div>
							</label>

						</div>
						<div class="weui-msg__opr-area">
							<p class="weui-btn-area">
								<a href="javascript:;"
								   class="weui-btn weui-btn_default close-popup">关闭</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" value="${pay_way}" id="pay_way"/>
		</div>
		<div class="zf">
			<p>总金额：<i>￥</i><em>${totalPrice}</em></p>
			<a href="javascript:payorder()">确认支付</a>
		</div>
		<script type="text/javascript">
			var index ;
		function selectAddress(){
			 var goods_Id=$('#goods_id').val();
			 window.location.href = '${ctx}/page/addresslist?goodsId='+goods_id;
		}
        function show_pay_way(){
            $('#show_pay_way').attr('class','weui-popup__container popup-bottom weui-popup__container--visible');
        }
        function pay_way_name(pay_way){
            var pay_way_name = '';
            if(pay_way==1){
                pay_way_name = '微信支付';
            }else if(pay_way==2){
                pay_way_name = '账户余额支付';
            }
            $('#pay_way').val(pay_way);
            $('#pay_way_name').text(pay_way_name);
            $('#show_pay_way').attr('class','weui-popup__container popup-bottom');
        }
        function payorder() {
            var goods_id=$('#goods_id').val();
            var addr_id=$('#addr_id').val();
            var pay_way=$('#pay_way').val();
            index=layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
            $.ajax({
                url:'${ctx}/page/payOrder',
                type:'post',
                data:{
                    'goodsId':goods_id,
					'addrId':addr_id,
					'payType':pay_way
				},
                success:function(rs){
                    layer.close(index);
                    if(rs.rs_code=='1'){
                        if(pay_way=='1'){
                            callpay(rs.appId,rs.timeStamp,rs.nonceStr,rs.package,rs.paySign);
						}else{

						}
					}else{
                        Popbox.box(rs.message);
					}
                }
            })
        }
        function callpay(appId,timeStamp,nonceStr,package,paySign){
            WeixinJSBridge.invoke('getBrandWCPayRequest',{
                "appId" : appId,"timeStamp" : timeStamp, "nonceStr" : nonceStr, "package" : package,"signType" : "MD5", "paySign" :paySign
            },function(res){
                WeixinJSBridge.log(res.err_msg);
                if(res.err_msg == "get_brand_wcpay_request:ok"){
                    $.toast("支付成功!");
                    setTimeout('window.location.href=""',2000);
                }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                    $.toast("支付已取消!");
                    setTimeout('window.location.href="order_list"',2000);
                }else{
                    $.alert("支付失败!",'微信支付');
                }
            })
        }
		</script>
	</body>
</html>