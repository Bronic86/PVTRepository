<table>
	<tr>
		<th> Login </th>
		<th> First name </th>
		<th> Second name </th>
		<th> Telephone </th>
		<th> Roles </th>
		
		<c:if test='${not empty user}'>
			<th><br></th>
		</c:if>
	</tr>
	<c:forEach var='user' items='${users}'>
		<tr>
			<td><c:out value="${user.login}" /></td>
			<td><c:out value="${user.firstName}" /></td>
			<td><c:out value="${user.secondName}" /></td>
			<td><c:out value="${user.telephone}" /></td>
			<td>
				<ul>
					<c:forEach var='role' items='${user.roles}'>
						<li>
							<c:out value="${role.name}" />
						</li>
					</c:forEach>
				</ul>
			</td>
			<td>
				<a href="users/user/${user.id }">
					Change roles
					</a>
			</td>
		</tr>
	</c:forEach>
</table>