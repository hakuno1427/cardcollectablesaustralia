<!-- rutvi   -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../frontend/page_head.jsp">
	<jsp:param name="pageTitle" value="CCA - My Listings" />
</jsp:include>
<body>
		<!-- Header Section -->
		<jsp:directive.include file="../frontend/header_without_searchbar.jsp" />
	<div class="container">


		<!-- New Listings Section -->
		<div class="row text-center">
			<div class="col">
				<h2>My Listings</h2>
				<p>You can manage your listing here.</p>
				<hr>
				<div class="row justify-content-end">
					<a class="btn btn-primary mr-3" href="listings?action=add">Add Card</a>
				</div>
				<div class="row justify-content-center">

					<div class="col listing-item" style="margin-bottom: 15px;">
						<table class="table table-striped table-bordered mt-4">
							<thead>
								<tr>
									<th style="width: 10%">Image</th>
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
										<td><img class="card-img-top img-fluid"
											src="${listing.card.imageUrl}" alt="${listing.card.cardName}"
											style="max-width: 100%; height: auto;"></td>
										<td>${listing.card.serialNumber}</td>
										<td>${listing.card.cardName}</td>
										<td>${listing.condition}</td>
										<td>${listing.quantity}</td>
										<td>$${(listing.card.marketprice !=-1) ? listing.card.marketprice : "N/A" }</td>
										<td>${listing.price}</td>
										<td>
											<div class="btn-group" role="group">
												<a type="button" class="btn btn-primary btn-sm mr-2"
													href="listings?action=edit&listingId=${listing.listingId}">Update</a>
												<!-- Button trigger modal -->
												<button type="button" class="btn btn-sm btn-danger"
													data-toggle="modal" data-target="#deleteModal">
													Delete</button>
											</div> <!-- Modal -->
											<div class="modal fade" id="deleteModal" tabindex="-1"
												role="dialog" aria-labelledby="deleteModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="deleteModalLabel">Delete
																Listing</h5>
															<button type="button" class="close" data-dismiss="modal"
																aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
															<p>Are you sure you want to delete lisitng with card
																name:</p>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-sm btn-secondary"
																data-dismiss="modal">Close</button>
															<a type="button" class="btn btn-danger btn-sm text-white"
																href="listings?action=del&listingId=${listing.listingId}">Delete</a>
														</div>
													</div>
												</div>
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


	</div>
			<!-- Footer Section -->
		<jsp:directive.include file="../frontend/footer.jsp" />
</body>
</html>
