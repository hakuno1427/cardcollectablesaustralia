
<div class="form-group row">
	<label class="col-sm-4 col-form-label">E-mail:</label>
	<div class="col-sm-8">
		<input type="email" name="email" class="form-control"
			value="${customer.email}" required minlength="5" maxlength="64" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">First Name:</label>
	<div class="col-sm-8">
		<input type="text" name="firstname" class="form-control"
			value="${customer.firstname}" required minlength="2" maxlength="30" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Last Name:</label>
	<div class="col-sm-8">
		<input type="text" name="lastname" class="form-control"
			value="${customer.lastname}" required minlength="2" maxlength="30" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Password:</label>
	<div class="col-sm-8">
		<input type="password" id="password" name="password"
			class="form-control" value="${customer.password}" required
			minlength="5" maxlength="16" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Confirm Password:</label>
	<div class="col-sm-8">
		<input type="password" name="confirmPassword" class="form-control"
			value="${customer.password}" required minlength="5" maxlength="16"
			oninput="checkPasswordMatch(this)" />
	</div>
</div>
<div class="form-group row">
	<label class="col-sm-4 col-form-label">Phone Number:</label>
	<div class="col-sm-8">
		<input type="text" name="phone" class="form-control"
			value="${customer.phone}" required minlength="9" maxlength="15" />
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
