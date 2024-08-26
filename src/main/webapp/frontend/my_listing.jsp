<%--
	Created by rutvi
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="CCA - My Listings" />
</jsp:include>
<body>
    <div class="container">
        <!-- Header Section -->
        <jsp:directive.include file="header.jsp" />

        <!-- New Listings Section -->
        <div class="row text-center">
            <div class="col">
                <h2>My Listings</h2>
                <p>You can manage your listing here.</p>
                <hr>
                <div class="row justify-content-end">
                    <a class="btn btn-primary mr-3" href="listings?action=add">Add</a>
                </div>
                <div class="row justify-content-center">

                        <div class="col listing-item" style="margin-bottom: 15px;">
                            <table class="table table-striped table-bordered mt-4">
                                <thead>
                                    <tr>
                                        <th style="width:10%">Image</th>
                                        <th>Serial Number</th>
                                        <th>Card Name</th>
                                        <th>Condition</th>
                                        <th>Quantity</th>
                                        <th>Market Price</th>
                                        <th>Price</th>
                                        <th>Action</th>
                                    </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach items="${listOfListings}" var="listing">
                                      <tr>
                                         <td>
                                            <img class="card-img-top img-fluid" src="${listing.imageUrl}" alt="${listing.cardName}" style="max-width: 100%; height: auto;">
                                         </td>
                                         <td>${listing.serialNumber}</td>
                                         <td>${listing.cardName}</td>
                                         <td>${listing.condition}</td>
                                         <td>${listing.quantity}</td>
                                         <td>${listing.marketPrice}</td>
                                         <td>${listing.price}</td>
                                         <td>
                                            <div class="btn-group" role="group">
                                                <a type="button" class="btn btn-primary btn-sm mr-2" href="listings?action=edit&listingId=${listing.listingId}">Edit</a>
                                                <a type="button" class="btn btn-danger btn-sm text-white">Delete</a>
                                            </div>
                                         </td>
                                      </tr>
                                    </c:forEach>
                                 </tbody>
                            </table>
                        </div>

                </div>
            </div>
        </div>

        <!-- Footer Section -->
        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
</html>
