<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<h1>Listing People</h1>
<a href="/j_spring_security_logout">Logout</a>
<form  method="GET" action="/person/search">
    <label >First Name     <input type="text" name="firstName" value="${searchParams.get("firstName")}"/></label> <br/>
    <label >City <input type="text" name="city" value="${searchParams.get("city")}" /></label><br/>
    <input type="submit" value="Search"/>
</form>



<c:forEach items="${people}" var="v_person">
	<a href="edit?id=${v_person.id}">${v_person.id} -
	${v_person.firstName} ${v_person.lastName}</a>
	<a href="delete?id=${v_person.id}">x</a>
    <c:if test="${v_person.addresses.size() > 0}">
        <br/>  <br/>
	<div>Addresses</div>
    <ol>
    	<c:forEach var="address" items="${v_person.addresses}">
		<li>${address.zip} ${address.city}, ${address.street} </li>
	    </c:forEach>
    </ol>
    </c:if>
	<br />
	<br />

</c:forEach>
<a href="new"> Add Person</a>
</body>
</html>
