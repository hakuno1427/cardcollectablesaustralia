<!--
	@author Sera Jeong 12211242 Created Date: 24/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Review Management" />
</jsp:include>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Review Management</h2>
                <p>You can monitor all reviews here.</p>
                <p>It is important to ensure that all reviews and ratings are fair and respectful. Any biased or unfair review and rating should be removed (hidden) from users' screens to maintain accurate feedback and a reliable community.</p>
                <hr class="my-4">
                <p><em>Click the 'Hide' button to hide a review from users' screens. To make the review visible again, click the 'Unhide' button.</em> </p>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        
        <!-- Existing Reviews Section -->
        <div class="row">
            <div class="col">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Review ID</th>
                            <th>Buyer ID</th>
                            <th>Seller ID</th>
                            <th>Rating</th>
                            <th>Comment</th>
                            <th>Review Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listReviews}" var="review">
                            <tr>
                                <td>${review.reviewId}</td>
                                <td>${review.buyerId}</td>
                                <td>${review.sellerId}</td>
                                <td>${review.rating}</td>
                                <td>${review.comment}</td>
                                <td>${review.reviewDate}</td>
                                <td>
                                    <a href="review_hide?id=${review.reviewId}" class="btn btn-danger btn-sm">Hide</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- Pagination Links -->
        <div class="row">
            <div class="col text-center">
                <c:if test="${currentPage > 1}">
                    <a href="?page=1">&laquo; First</a>
                    <a href="?page=${currentPage - 1}">&lt; Previous</a>
                </c:if>
                
                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span>${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}">Next &gt;</a>
                    <a href="?page=${totalPages}">Last &raquo;</a>
                </c:if>
            </div>
        </div>

        <div class="row">&nbsp;</div>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>
