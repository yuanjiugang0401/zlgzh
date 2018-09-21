<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地址管理</title>
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
		<li class="active"><a href="${ctx}/zl/zlAddress/">地址列表</a></li>
		<shiro:hasPermission name="zl:zlAddress:edit"><li><a href="${ctx}/zl/zlAddress/form">地址添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="zlAddress" action="${ctx}/zl/zlAddress/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>收件人：</label>
				<form:input path="addrUser" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>收件电话：</label>
				<form:input path="addrTel" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>收件人</th>
				<th>收件电话</th>
				<th>收件省份</th>
				<th>收件城市</th>
				<th>收件区域</th>
				<th>收货地址</th>
				<th>城市</th>
				<th>是否默认</th>
				<th>地址归属者</th>
				<shiro:hasPermission name="zl:zlAddress:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zlAddress">
			<tr>
				<td><a href="${ctx}/zl/zlAddress/form?id=${zlAddress.id}">
					${zlAddress.addrUser}
				</a></td>
				<td>
					${zlAddress.addrTel}
				</td>
				<td>
					${zlAddress.province}
				</td>
				<td>
					${zlAddress.city}
				</td>
				<td>
					${zlAddress.area}
				</td>
				<td>
					${zlAddress.addrName}
				</td>
				<td>
					${zlAddress.addrCity}
				</td>
				<td>
					${zlAddress.isDefault}
				</td>
				<td>
					${zlAddress.oppenId}
				</td>
				<shiro:hasPermission name="zl:zlAddress:edit"><td>
    				<a href="${ctx}/zl/zlAddress/form?id=${zlAddress.id}">修改</a>
					<a href="${ctx}/zl/zlAddress/delete?id=${zlAddress.id}" onclick="return confirmx('确认要删除该地址吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>