<table>
	<tr>
		<th> Price </th>
	</tr>
	<c:forEach var='clother' items='${clothes}'>
		<tr>
			<td><c:out value="${clother.price}" /></td>
		</tr>
	</c:forEach>
</table>