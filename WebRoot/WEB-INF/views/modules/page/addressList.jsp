<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>地址管理</title>
		<meta name="viewport"
			content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/normalize.css">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/global.css">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/address.css">
	</head>
	<body>
		<body class="body">
			<header class="header">
			<div class="header-img">
				<img src="${ctxStatic}/images/icon-back.png">
			</div>
			<div class="header-title">
				地址管理
			</div>
			</header>
			<ul class="list">
				<c:forEach items="${addressList}" var="address" varStatus="s">
					<li class="item">
						<div class="item-t">
							<h4>
								${address.addrUser}&nbsp;&nbsp;${fn:substring(address.addrTel, 0, 3)}****${fn:substring(address.addrTel, 7, 11)}
							</h4>
							<a href="${ctx}/page/addressInfo?id=${address.id}"><img src="${ctxStatic}/images/icon-edit.png">
							</a>
						</div>
						<p>
							${address.addrCity}&nbsp;&nbsp;${address.addrName}
						</p>
						<c:if test="${address.isDefault==1}"><span>默认地址</span></c:if>
					</li>
				</c:forEach>
			</ul>
			<div class="link">
				<a href="${ctx}/page/addressInfo">添加收货地址</a>
			</div>
		</body>
</html>