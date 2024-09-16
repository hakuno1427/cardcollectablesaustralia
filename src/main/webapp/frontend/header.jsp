<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="auth" uri="/WEB-INF/tld/permission.tld" %>

<div class="row">
    <a href="${pageContext.request.contextPath}/">
        <img src="https://i.imgur.com/OQVteNg.png" class="img-fluid" />
    </a>
</div>

<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#topNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="topNavbar">
        <form action="search" method="get" class="form-inline">
            <input type="search" name="keyword" class="form-control mr-sm-2 mt-1" placeholder="keyword" />
            <input type="submit" value="Search" class="btn btn-outline-success mt-1" />
        </form>
        <ul class="navbar-nav">
            <c:if test="${user == null}">
                <li class="nav-item"><a href="login" class="nav-link">Sign In</a></li>
                <li class="nav-item"><a href="register" class="nav-link">Register</a></li>
            </c:if>
            <c:if test="${user != null}">
                <li class="nav-item"><a href="profile" class="nav-link">Welcome, ${user.firstName}</a></li>
                <li class="nav-item"><a href="my_messages" class="nav-link">Messages</a></li>
                <c:if test="${auth:hasPermission(role, 'VIEW_MY_ORDERS')}">
                    <li class="nav-item"><a href="view_orders" class="nav-link">My Orders</a></li>
                </c:if>
                <c:if test="${auth:hasPermission(role, 'MANAGE_MY_ORDERS')}">
                    <li class="nav-item"><a href="cart" class="nav-link">Cart</a></li>
                </c:if>
               <c:if test="${auth:hasPermission(role, 'REVIEW_BUYER')}">
                    <li class="nav-item"><a href="review_buyer" class="nav-link">Buyer Reviews</a></li>
                </c:if>
                <c:if test="${auth:hasPermission(role, 'MANAGE_MY_LISTING')}">
                    <li class="nav-item"><a href="listings" class="nav-link">My Listing</a></li>
                </c:if>
                <c:if test="${auth:hasPermission(role, 'PROCEED_ORDER')}">
                    <li class="nav-item"><a href="view_seller_orders" class="nav-link">Pending Orders</a></li>
                </c:if>
                <c:if test="${auth:hasPermission(role, 'REVIEW_SELLER')}">
                    <li class="nav-item"><a href="review_seller" class="nav-link">Seller Reviews</a></li>
                </c:if>
                <li class="nav-item"><a href="logout" class="nav-link">Logout</a></li>
            </c:if>
        </ul>
    </div>
</nav>

<div>&nbsp;</div>
<div class="row justify-content-center">
    <c:forEach var="category" items="${listCategory}" varStatus="status">
        <a href="view_category?id=${category.categoryId}">
            <font size="+1"><b><c:out value="${category.name}" /></b></font>
        </a>
        <c:if test="${not status.last}">
            &nbsp; | &nbsp;
        </c:if>
    </c:forEach>
</div>
