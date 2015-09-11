<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>Registration</title>
</head>
<body>
	<header style="text-align: center">
		<h1>Registration menu.</h1>
	</header>
	<article>
		<div style='float: left; width: 200px; height: 800px'>
			<%@include file="puzzle/menu.jsp"%>
		</div>
		<form
			action="<fmt:message key='key.controller' bundle='${resource}'/>"
			method="post">

			<table>
				<caption>Registration form</caption>
				<tr>
					<th>Login (e-mail)</th>
					<td><input type="text"
						name="<fmt:message key='user.column.login' bundle='${resource}'/>"
						placeholder="Login"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password"
						name="<fmt:message key='user.column.password' bundle='${resource}'/>"
						placeholder="Password"></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><input type="text"
						name="<fmt:message key='user.column.first.name' bundle='${resource}'/>"
						placeholder="Name"></td>
				</tr>
				<tr>
					<th>Surname</th>
					<td><input type="text"
						name="<fmt:message key='user.column.second.name' bundle='${resource}'/>"
						placeholder="Second name"></td>
				</tr>
				<tr>
					<th>Telephone</th>
					<td><input type="text"
						name="<fmt:message key='user.column.telephone' bundle='${resource}'/>"
						placeholder="375291234567"></td>
				</tr>
				<tr>
					<td><input type="submit" value="<fmt:message key='button.registrate' bundle='${resource}'/>"></td>
				</tr>
			</table>
			<input type="hidden" name="command" value="registration">
		</form>
	</article>
	<%@include file="puzzle/footer_jsp.jsp"%>
</body>
</html>