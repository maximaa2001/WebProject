<%--
  Created by IntelliJ IDEA.
  User: maxim
  Date: 21.10.2021
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Title</title>
</head>
<body>
<jsp:include page="static/header.jsp"></jsp:include>

${sessionScope.role}

<jsp:include page="static/footer.jsp"></jsp:include>

</body>
</html>
