<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>
${controllerMessage}<br/>
<br/>
<br/>
<a href="person/list">Go to the person list</a>
<c:url value="/j_spring_security_logout" var="logoutUrl" />

<form action="${logoutUrl}" method="post" id="logoutForm">
    <input name="submit" type="submit" value="Logout"/>
</form>
</body>
</html>
