<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>Authorization</title>
</head>
<body>

	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<div>
				<%@include file="puzzle/menu.jsp"%>
			</div>
			<div>
				<form
					action="<fmt:message key='key.controller' bundle='${resource}'/>"
					method="post">
					<table>
						<caption>Input login and password</caption>
						<tr>
							<th>Login</th>
							<td><input
								name="<fmt:message key='user.column.login' bundle='${resource}'/>"
								type="text" placeholder="Login"></td>
						</tr>
						<tr>
							<th>Password</th>
							<td><input
								name="<fmt:message key='user.column.password' bundle='${resource}'/>"
								type="password" placeholder="Password"></td>
						</tr>
					</table>
					<br> <input type="submit" value="<fmt:message key='button.authorization' bundle='${resource}'/>"> 
					<input
						name="<fmt:message key='key.command' bundle='${resource}'/>"
						type="hidden"
						value="<fmt:message key='command.key.authorization' bundle='${resource}'/>">
				</form>
			</div>
		</article>
		<footer><%=new java.util.Date()%></footer>
	</div>
</body>
</html>
