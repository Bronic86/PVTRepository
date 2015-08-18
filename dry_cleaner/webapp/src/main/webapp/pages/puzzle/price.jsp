<table>
	<caption style="text-align: left">Clothes price.</caption>
	<tr>
		<td>
			<%@include file="type_list.jsp"%>
		</td>
		<td>
			<c:choose>
				<c:when test='${not empty models}'>
					<%@include file="model_list.jsp"%>
				</c:when>
				<c:otherwise>
					<br>
				</c:otherwise>
			</c:choose>
		</td>
		<td>			
			<c:choose>
				<c:when test='${not empty clothes}'>
					<%@include file="clothes_price_list.jsp"%>
				</c:when>
				<c:otherwise>
					<br>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>