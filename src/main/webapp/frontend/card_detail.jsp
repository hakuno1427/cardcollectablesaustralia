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
	flex-direction: row;
	align-items: flex-start;
	border: 1px dashed #ccc;
	padding: 15px;
	border-radius: 10px;
	width: 100%;
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
	width: 100%;
	margin-bottom: 10px;
	padding-bottom: 10px;
}

.market-price {
	margin-top: 15px;
	font-weight: bold;
	font-size: 1.3em;
	color: #d35400;
	width: 100%;
	border-top: 1px solid #ccc;
	margin-bottom: 10px;
	padding-bottom: 10px;
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
	background-color: #f9f9f9;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	font-size: 1.1em;
	margin-bottom: 10px;
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

.pagination {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.pagination button {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 8px 16px;
    margin: 0 4px;
    border-radius: 4px;
    cursor: pointer;
}

.pagination button:hover {
    background-color: #0056b3;
}

.pagination button:disabled {
    background-color: #ddd;
    cursor: not-allowed;
}

</style>

<body>
		<jsp:directive.include file="header.jsp" />
	<div class="container">

		<div class="card-detail">
	<div>
		<img class="card-img" src="${card.imageUrl}" alt="${card.cardName}">
	</div>
	<div class="card-info">
	<div class="card-name mb-5">
	<h2>${card.cardName}</h2>
	</div>
		
		<div class="card-description">
			<p><strong>Game:</strong> ${card.game}</p>
			<p><strong>Description:</strong> ${card.description}</p>
		</div>
		<div class="market-price mt-5">Market Price: $${card.marketprice}</div>
	</div>
</div>

<div class="listings-info">
    <h4>Available Listings:</h4>
    
    <!-- Listings Table Header -->
    <div class="row bg-light font-weight-bold py-2 align-items-center text-center m-0 mb-3">
        <div class="col-md-3">Seller</div>
        <div class="col-md-2">Condition</div>
        <div class="col-md-2">Price</div>
        <div class="col-md-2">Quantity</div>
        <div class="col-md-3"></div>
    </div>

    <!-- Listings Data with Card Effect -->
    <c:forEach items="${card.listings}" var="listing">
        <div class="card mb-3 shadow-sm border-0">
            <div class="card-body">
                <div class="row align-items-center text-center">
                    <div class="col-md-3">
                        <a href="view_seller?sellerId=${listing.seller.userId}" class="text-dark font-weight-bold">
                            ${listing.seller.firstName} ${listing.seller.lastName}
                        </a>
                    </div>
                    <div class="col-md-2">
                        ${listing.condition}
                    </div>
                    <div class="col-md-2 text-success">$${listing.price}</div>
                    <div class="col-md-2">${listing.quantity}</div>
                    <div class="col-md-3 text-right">
                        <a href="add_to_cart?listingId=${listing.listingId}" class="btn btn-success">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>





</div>


		
	</div>
	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>
