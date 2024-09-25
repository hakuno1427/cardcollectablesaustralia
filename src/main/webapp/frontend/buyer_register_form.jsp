<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Register as a Buyer" />
</jsp:include>
<body>
		<jsp:directive.include file="header.jsp" />
	<div class="container">

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Register as a New Buyer</h2>
			</div>
		</div>
		<div class="row">&nbsp;</div>

		<form action="register_buyer" method="post"
			style="max-width: 800px; margin: 0 auto;">
			<jsp:directive.include file="../common/user_form.jsp" />
		</form>
		
		<div class="row">
			<div class="col text-center">
				<a href="seller_register" class="nav-link">Sign Up as Seller</a>
			</div>
		</div>

		
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript" src="js/user_form.js"></script>
</html>