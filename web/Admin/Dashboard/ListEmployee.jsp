

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Employee</title>
    </head>
    <body>
         <%@ include file="../AdminNav.jsp" %>
         <br>
        <h1>List Employee 1 2</h1>
        
        
   <c:forEach items="${employees}" var="c">
        <p>${c.fullName} - ${c.gender}</p>
       
    </c:forEach>

</body>
</html>
