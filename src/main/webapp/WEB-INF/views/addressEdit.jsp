<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Address</title>
</head>
<body>
<h1>
	Address
</h1>
<form:form style="padding:8px" modelAttribute="address" >
	<p>
		Zip<br/>
		<form:input path="zip"/>
	</p>
	<p>
		City<br/>
		<form:input path="city"/>
	</p>
	<p>
		Street<br/>
		<form:input path="street"/>
	</p>

	<input type="hidden" value="${personId}"/>
	<input type="hidden" value="${address.id}"/>
	<input type="submit" value="Save"/>
</form:form>

</body>
</html>
