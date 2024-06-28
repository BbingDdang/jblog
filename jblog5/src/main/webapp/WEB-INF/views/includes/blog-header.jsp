<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <div id="header">
		<h1>${blogVo.title }</h1>
			<ul>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a><li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="user"/>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a><li>
					<c:choose>
						<c:when test="${user.id eq blogVo.id }">
							<li><a href="${pageContext.request.contextPath}/${user.id}/admin/basic">블로그 관리</a><li>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</sec:authorize>
			</ul>
	</div>