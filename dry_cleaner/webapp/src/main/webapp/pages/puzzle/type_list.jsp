<table>
	<tr>
		<th> Type </th>
	</tr>
	<c:forEach var='type' items='${types}'>
		<tr>
			<td><a
				href="/dry_cleaner/controller?command=price_list&type_id=${type.id}">
					<c:out value="${type.name}" />
			</a></td>
		</tr>
	</c:forEach>

</table>