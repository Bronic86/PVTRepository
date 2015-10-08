<form:form action="change" method="POST" modelAttribute="addRoleForm">
<table>
	<tr>
		<th> Login </th>
		<th> First name </th>
		<th> Second name </th>
		<th> Telephone </th>
		<th> Roles </th>
		<th> add/remove </th>
		<th> Role </th>
	</tr>
	<tr>
		<td><c:out value="${userInformation.login}" /></td>
		<td><c:out value="${userInformation.firstName}" /></td>
		<td><c:out value="${userInformation.secondName}" /></td>
		<td><c:out value="${userInformation.telephone}" /></td>
		<td>
			<ul>
				<c:forEach var='role' items='${userInformation.roles}'>
					<li>
						<c:out value="${role.name}" />
					</li>
				</c:forEach>
			</ul>
		</td>
		<td>
			<form:select path="add_remove">
				<option value=""></option>
				<option value="add">add</option>
				<option value="remove">remove</option>
			</form:select>
		</td>
		<td>
			<form:select path="role_id">
				<option value=""></option>
				<c:forEach var='role' items='${roles}'>
					<option value=${role.id }><c:out value="${role.name}" /></option>
				</c:forEach>
			</form:select>
		</td>
		<td><input type="submit" value="change" ></td>
		<form:input path="user_id" type="hidden" value="${userInformation.id }" />
	</tr>
	
</table>
</form:form>