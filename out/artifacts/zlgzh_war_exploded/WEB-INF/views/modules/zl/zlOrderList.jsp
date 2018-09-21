<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/zl/zlOrder/">订单列表</a></li>
		<shiro:hasPermission name="zl:zlOrder:edit"><li><a href="${ctx}/zl/zlOrder/form">订单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zlOrder" action="${ctx}/zl/zlOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>订单编号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>商品名称：</label>
				<form:input path="goodsName" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>收货地址：</label>
				<form:input path="addrName" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>购买人：</label>
				<form:input path="oppenId" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li><label>快递单号：</label>
				<form:input path="expressHm" htmlEscape="false" maxlength="500" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单编号</th>
				<th>商品名称</th>
				<th>商品规格</th>
				<th>商品价格</th>
				<th>商品数量</th>
				<th>商品总价</th>
				<th>商品总数</th>
				<th>收货地址</th>
				<th>配送方式</th>
				<th>购买人</th>
				<th>付款方式</th>
				<th>订单状态</th>
				<th>快递名称</th>
				<th>快递单号</th>
				<shiro:hasPermission name="zl:zlOrder:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zlOrder">
			<tr>
				<td><a href="${ctx}/zl/zlOrder/form?id=${zlOrder.id}">
					${zlOrder.orderId}
				</a></td>
				<td>
					${zlOrder.goodsName}
				</td>
				<td>
					${zlOrder.goodsSpe}
				</td>
				<td>
					${zlOrder.goodsPrice}
				</td>
				<td>
					${zlOrder.goodsNum}
				</td>
				<td>
					${zlOrder.goodsTotalPrice}
				</td>
				<td>
					${zlOrder.goodsTotalNum}
				</td>
				<td>
					${zlOrder.addrName}
				</td>
				<td>
					${zlOrder.receive}
				</td>
				<td>
					${zlOrder.oppenId}
				</td>
				<td>
					${zlOrder.payType}
				</td>
				<td>
					${zlOrder.status}
				</td>
				<td>
					${zlOrder.expressName}
				</td>
				<td>
					${zlOrder.expressHm}
				</td>
				<shiro:hasPermission name="zl:zlOrder:edit"><td>
    				<a href="${ctx}/zl/zlOrder/form?id=${zlOrder.id}">修改</a>
					<a href="${ctx}/zl/zlOrder/delete?id=${zlOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>