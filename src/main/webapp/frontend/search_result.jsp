<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Search Result" />
</jsp:include>

<style>
.container {
	margin-top: 20px;
}

/* result style */
.results-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px; /* Space between cards */
  width: 80%;
  margin: 0 auto;
  padding: 20px;
  background-color: #f8f9fa; /* Light grey background */
  border-radius: 10px; /* Rounded corners */
  border: 1px solid #ddd; /* Light border */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* Light shadow */
}

.results-text h2 {
  font-family: 'Montserrat', sans-serif;
  font-weight: 700; 
  color: #18BC9C;
  margin-bottom: 20px;
}

.keyword-text {
    color: #0d6957; 
    font-weight: bold; 
}

/* card style */
.card-item {
  display: inline-block;
  flex: 0 1 calc(25% - 40px); 
  margin: 20px;
  width: 100%;
}

@media (max-width: 1200px) {
  .card-item {
    flex: 0 1 calc(33.33% - 40px); 
  }
}

@media (max-width: 992px) {
  .card-item {
    flex: 0 1 calc(50% - 40px); 
  }
}

@media (max-width: 768px) {
  .card-item {
    flex: 0 1 100%; 
  }
}

/* card hover style */
.card {
 display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
	border: 1px solid #ddd;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s ease;
}

.card:hover {
	transform: translateY(-5px);
}

/* card img style */
.card-img-top {
	width: 100%;
	height: 300px;
	object-fit: cover;
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* card name style */
.card-body {
 text-align: center;
	font-family: 'Montserrat', sans-serif;
}

.card-body .card-name a{
	font-weight: 700;
	color: #18BC9C; 
	text-decoration: none;
}

.card-body .card-name a:hover {
	color: #2aa08a; 
}

.card-item a {
	text-decoration: none;
	color: #333;
	font-weight: bold;
	display: block;
	margin-top: 10px;
}

.card-item a:hover {
	text-decoration: underline;
	color: #007bff;
}

/* card game style */
.card-item .game-name {
	color: #666;
	font-size: 14px;
	margin-top: 5px;
}
/* card market price style */
.market-price {
	font-weight: bold;
	color: #555;
	margin-top: 5px;
}
/* Pagination styles */
.pagination {
	margin-top: 20px;
	display: flex;  
	justify-content: center; 
	text-align: center;
}

.pagination a {
	color: #007bff;
	text-decoration: none;
	padding: 8px 16px;
	margin: 0 4px;
	border: 1px solid #ddd;
	border-radius: 4px;
	display: inline-block;
}

.pagination a.active {
	background-color: #007bff;
	color: white;
	border: 1px solid #007bff;
}

.pagination a:hover {
	background-color: #ddd;
}
</style>

<body class="d-flex flex-column min-vh-100">
	<jsp:directive.include file="header.jsp" />
	<div class="container-fluid flex-grow-1">
<div class="results-text mb-5" style="text-align: center; margin: 20px 0;">
    <c:if test="${fn:length(result) == 0}">
        <h2>NO RESULT FOR "
        <span class="keyword-text">${keyword}
        </span>"
        </h2>
    </c:if>

    <c:if test="${fn:length(result) > 0}">
        <h2>RESULTS FOR "<span class="keyword-text">
            <c:choose>
                <c:when test="${fn:trim(keyword) == ''}">
                    ALL CARDS
                </c:when>
                <c:otherwise>
                    ${keyword}
                </c:otherwise>
            </c:choose>
        </span>":</h2>
    </c:if>
</div>

		<div class="results-container">
			<c:if test="${fn:length(result) > 0}">
				<c:forEach items="${result}" var="card">
					<div class="card-item card">
						<a href="view_card?serialNumber=${card.serialNumber}"> <img
							class="card-img-top img-fluid" src="${card.imageUrl}"
							alt="${card.cardName}" style="cursor: pointer;">
						</a>
						<div class="card-body">
							<div class="card-name mb-3">
								<a href="view_card?serialNumber=${card.serialNumber}">${card.cardName}</a>
							</div>
							<div class="game-name">${card.game}</div>
							<div class="market-price">Market Price:
								$${(card.marketprice !=-1) ? card.marketprice : "N/A" }</div>

							<!-- Listing info -->
							<c:choose>
								<c:when test="${fn:length(card.listings) == 0}">
									<div class="listing-info text-danger">Out of Stock</div>
								</c:when>
								<c:otherwise>
									<c:set var="lowestPrice" value="${card.listings[0].price}" />
									<c:forEach items="${card.listings}" var="listing">
										<c:if test="${listing.price < lowestPrice}">
											<c:set var="lowestPrice" value="${listing.price}" />
										</c:if>
									</c:forEach>
									<div class="listing-info text-success">
										${fn:length(card.listings)} listings from $${lowestPrice}</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</c:if>
			
		</div>

		<div class="pagination">
			<c:if test="${currentPage > 1}">
				<a href="?keyword=${keyword}&page=${currentPage - 1}">Previous</a>
			</c:if>

			<c:set var="startPage"
				value="${currentPage - 3 > 1 ? currentPage - 3 : 1}" />
			<c:set var="endPage"
				value="${currentPage + 3 < totalPages ? currentPage + 3 : totalPages}" />

			<c:if test="${startPage > 1}">
				<a href="?keyword=${keyword}&page=1">1</a>
				<span>...</span>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="?keyword=${keyword}&page=${i}"
					class="${i == currentPage ? 'active' : ''}">${i}</a>
			</c:forEach>

			<c:if test="${endPage < totalPages}">
				<a href="?keyword=${keyword}&page=${totalPages}">${totalPages}</a>
			</c:if>

			<c:if test="${currentPage < totalPages}">
				<a href="?keyword=${keyword}&page=${currentPage + 1}">Next</a>
			</c:if>
		</div>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
