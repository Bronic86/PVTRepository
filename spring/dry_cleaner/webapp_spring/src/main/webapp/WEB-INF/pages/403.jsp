<%@include file="puzzle/head_jsp.jsp"%>
<html>
<body>
	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<div>
				<%@include file="puzzle/menu.jsp"%>
			</div>
	
			<h1>HTTP Status 403 - Access is denied</h1>
		
			<c:choose>
				<c:when test="${empty username}">
					<h2>You do not have permission to access this page!</h2>
				</c:when>
				<c:otherwise>
					<h2>Username : ${username} <br>
					You do not have permission to access this page!</h2>
				</c:otherwise>
			</c:choose>
		</article>
	
		<%@include file="puzzle/footer_jsp.jsp"%>
	</div>
	
</body>
</html>