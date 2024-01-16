<%-- 
    Document   : ListTest.jsp
    Created on : Jan 13, 2024, 10:41:32 PM
    Author     : jpesewang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ListTest</h1>
        <c:forEach items="${requestScope.data}" var="c">
            <p>${c.fullName}</p>
            
        </c:forEach>
    </body>
</html>
