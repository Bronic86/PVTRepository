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
		<form:form action="registration" method="POST" modelAttribute="userForm" >

			<table>
				<caption>Registration form</caption>
				<tr>
					<th>Login (e-mail)</th>
					<td><form:input  path="login"  placeholder="Login"/></td>
					<td><form:errors path="login" cssStyle="color: #ff0000;"/>
						<label cssStyle="color: #ff0000;">${errorLoginExist}</label>
					</td>
				</tr>
				<tr>
					<th>Password</th>
					<td><form:input type="password"	path="password"	placeholder="Password"/></td>
					<td><form:errors path="password" cssStyle="color: #ff0000;"/></td>
				</tr>
				<tr>
					<th>Name</th>
					<td><form:input type="text"	path="firstName" placeholder="Name"/></td>
					<td><form:errors path="firstName" cssStyle="color: #ff0000;"/></td>
				</tr>
				<tr>
					<th>Surname</th>
					<td><form:input type="text"	path="secondName" placeholder="Second name"/></td>
					<td><form:errors path="secondName" cssStyle="color: #ff0000;"/></td>
				</tr>
				<tr>
					<th>Telephone</th>
					<td><form:input type="text"	path="telephone" placeholder="375291234567"/></td>
					<td><form:errors path="telephone" cssStyle="color: #ff0000;"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Registrate"/></td>
				</tr>
			</table>
			
		</form:form>
	</article>
	<%@include file="puzzle/footer_jsp.jsp"%>
</body>
</html>