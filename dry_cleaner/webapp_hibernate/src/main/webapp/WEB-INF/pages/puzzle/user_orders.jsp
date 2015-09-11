<table>
	<tr style='text-align: center'>
		<th><c:out value="Type" /></th>
		<th><c:out value="Model" /></th>
		<th><c:out value="Price" /></th>
		<th><c:out value="Quantity" /></th>
		<th><c:out value="Date" /></th>
		<th><c:out value="Remove order" /></th>
	</tr>
	<c:forEach var='order' items='${user_orders }'>
		<tr>
			<td style='text-align: left'><c:out value="${order.clother.model.type.name}"/></td>
			<td style='text-align: center'><c:out value="${order.clother.model.name }" /></td>
			<td style='text-align: center'><c:out value="${order.clother.price }" /></td>
			<td style='text-align: center'><c:out value="${order.quantity }" /></td>
			<td style='text-align: center'><c:out value="${order.ordering_day }" /></td>
			<td style='text-align: center'><c:out value="${order.state.state }" /></td>
			<td style='text-align: center'><a
				href="/dry_cleaner/controller?command=delete_order&order_id=${order.id }">
					Remove </a>
			</td>
		</tr>
	</c:forEach>
</table>