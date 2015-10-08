<header style='text-align: center'>
	<h1>
		Hello 
		<c:choose>
			<c:when test= '${empty userFirstName}'>
				Guest
			</c:when>
			<c:otherwise>				
				${userFirstName} 
			</c:otherwise>
		</c:choose>.
	</h1>
</header>