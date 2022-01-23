<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet">
</head>
<body>
<nav class="nav">
    <div class="nav__elements">
        <div class="nav__logo">
            <a href=<c:choose>
               <c:when test="${sessionScope.user.getUserType() eq 'ADMIN'}">
                       "${pageContext.request.contextPath}/jsp/admin/homeAdmin.jsp"
               </c:when>
               <c:otherwise>
                       "${pageContext.request.contextPath}/jsp/user/homeUser.jsp"
               </c:otherwise>
                    </c:choose>><span>Lioha</span><span>M</span><span>i</span><span>r</span><span>o</span><span>s</span><span>h</span><span>n</span><span>i</span><span>q</span><span>u</span><span>e</span><span>u</span><span>e</span></a>
        </div>
        <ul class="nav__groups" style="list-style: none;">
            <li class="nav__group"><a style="text-decoration: none;" class="nav__group__link" href="${pageContext.request.contextPath}/jsp/modules/footbik.jsp">Football</a></li>
            <li class="nav__group"><a style="text-decoration: none;" class="nav__group__link" href="${pageContext.request.contextPath}/jsp/modules/basket.jsp">Basketball</a></li>
            <li class="nav__group"><a style="text-decoration: none;" class="nav__group__link" href="${pageContext.request.contextPath}/jsp/modules/hockey.jsp">Hockey</a></li>
        </ul>
        <div class="nav__cart"><a style="text-decoration: none;" class="nav__group__link" href="${pageContext.request.contextPath}/jsp/modules/contactPage.jsp">Contacts</a></div>
        <div class="nav__cart">
            <a class="nav__group__link">
                <c:if test="${not empty sessionScope.user}">
                    <form style="margin-top: 20px;" method="get" action="<c:url value="/controller"/>">
                        <input type="hidden" name="command" value="log_out">
                        <input style="width: 85px;height: 55px;background-image: linear-gradient(315deg, brown 0%, antiquewhite 74%);border-radius: 16%;" type="submit" value="Log Out">
                    </form>
                </c:if>
            </a>
        </div>
    </div>
</nav>
</body>
</html>