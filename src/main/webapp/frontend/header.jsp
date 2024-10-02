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

.navbar-nav {
	margin-left: auto;
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

.navbar-nav .nav-link {
	color: #fff !important;
	padding: 0.75rem 1.5rem;
}

.navbar-nav .nav-link:hover {
	margin-left: auto;
	color: #18BC9C !important;
}

.search-form {
	margin: 40px 5px;
	font: normal 12px sans-serif;
	box-shadow: 1px 2px 4px 0 rgba(0, 0, 0, 0.08);
}

*, ::after, ::before {
	box-sizing: border-box;
}
</style>
</head>

<div class="container-fluid">

	<!-- navbar -->
	<div>
		<nav class="navbar navbar-expand-lg bg-light navbar-light">

			<!-- brand -->
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">
				<img class="rounded-circle img-fluid logo-img"
				src="https://i.imgur.com/JwkctkT.png" /> Card Collectables Australia
			</a>


			<!-- Toggler -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#topNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="topNavbar">

				<ul class="navbar-nav ms-auto">
					<c:if test="${user == null}">
						<li class="nav-item"><a href="login" class="nav-link">Sign
								In</a></li>
						<li class="nav-item"><a href="register" class="nav-link">Register</a></li>
					</c:if>

					<c:if test="${user != null}">
						<!-- Profile Dropdown Menu -->
						<li class="nav-item dropdown"><a
							class="dropdown-toggle nav-link" aria-expanded="false"
							data-bs-toggle="dropdown" href="#";"> <i
								class="bi bi-person-circle"></i> Welcome, ${user.firstName}
						</a>
							<div class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<a class="dropdown-item" href="profile">Profile</a>
								<c:if test="${auth:hasPermission(role, 'VIEW_MY_ORDERS')}">
									<a class="dropdown-item" href="view_orders">My Orders</a>
								</c:if>
								<c:if test="${auth:hasPermission(role, 'REVIEW_BUYER')}">
									<a class="dropdown-item" href="review_buyer">Buyer Reviews</a>
								</c:if>
								<c:if test="${auth:hasPermission(role, 'MANAGE_MY_LISTING')}">
									<a class="dropdown-item" href="listings">My Listing</a>
								</c:if>
								<c:if test="${auth:hasPermission(role, 'PROCEED_ORDER')}">
									<a class="dropdown-item" href="view_seller_orders">Pending
										Orders</a>
								</c:if>
								<a href="my_messages" class="dropdown-item">Messages</a>
								<c:if test="${auth:hasPermission(role, 'REVIEW_SELLER')}">
									<a class="dropdown-item" href="review_seller">Seller
										Reviews</a>
								</c:if>
								<a class="dropdown-item" href="logout">Logout</a>
							</div></li>
							

						<c:if test="${auth:hasPermission(role, 'MANAGE_MY_ORDERS')}">
							<li class="nav-item"><a href="cart" class="nav-link"><i
									class="bi bi-cart"></i></a></li>
						</c:if>
					</c:if>
				</ul>
			</div>
		</nav>
	</div>

	<!-- search -->
	<div>
	<form action="search" method="get" class="search-form">
		<div class="input-group">
			<span class="input-group-text"><i class="fa fa-search"></i></span> <input
				class="form-control" type="search" name="keyword"
				class="form-control me-2 w-100" placeholder="I am looking for.." />
			<button type="submit" class="btn btn-primary py-0">
				<i class="fas fa-search"></i>
			</button>
		</div>
	</form>
	</div>
	
	
</div>

<div>&nbsp;</div>
<div class="row justify-content-center">
	<c:forEach var="category" items="${listCategory}" varStatus="status">
		<a href="view_category?id=${category.categoryId}"> <font size="+1"><b><c:out
						value="${category.name}" /></b></font>
		</a>
		<c:if test="${not status.last}">
            &nbsp; | &nbsp;
        </c:if>
	</c:forEach>
</div>
