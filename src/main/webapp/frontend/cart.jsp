<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>

<head>
<style type="text/css">
.rounded {
	border-radius: var(--bs-border-radius) !important;
}

.bg-white {
	--bs-bg-opacity: 1;
	background-color: rgba(var(--bs-white-rgb), var(--bs-bg-opacity))
		!important;
}

.py-5 {
	padding-top: 3rem !important;
	padding-bottom: 3rem !important;
}

.p-4 {
	padding: 1.5rem !important;
}

.shadow-sm {
	box-shadow: var(--bs-box-shadow-sm) !important;
}

.row {
	--bs-gutter-x: 1.5rem;
	--bs-gutter-y: 0;
	display: flex;
	flex-wrap: wrap;
	margin-top: calc(-1 * var(--bs-gutter-y));
	margin-right: calc(-.5 * var(--bs-gutter-x));
	margin-left: calc(-.5 * var(--bs-gutter-x));
}

.cart-header {
	font-family: 'Montserrat', sans-serif;
	font-weight: 700;
	color: #18BC9C;
	margin-bottom: 20px;
}

.cart-empty-message {
	color: #0d6957;
	font-weight: bold;
}

/* Proceed to Checkout button style */
.btn-proceed-checkout {
	color: #fff;
	background-color: #18BC9C;
	border-color: #18BC9C;
	padding: 1rem 1.75rem;
	font-size: 1.25rem;
	border-width: 2px;
	border-radius: 0.375rem;
	transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
		border-color 0.15s ease-in-out;
}

.btn-proceed-checkout:hover {
	color: #000;
	background-color: #16a98c;
	border-color: #16a98c;
}

.btn-proceed-checkout:active {
	background-color: #46c9b0;
	border-color: #2fc3a6;
}

/* Continue Shopping button style */
.btn-continue-shopping {
	color: #fff;
	background-color: #18BC9C;
	border-color: #f8f9fa;
	padding: 1rem 1.75rem;
	font-size: 1.25rem;
	border-width: 2px;
	border-radius: 0.375rem;
	transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
		border-color 0.15s ease-in-out;
}

.btn-continue-shopping:hover {
	background-color: #f8f9fa;
	color: #18BC9C;
	border-color: #f8f9fa;
}

.btn-continue-shopping:active {
	background-color: #f8f9fa;
	border-color: #f8f9fa;
}

*, ::after, ::before {
	box-sizing: border-box;
}
</style>
</head>


<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Your Shopping Cart" />
</jsp:include>
<body class="d-flex flex-column min-vh-100">
	<jsp:directive.include file="header.jsp" />
	<div class="container flex-grow-1">


		<div>&nbsp;</div>

		<div class="row">
			<div class="col text-center">
				<h2 class="cart-header">Your Cart Details</h2>
			</div>
		</div>


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
					<h4 class="cart-empty-message">There's no items in your cart</h4>
				</div>
			</div>
		</c:if>

		<div>
			<c:if test="${cart.totalItems > 0}">
				<form action="update_cart" method="post">
					<div class="row">
						<div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

							<!-- Shopping cart table -->
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
												<div class="py-2 text-uppercase">Remove</div>
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
												<td class="border-0 align-middle"><input type="hidden"
													name="listingId" value="${item.key.listingId}" /> <input
													type="number" name="quantity${status.index + 1}"
													value="${item.value}" class="form-control"
													style="max-width: 50px" size="5" required step="1" min="1"
													max="${item.key.quantity}" /> <span
													class="text-muted font-weight-normal font-italic d-block">
														Max: ${item.key.quantity} </span></td>
												<td class="border-0 align-middle"><a
													href="remove_from_cart?listingId=${item.key.listingId}"
													class="text-dark"> <i class="fa fa-trash"></i>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- End -->

							<div class="row">&nbsp;</div>

							<div class="row">
								<div class="col-12 text-center">
									<button type="submit" class="btn btn-primary">Update</button>
									&nbsp;&nbsp; <input type="button" class="btn btn-secondary"
										id="clearCart" value="Clear Cart" />
								</div>
							</div>

							<div class="row">&nbsp;</div>
						</div>
					</div>
				</form>

				<div class="summary">
					<div class="px-4 px-lg-0">
						<div class="pb-5">
							<div class="container">
								<div
									class="row justify-content-center py-5 p-4 bg-white rounded shadow-sm">
									<div class="col-lg-8">
										<div
											class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order
											summary</div>
										<div class="p-4">
											<ul class="list-unstyled mb-4">
												<li
													class="d-flex justify-content-between py-3 border-bottom">
													<strong class="text-muted">Card Quantity</strong> <strong>${cart.totalQuantity}</strong>
												</li>
												<li
													class="d-flex justify-content-between py-3 border-bottom">
													<strong class="text-muted">Total</strong>
													<h5 class="font-weight-bold">
														<fmt:formatNumber value="${cart.totalAmount}"
															type="currency" />
													</h5>
												</li>
											</ul>
											<div class="d-flex justify-content-between">
												<a href="${pageContext.request.contextPath}/"
													class="btn-continue-shopping">Continue Shopping</a> <a
													href="checkout" class="btn-proceed-checkout">Proceed to
													Checkout</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


			</c:if>
		</div>






	</div>
	<jsp:directive.include file="footer.jsp" />

	<script type="text/javascript">
		$(document).ready(function() {
			$("#clearCart").click(function() {
				window.location = 'clear_cart';
			});
		});
	</script>
</body>
</html>