<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="auth" uri="/WEB-INF/tld/permission.tld"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<style>
.logo-img {
	max-width: 100px;
	margin: 10px;
}

.navbar-nav {
	margin-left: auto;
}
</style>

<div class="container-fluid">
	<div>
		<nav class="navbar navbar-expand-lg bg-light navbar-light">
			<!-- logo -->
			<div>
				<a href="${pageContext.request.contextPath}/"> <img
					src="https://i.imgur.com/JwkctkT.png" class="img-fluid logo-img" />
				</a>
			</div>

			<!-- Toggler -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#topNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="topNavbar">
				<!-- search -->

                <form action="search" method="get" class="d-flex flex-grow-1">
                    <input type="search" name="keyword" class="form-control me-2 w-100" placeholder="keyword" />
                    <input type="submit" value="Search" class="btn btn-outline-success" />
                </form>
				<ul class="navbar-nav ms-3">
					<c:if test="${user == null}">
						<li class="nav-item"><a href="login" class="nav-link">Sign
								In</a></li>
						<li class="nav-item"><a href="register" class="nav-link">Register</a></li>
					</c:if>
					<c:if test="${user != null}">
						<!-- Profile Dropdown Menu -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
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
									<a class="dropdown-item" href="proceed_orders">Pending
										Orders</a>
								</c:if>
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
