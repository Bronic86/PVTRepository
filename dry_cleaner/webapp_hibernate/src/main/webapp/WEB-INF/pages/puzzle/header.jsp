<header style='text-align: center'>
	<h1>
		Hello
		<c:choose>
			<c:when test= '${empty user}'>
				Guest
			</c:when>
			<c:otherwise>				
				${user.firstName} ${user.secondName }
			</c:otherwise>
		</c:choose>.
	</h1>
</header>