<!--
	@author Sera Jeong 12211242 Created Date: 24/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Admin Home" />
</jsp:include>
<body>
    <div class="container">
        <!-- Header Section -->
        <jsp:directive.include file="header.jsp" />

        <!-- Admin Home Section -->
        <div class="row text-center">
            <div class="col">
                <h2>Admin Home</h2>
                
                <!-- Main page before login -->
                <c:if test="${user == null}">
                    <div class="row justify-content-center">
                        <div class="col-md-8 col-sm-12">
                            <p>If you would like to use administration functions, please log in.</p> 
                            <a href="login" class="btn btn-primary">Login</a>
                            <hr class="my-4">
                            <p>If you do not have a valid account, please register first.</p>
                            <a href="register" class="btn btn-secondary">Register Admin</a>
                        </div>
                    </div>
                </c:if>

                <!-- Main page after login -->
                <c:if test="${user != null}">
                    <div class="row justify-content-center">
                        <div class="col-md-8 col-sm-12">
                            <p>Welcome, administrator ${user.firstName}!</p>
                            <p>To utilise administration functions, please click the respective buttons below.</p>
                            <hr class="my-4">
                            <c:if test="${auth:hasPermission(role, 'MANAGE_USER')}">
                            	<p>To manage users:</p> 
	                            <a href="/admin/users" class="btn btn-info">User Management</a>
	                            <p></p>
	                            <p></p>
	                            <p></p>
	                            <p></p>
	                            <p></p>	
                            </c:if>
                            
                           	<c:if test="${auth:hasPermission(role, 'MANAGE_CARD_CARTALOGUE')}">
                            	<p>To manage the card catalogue:</p> 
	                            <a href="/admin/catalogue" class="btn btn-info">Catalogue</a>
	                            <p></p>
	                            <p></p>
	                            <p></p>
	                            <p></p>
	                            <p></p>	
                            </c:if>
                            
                            <c:if test="${auth:hasPermission(role, 'MANAGE_REVIEW')}">
                            	<p>To monitor customer reviews:</p> 
	                            <a href="/admin/review_manage" class="btn btn-info">Review Management</a>
	                            <hr class="my-4">
	                            <a href="logout" class="btn btn-danger">Logout</a>
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <!-- Footer Section -->
        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
</html>
