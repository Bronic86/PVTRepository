<table>
	<tr>
		<th> Model </th>
		<th> Price </th>
		<sec:authorize access="hasRole('ROLE_USER')" >
			<th><br></th>
		</sec:authorize>
	</tr>
	<c:forEach var='clother' items='${clothes}'>
		<tr>
			<td><c:out value="${clother.model.name}" /></td>
			<td><c:out value="${clother.price}" /></td>
			<sec:authorize access="hasRole('ROLE_USER')" >
				<td>
					<a href="order/${clother.id}">
					Add order
					</a>
				</td>
			</sec:authorize>
		</tr>
	</c:forEach>
</table>