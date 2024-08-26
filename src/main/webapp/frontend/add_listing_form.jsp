<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Add Listing" />
</jsp:include>
<body>
	<div class="container">
		<jsp:directive.include file="header.jsp" />
		<div class="row">
			<div class="col text-center">
				<h2>${action} New Listing</h2>
				<p>You can add your new listing from here.</p>
				<hr>
			</div>
		</div>

		<form action="listings" method="post"
			style="max-width: 800px; margin: 0 auto;">
			<jsp:directive.include file="../common/listing_form.jsp" />
		</form>

		<jsp:directive.include file="footer.jsp" />
	</div>
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>