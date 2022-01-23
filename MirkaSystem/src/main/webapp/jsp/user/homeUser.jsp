<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
    <title>Home</title>
    <style>
        .brd {
            border: 4px double black; /* Параметры границы */
            background: wheat; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
        }
    </style>
</head>

<body background="${pageContext.request.contextPath}/pics/main.jpg">
<c:import url="/jsp/modules/header.jsp"/>

<p class="fig">
    <img  src="${pageContext.request.contextPath}/pics/Group_9.png" alt="Fon">
</p>


<c:import url="/jsp/modules/footer.jsp"/>
</body>
</html>