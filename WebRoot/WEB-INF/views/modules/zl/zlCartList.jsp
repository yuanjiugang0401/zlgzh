<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>购物车管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/zl/zlCart/">购物车列表</a></li>
		<shiro:hasPermission name="zl:zlCart:edit"><li><a href="${ctx}/zl/zlCart/form">购物车添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zlCart" action="${ctx}/zl/zlCart/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商品名称：</label>
				<form:input path="goodsName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>购买者：</label>
				<form:input path="oppenId" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>商品规格</th>
				<th>商品价格</th>
				<th>商品数量</th>
				<th>商品总价</th>
				<th>购买者</th>
				<th>商品状态</th>
				<shiro:hasPermission name="zl:zlCart:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zlCart">
			<tr>
				<td><a href="${ctx}/zl/zlCart/form?id=${zlCart.id}">
					${zlCart.goodsName}
				</a></td>
				<td>
					${zlCart.goodsPic}
				</td>
				<td>
					${zlCart.goodsSpe}
				</td>
				<td>
					${zlCart.goodsPrice}
				</td>
				<td>
					${zlCart.goodsNum}
				</td>
				<td>
					${zlCart.goodsTotal}
				</td>
				<td>
					${zlCart.oppenId}
				</td>
				<td>
					${zlCart.status}
				</td>
				<shiro:hasPermission name="zl:zlCart:edit"><td>
    				<a href="${ctx}/zl/zlCart/form?id=${zlCart.id}">修改</a>
					<a href="${ctx}/zl/zlCart/delete?id=${zlCart.id}" onclick="return confirmx('确认要删除该购物车吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>