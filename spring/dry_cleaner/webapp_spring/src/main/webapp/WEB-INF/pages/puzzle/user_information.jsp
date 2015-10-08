<table>
	<tr>
		<th> Login </th>
		<th> First name </th>
		<th> Second name </th>
		<th> Telephone </th>
		<th>  </th>
	</tr>
	<tr>
		<td><c:out value="${userInformation.login}" /></td>
		<td><c:out value="${userInformation.firstName}" /></td>
		<td><c:out value="${userInformation.secondName}" /></td>
		<td><c:out value="${userInformation.telephone}" /></td>
		<td><a href="/user/change">Change information.</a></td>
	</tr>

</table>