<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Card Detail" />
</jsp:include>

<style>
.container {
	margin-top: 20px;
	width: 80%;
	margin: 0 auto;
}

.card-detail {
	display: flex;
	justify-content: space-between;
	align-items: flex-start;
}

.card-img {
	max-width: 300px;
	height: auto;
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-info {
	flex: 1;
	margin-left: 20px;
	color: #333;
	font-family: 'Arial', sans-serif;
}

.card-info h2 {
	margin-top: 0;
	color: #2c3e50;
	font-size: 2em;
	font-weight: bold;
}

.card-description p {
	font-size: 1.1em;
	line-height: 1.6;
	color: #555;
	margin: 10px 0;
}

.market-price {
	margin-top: 15px;
	font-weight: bold;
	font-size: 1.3em;
	color: #d35400;
}

.listings-info {
	margin-top: 30px;
}

.listings-info h4 {
	color: #2980b9;
	font-size: 1.5em;
	border-bottom: 2px solid #2980b9;
	padding-bottom: 5px;
}

.listing-item {
	margin-bottom: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px;
	background-color: #f9f9f9;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	font-size: 1.1em;
}

.listing-item span {
	font-weight: bold;
	color: #555;
}

.listing-item div {
	flex: 1;
	padding: 0 10px;
}

.add-to-cart-btn {
	background-color: #28a745;
	color: white;
	padding: 8px 12px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.add-to-cart-btn:hover {
	background-color: #218838;
}
</style>

<body>
	<div class="container">
		<jsp:directive.include file="header.jsp" />

		<div class="card-detail">
			<div>
				<img class="card-img" src="${card.imageUrl}" alt="${card.cardName}">
			</div>
			<div class="card-info">
				<h2>${card.cardName}</h2>
				<div class="card-description">
					<p>
						<strong>Game:</strong> ${card.game}
					</p>
				</div>
				<div class="card-description">
					<p>
						<strong>Description:</strong> ${card.description}
					</p>
				</div>
				<div class="market-price">Market Price: $${card.marketprice}</div>

				<!-- Listing Info -->
				<div class="listings-info">
					<h4>Available Listings:</h4>
					<c:choose>
						<c:when test="${fn:length(card.listings) == 0}">
							<p class="text-danger">Out of Stock</p>
						</c:when>
						<c:otherwise>
							<c:forEach items="${card.listings}" var="listing">
								<div class="listing-item">
									<div>
										<span>Seller:</span> <a
											href="view_seller?userId=${listing.seller.userId}">
											${listing.seller.firstName} ${listing.seller.lastName} </a>
									</div>
									<div>
										<span>Condition:</span> ${listing.condition}
									</div>
									<div>
										<span>Price:</span> $${listing.price}
									</div>
									<div>
										<span>Quantity:</span> ${listing.quantity}
									</div>
									<button class="add-to-cart-btn">Add to Cart</button>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<jsp:directive.include file="footer.jsp" />
	</div>
</body>
</html>
