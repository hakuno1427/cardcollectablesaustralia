<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
	<jsp:include page="page_head.jsp">
		<jsp:param name="pageTitle" value="Checkout" />
	</jsp:include>
<body class="d-flex flex-column min-vh-100">
	<jsp:directive.include file="header.jsp" />
<div class="container flex-grow-1">

	
	<div class="row">&nbsp;</div>
	
	<c:if test="${message != null}">
		<div class="row">
			<div class="col text-center"><h4>${message}</h4></div>
		</div>		
	</c:if>

	<c:set var="cart" value="${sessionScope['cart']}" />

	<c:if test="${cart.totalItems == 0}">
		<div class="row">
			<div class="col text-center"><h4>There's no items in your cart</h4></div>
		</div>		
	</c:if>

	<c:if test="${cart.totalItems > 0}">			
		<div class="row">
			<div class="col-sm-2"></div>
			
			<div class="col-sm-8">
				<div class="card">
					<div class="card-header">
						<div class="text-center"><h3>Review Your Order Details <a href="cart">Edit</a></h3></div>		
					</div>
					<div class="card-body">
						<div>
							<c:forEach items="${cart.items}" var="item" varStatus="status">
								<div class="row">
									<div class="col-sm">${status.index + 1}</div>
									<div class="col-3"><img width="128" height="164" src="${item.key.card.imageUrl}" /></div>
									<div class="col-8">
										<div><h6>${item.key.card.cardName}</h6></div>
										<div>by <i>${item.key.seller.firstName} ${item.key.seller.lastName}</i></div>
										<div><fmt:formatNumber value="${item.key.price}" type="currency" /></div>								
										<div>									
											X <input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" readonly="readonly" />
										</div>
										<div>= <b><fmt:formatNumber	value="${item.value * item.key.price}" type="currency" /></b></div>		
										<div>
										<span>Seller:</span> <a
											href="view_seller?userId=${listing.seller.userId}">
											${listing.seller.firstName} ${listing.seller.lastName} </a>
									</div>							
									</div>								
								</div>
								<div class="row">&nbsp;</div>
							</c:forEach>
			
							<div class="row">							
								<div class="col text-center">
									<p>Number of cards: ${cart.totalQuantity}</p>
									<p>Subtotal: <fmt:formatNumber value="${cart.totalAmount}" type="currency" /></p>
									<p>Tax: <fmt:formatNumber value="0" type="currency" /></p>
									<p>Shipping Fee: <fmt:formatNumber value="10" type="currency" /></p>
									<p>TOTAL: <fmt:formatNumber value="${total}" type="currency" /></p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">&nbsp;</div>
				
				<div class="card">
					<div class="card-header">
						<div class="text-center"><h3>Shipping Address</h3></div>		
					</div>
				</div>
				<div class="card-body">
					<form action="place_order" method="post">
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Street Address:</label>
							<div class="col-sm-8"><input type="text" name="streetAddress" required minlength="10" maxlength="256" class="form-control" /></div>
						</div>
									
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">City:</label>
							<div class="col-sm-8"><input type="text" name="city"required minlength="3" maxlength="32" class="form-control"/></div>
						</div>
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">State:</label>
							<div class="col-sm-8"><input type="text" name="state" required minlength="3" maxlength="45" class="form-control" /></div>
						</div>												
						<div class="form-group row">
							<label class="col-sm-4 col-form-label">Zip Code:</label>
							<div class="col-sm-8"><input type="text" name="zipcode" required minlength="3" maxlength="24" class="form-control"/></div>
						</div>						
						<div class="row">
							<div class="col text-center">								
								<button type="submit" class="btn btn-primary">Place Order</button>							
								&nbsp;&nbsp;	
								<a href="${pageContext.request.contextPath}/">Continue Shopping</a>								
							</div>
						</div>					
					</form>						
				</div>
				
	
			</div>
			
			<div class="col-sm-2"></div>
			
		</div>			
	</c:if>


</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>