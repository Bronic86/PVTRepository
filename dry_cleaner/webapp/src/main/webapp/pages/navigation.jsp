<%@include file="puzzle/head_jsp.jsp"%>
<html>
    <head>
        <title>Navigation</title>
    </head>
    <body>
    	<%@include file="puzzle/header.jsp" %>
        <div>
        	<%@include file="puzzle/menu.jsp"%>
        	<article>
        	<c:out value = "${user}"/>
            </article>
        </div>
        <%@include file="puzzle/footer_jsp.jsp"%>
    </body>
</html>