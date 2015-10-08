<form:form action="add" method="POST" modelAttribute="orderForm">
	<table>

		<tr>
			<th><c:out value='Type' /></th>
			<td><c:out value='${add_clother.model.type.name }' /></td>
			<td></td>
		</tr>
		<tr>	
			<th><c:out value='Model' /></th>
			<td><c:out value='${add_clother.model.name }' /></td>
			<td></td>
		</tr>
		<tr>	
			<th><c:out value='Price' /></th>
			<td><c:out value='${add_clother.price }' /></td>
			<td></td>
		</tr>
		<tr>
			<th><c:out value='Quantity' /></th>
			<td><form:input type="text" path="quantity" value="0"/></td>
			<td><form:errors path="quantity" cssStyle="color: #ff0000;"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="add order"></td>
		</tr>
		
	</table>
	
	<form:input path='id' type='hidden' value='${add_clother.id }'/>
	
</form:form>