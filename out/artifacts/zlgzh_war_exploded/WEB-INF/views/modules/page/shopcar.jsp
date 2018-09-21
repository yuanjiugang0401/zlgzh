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
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/weui.min.css">
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
			<input type="hidden" value="" id="goods_id" name="goods_id"/>
		</header>
		<div class="list" id="list">
			<c:forEach items="${cartList}" var="zlCart" varStatus="s">
				<div class="item">
					<div class="item-t">
						<a href="javascript:void(0)" onclick="del('${zlCart.goodsId}')"><img src="${ctxStatic}/images/icon-dele.png"></a>
					</div>
					<div class="item-cont">
						<div class="weui-media-box__hd check-w weui-cells_checkbox">
				          	<label class="weui-check__label" for="cart_pto${zlCart.id}">
				            	<div class="weui-cell__hd cat-check"><input class="weui-check" name="cartpro" id="cart_pto${zlCart.id}" onclick="selectbox()" type="checkbox" value="${zlCart.id}"><i class="weui-icon-checked"></i></div>
				          	</label>
				        </div>
						<div class="item-cont-l">
							<img src="${zlCart.goodsPic}">
						</div>
						<div class="item-cont-r">
							<h3 class="ellipsis">${zlCart.goodsName}</h3>
							<p>规格：${zlCart.goodsSpe}</p>
							<div class="price-calc">
								<div class="price">
								     <input type="hidden" value="${zlCart.goodsPrice}" name="goods_price" readonly="readonly" id="goods_price_${zlCart.id}"/>
								     <input type="hidden" value="${zlCart.goodsId}" id="goods_id_${zlCart.id}" readonly="readonly">
									￥<em id="em_price">${zlCart.goodsPrice}</em>
								</div>
								<div class="calc">
									<a href="javascript:void(0)" onclick="min('${zlCart.id}','${zlCart.goodsPrice}','${zlCart.goodsId}')"><i>-</i></a>
									<input  value="${zlCart.goodsNum}" type="text" readonly="readonly" name="goods_count" id="goods_count_${zlCart.id}"/>
									<a href="javascript:void(0)" onclick="plus('${zlCart.id}','${zlCart.goodsPrice}','${zlCart.goodsId}')"><i>+</i></a>
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
		   <div class="weui-media-box__hd check-w weui-cells_checkbox">
              	<label class="weui-check__label fl" for="all">
                	<div class="weui-cell__hd cat-check"><input class="weui-check"  id="all" type="checkbox"><i class="weui-icon-checked"></i></div>
                	<div class="weui-cell__bd">
	                    <p>全选</p>
	                </div>
              	</label>
            </div>
			<div class="set-l">
				<p>合计：<i>￥</i><em id="em_total_price">0.00</em></p>
			</div>
			<a id="order" class="set-link" href="javascript:;" onclick="order()">
				<p>去结算</p>
			</a>
		</div>
	<jsp:include page="footer1.jsp"></jsp:include>
	<script type="text/javascript">
	 //点加号
	 function plus(cart_id,goods_price,goods_id){
		 //获取每一行商品的数量
		 var goods_count=$('#goods_count_'+cart_id).val();
		 //减去之后的结果
		 var goods_num=parseInt(goods_count)+1;
		 $('#goods_count_'+cart_id).val(goods_num);
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
	    				$('#cart_num').text(rs.cart_num);
	    				selectbox();
	    			}else if(rs.rs_code==1005){
	    				Popbox.box("登录已失效，重新登录中，请稍后...");
						setTimeout('window.location.href=history.go(-1)',2000);
					}
	    		}
	    	})
	 }
	 //减号事件
	 function min(cart_id,goods_price,goods_id){
		 //获取每一行商品的数量
		 var goods_count=$('#goods_count_'+cart_id).val();
		 if(parseInt(goods_count)==1||parseInt(goods_count)<1){
			 Popbox.box('客官不能再少了');
			 return;
		 }
		 //减去之后的结果
		 var goods_num=parseInt(goods_count)-1;
		 $('#goods_count_'+cart_id).val(goods_num);
	   	$.ajax({
	    		url:'${ctx}/page/cartUpdate',
	    		type:'post',
	    		data:{
					'goodsId':goods_id,
					'goodsPrice':goods_price,
					'goodsNum':goods_num,
					's':0
				},
	    		success:function(rs){
	    			if(rs.rs_code==1){
	    				$('#cart_num').text(rs.cart_num);
	    				selectbox();
	    			}else if(rs.rs_code==1005){
	    				Popbox.box("登录已失效，重新登录中，请稍后...");
						setTimeout('window.location.href=history.go(-1)',2000);
					}
	    		}
	    });

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
	<script type="text/javascript">
		var checks = list.querySelectorAll('input[type = checkbox]');
		//全选
		all.onclick = function() {
			if(all.checked){
				var i = 0, len = checks.length;
				for(; i < len; i++) {
					var check = checks[i];
					check.checked = this.checked;
				};
				 <%--var totalPrice=0;
				 var goods_price = $("input[name=goods_price]");
				 var goods_count=$("input[name=goods_count]");
				 $.each(goods_price,function(i,item){
					 totalPrice = parseFloat(totalPrice) + parseFloat(item.value*goods_count[i].value);
				 });
				 $('#em_total_price').text(totalPrice.toFixed(2));--%>
				 selectbox();
			}else{
				var i = 0, len = checks.length;
				for(; i < len; i++) {
					var check = checks[i];
					check.checked = false;
				};
				$('#em_total_price').text('0.00');
				$('#goods_id').val('')
			}
		};
		//每一个复选框，都会执行的函数
		function selectbox(){
			  var goods_id="";
		      var cartpro = $("input[name=cartpro]:checked");
		      var totalprice = 0;
		      $.each(cartpro,function(c,item){
		    	  totalprice = parseFloat(totalprice) + parseFloat($('#goods_price_'+item.value).val()*$('#goods_count_'+item.value).val());
		    	  if(c==0){
		    		  goods_id = $('#goods_id_'+item.value).val();
				  }else{
					  goods_id=goods_id+','+$('#goods_id_'+item.value).val();
				  }
			  })
		      $('#em_total_price').text(totalprice.toFixed(2));
		      if(checks.length==cartpro.length){
		    	  all.checked = true;
			  }else{
				  all.checked = false;
			  }
		      $('#goods_id').val(goods_id);
		}
		function order(){
			 var goods_id=$('#goods_id').val();
			 var cartpro = $("input[name=cartpro]:checked");
			 if(cartpro.length==0){
				 Popbox.box('您还未选中任何商品')
			 }else{
				 window.location.href = '${ctx}/page/cartOrder?goodsId='+goods_id;
			 }
		}
		</script>
	</body>
</html>