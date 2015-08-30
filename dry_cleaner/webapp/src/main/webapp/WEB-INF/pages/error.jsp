<%@include file="puzzle/head_jsp.jsp"%>
<html>
    <head>
        <title>Error page</title>
    </head>
    <body>
        <header style="text-align: center">
            <h1>Error</h1>
        </header>
        <article>
            <%@include file="puzzle/menu.jsp"%>
            <div>
                <h4><c:out value="${message_error}"/></h4>
            </div>
        </article>
        <%@include file="puzzle/footer_jsp.jsp"%>
    </body>
</html>