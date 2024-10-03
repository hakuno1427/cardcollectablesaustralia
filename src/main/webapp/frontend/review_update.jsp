<!--
    @author Sera Jeong 12211242 Created Date: 05/09/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Update Review" />
</jsp:include>
<body class="d-flex flex-column min-vh-100">
        <jsp:directive.include file="header_without_searchbar.jsp" />
    <div class="container flex-grow-1">


        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Update Review</h2>
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <!-- Review update form -->
        <form action="review_update_save" method="post" style="max-width: 800px; margin: 0 auto;">
            <!-- Hidden field to store the review ID -->
            <input type="hidden" name="reviewId" value="${review.reviewId}" />

            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Seller :</label>
                <div class="col-sm-8">
                    <select name="sellerId" class="form-control" disabled>
                        <option value="" disabled>Select seller name here...</option>
                        <c:forEach var="seller" items="${sellers}">
                            <option value="${seller.userId}" <c:if test="${seller.userId == review.sellerId}">selected</c:if>>
                                ${seller.firstName} ${seller.lastName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Rating :</label>
                <div class="col-sm-8">
                    <input type="number" name="rating" class="form-control" min="1" max="5" step="0.1"
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

    </div>
        <jsp:directive.include file="footer.jsp" />
</body>
</html>