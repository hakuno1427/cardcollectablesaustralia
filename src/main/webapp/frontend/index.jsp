<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="CCA - New Listings" />
</jsp:include>
<body>
    <div class="container">
        <!-- Header Section -->
        <jsp:directive.include file="header.jsp" />

        <!-- New Listings Section -->
        <div class="row text-center">
            <div class="col">
                <h2>New Listings:</h2>
                <div class="row justify-content-center">
                    <c:forEach items="${listNewListings}" var="listing">
                        <div class="col-md-3 col-sm-6 listing-item" style="margin-bottom: 15px;">
                            <div class="card">
                                <!-- Card Image Section -->
                                <img class="card-img-top img-fluid" src="${listing.imageUrl}" alt="${listing.cardName}" style="max-width: 100%; height: auto;">
                                
                                <div class="card-body">
                                    <a href="view_listing?id=${listing.listingId}" title="View Listing ${listing.listingId}">
                                        <b>Listing ID: ${listing.listingId}</b>
                                    </a>
                                    <div>
                                        <b>Card Name: ${listing.cardName}</b>
                                    </div>
                                    <div>
                                        <b>Market Price: $${listing.marketPrice}</b>
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

        <!-- Footer Section -->
        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
</html>
