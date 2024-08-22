<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Card Catalogue" />
</jsp:include>
<body>
	<div class="container">
		<jsp:directive.include file="header.jsp" />

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Card Catalogue</h2>
				<p>You can manage the card catalogue here.</p>
                <div>
                    <a href="card_add" class="btn btn-primary">Add Card</a>
                    <a href="card_update" class="btn btn-warning">Update Card</a>
                    <a href="card_delete" class="btn btn-danger">Delete Card</a>
                </div>
			</div>
		</div>
		<div class="row">&nbsp;</div>

		<jsp:directive.include file="footer.jsp" />
	</div>
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>