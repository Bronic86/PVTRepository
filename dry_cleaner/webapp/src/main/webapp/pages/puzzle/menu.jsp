<div style='float: left; width: 200px; height: 800px'>
	<li>
		<h3>Navigation menu</h3> 
		<c:choose>
			<c:when test='${empty userRole}'>
				<ul>
					<a href="<c:url value='/pages/authorization.jsp' />">Authorization</a>
				</ul>
				<ul>
					<a href="<c:url value='/pages/registration.jsp' />">Registration</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=price_list' />">Price
						list</a>
				</ul>
			</c:when>
			<c:when test='${userRole.name == "user" }'>
				<ul>
					<a href="<c:url value='/controller?command=add_order' />">Add
						order</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=orders' />">Orders</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=price_list' />">Price
						list</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=log_out' />">Log_out</a>
				</ul>
			</c:when>
			<c:when test='${userRole.name == "admin" }'>
				<ul>
					<a href="<c:url value='/controller?command=change_price.jsp' />">Change
						price</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=dd_order.jsp' />">Add
						order</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=orders' />">Orders</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=price_list' />">Price
						list</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=log_out' />">Log out</a>
				</ul>
			</c:when>
		</c:choose>

	</li>
</div>