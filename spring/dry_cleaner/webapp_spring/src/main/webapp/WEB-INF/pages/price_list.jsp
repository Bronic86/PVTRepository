<%@include file="puzzle/head_jsp.jsp"%>
<html>
<head>
<title>Price list</title>
</head>
<body>
	<div>
		<%@include file="puzzle/header.jsp"%>
		<article>
			<%@include file="puzzle/menu.jsp"%>
			<article>
				<header style='text-align: center'><h2>Clothes price.</h2></header>
				<article>
					<div style='float: left; width: 200px; height: 800px'>
						<%@include file="puzzle/type_list.jsp"%>
					</div>
					<div>
						<c:if test='${not empty clothes}'>
							<%@include file="puzzle/clothes_price_list.jsp"%>
						</c:if>
					</div>
				</article>
			</article>
		</article>
		<%@include file="puzzle/footer_jsp.jsp"%>
	</div>
</body>
</html>