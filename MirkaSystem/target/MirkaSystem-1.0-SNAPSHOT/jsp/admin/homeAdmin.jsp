<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
    <title>Home</title>
</head>
<body background="${pageContext.request.contextPath}/pics/main.jpg">
<c:import url="/jsp/modules/header.jsp"/>
<div style="border: #56baed">
    <h2 style="font-size: 50px; color: azure; position: absolute; bottom: 0">Lioha Miroshniqueue. Это окно видит только админ.</h2>
</div>
<c:import url="/jsp/modules/footer.jsp"/>
</body>
</html>