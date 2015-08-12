<%@include file="head_jsp.jsp"%>
<html>
    <head>
        <title>Error authorization</title>
    </head>
    <body>
        <header style="text-align: center">
            <h2>Error</h2>
        </header>
        <article>
            <div style='float: left; width: 200px; height: 800px'>
                <li>
                    <h3>Navigation menu</h3>
                    <ul>
                        <a href="authorization.jsp">Authorization</a>
                    </ul>
                    <ul>
                        <a href="registration.jsp">Registration</a>
                    </ul>
                </li>
            </div>
            <div>
                <h4><%=request.getAttribute("message_error")%></h4>
            </div>
        </article>
        <%@include file="footer_jsp.jsp"%>
    </body>
</html>