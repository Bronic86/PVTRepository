<header style='text-align: center'>
	<h1>
		Hello 
		
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.username" />
		</sec:authorize>
		<sec:authorize access="isAnonymous()" >
		Guest
		</sec:authorize>.
	</h1>
</header>