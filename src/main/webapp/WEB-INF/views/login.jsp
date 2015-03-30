<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Login Page</title></head>
<body onload='document.f.j_username.focus();'>
<h3>Custom Login with Username and Password</h3>

<form name='f' action='<c:url value='j_spring_security_check' />' method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='login' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Login"/></td>
        </tr>
    </table>
</form>
</body>
</html>
