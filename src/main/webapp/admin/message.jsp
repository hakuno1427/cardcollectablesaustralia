<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>
<body>
		<jsp:directive.include file="header.jsp" />
	<div class="container">


		<div>&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h4>${message}</h4>
			</div>
		</div>


	</div>
			<jsp:directive.include file="footer.jsp" />
</body>
</html>