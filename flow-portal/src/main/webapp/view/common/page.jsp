<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="dataTables_footer clearfix">
		<div class="col-md-6">
			<div class="dataTables_info" id="DataTables_Table_0_info">
				(每页显示${page.pageSize}条，共${page.totalPage}页)
			</div>
		</div>
		<div class="col-md-6">
			<div class="dataTables_paginate paging_bootstrap">
				<ul class="pagination">
					<c:choose>
						<c:when test="${page.page eq 1}">
							<li class="prev disabled"><a href="${url}?page=${page.prePage}">上一页</a></li>
						</c:when>
						<c:otherwise>
							<li class="prev"><a href="${url}?page=${page.prePage}">上一页</a></li>
						</c:otherwise>					
					</c:choose>		
					
					
					<c:forEach var="p" begin="${page.page - 3 < 0 ? 1 : page.page - 3}" end="${page.page + 3 > page.totalPage ? page.totalPage : page.page + 3}">
						<c:choose>
							<c:when test="${page.page eq p}">
								<li class="active"><a href="${url}?page=${p}">${p}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${url}?page=${p}">${p}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			
					<c:choose>
						<c:when test="${page.page eq totalPage}">
							<li class="next disabled"><a href="${url}?page=${page.nextPage}">下一页</a></li>
						</c:when>
						<c:otherwise>
							<li class="next"><a href="${url}?page=${page.nextPage}">下一页</a></li>
						</c:otherwise>					
					</c:choose>					
				</ul>
			</div>
		</div>
	</div>
</div>