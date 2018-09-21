<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>地址管理</title>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/normalize.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/global.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/jquery-weui.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/address_edit.css">
		<script type="text/javascript" src="${ctxStatic}/jquery/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="${ctxStatic}/layer-v3.1.1/layer/layer.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/popbox.js"></script>
        <script type="text/javascript"  src="${ctxStatic}/js/jquery-weui.js"></script>
        <script type="text/javascript" src="${ctxStatic}/js/city-picker.js"></script>
	</head>
	<body class="body">
		<header class="header">
			<div class="header-img">
				<img src="${ctxStatic}/images/icon-back.png">
			</div>
			<div class="header-title">编辑地址</div>
		</header>
		<div class="weui-cells weui-cells_form">
            <input type="hidden" value="${address.id}" id="addrId">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">收货人</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input"  placeholder="请填写收货人" type="text" value="${address.addrUser}" id="addrUser">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">手机号</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="请填写手机号" type="tel" pattern="[0-9]*" value="${address.addrTel}" id="addrTel">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">所在地区</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="请选择地区" type="text" id="address" value="${address.addrCity}" readonly="readonly">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">详细地址</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" placeholder="请填写详细地址" type="text" value="${address.addrName}" id="addrName">
                </div>
            </div>
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">设为默认地址</div>
                <div class="weui-cell__ft">
                    <c:if test="${address.isDefault=='1'}">
                       <input class="weui-switch" name="addrpro" type="checkbox" checked="checked" value="${address.isDefault}" id="isDefault" onclick="cho()">
                    </c:if>
                    <c:if test="${empty address.isDefault||address.isDefault==0}">
                       <input class="weui-switch" name="addrpro" type="checkbox" value="${address.isDefault}" id="isDefault" onclick="cho()">
                    </c:if>
                </div>
            </div>
        </div>
        <div class="link">
			<a href="javascript:;" onclick="add()">${not empty address.id?'修改':'添加'}地址</a>
			<c:if test="${not empty address.id}">
			   <a class="red" href="javascript:;" onclick="del(${address.id})">删除此地址</a>
			</c:if>
		</div>
        
        <script type="text/javascript">
        $("#address").cityPicker({
            title: "选择所在地区",
            onChange: function (picker, values, displayValues) {
              console.log(values, displayValues);
            }
          });
        function cho() {
    		var isDefault = $('#isDefault').val();
    		if (isDefault == 1) {
    			$('#isDefault').val(0);
    		} else {
    			$('#isDefault').val(1);
    		}
    	}
    	function add(){
        	var addrUser=$('#addrUser').val();
        	if(addrUser==""){
        		Popbox.box("请填写收货人");return;
			}
        	var addrTel=$('#addrTel').val();
        	if(addrTel==""){
        		Popbox.box("请填写手机号");return;
			}
        	var address=$('#address').val();
        	if(address==""){
        		Popbox.box("请选择地区");return;
			}
        	var addrName=$('#addrName').val();
        	if(addrName==""){
        		Popbox.box("请填写详细地址");return;
			}
			var isDefault=$('#isDefault').val();
           // if($("input[name=addrpro]:checked")){
              //  isDefault=1;
           // }else{
            //    isDefault=0;
           // }
            var id=$('#addrId').val();
        	$.ajax({
	    		url:'${ctx}/page/addressAdd',
	    		type:'post',
	    		data:{
					'addrUser':addrUser,
					'addrTel':addrTel,
					'addrCity':address,
					'addrName':addrName,
                    'isDefault':isDefault,
                    'id':id
				},
	    		success:function(rs){
	    			if(rs.rs_code==1){
	    				window.location.href = document.referrer;
	    			}else if(rs.rs_code==1005){
	    				Popbox.box("登录已失效，重新登录中，请稍后...");
						setTimeout('window.location.href=history.go(-1)',2000);
					}
	    		}
	    })
        }
    	//删除事件
   	 function del(addressId){
   	    	layer.confirm('确定要删除？', {
   	    		 skin: 'layui-layer-molv',
   	             btn: ['确定','取消'] //按钮
   	    		}, function(){
   	    			$.ajax({
   	    	    		url:'${ctx}/page/addressDel',
   	    	    		type:'post',
   	    	    		data:{
   	    					'id':addressId
   	    				},
   	    	    		success:function(rs){
   	    					if(rs.rs_code==1){
   	    						window.location.href = document.referrer;
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