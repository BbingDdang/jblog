<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
                        <li class="selected">기본설정</li>
                        <li><a href="${pageContext.request.contextPath}/${user.id}/admin/category">카테고리</a></li>
                        <li><a href="${pageContext.request.contextPath}/${user.id}/admin/write">글작성</a></li>
                    </ul>
                    <form action="${pageContext.request.contextPath}/${user.id}/admin/basic" method="post" enctype="multipart/form-data">
                        <table class="admin-config">
                            <tr>
                                <td class="t">블로그 제목</td>
                                <td><input type="text" size="40" name="title"></td>
                            </tr>
                            <tr>
                                <td class="t">로고이미지</td>
                                <td><img src="${pageContext.request.contextPath}${blogVo.logo}"></td>
                            </tr>
                            <tr>
                                <td class="t">&nbsp;</td>
                                <td><input type="file" name="file"></td>
                            </tr>
                            <tr>
                                <td class="t">&nbsp;</td>
                                <td class="s"><input type="submit" value="기본설정 변경"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
        </div>
    </sec:authorize>
</body>
</html>