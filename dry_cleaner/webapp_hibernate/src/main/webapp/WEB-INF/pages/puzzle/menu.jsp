<div style='float: left; width: 200px; height: 800px'>
	<li>
		<h3>Navigation menu</h3>
			<c:if test='${empty roles}'>
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
			</c:if>
			<c:if test='${roles.contains("user")}'>
				<ul>
					<a href="<c:url value='/controller?command=orders' />">Orders</a>
				</ul>
				<ul>
					<a href="<c:url value='/controller?command=price_list' />">Price
						list</a>
				</ul>
				
			</c:if>
			<c:if test='${roles.contains("manager") }'>
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
				
				</c:if>
				<c:if test='${roles.contains("admin") }'>
					<ul>
					<a href="<c:url value='/controller?command=all_users' />">See all users.</a>
				</ul>
				</c:if>
				
				<c:if test='${not empty roles }'>
				<ul>
					<a href="<c:url value='/controller?command=user_information&login=${user.login }' />">About myself.</a>
				</ul>
					<ul>
						<a href="<c:url value='/controller?command=log_out' />">Log out</a>
					</ul>
				</c:if>
	</li>
</div>