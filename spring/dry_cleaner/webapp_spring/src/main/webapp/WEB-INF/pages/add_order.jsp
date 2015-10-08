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
					<c:when test='${not empty add_clother}'>
						<%@include file="puzzle/add_order_form.jsp" %>
					</c:when>
					<c:otherwise>
						<c:out value="You didn't choose clother."/>
					</c:otherwise>
				</c:choose>
			</div>
		</article>
		<footer><%=new java.util.Date()%></footer>
	</div>
</body>
</html>
