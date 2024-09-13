<!--
	@author Sera Jeong 12211242 Created Date: 24/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Register Admin" />
</jsp:include>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Register New Admin Account</h2>
                <p>Please use your company email (@cca.com) for your email address when you register.</p>
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <form action="register_save" method="post" style="max-width: 800px; margin: 0 auto;">
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">E-mail:</label>
                <div class="col-sm-8">
                    <input type="email" name="email" class="form-control" required minlength="5" maxlength="64" pattern="[a-zA-Z0-9._%+-]+@cca.com" title="Email must end with @ccam.com"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">First Name:</label>
                <div class="col-sm-8">
                    <input type="text" name="firstname" class="form-control" required minlength="2" maxlength="30" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Last Name:</label>
                <div class="col-sm-8">
                    <input type="text" name="lastname" class="form-control" required minlength="2" maxlength="30" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Password:</label>
                <div class="col-sm-8">
                    <input type="password" id="password" name="password" class="form-control" required minlength="5" maxlength="16" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Confirm Password:</label>
                <div class="col-sm-8">
                    <input type="password" name="confirmPassword" class="form-control" required minlength="5" maxlength="16" oninput="checkPasswordMatch(this)" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Phone Number:</label>
                <div class="col-sm-8">
                    <input type="text" name="phone" class="form-control" required minlength="9" maxlength="15" />
                </div>
            </div>
            <div class="row">&nbsp;</div>
            <div class="row">
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary mr-3">Save</button>
                    <input type="button" value="Cancel" class="btn btn-secondary" onclick="history.go(-1);" />
                </div>
            </div>
        </form>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
<script type="text/javascript" src="js/user_form.js"></script>
</html>
