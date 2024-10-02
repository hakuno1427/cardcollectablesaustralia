<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Checkout" />
</jsp:include>

<head>
<style>
.rounded {
	border-radius: var(--bs-border-radius) !important;
}

.bg-white {
	--bs-bg-opacity: 1;
	background-color: rgba(var(--bs-white-rgb), var(--bs-bg-opacity))
		!important;
}

.shadow-sm {
	box-shadow: var(--bs-box-shadow-sm) !important;
}

.py-5 {
	padding-top: 3rem !important;
	padding-bottom: 3rem !important;
}

.p-4 {
	padding: 1.5rem !important;
}

.text-uppercase {
	text-transform: uppercase !important;
}

.font-weight-bold {
	font-weight: 700 !important;
}

.d-flex {
	display: flex !important;
}

.justify-content-between {
	justify-content: space-between !important;
}

.border-bottom {
	border-bottom: 1px solid #dee2e6 !important;
}

.list-unstyled {
	padding-left: 0;
	list-style: none;
}

/* Order summary section styling */
.bg-light {
	background-color: #f8f9fa !important;
}

.rounded-pill {
	border-radius: 50rem !important;
}

.bg-light {
	background-color: #f8f9fa !important;
}

.rounded-pill {
	border-radius: 50rem !important;
}

.text-uppercase {
	text-transform: uppercase !important;
}

.font-weight-bold {
	font-weight: 700 !important;
}

.text-muted {
	color: #6c757d !important;
}

.d-flex {
	display: flex !important;
}

.justify-content-between {
	justify-content: space-between !important;
}

.list-unstyled {
	padding-left: 0;
	list-style: none;
}

.seller-link {
	color: #000;
	text-decoration: none;
	transition: color 0.3s, text-decoration 0.3s;
}

.seller-link:hover {
	color: #18BC9C;
	text-decoration: underline;
}
</style>
</head>


<body class="d-flex flex-column min-vh-100">
	<jsp:directive.include file="header.jsp" />
	<div class="container flex-grow-1">

		<div class="row">&nbsp;</div>

		<c:if test="${message != null}">
			<div class="row">
				<div class="col text-center">
					<h4>${message}</h4>
				</div>
			</div>
		</c:if>

		<c:set var="cart" value="${sessionScope['cart']}" />

		<c:if test="${cart.totalItems == 0}">
			<div class="row">
				<div class="col text-center">
					<h4>There's no items in your cart</h4>
				</div>
			</div>
		</c:if>

		<c:if test="${cart.totalItems > 0}">
			<div
				class="row justify-content-center py-5 p-4 bg-white rounded shadow-sm">

				<!--Review Your Order Details -->
				<div class="col-lg-12">
					<div class="bg-white rounded shadow-sm p-4">
						<div class="d-flex justify-content-between align-items-center">
							<h2 class="cart-header mx-auto">Review Your Order</h2>
							<a href="cart" class="btn btn-link text-muted ml-auto">Edit</a>
						</div>

						<div class="p-4">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th scope="col" class="border-0 bg-light">
												<div class="p-2 px-3 text-uppercase">Product</div>
											</th>
											<th scope="col" class="border-0 bg-light">
												<div class="py-2 text-uppercase">Price</div>
											</th>
											<th scope="col" class="border-0 bg-light">
												<div class="py-2 text-uppercase">Quantity</div>
											</th>
											<th scope="col" class="border-0 bg-light">
												<div class="py-2 text-uppercase">Total</div>
											</th>
											<th scope="col" class="border-0 bg-light">
												<div class="py-2 text-uppercase">Seller</div>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${cart.items}" var="item" varStatus="status">
											<tr>
												<th scope="row" class="border-0">
													<div class="p-2">
														<img src="${item.key.card.imageUrl}"
															alt="${item.key.card.cardName}" width="70"
															class="img-fluid rounded shadow-sm">
														<div class="ml-3 d-inline-block align-middle">
															<h5 class="mb-0">
																<a href="#"
																	class="text-dark d-inline-block align-middle">${item.key.card.cardName}</a>
															</h5>
															<span
																class="text-muted font-weight-normal font-italic d-block">Game:
																${item.key.card.game}</span>
														</div>
													</div>
												</th>
												<td class="border-0 align-middle"><strong><fmt:formatNumber
															value="${item.key.price}" type="currency" /></strong></td>
												<td class="border-0 align-middle"><strong>${item.value}</strong></td>
												<td class="border-0 align-middle"><strong><fmt:formatNumber
															value="${item.value * item.key.price}" type="currency" /></strong></td>
												<td class="border-0 align-middle"><a
													href="view_seller?sellerId=${item.key.seller.userId}"
													class="seller-link"><strong>${item.key.seller.firstName}
															${item.key.seller.lastName}</strong></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div
				class="row justify-content-center py-5 p-4 bg-white rounded shadow-sm">
				<div class="col-lg-8">
					<div class="bg-white rounded shadow-sm p-4">
						<div
							class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">
							Shipping Address</div>
						<div class="p-4">
							<form action="place_order" method="post">
								<div class="form-group row">
									<label class="col-sm-4 col-form-label">Street Address:</label>
									<div class="col-sm-8">
										<input type="text" name="streetAddress" required
											minlength="10" maxlength="256" class="form-control" />
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-4 col-form-label">City:</label>
									<div class="col-sm-8">
										<input type="text" name="city" required minlength="3"
											maxlength="32" class="form-control" />
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-4 col-form-label">State:</label>
									<div class="col-sm-8">
										<input type="text" name="state" required minlength="3"
											maxlength="45" class="form-control" />
									</div>
								</div>
								<div class="form-group row">
									<label class="col-sm-4 col-form-label">Zip Code:</label>
									<div class="col-sm-8">
										<input type="text" name="zipcode" required minlength="3"
											maxlength="24" class="form-control" />
									</div>
								</div>
								<div class="row">
									<div class="col text-center">
										<button type="submit" class="btn btn-primary">Place
											Order</button>
										&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/">Continue
											Shopping</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<!-- Order Summary -->
				<div class="col-md-4">
					<div class="bg-white rounded shadow-sm p-4">
						<div
							class="bg-light rounded-pill text-uppercase font-weight-bold px-4 py-3">Order
							Summary</div>
						<ul class="list-unstyled mb-4">
							<li class="d-flex justify-content-between py-3 border-bottom">
								<strong class="text-muted">Card Quantity</strong> <strong>${cart.totalQuantity}</strong>
							</li>
							<li class="d-flex justify-content-between py-3 border-bottom">
								<strong class="text-muted">Subtotal</strong>
								<h5 class="font-weight-bold">
									<fmt:formatNumber value="${cart.totalAmount}" type="currency" />
								</h5>
							</li>
							<li class="d-flex justify-content-between py-3 border-bottom">
								<strong class="text-muted">Shipping Fee</strong>
								<h5 class="font-weight-bold">
									<fmt:formatNumber value="${shippingFee}" type="currency" />
								</h5>
							</li>
							<li class="d-flex justify-content-between py-3 border-bottom">
								<strong class="text-muted">Tax</strong>
								<h5 class="font-weight-bold">$0.00</h5>
							</li>
							<li class="d-flex justify-content-between py-3 border-bottom">
								<strong class="text-muted">TOTAL</strong>
								<h5 class="font-weight-bold">
									<fmt:formatNumber value="${total}" type="currency" />
								</h5>
							</li>
						</ul>
					</div>
				</div>

			</div>
		</c:if>

	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>
