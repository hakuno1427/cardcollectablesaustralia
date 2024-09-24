<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../frontend/page_head.jsp">
	<jsp:param name="pageTitle" value="Add Listing" />
</jsp:include>
<style>
.seller-section {
	border: 1px solid #ddd;
	border-radius: .25rem;
	padding: 15px;
	margin-bottom: 20px;
	background-color: #f8f9fa;
}

.seller-img {
	border-radius: 50%;
	width: 80px;
	height: 80px;
	object-fit: cover;
}

.custom-card {
	height: auto; /* Fixed height */
	overflow: hidden; /* Hide overflow */
	position: relative;
	/* Position relative for absolute positioning of more button */
}

.custom-card .card-text {
	display: -webkit-box;
	-webkit-line-clamp: 3; /* Number of lines to show before truncation */
	-webkit-box-orient: vertical;
	overflow: hidden;
	text-overflow: ellipsis;
}

.custom-card.expanded .card-text {
	display: block;
	-webkit-line-clamp: unset; /* Remove line clamping */
	height: auto; /* Allow height to grow */
}

.custom-card .more-button {
	bottom: 10px;
	left: 10px;
	background: white;
	border: none;
	color: #007bff;
	cursor: pointer;
}
</style>
<body>
	<div class="container">
		<jsp:directive.include file="../frontend/header.jsp" />

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Seller Profile</h2>
				<c:choose>
					<c:when test="${not empty seller.description}">
						<p>${seller.description}</p>
					</c:when>
					<c:otherwise>
						<p>Explore the seller's profile to discover their range of cards
					and more about their offerings.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<hr />
		<div class="seller-section mb-4">
			<div class="row align-items-center">
				<div class="col-md-2 text-center">
					<img
						src="https://images.pexels.com/photos/2838511/pexels-photo-2838511.jpeg"
						alt="Seller Image" class="seller-img">
				</div>
				<div class="col-md-8 text-center">
					<h5>Seller Name</h5>
					<p>${seller.firstName} ${seller.lastName}</p>
				</div>
				<div class="col-md-2 text-center">
					<p>
						<strong>Total Cards:</strong>
					</p>
					<%
					// Retrieve the cardsCount from the request attribute
					Integer cardsCount = (Integer) request.getAttribute("cardsCount");

					// If cardsCount is null, default to 0
					if (cardsCount == null) {
						cardsCount = 0;
					}
					%>

					<p>
						<%=cardsCount > 100 ? "100+" : cardsCount%>
					</p>
				</div>
			</div>
		</div>
		<hr />
		<!-- Tab Navigation -->
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item"><a class="nav-link active" id="cards-tab"
				data-toggle="tab" href="#cards" role="tab" aria-controls="cards"
				aria-selected="true">Cards (${cardsCount})</a></li>
			<li class="nav-item"><a class="nav-link" id="reviews-tab"
				data-toggle="tab" href="#reviews" role="tab" aria-controls="reviews"
				aria-selected="false">Reviews (${totalReviews})</a></li>
		</ul>

		<!-- Tab Content -->
		<div class="tab-content" id="myTabContent">
			<!-- Cards Tab -->
			<div class="tab-pane fade show active" id="cards" role="tabpanel"
				aria-labelledby="cards-tab">
				<div class="row mt-3">
					<c:forEach items="${listOfListings}" var="listing">
						<div class="col-md-3">
							<div class="card mb-3 custom-card">
								<img src="${listing.card.imageUrl}"
									alt="${listing.card.cardName}" class="card-img-top">
								<div class="card-body">
									<h5 class="card-title">${listing.card.cardName}</h5>
									<p class="card-text" id="card-text">${listing.card.description}</p>
									<a href="#" class="btn btn-primary btn-sm more-button">Read
										more</a>
								</div>
								<ul class="list-group list-group-flush">
									<li class="list-group-item"><b>Market Price: </b>
										${listing.card.marketprice}</li>
									<li class="list-group-item"><b>Price: </b>
										${listing.price}</li>
								</ul>
								<div class="card-body text-center">
									<a type="button" href="add_to_cart?listingId=${listing.listingId}" class="btn btn-success btn-sm">Add
										to Card</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<c:if test="${cardsCurrentPage > 1}">
							<li class="page-item"><a class="page-link"
								href="?sellerId=${seller.userId}&cardPage=${cardsCurrentPage - 1}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>

						<c:forEach var="i" begin="1" end="${cardsTotalPages}">
							<li
								class="page-item <c:if test="${i == cardsCurrentPage}">active</c:if>">
								<a class="page-link"
								href="?sellerId=${seller.userId}&cardPage=${i}">${i}</a>
							</li>
						</c:forEach>

						<c:if test="${cardsCurrentPage < cardsTotalPages}">
							<li class="page-item"><a class="page-link"
								href="?sellerId=${seller.userId}&cardPage=${cardsCurrentPage + 1}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
				</nav>
			</div>

			<!-- Reviews Tab -->
			<div class="tab-pane fade" id="reviews" role="tabpanel"
				aria-labelledby="reviews-tab">
				<div class="table-responsive mt-3">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Comment</th>
								<th>Rating</th>
								<th>Review Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${reviews}" var="review">
								<tr>
									<td>${review.comment}</td>
									<td>${review.rating}</td>
									<td>${review.reviewDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- Pagination Links -->
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<c:if test="${reviewCurrentPage > 1}">
							<li class="page-item"><a class="page-link"
								href="?sellerId=${seller.userId}&reviewPage=${reviewCurrentPage - 1}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>

						<c:forEach var="i" begin="1" end="${reviewTotalPages}">
							<li
								class="page-item <c:if test="${i == reviewCurrentPage}">active</c:if>">
								<a class="page-link"
								href="?sellerId=${seller.userId}&reviewPage=${i}">${i}</a>
							</li>
						</c:forEach>

						<c:if test="${reviewCurrentPage < reviewTotalPages}">
							<li class="page-item"><a class="page-link"
								href="?sellerId=${seller.userId}&reviewPage=${reviewCurrentPage + 1}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>

		<div class="row">&nbsp;</div>

		<div class="text-center">
			<button type="button" class="btn btn-secondary"
				onclick="history.go(-1);">Back</button>
		</div>


		<jsp:directive.include file="../frontend/footer.jsp" />
	</div>
	<script>
	document.addEventListener('DOMContentLoaded', function () {
	    // Get all cards and their corresponding "more" buttons
	    const cards = document.querySelectorAll('.custom-card');
	    
	    cards.forEach(card => {
	        const button = card.querySelector('.more-button');

	        button.addEventListener('click', function (e) {
	            e.preventDefault();
	            card.classList.toggle('expanded');
	            button.textContent = card.classList.contains('expanded') ? 'Read less' : 'Read more';
	        });
	    });
	});
	</script>
</body>
</html>