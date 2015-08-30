<table style="position: top">
	<tr>
		<th> Model </th>
	</tr>
	<c:forEach var='clother' items='${clothes}'>
		<tr>
			<td><c:out value="${clother.model.name}" /></td>
		</tr>
	</c:forEach>
</table>
