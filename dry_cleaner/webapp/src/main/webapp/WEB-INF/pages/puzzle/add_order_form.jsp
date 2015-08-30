<form action="controller" methode="post">
	<table>

		<tr>
			<th><c:out value='Type' /></th>
			<th><c:out value='Model' /></th>
			<th><c:out value='Price' /></th>
			<th><c:out value='Quantity' /></th>
		</tr>
		<tr>
			<td><c:out value='${add_clother.model.type.name }' /></td>
			<td><c:out value='${add_clother.model.name }' /></td>
			<td><c:out value='${add_clother.price }' /></td>
			<td><input type="text" name="quantity" value="0"></td>
			<td><input type="submit" value="add order"></td>
		</tr>
	</table>
	<input name="<fmt:message key='key.command' bundle='${resource}'/>"
		type="hidden" value="add_order"> 
	<input name='clother_id' type='hidden' value='${add_clother.id }'> 
</form>