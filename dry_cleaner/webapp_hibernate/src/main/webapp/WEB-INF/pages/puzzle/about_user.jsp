<table>
	<tr>
		<th> Login </th>
		<th> First name </th>
		<th> Second name </th>
		<th> Telephone </th>
		<th> Roles </th>
	</tr>
	<tr>
		<td><c:out value="${userRoles.user.login}" /></td>
		<td><c:out value="${userRoles.user.firstName}" /></td>
		<td><c:out value="${userRoles.user.secondName}" /></td>
		<td><c:out value="${userRoles.user.telephone}" /></td>
		<td>
			<!-- Not work.Empty roles -->
		<ul>
			<c:forEach var='role' items='${usersRoles.roles}'>
				<li>
					<c:out value="${role.name}" />
				</li>
			</c:forEach></ul>
		</td>
	</tr>
</table>