<!--
	@author Sera Jeong 12211242 Created Date: 24/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Login" />
</jsp:include>
<body>
		<jsp:directive.include file="header.jsp" />
	<div class="container">


		<div class="row">&nbsp;</div>

		<div class="row">
			<div class="col text-center">
				<h2>Admin Login</h2>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<c:if test="${message != null}">
			<div class="row">
				<div class="col text-center">
					<h4 class="message">${message}</h4>
				</div>
			</div>
		</c:if>

		<form action="login" method="post"
			style="max-width: 400px; margin: 0 auto;">
			<div class="border border-secondary rounded p-3">
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Email:</label>
					<div class="col-sm-8">
						<input type="email" name="email" class="form-control" required
							minlength="5" maxlength="64">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-4 col-form-label">Password:</label>
					<div class="col-sm-8">
						<input type="password" name="password" class="form-control"
							required minlength="5" maxlength="16">
					</div>
				</div>
				<div class="row">
					<div class="col text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</div>
			</div>
		</form>


	</div>
			<jsp:directive.include file="footer.jsp" />
</body>
</html>