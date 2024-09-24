<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="My Profile" />
	</jsp:include>
<body>
<div class="container">
	<jsp:directive.include file="header.jsp" />
	
	<div class="row">
		<div class="col text-center"><h3>My Profile</h3></div>
	</div>		
			
	<div class="row">
		<div class="col-sm-3"></div>
		
		<div class="col-sm-6">
			<div class="row">
				<div class="col"><b>E-mail Address:</b></div>
				<div class="col">${user.email}</div>
			</div>
			
			<div class="row">
				<div class="col"><b>First Name:</b></div>
				<div class="col">${user.firstName}</div>
			</div>
					
			<div class="row">
				<div class="col"><b>Last Name:</b></div>
				<div class="col">${user.lastName}</div>
			</div>
		
			<div class="row">
				<div class="col"><b>Phone Number:</b></div>
				<div class="col">${user.phone}</div>
			</div>
				
			<c:if test="${not empty user.description}">
			<div class="row">
				<div class="col"><b>Description</b></div>
				<div class="col">${user.description}</div>
			</div>
			</c:if>
			<div class="row">&nbsp;</div>
			
			<div class="row">
				<div class="col text-center"><b><a href="edit_profile">Edit</a></b></div>
			</div>		
		</div>
		
		<div class="col-sm-3"></div>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</div>
</body>
</html>