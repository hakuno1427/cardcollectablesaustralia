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
                <div class="listings-container" style="width: 80%; margin: 0 auto;">
                    <c:forEach items="${listNewListings}" var="listing">
                        <div class="listing-item" style="border-bottom: 1px solid #ddd; padding: 10px;">
                            <div>
                                <a href="view_listing?id=${listing.listingId}" title="View Listing ${listing.listingId}">
                                    <b>Listing ID: ${listing.listingId}</b>
                                </a>
                            </div>
                            <div>
                                <b>Price: $${listing.price}</b>
                            </div>
                            <!-- Additional fields can be added here -->
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
