<table>
	<tr>
		<th> Login </th>
		<th> Roles </th>
		<c:if test='${not empty user}'>
			<th><br></th>
		</c:if>
	</tr>
	<c:forEach var='userRoles' items='${usersRoles}'>
		<tr>
			<td><c:out value="${userRoles.user.login}" /></td>
			<td><ul>
				<c:forEach var='role' items='${usersRoles.roles}'>
					<li>
						<c:out value="${role.name}" />
					</li>
				</ul></c:forEach>
			</td>
			<td>
				<a href="/dry_cleaner/controller?command=user_information&login=${userRoles.user.login}">
					Add order
					</a>
			</td>
		</tr>
	</c:forEach>
</table>