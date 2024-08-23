<!--
	@author Sera Jeong 12211242
	Created Date: 22/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Add Card" />
</jsp:include>
<body>
	<div class="container">
		<jsp:directive.include file="header.jsp" />

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Add New Card</h2>
			</div>
		</div>
		<div class="row">&nbsp;</div>

		<form action="card_add_save" method="post"
			style="max-width: 800px; margin: 0 auto;">
			<jsp:directive.include file="../common/card_form.jsp" />
		</form>

		<jsp:directive.include file="footer.jsp" />
	</div>
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>