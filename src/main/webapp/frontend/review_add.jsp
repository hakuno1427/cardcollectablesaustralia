<!--
    @author Sera Jeong 12211242 Created Date: 03/09/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Add Review" />
</jsp:include>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Add New Review</h2>
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <!-- Review form -->
        <form action="review_add_save" method="post" style="max-width: 800px; margin: 0 auto;">
            
			<div class="form-group row">
			    <label class="col-sm-4 col-form-label">Seller :</label>
			    <div class="col-sm-8">
			        <select name="sellerId" class="form-control" required>
			         	<option value="" disabled selected>Select seller name here...</option>
			            <c:forEach var="seller" items="${sellers}">
			                <option value="${seller.userId}">
			                    ${seller.firstName} ${seller.lastName}
			                </option>
			            </c:forEach>
			        </select>
			    </div>
			</div>
                 
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Rating :</label>
                <div class="col-sm-8">
                    <input type="number" name="rating" class="form-control" min="1" max="5" step="1"
                        value="${review.rating}" required />
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Comment :</label>
                <div class="col-sm-8">
                    <textarea name="comment" class="form-control" rows="4" required>${review.comment}</textarea>
                </div>
            </div>

            <div class="row">&nbsp;</div>
            <div class="row">
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary mr-3">Save</button>
                    <input type="button" value="Cancel" class="btn btn-secondary"
                        onclick="history.go(-1);" />
                </div>
            </div>
        </form>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
</html>
