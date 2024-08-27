<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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
        width: 80%;
        margin: 0 auto;
        text-align: center;
    }

    /* card style */
    .card-item {
        display: inline-block;
        margin: 20px;
        width: 200px;
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
</style>

<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="results-container">
            <c:if test="${fn:length(result) == 0}">
                <h2>No Result for "${keyword}"</h2>
            </c:if>

            <c:if test="${fn:length(result) > 0}">
                <h2>Results for "${keyword}":</h2>
<c:forEach items="${result}" var="card">
    <div class="card-item card">
        <a href="view_card?serialNumber=${card.serialNumber}">
            <img class="card-img-top img-fluid" src="${card.imageUrl}" alt="${card.cardName}" style="cursor: pointer;">
        </a>
        <div class="card-body">
            <a href="view_card?serialNumber=${card.serialNumber}">
                ${card.cardName}
            </a>
            <div class="game-name">${card.game}</div>
            <div class="market-price">Market Price: $${(card.marketprice !=-1) ? card.marketprice : "N/A" }</div>

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
                        ${fn:length(card.listings)} listings from $${lowestPrice}
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:forEach>


            </c:if>
        </div>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
</html>
