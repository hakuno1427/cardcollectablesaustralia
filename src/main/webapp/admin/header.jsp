<!--
	@author Sera Jeong 12211242 Created Date: 24/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="auth" uri="/WEB-INF/tld/permission.tld" %>

<div class="row">
    <a href="${pageContext.request.contextPath}/">
        <img src="https://i.imgur.com/OQVteNg.png" class="img-fluid" />
    </a>
</div>

<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#topNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="topNavbar">

        <ul class="navbar-nav">
            <!-- Links shown when user is not logged in -->
            <c:if test="${user == null}">
                <li class="nav-item"><a href="login" class="nav-link">Login</a></li>
                <li class="nav-item"><a href="register" class="nav-link">Register Admin</a></li>
            </c:if>

            <!-- Links shown when user is logged in -->
            <c:if test="${user != null}">
                <li class="nav-item"><a href="/admin/" class="nav-link">Welcome, administrator ${user.firstName}</a></li>
                
                <c:if test="${auth:hasPermission(role, 'MANAGE_USER')}">
                	<li class="nav-item"><a href="/admin/users" class="nav-link">User Management</a></li>
                </c:if>
                <c:if test="${auth:hasPermission(role, 'VIEW_CARD_CARTALOGUE')}">
                	<li class="nav-item"><a href="/admin/catalogue" class="nav-link">Catalogue</a></li>
                </c:if>
                <c:if test="${auth:hasPermission(role, 'MANAGE_REVIEW')}">
	                <li class="nav-item"><a href="/admin/review_manage" class="nav-link">Review Management</a></li>
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
