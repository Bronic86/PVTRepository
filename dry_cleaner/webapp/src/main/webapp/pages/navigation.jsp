<%@include file="head_jsp.jsp"%>
<html>
    <head>
        <title>Navigation</title>
    </head>
    <body>
        <h1>Hello user.</h1>
        <div>
        	<%@include file="header.jsp"%>
        	<article>
        	<%=request.getAttribute("user") %><br>
            	<a href="#">Add order</a>
            </article>
            <%@include file="footer_jsp.jsp"%>
        </div>
    </body>
</html>