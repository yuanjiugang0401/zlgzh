<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
		<title>我的</title>
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/normalize.css">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/home.css">
		<link rel="stylesheet" type="text/css"
			href="${ctxStatic}/css/footer.css">
		<script type="text/javascript" src="${ctxStatic}/js/autosize.js"></script>
	</head>
	<body>
		<header class="user">
		<div class="user-img">
			<img src="${zlUser.headImg}">
		</div>
		<div class="user-text">
			<h4>
				${zlUser.realname}
			</h4>
		</div>
		</header>
		<div class="content">
			<div class="list">
				<div class="list-t">
					<div class="list-t-l">
						<div class="list-icon">
							<img src="${ctxStatic}/images/center-icon-order-all.png">
						</div>
						<p>
							全部订单
						</p>
					</div>
					<div class="icon-arrow">
						<i class="iconfont icon-jiantou1"></i>
					</div>
				</div>
				<ul class="item-box">
					<li class="item">
						<a href=""> <img src="${ctxStatic}/images/center-icon-order-dfk.png">
							<p>
								待付款
							</p> <span>2</span> </a>
					</li>
					<li class="item">
						<a href=""> <img src="${ctxStatic}/images/center-icon-order-dfh.png">
							<p>
								待发货
							</p> <span>2</span> </a>
					</li>
					<li class="item">
						<a href=""> <img src="${ctxStatic}/images/center-icon-order-dsh.png">
							<p>
								待收货
							</p> <span>2</span> </a>
					</li>
				</ul>
			</div>
			<div class="list">
				<div class="list-t">
					<div class="list-t-l">
						<div class="list-icon">
							<img src="${ctxStatic}/images/center-icon-jk.png">
						</div>
						<p>
							我的小金库
						</p>
					</div>
					<div class="icon-arrow">
						<i class="iconfont icon-jiantou1"></i>
					</div>
				</div>
				<ul class="item-box">
					<li class="item">
						<a href="">
							<h4>
								800.0
							</h4>
							<p>
								账户总额
							</p> </a>
					</li>
				</ul>
			</div>
			<div class="list">
				<a href="${ctx}/page/addresslist">
					<div class="list-t">
						<div class="list-t-l">
							<div class="list-icon">
								<img src="${ctxStatic}/images/center-icon-dz.png">
							</div>
							<p>
								地址管理
							</p>
						</div>
						<div class="icon-arrow">
							<i class="iconfont icon-jiantou1"></i>
						</div>
					</div>
				</a>
			</div>
			<div class="list">
				<div class="list-t">
					<div class="list-t-l">
						<div class="list-icon">
							<img src="${ctxStatic}/images/center-icon-set.png">
						</div>
						<p>
							个人资料
						</p>
					</div>
					<div class="icon-arrow">
						<i class="iconfont icon-jiantou1"></i>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer2.jsp"></jsp:include>
	</body>
</html>