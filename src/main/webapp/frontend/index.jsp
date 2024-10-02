<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="CCA - Index" />
</jsp:include>

<style>
/* Page container */
.page-container {
	max-width: 1500px;
	margin: 0 auto;
	padding: 0 20px;
	position: relative;
}

/* Top banner style */
.top-banner {
	width: 100%;
	height: 300px;
	overflow: hidden;
}

/* New listings and best sellers container styles */
.new-listings-section, .best-sellers-section {
	margin-bottom: 40px;
	padding: 20px;
	background-color: #e9ebed;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.new-listings-section h2, .best-sellers-section h2 {
	font-family: 'Montserrat', sans-serif;
	font-weight: 700;
	color: #2aa08a;
	text-transform: uppercase;
	margin-bottom: 20px;
}

/* Card styles */
.listing-item {
	margin-bottom: 15px;
}

.card {
	border: 1px solid #ddd;
	border-radius: 10px; /
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s ease;
}

.card:hover {
	transform: translateY(-5px);
}

.card-img-top {
	max-width: 100%;
	height: auto;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
}

.card-body {
	font-family: 'Montserrat', sans-serif;
}

.card-body a {
	text-decoration: none;
	font-weight: 700;
	color: #333;
}

.card-body a:hover {
	color: #007bff;
	text-decoration: none;
}

.card-body b {
	font-weight: 700;
	color: #18BC9C;
}

.card-body .card-name {
	text-align: center;
}

.card-body .card-name b:hover {
	color: #2aa08a;
}

.card-body .market-price b {
	color: #555;
	font-size: 14px;
}

.card-body .price {
	color: #555;
	font-size: 16px;
	font-weight: bold;
}

/* Ad banner */
.ad-banner {
	width: 100%;
	height: auto;
	margin-bottom: 20px;
}

.new-listings-section {
	margin-bottom: 40px;
}
</style>
</head>


<body>


	<!-- Top Banner Section -->
	<div class="container-fluid">
		<div class="top-banner">
			<img src="https://i.imgur.com/fccHfPo.png" alt="Top-banner"
				style="width: 100%; height: 100%; object-fit: cover;">
		</div>
	</div>

	<!-- Navbar Section -->
	<jsp:directive.include file="header.jsp" />


	<div class="page-container">

		<div class="container-fluid">
			<div class="row">
				<!-- Left Side: New Listings and Best sellers section -->
				<div class="col-lg-9 col-md-12">
					<!-- New Listings Section -->
					<div class="new-listings-section">
						<h2>New Listings:</h2>
						<div class="row">
							<c:forEach items="${listNewListings}" var="listing">
								<div class="col-md-3 col-sm-6 listing-item">
									<div class="card">
										<!-- Card Image Section -->
										<a href="view_card?serialNumber=${listing.card.serialNumber}"
											title="View Card ${listing.card.serialNumber}"> <img
											class="card-img-top img-fluid" src="${listing.card.imageUrl}"
											alt="${listing.card.cardName}">
										</a>

										<div class="card-body">
											<div class="card-name mb-3">
												<a
													href="view_card?serialNumber=${listing.card.serialNumber}"
													title="View Card ${listing.card.serialNumber}"> <b>${listing.card.cardName}</b>
												</a>
											</div>

											<div class="market-price mb-1">Market Price:
												$${(listing.card.marketprice !=-1) ?
												listing.card.marketprice : "N/A"}</div>
											<div class="price mb-3">Price: $${listing.price}</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>

					</div>

					<!-- Best Sellers Section -->
					<div class="best-sellers-section">
						<h2>Best Sellers:</h2>

						<div class="row">
							<c:forEach items="${listNewListings}" var="listing">
								<div class="col-md-3 col-sm-6 listing-item">
									<div class="card">
										<!-- Card Image Section -->
										<a href="view_card?serialNumber=${listing.card.serialNumber}"
											title="View Card ${listing.card.serialNumber}"> <img
											class="card-img-top img-fluid" src="${listing.card.imageUrl}"
											alt="${listing.card.cardName}">
										</a>

										<div class="card-body">
											<div class="card-name mb-3">
												<a
													href="view_card?serialNumber=${listing.card.serialNumber}"
													title="View Card ${listing.card.serialNumber}"> <b>${listing.card.cardName}</b>
												</a>
											</div>

											<div class="market-price mb-1">Market Price:
												$${(listing.card.marketprice !=-1) ?
												listing.card.marketprice : "N/A"}</div>
											<div class="price mb-3">Price: $${listing.price}</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>


					</div>
				</div>


				<!-- Right Side: Ad Banners -->
				<div class="col-lg-3 col-md-12">
					<!-- First Ad Banner -->
					<a href="https://github.com/hakuno1427/cardcollectablesaustralia"
						target="_blank">
						<div class="ad-banner">
							<img src="https://i.imgur.com/vJGXodh.png" alt="First Ad"
								style="width: 100%; height: auto;">
						</div>
					</a>
					<!-- Second Ad Banner -->
					<a href="https://github.com/hakuno1427/cardcollectablesaustralia"
						target="_blank">
						<div class="ad-banner">
							<img src="https://i.imgur.com/xWzzMZD.png" alt="Second Ad"
								style="width: 100%; height: auto;">
						</div>
					</a>
				</div>

			</div>
		</div>


	</div>
	<!-- Footer Section -->
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
