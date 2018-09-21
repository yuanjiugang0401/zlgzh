<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>美柚新闻管理</title>
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
		<li class="active"><a href="${ctx}/zl/myNews/">美柚新闻列表</a></li>
		<shiro:hasPermission name="zl:myNews:edit"><li><a href="${ctx}/zl/myNews/form">美柚新闻添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="myNews" action="${ctx}/zl/myNews/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>备注</th>
				<shiro:hasPermission name="zl:myNews:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="myNews">
			<tr>
				<td><a href="${ctx}/zl/myNews/form?id=${myNews.id}">
					${myNews.remarks}
				</a></td>
				<shiro:hasPermission name="zl:myNews:edit"><td>
    				<a href="${ctx}/zl/myNews/form?id=${myNews.id}">修改</a>
					<a href="${ctx}/zl/myNews/delete?id=${myNews.id}" onclick="return confirmx('确认要删除该美柚新闻吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>