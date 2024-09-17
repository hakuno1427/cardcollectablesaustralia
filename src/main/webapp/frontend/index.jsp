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

/* New listings container */
.new-listings-container {
	margin: 0 20px;
}

/* Card styles */
.listing-item {
	margin-bottom: 15px;
}

.card-img-top {
	max-width: 100%;
	height: auto;
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
	<div class="page-container">
		<!-- Top banner -->
		<div class="container-fluid">
			<div class="top-banner">
				<img src="https://i.imgur.com/yIqU2Nl.png" alt="Top-banner"
					style="width: 100%; height: 100%; object-fit: cover;">
			</div>
		</div>

		<!-- Header Section -->
		<jsp:directive.include file="header.jsp" />

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
											<a href="view_card?serialNumber=${listing.card.serialNumber}"
												title="View Card ${listing.card.serialNumber}"> <b>${listing.card.cardName}</b>
											</a>
											<div>
												<b>Market Price: $${(listing.card.marketprice !=-1) ?
													listing.card.marketprice : "N/A" }</b>
											</div>
											<div>
												<b>Price: $${listing.price}</b>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- Best Sellers Section -->
					<div class="best-sellers-section">
						<h2>Best Sellers:</h2>

  <!--   CAN'T WORK, REPLACED WITH NEW LISTINGS FOR TESTING LAYOUT
						<div class="row">
							<c:forEach items="${bestSellers}" var="bestSellerCard">
								<div class="col-md-3 col-sm-6 listing-item">
									<div class="card">
										
										<a
											href="view_card?serialNumber=${bestSellerCard.serialNumber}"
											title="View Card ${bestSellerCard.serialNumber}"> <img
											class="card-img-top img-fluid"
											src="${bestSellerCard.imageUrl}"
											alt="${bestSellerCard.cardName}">
										</a>
										<div class="card-body">
											<a
												href="view_card?serialNumber=${bestSellerCard.serialNumber}"
												title="View Card ${bestSellerCard.serialNumber}"> <b>${bestSellerCard.cardName}</b>
											</a>
											<div>
												<b>Market Price: $${(bestSellerCard.marketprice !=-1) ?
													bestSellerCard.marketprice : "N/A"}</b>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
-->

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
											<a href="view_card?serialNumber=${listing.card.serialNumber}"
												title="View Card ${listing.card.serialNumber}"> <b>${listing.card.cardName}</b>
											</a>
											<div>
												<b>Market Price: $${(listing.card.marketprice !=-1) ?
													listing.card.marketprice : "N/A" }</b>
											</div>
											<div>
												<b>Price: $${listing.price}</b>
											</div>
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
						<div class="ad-banner">
							<img src="https://i.imgur.com/vJGXodh.png" alt="First Ad"
								style="width: 100%; height: auto;">
						</div>
						<!-- Second Ad Banner -->
						<div class="ad-banner">
							<img src="https://i.imgur.com/xWzzMZD.png" alt="Second Ad"
								style="width: 100%; height: auto;">
						</div>
					</div>
				</div>
			</div>

			<!-- Footer Section -->
			<jsp:directive.include file="footer.jsp" />
		</div>
</body>
</html>
