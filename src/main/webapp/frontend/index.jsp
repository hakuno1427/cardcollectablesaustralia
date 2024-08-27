<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="CCA - New Listings" />
</jsp:include>
<style>
    /* Page container*/
    .page-container {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 20px;
        position: relative;
    }

    /* Banner container */
    .banner-container {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        position: sticky;
        top: 100px;
    }

    /* left banner */
    .left-ad {
        margin-right: 20px;
    }

    /* right banner */
    .right-ad {
        margin-left: 20px;
    }

    /* Banner image */
    .ad-img {
        width: 120px;
        height: 600px;
        object-fit: cover;
    }

    /* New listings container */
    .new-listings-container {
        flex: 1;
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

</style>
<body>

    <div class="page-container">
        
        <!-- Left banner -->
        <div class="banner-container left-ad">
            <a href="https://github.com/hakuno1427/cardcollectablesaustralia" target="_blank">
                <img src="https://picsum.photos/120/300" alt="Ad 1" class="ad-img">
            </a>
        </div>

<!-- New Listings Section -->
        <div class="new-listings-container">
            <!-- Header Section -->
            <jsp:directive.include file="header.jsp" />

            <div class="row text-center">
                <div class="col">
                    <h2>New Listings:</h2>
                    <div class="row justify-content-center">
                        <c:forEach items="${listNewListings}" var="listing">
                            <div class="col-md-3 col-sm-6 listing-item">
                                <div class="card">
                                    <!-- Card Image Section -->
                                    <a href="view_card?id=${listing.card.serialNumber}" title="View Listing ${listing.listingId}">
                                        <img class="card-img-top img-fluid" src="${listing.card.imageUrl}" alt="${listing.card.cardName}">
                                    </a>
                                    
                                    <div class="card-body">
                                        <a href="view_card?id=${listing.card.serialNumber}" title="View Listing ${listing.listingId}">
                                            <b>${listing.card.cardName}</b>
                                        </a>
                                        <div>
                                            <b>Market Price: $${listing.card.marketprice}</b>
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

        <!-- Right banner -->
        <div class="banner-container right-ad">
            <a href="https://github.com/hakuno1427/cardcollectablesaustralia" target="_blank">
                <img src="https://picsum.photos/120/300" alt="Ad 2" class="ad-img">
            </a>
        </div>

    </div>
</body>
</html>
