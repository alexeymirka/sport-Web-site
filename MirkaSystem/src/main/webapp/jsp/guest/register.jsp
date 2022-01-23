<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <title>Register Page</title>
</head>
<body background="${pageContext.request.contextPath}/pics/p3.png">
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form name="logInForm" method="post" action="<c:url value="/controller"/>">
            <input type="hidden" name="command" value="register">
            <h2 class="active"> Register </h2>
            <a class="inactive underlineHover" href="${pageContext.request.contextPath}/jsp/guest/login.jsp" >Sign In </a>
            <div class="form-group">
                <input type="text" name="login" class="fadeIn second" required placeholder="Enter login">
            </div>
            <div class="form-group">
                <input type="password" name="password" class="fadeIn second" required placeholder="Enter Password">
            </div>
            <div class="form-group">
                <input type="password" name="repeatPassword" class="fadeIn second" required placeholder="Enter Password">
            </div>
            <input type="submit" value="Register" class="fadeIn fourth">
            <c:if test="${not empty sessionScope.error_message}">
                <p style="color: red; font-size: 15px; text-align: center;border: 1px solid red;padding: 15px;">${sessionScope.error_message}</p>
            </c:if>
            <c:remove var="error_message" scope="session"/>
        </form>
    </div>
</div>
</body>
<c:import url="/jsp/modules/footer.jsp"/>
</html>