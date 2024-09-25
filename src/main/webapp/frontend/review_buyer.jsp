<!--
    @author Sera Jeong 12211242 Created Date: 01/09/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Buyer Reviews" />
</jsp:include>
<body>
	<jsp:directive.include file="header.jsp" />
	<div class="container">

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Buyer Reviews</h2>
				<p>You can leave reviews and ratings about the sellers from your
					card purchases here.</p>
				<hr class="my-4">
				<div>
					<p>To add a new review and rating:</p>
					<a href="review_add" class="btn btn-primary">Add Review</a>
				</div>
				<hr class="my-4">
				<div>
					<p>Your Buyer ID number is: ${buyerId}</p>
				</div>
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
							<th>Seller ID</th>
							<th>Comment</th>
							<th>Rating</th>
							<th>Review Date</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${reviews}" var="review">
							<tr>
								<td>${review.reviewId}</td>
								<td>${review.sellerId}</td>
								<td>${review.comment}</td>
								<td>${review.rating}</td>
								<td>${review.reviewDate}</td>
								<td><a href="review_update?id=${review.reviewId}"
									class="btn btn-warning btn-sm">Update</a> <a
									href="review_delete?id=${review.reviewId}"
									class="btn btn-danger btn-sm">Delete</a></td>
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

		
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
