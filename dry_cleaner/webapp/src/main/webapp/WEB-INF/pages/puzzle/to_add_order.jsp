<table>
	<tr>
		<th><br></th>
	</tr>
	<c:forEach var='clother' items='${clothes}'>
		<tr>
			<td>
				<a href="/dry_cleaner/controller?command=change_order&clother_id=${clother.id}">
					Add order
				</a>
			</td>
		</tr>
	</c:forEach>
</table>