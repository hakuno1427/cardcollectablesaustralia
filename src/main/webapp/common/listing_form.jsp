<%--<div class="form-group row">
	<label class="col-sm-4 col-form-label">Game:</label>
    <div class="col-sm-8">
        <select id="game" name="game" class="form-control">
            <option value="mtg">Magic The Gathering</option>
        </select>
    </div>
</div>--%>
<c:if test="${listing != null}">
    <input type="hidden" name="action" value="update" />
</c:if>
<c:if test="${listing == null}">
    <input type="hidden" name="action" value="insert" />
</c:if>
<c:if test="${listing != null}">
     <input type="hidden" name="listingId" value="<c:out value='${listing.listingId}' />" />
</c:if>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Serial Number</label>
	<div class="col-sm-8">
		<input type="text" name="serialNumber" class="form-control" value="<c:out value='${listing.serialNumber}' />" required minlength="5" maxlength="64" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Card Name</label>
	<div class="col-sm-8">
		<input type="text" name="cardName" class="form-control" value="<c:out value='${listing.cardName}' />" required minlength="5" maxlength="64" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Condition:</label>
    <div class="col-sm-8">
        <select id="condition" name="condition" class="form-control" value="<c:out value='${listing.condition}' />">
            <option value="nearMint">Near Mint</option>
            <option value="lightlyPlayed">Lightly Played</option>
            <option value="damaged">Damaged</option>
        </select>
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-4 col-form-label">Market Price:</label>
    <div class="col-sm-8">
        <input type="number" name="marketPrice" class="form-control" step="0.01" min="0"
             value="<c:out value='${listing.marketPrice}' />"   required />
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-4 col-form-label">Price:</label>
    <div class="col-sm-8">
        <input type="number" name="price" class="form-control" step="0.01" min="0" 
              value="<c:out value='${listing.price}' />"  required />
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-4 col-form-label">Quantity:</label>
    <div class="col-sm-8">
        <input type="number" name="quantity" class="form-control" step="1" min="0" 
              value="<c:out value='${listing.quantity}' />"  required />
    </div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Image Url</label>
	<div class="col-sm-8">
		<input type="text" name="imageUrl" class="form-control" value="<c:out value='${listing.imageUrl}' />" required />
	</div>
</div>

<div class="row">
	<div class="col text-center">
	   <c:choose>
           <c:when test="${listing != null}">
               <button type="submit" class="btn btn-primary mr-3">Edit</button>
           </c:when>
           <c:otherwise>
               <button type="submit" class="btn btn-primary mr-3">Add</button>
           </c:otherwise>
       </c:choose>
		<input type="button" value="Cancel" class="btn btn-secondary"
			onclick="history.go(-1);" />
	</div>
</div>
