<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../frontend/page_head.jsp">
	<jsp:param name="pageTitle" value="Edit Listing" />
</jsp:include>
<body>
		<jsp:directive.include file="../frontend/header_without_searchbar.jsp" />
	<div class="container">


		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Edit Listing</h2>
			</div>
		</div>
		<div class="row">&nbsp;</div>

		<form action="listings" method="post"
			style="max-width: 800px; margin: 0 auto;">
			<input type="hidden" name="action" value="update" /> <input
				type="hidden" name="listingId"
				value="<c:out value='${listing.listingId}' />" />

			<div class="row mt-5">
				<div class="col-md-4">
					<!-- Image Section with Inline CSS -->
					<img src="${listing.card.imageUrl}" id="cardImage"
						alt="Placeholder Image" style="max-width: 100%; height: auto;">
				</div>
				<div class="col-md-8">
					<div class="form-group">
						<label for="name">Serial Number</label> <input type="text"
							id="serialNumber" name="serialNumber" class="form-control"
							value="<c:out value='${listing.card.serialNumber}' />" readonly />
					</div>

					<div class="form-group">
						<label for="cardName">CardName</label> <input type="text"
							id="cardName" name="cardName" class="form-control"
							value="<c:out value='${listing.card.cardName}' />" readonly />
					</div>
					<div class="form-group">
						<label for="game">Game</label> <input type="text" id="game"
							name="game" class="form-control"
							value="<c:out value='${listing.card.game}' />" readonly />
					</div>
					<div class="form-group">
						<label for="description">Description</label>
						<textarea id="description" name="description" class="form-control"
							readonly><c:out value='${listing.card.description}' /></textarea>
					</div>
					<div class="form-group">
						<label for="marketprice">Market Price</label> <input type="text"
							id="marketprice" name="marketprice" class="form-control"
							value="<c:out value='${listing.card.marketprice}' />" readonly />
					</div>


					<div class="form-group">
						<label for="condition">Condition</label> <select id="condition"
							name="condition" class="form-control">
							<option value="nearMint"
								<c:if test="${listing.condition == 'nearMint'}">selected</c:if>>Near
								Mint</option>
							<option value="lightlyPlayed"
								<c:if test="${listing.condition == 'lightlyPlayed'}">selected</c:if>>Lightly
								Played</option>
							<option value="damaged"
								<c:if test="${listing.condition == 'damaged'}">selected</c:if>>Damaged</option>
						</select>
					</div>
					<div class="form-group">
						<label for="price">Price</label> <input type="number" id="price"
							name="price" class="form-control" step="0.01" min="0"
							value="<c:out value='${listing.price}' />" required />
					</div>
					<div class="form-group">
						<label for="quantity">Quantity</label> <input type="number"
							name="quantity" class="form-control" step="1" min="0"
							value="<c:out value='${listing.quantity}' />" required />
					</div>
					<div class="row">
						<div class="col text-center">

							<button type="submit" class="btn btn-primary mr-3">Edit</button>

							<input type="button" value="Cancel" class="btn btn-secondary"
								onclick="history.go(-1);" />
						</div>
					</div>
				</div>
			</div>


		</form>


	</div>
			<jsp:directive.include file="../frontend/footer.jsp" />

</body>
</html>