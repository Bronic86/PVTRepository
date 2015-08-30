<div style='float: left; width: 200px; height: 800px'>
	<li>
		<h3>Navigation menu</h3>
		<c:choose>
			<c:when test='${empty userRole}'>
				<ul>
					<a href="<c:url value='/controller?command=to_authorization' />">Authorization</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=to_registration' />">Registration</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=price_list' />">Price
						list</a>
				</ul>
			</c:when>
			<c:when test='${userRole == "user" }'>
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
			<c:when test='${userRole == "admin" }'>
				<ul>
					<a href="<c:url value='/controller?command=change_clother_list.jsp' />">Change
						clother list</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=change_model_list.jsp' />">Change
						model list</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=change_type_list.jsp' />">Change
						type list</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=add_order.jsp' />">Add
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