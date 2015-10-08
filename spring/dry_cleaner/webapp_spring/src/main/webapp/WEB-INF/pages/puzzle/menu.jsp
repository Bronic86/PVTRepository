<div style='float: left; width: 200px; height: 800px'>
	<li>
		<h3>Navigation menu</h3>
			<sec:authorize access="isAnonymous()" >
				<ul>
					<a href="<c:url value='/authorization' />">Authorization</a>
				</ul>
				<ul>
					<a href="<c:url value='/registration' />">Registration</a>
				</ul>
				<ul>
					<a href="<c:url value='/price_list' />">Price list</a>
				</ul>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER')" >
				<ul>
					<a href="<c:url value='/orders' />">Orders</a>
				</ul>
				<ul>
					<a href="<c:url value='/price_list' />">Price list</a>
				</ul>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_MANAGER')" >
				<ul>
					<a href="<c:url value='/clothes/change' />">Change clother list</a>
				</ul>
				<ul>
					<a href="<c:url value='/models/change' />">Change model list</a>
				</ul>
				<ul>
					<a href="<c:url value='/types/change' />">Change type list</a>
				</ul>
				<ul>
					<a href="<c:url value='/orders/add' />">Add	order</a>
				</ul>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')" >
					<ul>
						<a href="<c:url value='/users'/> ">See all users.</a>
					</ul>
				</sec:authorize>				
				
				<sec:authorize access="isAuthenticated()">
					<ul>
						<a href="<c:url value='/user' />">About myself.</a>
					</ul>
					<ul>
						<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
					</ul>
				</sec:authorize>
	</li>
</div>