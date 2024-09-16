<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="My Orders" />
</jsp:include>

<style>
.container {
	margin-top: 20px;
	width: 80%;
	margin: 0 auto;
}

.orders-section {
	margin-top: 30px;
}

.order-card {
	background-color: #f9f9f9;
	padding: 25px;
	margin-bottom: 25px;
	border-radius: 5px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	display: flex;
	align-items: center;
}

.order-details {
	flex: 3;
	margin-left: 20px;
	font-size: 1.1em;
}

.order-items-carousel {
	flex: 2;
	display: flex;
	justify-content: center;
	align-items: center;
	position: relative;
	overflow: hidden;
	max-width: 180px;
	height: 220px;
}

.carousel-item {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: center;
	width: 100%;
	height: auto;
}

.carousel-item img {
	max-width: 100%;
    max-height: 180px;
	object-fit: contain;
	border-radius: 5px;
	margin-bottom: 10px;
}
.carousel-item p {
    margin: 0;
    padding-top: 10px;
}

.carousel-controls {
	position: static;
	top: 50%;
	transform: translateY(-50%);
	display: flex;
	justify-content: space-between;
	width: 100%;
}

.carousel-controls button {
	background-color: transparent;
	border: none;
	font-size: 1.5em;
	cursor: pointer;
	color: #333;
}

.carousel-controls button:hover {
	color: #000;
}

.no-orders {
	color: #d35400;
	font-size: 1.2em;
	text-align: center;
	margin-top: 50px;
}
</style>

<body>
	<div class="container">
		<jsp:directive.include file="header.jsp" />

		<h1>My Sold Orders</h1>
		<h2>Total Earning: <c:out value="${totalEarning}" /></h2>
		

		<c:if test="${empty orders}">
			<p class="no-orders">You have no orders.</p>
		</c:if>

		<c:if test="${not empty orders}">
			<div class="orders-section">
				<c:forEach var="order" items="${orders}">
					<div class="order-card">

<!-- Order items carousel (image and name) -->
<div class="order-items-carousel" id="carousel-${item.orderItemId}">
    <c:forEach var="item" items="${order.orderItems}" varStatus="status">
        <div class="carousel-item" style="display: ${status.index == 0 ? 'block' : 'none'};">
            <a href="view_card?serialNumber=${item.listing.card.serialNumber}">
                <img class="item-image" src="${item.listing.card.imageUrl}" alt="${item.listing.card.cardName}" loading="lazy" style="cursor: pointer;" />
            </a>
            <p>
                <a href="view_card?serialNumber=${item.listing.card.serialNumber}">
                    <strong><c:out value="${item.listing.card.cardName}" /></strong>
                </a>
            </p>
        </div>
    </c:forEach>
    <div class="carousel-controls">
        <button onclick="prevItem(${item.orderItemId})">&lt;</button>
        <button onclick="nextItem(${item.orderItemId})">&gt;</button>
    </div>
</div>
						<!-- Order details (status, price, shipping, etc.) -->
						<div class="order-details">
							<div class="order-header">
								<h3>
									Order Number:
									<c:out value="${order.orderId}" />
								</h3>
								<span>Date: <c:out value="${order.orderDate}" /></span>								
							</div>

							<div class="order-details-info">
								<div>
									<span>Status:</span> <span
										class="${order.status == 'Shipped' ? 'status-shipped' : order.status == 'Pending' ? 'status-pending' : 'status-other'}">
										<c:out value="${order.status}" />
									</span>
								</div>
								<div>
									<span>Total Price:</span>
									<fmt:formatNumber value="${order.totalPrice}" type="currency" />
								</div>
								<div>
									<span>Shipping Address:</span>
									<c:out value="${order.shippingAddress}" />
								</div>
								<div>
									<span>Billing Address:</span>
									<c:out value="${order.billingAddress}" />
								</div>
							</div>
							
							<c:if test="${order.status == 'shipment pending' }">
								<div class="container mt-5">
								    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
								      Ship Order
								    </button>
								  </div>
								
								  <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Ship Order</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <form id="ship_order ${order.orderId}" action="ship_order" method="POST">
								        <div class="modal-body">
								        	<input type="hidden" name="orderId" value="${order.orderId }">
						                	<input type="text" name="trackingNumber" class="form-control" placeholder="Tracking number">
								          	<textarea class="form-control" name="message" rows="4"" required>Hello buyer, we have shipped your order</textarea>
								        </div>
								        <div class="modal-footer">
								          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
								          <button type="submit" class="btn btn-primary">Submit</button>
								        </div>
								      </form>
								    </div>
								  </div>
								</div>				
							</c:if>
							<c:if test="${order.status == 'shipped' || order.status =='complete' }">
								<span>Tracking number: <c:out value="${order.trackingNumber}" /></span>
							</c:if>
							
							
					</div>
				</c:forEach>
			</div>
		</c:if>

		<jsp:directive.include file="footer.jsp" />
	</div>

	<script>
	function nextItem(orderItemId) {
	    console.log(`Next item for orderItemId: ${orderItemId}`);
	    const carousel = document.getElementById(`carousel-${orderItemId}`);
	    if (!carousel) {
	        console.error(`Carousel not found for orderItemId: ${orderItemId}`);
	        return;
	    }
	    const items = carousel.querySelectorAll('.carousel-item');
	    let activeIndex = Array.from(items).findIndex(item => item.style.display === 'block');
	    items[activeIndex].style.display = 'none'; 
	    const nextIndex = (activeIndex + 1) % items.length;
	    items[nextIndex].style.display = 'block';
	}

	function prevItem(orderItemId) {
	    console.log(`Previous item for orderItemId: ${orderItemId}`);
	    const carousel = document.getElementById(`carousel-${orderItemId}`);
	    if (!carousel) {
	        console.error(`Carousel not found for orderItemId: ${orderItemId}`);
	        return;
	    }
	    const items = carousel.querySelectorAll('.carousel-item');
	    let activeIndex = Array.from(items).findIndex(item => item.style.display === 'block');
	    items[activeIndex].style.display = 'none';
	    const prevIndex = (activeIndex - 1 + items.length) % items.length;
	    items[prevIndex].style.display = 'block';
	}


    </script>
</body>
</html>
