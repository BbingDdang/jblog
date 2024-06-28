<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal" var="user"/>
        <div id="container">
            <c:import url="/WEB-INF/views/includes/blog-header.jsp" />
            <div id="wrapper">
                <div id="content" class="full-screen">
                    <ul class="admin-menu">
                        <li><a href="${pageContext.request.contextPath}/${user.id}/admin/basic">기본설정</a></li>
                        <li><a href="${pageContext.request.contextPath}/${user.id}/admin/category">카테고리</a></li>
                        <li class="selected">글작성</li>
                    </ul>
                    <form:form modelAttribute="postVo" method="post" action="${pageContext.request.contextPath}/${user.id}/admin/write">
                        <table class="admin-cat-write">
                            <tr>
                                <td class="t">제목</td>
                                <td>
                                    <form:input path="title" size="60" />
                                    <form:select path="categoryNo">
                                        <c:forEach var="category" items="${list}">
                                            <form:option value="${category.no}" label="${category.name}" />
                                        </c:forEach>
                                    </form:select>
                                </td>
                            </tr>
                            <tr>
                                <td class="t">내용</td>
                                <td><form:textarea path="contents"></form:textarea></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="s"><input type="submit" value="포스트하기"></td>
                            </tr>
                        </table>
                    </form:form>
                </div>
            </div>
            <c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
        </div>
    </sec:authorize>
</body>
</html>