<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<h2>Create New Account</h2>
	<sf:form method="POST"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">User name:</td>
				<td><sf:input class="control" path="username" type="text" /><br />
				<div class="error"><sf:errors path="username"></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" type="text" /><br />
				<div class="error"><sf:errors path="email"></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input class="control" path="password" type="text" /><br />
				<div class="error"><sf:errors path="password"></sf:errors></div></td>
			</tr>
			<tr>
				<td class="label">Confirm password:</td>
				<td><input class="control" name="confirmpass" type="text" /><br /></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="control" value="Create offer" type="submit" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>