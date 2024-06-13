<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul class="admin-menu">
<c:forEach var="item" items="${entry }">
	<c:choose>
		<c:when test="${item.pageNo == param.p}">
			<li class="selected">${item.pageNo}</li>
			<!-- Current page -->
		</c:when>
		<c:when test="${item.active eq true }">
			<li><a
				href="${pageContext.request.contextPath}/board?p=${item.pageNo}&kwd=${keyword }">${item.pageNo}</a></li>
		</c:when>
		<c:otherwise>
			<li>${item.pageNo }</li>
		</c:otherwise>
	</c:choose>
</c:forEach>
</ul>