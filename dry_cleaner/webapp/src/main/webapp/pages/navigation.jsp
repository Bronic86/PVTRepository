<%@include file="head_jsp.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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