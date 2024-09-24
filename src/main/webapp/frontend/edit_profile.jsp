<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Edit My Profile" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<div class="row">&nbsp;</div>
	
	<div class="row">		
		<div class="col text-center"><h2>Edit My Profile</h2></div>
	</div>
	
	<div class="row">&nbsp;</div>
	
	<form action="edit_profile" method="post" style="max-width: 800px; margin: 0 auto;">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">E-mail:</label>
			<div class="col-sm-8"><b>${user.email}</b> (Cannot be changed)</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">First Name:</label>
			<div class="col-sm-8">
				<input type="text" name="firstname" class="form-control" value="${user.firstName}" required minlength="2" maxlength="30" />
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Last Name:</label>
			<div class="col-sm-8">
				<input type="text" name="lastname" class="form-control" value="${user.lastName}" required minlength="2" maxlength="30" />
			</div>
		</div>			
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Phone Number:</label>
			<div class="col-sm-8">
				<input type="text" name="phone" class="form-control" value="${user.phone}" required minlength="9" maxlength="15" />
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Password:</label>
			<div class="col-sm-8">
				<input type="password" id="password" name="password" class="form-control" maxlength="16"
					placeholder="Leave password fields blank if you don't want to change password" />
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Confirm Password:</label>
			<div class="col-sm-8">
				<input type="password" name="confirmPassword" class="form-control" maxlength="16" oninput="checkPasswordMatch(this)" />
			</div>
		</div>	
		
		<div class="form-group row">
			<label class="col-sm-4 col-form-label">Description</label>
			<div class="col-sm-8">
				<textarea name="description" class="form-control">${user.description}</textarea>
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
	<jsp:directive.include file="footer.jsp" />
</div>
<script type="text/javascript" src="js/user_form.js"></script>	
</body>
</html>