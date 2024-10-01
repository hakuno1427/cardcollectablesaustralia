<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Edit My Profile" />
</jsp:include>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
<title>Edit My Profile</title>
<style>
.form-label {
	font-weight: bold;
}

.form-control {
	margin-bottom: 15px;
}

.container-fluid {
	padding: 20px;
}

.text-center {
	margin-bottom: 20px;
}

.btn {
	margin-right: 10px;
}
</style>
</head>

<body class="d-flex flex-column min-vh-100">
	<!-- Header Section -->
	<jsp:directive.include file="header.jsp" />

	<div class="container-fluid flex-grow-1">
		<h3 class="text-dark mb-4">Edit My Profile</h3>
		<div class="card shadow mb-3">
			<div class="card-header py-3">
				<p class="text-primary m-0 fw-bold">User Settings</p>
			</div>
			<div class="card-body">
				<form action="edit_profile" method="post"
					style="max-width: 800px; margin: 0 auto;">

					<div class="row" style="margin-bottom: 25px; text-align: left;">

						<div class="col-sm-4 col-md-4 col-lg-3 col-xl-2 col-xxl-2"
							style="display: inline; text-align: center; margin-bottom: 25px;">
							<img class="rounded-circle mb-3 mt-4 img-fluid"
								src="https://cdn-icons-png.flaticon.com/512/4645/4645949.png"
								style="display: inline; max-height: 110px;" /><br />
						</div>

						<div
							class="col-sm-8 col-md-8 col-lg-9 col-xl-10 col-xxl-10 align-self-center">
							<div class="row">

								<!-- Email Address (Non-editable) -->
								<div class="col-md-12 text-start">
									<div class="mb-3">
										<label class="form-label" for="email"><strong>Email
												Address</strong></label> <input id="email" class="form-control" type="email"
											value="${user.email}" name="email" readonly />
									</div>
								</div>
								<!-- Phone Number -->
								<div class="col-md-12 text-start">
									<div class="mb-3">
										<label class="form-label" for="phone"><strong>Phone
												Number</strong></label> <input id="phone" class="form-control" type="text"
											placeholder="Phone Number" name="phone" value="${user.phone}"
											required minlength="9" maxlength="15" />
									</div>
								</div>


							</div>
						</div>

						<!-- First Name -->
						<div class="row">
							<div class="col-md-6">
								<label class="form-label" for="firstname">First Name:</label> <input
									type="text" id="firstname" name="firstname"
									class="form-control" value="${user.firstName}" required
									minlength="2" maxlength="30" />
							</div>

							<!-- Last Name -->
							<div class="col-md-6">
								<label class="form-label" for="lastname">Last Name:</label> <input
									type="text" id="lastname" name="lastname" class="form-control"
									value="${user.lastName}" required minlength="2" maxlength="30" />
							</div>
						</div>



						<!-- Password -->
						<div class="row">
							<div class="col-md-6">
								<label class="form-label" for="password">Password:</label> <input
									type="password" id="password" name="password"
									class="form-control" maxlength="16"
									placeholder="Leave blank if you don't want to change password" />
							</div>

							<!-- Confirm Password -->
							<div class="col-md-6">
								<label class="form-label" for="confirmPassword">Confirm
									Password:</label> <input type="password" id="confirmPassword"
									name="confirmPassword" class="form-control" maxlength="16"
									oninput="checkPasswordMatch(this)" />
							</div>
						</div>

						<!-- Description -->
						<div class="row">
							<div class="col-md-12">
								<label class="form-label" for="description">Description:</label>
								<textarea id="description" name="description"
									class="form-control">${user.description}</textarea>
							</div>
						</div>

						<!-- Buttons -->
						<div class="row">
							<div class="col-md-12 text-end">
								<button type="submit" class="btn btn-primary">Save</button>
								<button type="button" onclick="history.go(-1)"
									class="btn btn-secondary">Cancel</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Footer Section -->
	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript" src="js/user_form.js"></script>
</body>

</html>
