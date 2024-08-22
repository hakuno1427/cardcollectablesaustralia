<div class="form-group row">
	<label class="col-sm-4 col-form-label">Card Name :</label>
	<div class="col-sm-8">
		<input type="text" name="cardName" class="form-control"
			value="${card.cardName}" required minlength="2"/>
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Game :</label>
    <div class="col-sm-8">
        <select id="game" name="game" class="form-control">
            <option value="mtg">Magic The Gathering</option>
        </select>
    </div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Serial Number :</label>
	<div class="col-sm-8">
		<input type="text" name="serialNumber" class="form-control"
			value="${card.serialNumber}" required minlength="2"/>
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Description :</label>
	<div class="col-sm-8">
		<input type="text" name="description" class="form-control"
			value="${card.description}" required minlength="2"/>
	</div>
</div>
<div class="form-group row">
    <label class="col-sm-4 col-form-label">Market Price :</label>
    <div class="col-sm-8">
        <input type="number" name="marketprice" class="form-control" step="0.01" min="0" 
               value="${listing.marketprice}" required />
    </div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Image URL :</label>
	<div class="col-sm-8">
		<input type="text" name="imageUrl" class="form-control"
			value="${card.imageUrl}" required minlength="2"/>
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