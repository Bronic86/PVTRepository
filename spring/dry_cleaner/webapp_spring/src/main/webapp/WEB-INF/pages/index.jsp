<%@include file="puzzle/head_jsp.jsp"%>
<html>
	<head>
		<title>Start page</title>
	</head>
<body>
	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<div>
				<%@include file="puzzle/menu.jsp"%>
			</div>
			<div>
				<h1>Welcome!</h1>
				<p>Welcome to Classic Dry-cleaners.</p>
				<p>${j_username }</p>
			</div>
		</article>
		<%@include file="puzzle/footer_jsp.jsp"%>
	</div>
</body>
</html>
