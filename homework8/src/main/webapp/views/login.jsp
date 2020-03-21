<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1>Login</h1>
<form name='f' action="perform_login" method='POST'>
    <sec:csrfInput/>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password'/></td>
        </tr>
        <tr>
            <td>
                <input name="submit" type="submit" value="submit"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>