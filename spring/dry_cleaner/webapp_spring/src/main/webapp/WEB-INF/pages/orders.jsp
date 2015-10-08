<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>Orders</title>
</head>
<body>

	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<div>
				<%@include file="puzzle/menu.jsp"%>
			</div>
			<div>
				<c:choose>
					<c:when test="${not empty user_orders }">
						<%@include file="puzzle/user_orders.jsp" %>
					</c:when>
					<c:otherwise>
						<c:out value="You didn't have orders."/>
					</c:otherwise>
				</c:choose>
			</div>
		</article>
		<footer><%=new java.util.Date()%></footer>
	</div>
</body>
</html>
