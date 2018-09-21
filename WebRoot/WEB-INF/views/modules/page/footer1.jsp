<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<footer class="page-footer fixed-footer" id="footer">
	<ul>
		<li>
			<a href="${ctx}/page/index">
				<i class="iconfont icon-shouye"></i>
				<p>首页</p>
			</a>
		</li>
		<li class="active">
			<a href="${ctx}/page/shopcar">
				<i class="iconfont icon-gouwuche">
					<c:if test="${cart_num!=0&&!empty cart_num }"><span id="cart_num">${cart_num}</span></c:if>
				</i>
				<p>购物车</p>
			</a>
		</li>
		<li>
			<a href="${ctx}/page/home">
				<i class="iconfont icon-yonghuming"></i>
				<p>我的</p>
			</a>
		</li>
	</ul>
</footer>