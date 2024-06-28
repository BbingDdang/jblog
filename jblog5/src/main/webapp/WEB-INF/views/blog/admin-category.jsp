<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                        <li class="selected">카테고리</li>
                        <li><a href="${pageContext.request.contextPath}/${user.id}/admin/write">글작성</a></li>
                    </ul>
                    <table class="admin-cat">
                        <tr>
                            <th>번호</th>
                            <th>카테고리명</th>
                            <th>포스트 수</th>
                            <th>설명</th>
                            <th>삭제</th>       
                        </tr>
                        <c:forEach var="row" items="${list}">
                            <tr>
                                <td>${row.no}</td>
                                <td>${row.name}</td>
                                <td>${row.postCount}</td>
                                <td>${row.description}</td>
                                <c:choose>
                                    <c:when test="${row.name eq '미분류'}">
                                        <td></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="${pageContext.request.contextPath}/${user.id}/admin/category/delete?no=${row.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                    <h4 class="n-c">새로운 카테고리 추가</h4>
                    <form:form modelAttribute="categoryVo" method="post" action="${pageContext.request.contextPath}/${user.id}/admin/category/add">
                        <table id="admin-cat-add">
                            <tr>
                                <td class="t">카테고리명</td>
                                <td><form:input path="name" /></td>
                            </tr>
                            <tr>
                                <td class="t">설명</td>
                                <td><form:input path="description" /></td>
                            </tr>
                            <tr>
                                <td class="s">&nbsp;</td>
                                <td><input type="submit" value="카테고리 추가"></td>
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