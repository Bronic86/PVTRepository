<%@include file="head_jsp.jsp"%>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
            <header>
                <h1>Registration menu.</h1>
            </header>
            <article>
                <div style='float: left; width: 200px; height: 800px'>
                    <li>
                        <h3>Navigation menu</h3>
                        <ul>
                            <a href="authorization.jsp">Authorization</a>
                        </ul>
                    </li>
                </div>
                    <form action="controller" method="post">

                        <table>
                            <caption>Registration</caption>
                            <tr>
                                <th>
                                    Login (e-mail)
                                </th>
                                <td>
                                    <input type="text" name="login" placeholder="Login">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Password
                                </th>
                                <td>
                                    <input type="password" name="password" placeholder="Password">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Name
                                </th>
                                <td>
                                    <input type="text" name="first_name" placeholder="Name">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Surname
                                </th>
                                <td>
                                    <input type="text" name="last_name" placeholder="Last_name">
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Telephone
                                </th>
                                <td>
                                    <input type="text" name="telephone" placeholder="375291234567">
                                </td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="check in"></td>
                            </tr>
                        </table>
                        <input type="hidden" name="command" value="registration">
                    </form>
            </article>
            <%@include file="footer_jsp.jsp"%>
    </body>
</html>