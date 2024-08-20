<div class="form-group row">
	<label class="col-sm-4 col-form-label">Game:</label>
    <div class="col-sm-8">
        <select id="game" name="game" class="form-control">
            <option value="mtg">Magic The Gathering</option>
        </select>
    </div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Card number (Set + collector number):</label>
	<div class="col-sm-8">
		<input type="text" name="serialNumber" class="form-control"
			value="${customer.serialNumber}" required minlength="5" maxlength="64" />
	</div>
</div>
To work on preview card's details
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Condition:</label>
    <div class="col-sm-8">
        <select id="condition" name="condition" class="form-control">
            <option value="nearMint">Near Mint</option>
            <option value="lightlyPlayed">Lightly Played</option>
            <option value="damaged">Damaged</option>
        </select>
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-4 col-form-label">Price:</label>
    <div class="col-sm-8">
        <input type="number" name="price" class="form-control" step="0.01" min="0" 
               value="${listing.price}" required />
    </div>
</div>
<div class="form-group row">
    <label class="col-sm-4 col-form-label">Quantity:</label>
    <div class="col-sm-8">
        <input type="number" name="quantity" class="form-control" step="1" min="0" 
               value="${listing.quantity}" required />
    </div>
</div>


<div class="row">&nbsp;</div>
<div class="row">
	<div class="col text-center">
		<button type="submit" class="btn btn-primary mr-3">Save</button>
		<input type="button" value="Cancel" class="btn btn-secondary"
			onclick="history.go(-1);" />
	</div>
</div>
