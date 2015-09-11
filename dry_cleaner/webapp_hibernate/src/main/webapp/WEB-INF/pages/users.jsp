<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>Users</title>
</head>
<body>
	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<%@include file="puzzle/menu.jsp"%>
			<article>
				<header style='text-align: center'><h2>Users</h2></header>
				<article>
					<div style='float: left; width: 200px; height: 800px'>
						<%@include file="puzzle/users_list.jsp"%>
					</div>
				</article>
			</article>
		</article>
		<%@include file="puzzle/footer_jsp.jsp"%>
	</div>
</body>
</html>