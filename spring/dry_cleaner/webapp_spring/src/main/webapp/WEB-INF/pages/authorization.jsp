<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>Authorization</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body>

	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<div>
				<%@include file="puzzle/menu.jsp"%>
			</div>
			<div id="login-box">

		<h3>Please, enter login and password for authorization</h3>

		
		
		<c:url value="/j_spring_security_check" var="loginUrl" />
		<form name='loginForm'
			action="${loginUrl}" method='POST'>

			<table>
				<tr>
					<td>Login:</td>
					<td><input type='text'  name='j_username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit" 
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		
		</form>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
			</div>
		</article>
		<footer><%=new java.util.Date()%></footer>
	</div>
</body>
</html>
