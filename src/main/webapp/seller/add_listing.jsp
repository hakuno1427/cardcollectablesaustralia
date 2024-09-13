<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../frontend/page_head.jsp">
	<jsp:param name="pageTitle" value="Add Listing" />
</jsp:include>
<body>
	<div class="container">
		<jsp:directive.include file="../frontend/header.jsp" />

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Add New Listing</h2>
			</div>
		</div>
		<div class="row">&nbsp;</div>

		<form action="listings" method="post"
			style="max-width: 800px; margin: 0 auto;">
			<%-- <jsp:directive.include file="../common/listing_form.jsp" /> --%>
			<div class="row mt-5">
				<div class="col-md-4" id="cardImageDiv" style="display: none;">
					<!-- Image Section with Inline CSS -->
					<img id="cardImage" alt="Placeholder Image"
						style="max-width: 100%; height: auto;">
				</div>
				<div class="col-md-12" id="listingDataDiv">
					<div class="form-group">
						<input type="hidden" name="action" value="insert" /> 
						<label for="name">Serial Number</label>
						<div class="row">
							<div class="col-sm-10">
								<select id="serialNumber" name="serialNumber"
									class="form-control" required>
									<c:forEach var="card" items="${cards}">
										<option value="${card.serialNumber}">
											${card.serialNumber} - ${card.cardName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2 text-right">
								<input type="button" onclick="fetchCardDetails()"
									class="btn btn-primary" value="Select" />
							</div>
						</div>
					</div>
					<div id="cardDetailsDiv" style="display: none;">
						<div class="form-group">
							<label for="cardName">CardName</label> <input type="text"
								id="cardName" name="cardName" class="form-control" readonly />
						</div>
						<div class="form-group">
							<label for="game">Game</label> <input type="text" id="game"
								name="game" class="form-control" readonly />
						</div>
						<div class="form-group">
							<label for="description">Description</label>
							<textarea id="description" name="description"
								class="form-control" readonly></textarea>
						</div>
						<div class="form-group">
							<label for="marketprice">Market Price</label> <input type="text"
								id="marketprice" name="marketprice" class="form-control"
								readonly />
						</div>
					</div>

					<div class="form-group">
						<label for="condition">Condition</label> <select id="condition"
							name="condition" class="form-control">
							<option value="nearMint" selected>Near Mint</option>
							<option value="lightlyPlayed">Lightly Played</option>
							<option value="damaged">Damaged</option>
						</select>
					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="number" id="price"
							name="price" class="form-control" step="0.01" min="0" required />
					</div>
					<div class="form-group">
						<label for="quantity">Quantity</label> <input type="number"
							name="quantity" class="form-control" step="1" min="0" required />
					</div>
					<div class="row">
						<div class="col text-center">


							<button type="submit" class="btn btn-primary mr-3">Add</button>

							<input type="button" value="Cancel" class="btn btn-secondary"
								onclick="history.go(-1);" />
						</div>
					</div>
				</div>
			</div>

		</form>

		<jsp:directive.include file="../frontend/footer.jsp" />
	</div>
	<script>
		function fetchCardDetails() {
			var cardId = document.getElementById("serialNumber").value;
			var cardDetailsDiv = document.getElementById("cardDetailsDiv");
			var imageDiv = document.getElementById("cardImageDiv");
			var listingDataDiv = document.getElementById("listingDataDiv");
			if (cardId === "") {
				alert("Please select a card.");
				return;
			}

			var xhr = new XMLHttpRequest();
			xhr.open("GET", "listings?action=fetchCard&serialNumber="
					+ encodeURIComponent(cardId), true);
			xhr.onreadystatechange = function() {

				if (xhr.readyState === 4) {

					if (xhr.status === 200) {
						var card = JSON.parse(xhr.responseText);
						console.log(card);
						var cardNameEle = document.getElementById("cardName");
						var cardImageEle = document.getElementById("cardImage");
						var cardGameEle = document.getElementById("game");
						var cardDescEle = document
								.getElementById("description");
						var cardMarketPriceEle = document
								.getElementById("marketprice")

						cardNameEle.value = card.cardName || "";
						cardImageEle.src = card.imageUrl || "";
						cardGameEle.value = card.game || "";
						cardDescEle.value = card.description || "";
						cardMarketPriceEle.value = card.marketprice || "";

						listingDataDiv.classList.remove("col-md-12");
						listingDataDiv.classList.add("col-md-8");

						cardDetailsDiv.style.display = "block";
						cardImageDiv.style.display = "block";

					} else {
						alert("Failed to fetch card details.");
					}
				}
			};
			xhr.send();
		}
	</script>
</body>
</html>