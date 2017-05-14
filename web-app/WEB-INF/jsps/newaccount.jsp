<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery-2.2.3.min.js">	
</script>

<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPasswordMatch);
		$("#confirmpass").keyup(checkPasswordMatch);
		$("#detail").submit(canSubmit);		
	}

	function canSubmit(){
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		return password === confirmpass;
	}
	
	function checkPasswordMatch(){
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		
		if (password.length < 3){
			return;
		}
		
		if (password === confirmpass){
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password' />");
			$("#matchpass").removeClass("error");
			$("#matchpass").addClass("valid");
		} else {
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password' />")
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}		
	}
	
	$(document).ready(onLoad);
</script>

</head>
<body>
	<h2>Create New Account</h2>
	<sf:form id="detail" method="POST"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">User name:</td>
				<td><sf:input class="control" path="username" type="text" /><br />
					<div class="error">
						<sf:errors path="username"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" type="text" /><br />
					<div class="error">
						<sf:errors path="email"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input class="control" id="password" path="password" type="text" /><br />
					<div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Confirm password:</td>
				<td><input class="control" id="confirmpass" name="confirmpass" type="text" />
				<div id="matchpass"></div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input class="control" value="Create offer" type="submit" /></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>