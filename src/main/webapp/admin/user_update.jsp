<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Edit My Profile" />
	</jsp:include>
<body>
	<jsp:directive.include file="header.jsp" />
<div class="container">

	
	<div class="row">&nbsp;</div>
	
	<div class="row">		
		<div class="col text-center"><h2>Edit {$selectedUser.firstName} Profile</h2></div>
	</div>
	
	<div class="row">&nbsp;</div>
	
	<form action="user_update?id=${selectedUser.userId}" method="post" style="max-width: 800px; margin: 0 auto;">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">E-mail:</label>
			<div class="col-sm-8"><b>${selectedUser.email}</b> (Cannot be changed)</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">First Name:</label>
			<div class="col-sm-8">
				<input type="text" name="firstname" class="form-control" value="${selectedUser.firstName}" required minlength="2" maxlength="30" />
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Last Name:</label>
			<div class="col-sm-8">
				<input type="text" name="lastname" class="form-control" value="${selectedUser.lastName}" required minlength="2" maxlength="30" />
			</div>
		</div>			
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Phone Number:</label>
			<div class="col-sm-8">
				<input type="text" name="phone" class="form-control" value="${selectedUser.phone}" required minlength="9" maxlength="15" />
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Role:</label>
			<div class="col-sm-8">
				${selectedUser.role.name}
			</div>
		</div>					
		<div class="row">&nbsp;</div>
		<div class="form-group row">
			<div class="col text-center">
				<button type="submit" class="btn btn-primary mr-3">Save</button>
				<button type="button" onclick="history.go(-1)" class="btn btn-secondary">Cancel</button>
			</div>
		</div>				
	</form>

</div>
	<jsp:directive.include file="footer.jsp" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>