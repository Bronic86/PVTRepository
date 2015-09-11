<table>
	<tr>
		<th> Model </th>
		<th> Price </th>
		<c:if test='${not empty user}'>
			<th><br></th>
		</c:if>
	</tr>
	<c:forEach var='clother' items='${clothes}'>
		<tr>
			<td><c:out value="${clother.model.name}" /></td>
			<td><c:out value="${clother.price}" /></td>
			<c:if test='${not empty user}'>
				<td>
					<a href="/dry_cleaner/controller?command=change_order&clother_id=${clother.id}">
					Add order
					</a>
				</td>
			</c:if>
		</tr>
	</c:forEach>
</table>