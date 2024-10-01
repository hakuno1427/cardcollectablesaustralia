<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="auth" uri="/WEB-INF/tld/permission.tld"%>

<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic&display=swap">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<link rel="stylesheet" href="assets/fonts/simple-line-icons.min.css">
<link rel="stylesheet" href="assets/css/Navbar-With-Button-icons.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
.logo-img {
	max-width: 100px;
	margin: 10px;
}

.rounded-circle {
	border-radius: 50% !important;
}

.navbar {
	background-color: #2c3e50 !important;
	font-family: 'Montserrat', sans-serif;
	font-weight: 700;
	text-transform: uppercase;
	padding-top: 1rem;
	padding-bottom: 1rem;
}

.navbar .navbar-brand {
	color: #ffffff !important;
	font-size: 1.8rem;
}

.navbar-brand:hover {
	color: #13967d !important;
}

.navbar-toggler {
	color: #ffffff !important;
	background-color: #18BC9C !important;
}

.navbar-nav .nav-link {
	color: #fff !important;
	padding: 0.75rem 1.5rem;
}

.navbar-nav .nav-link:hover {
	color: #18BC9C !important;
}

.dropdown-menu {
	background-color: rgba(33, 37, 41, 0.9);
	border: none;
	border-radius: 0.375rem;
	box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

.dropdown-item {
	color: rgba(224, 217, 217, 0.9);
	padding: 0.75rem 1.5rem;
}

.dropdown-item:hover {
	background-color: rgba(255, 255, 255, 0.1);
	color: #fff;
}

.dropdown-toggle::after {
	display: none;
}
</style>
</head>

<!-- header -->
<div class="container-fluid">
	<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="${pageContext.request.contextPath}/admin">
			<img class="rounded-circle img-fluid logo-img"
			src="https://i.imgur.com/JwkctkT.png" /> Card Collectables Australia
			Admin
		</a>

		<!-- Toggler -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#topNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="topNavbar">
			<ul class="navbar-nav ms-auto">
				<c:if test="${user == null}">
					<li class="nav-item"><a href="login" class="nav-link">Login</a></li>
					<li class="nav-item"><a href="register" class="nav-link">Register
							Admin</a></li>
				</c:if>

				<c:if test="${user != null}">
					<li class="nav-item dropdown"><a
						class="dropdown-toggle nav-link" aria-expanded="false"
						data-bs-toggle="dropdown" href="#"> <i
							class="bi bi-person-circle"></i> Welcome, ${user.firstName}
					</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<c:if test="${auth:hasPermission(role, 'MANAGE_USER')}">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/users">User Management</a>
							</c:if>
							<c:if test="${auth:hasPermission(role, 'VIEW_CARD_CARTALOGUE')}">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/catalogue">Catalogue</a>
							</c:if>
							<c:if test="${auth:hasPermission(role, 'MANAGE_REVIEW')}">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/admin/review_manage">Review
									Management</a>
							</c:if>
							<a class="dropdown-item" href="logout">Logout</a>
						</div></li>
				</c:if>
			</ul>
		</div>
	</nav>
</div>
