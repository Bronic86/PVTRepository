
<form:form action="add" method="post" modelAttribute="addOrder" >
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
			<td><form:input path="quantity" type="text" value="0"/></td>
			<td><input type="submit" value="add order"></td>
		</tr>
	</table>
	
	<form:input path='clother' type='hidden' value='${add_clother }'/> 
</form:form>