<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>User information</title>
</head>
<body>
	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<%@include file="puzzle/menu.jsp"%>
			<article>
				<header style='text-align: center'><h2>Information about user.</h2></header>
				<article>
					<div style='float: left; width: 200px; height: 800px'>
						<%@include file="puzzle/about_user.jsp"%>
					</div>
				</article>
			</article>
		</article>
		<%@include file="puzzle/footer_jsp.jsp"%>
	</div>
</body>
</html>