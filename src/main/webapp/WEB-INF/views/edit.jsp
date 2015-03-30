<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Editing Person ${person.id} - ${person.firstName}  ${person.lastName}

</h1>
<a href="<c:url value="/person/list" />">List of person</a>
<jsp:include page="modify_tpl.jsp"></jsp:include>

<h2>Addresses</h2>
<ol>
<c:forEach var="address" items="${person.addresses}">
	<li>${address.zip} ${address.city}, ${address.street}
		<a href="/address/edit?addrId=${address.id}&personId=${person.id}">edit</a>
		<a href="/address/delete?addrId=${address.id}&personId=${person.id}">x</a>
	</li>
</c:forEach>
</ol>
<a href="<c:url value="/address/new?personId=${person.id}"></c:url>">Add Address</a>

</body>
</html>
