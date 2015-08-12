<%@include file="head_jsp.jsp"%>
<html>
    <head>
        <title>Authorization</title>
    </head>
    <body>
        
        <div>
        <header>
        <h1>Hello user.</h1>
        </header>
            <form action="controller" method="post">

                <table>
                <caption>Input login and password</caption>
                    <tr>
                        <th>
                            Login
                        </th>
                        <td>
                            <input name="login" type="text" placeholder="Login">
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Password
                        </th>
                        <td>
                            <input name="password" type="password" placeholder="Password">
                        </td>
                    </tr>
                </table>
                <br>
                <input type="submit" value="Enter">
                <input name="command" type="hidden" value="authorization">
            </form>
            <a href="registration.jsp">Registration</a>
            <footer><%=new  java.util.Date() %></footer>
        </div>
    </body>
</html>
