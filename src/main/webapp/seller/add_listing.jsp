<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../frontend/page_head.jsp">
	<jsp:param name="pageTitle" value="Add Listing" />
</jsp:include>
<style>
#cardDropdown {
	max-height: 200px;
	overflow-y: auto;
	border: 1px solid #ccc;
}

.item {
	padding: 5px;
}

#inputBox{
	width: 100%;
}

#dropdown {
	width: 100%;
	border: 1px solid #ccc;
	display: none; /* Initially hidden */
	background: white;
	max-height: 150px;
	overflow-y: auto;
	z-index: 1000;
}

#dropdown div {
	padding: 10px;
	cursor: pointer;
}

#dropdown div:hover {
	background: #f0f0f0;
}
</style>
<body>
		<jsp:directive.include file="../frontend/header_without_searchbar.jsp" />
	<div class="container">


		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-center">
				<h2>Add New Listing</h2>
				<p>Add you new listing here.</p>
				<hr>
			</div>
		</div>

		<form action="listings" method="post"
			style="max-width: 800px; margin: 0 auto;">
			<%-- <jsp:directive.include file="../common/listing_form.jsp" /> --%>
			<div class="row mt-3">
				<div class="col-md-4" id="cardImageDiv" style="display: none;">
					<!-- Image Section with Inline CSS -->
					<img id="cardImage" alt="Placeholder Image"
						style="max-width: 100%; height: auto;">
				</div>
				<div class="col-md-12" id="listingDataDiv">
					<div class="form-group">
						<input type="hidden" name="action" value="insert" /> 
						<label
							for="name">Serial Number</label>
						<div class="row">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputBox"
									placeholder="Click to see cards" readonly>
								<div id="dropdown"></div>
							</div>
							<div class="col-sm-2 text-right">
								<input type="button" id="getCardDataBtn"
									class="btn btn-primary" value="Select" />
							</div>
						</div>
					</div>
					<div id="cardDetailsDiv" style="display: none;">
						<input type="hidden" name="serialNumber" id="serialNumberInput"  />
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


	</div>
			<jsp:directive.include file="../frontend/footer.jsp" />
	<script>
		function fetchCardDetails(serialNumber) {
			/* var cardId = document.getElementById("serialNumber").value; */
			var cardId = serialNumber;
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
		
		const inputBox = document.getElementById('inputBox');
        const dropdown = document.getElementById('dropdown');
        const getCardDataBtn = document.getElementById('getCardDataBtn');
        const serialNumberInput = document.getElementById("serialNumberInput");
		
		let offset = 0;
        const limit = 10; // Number of items to load on each scroll
        let loading = false;
        
        let selectedSerialNumber = null;
        
        function loadItems() {
        	console.log("I am in loadItems")
        	if (loading) return; // Prevent multiple requests
            loading = true;
            
            const xhr = new XMLHttpRequest();
            xhr.open('GET', 'loadCards?offset=' + offset + '&limit=' + limit, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const data = JSON.parse(xhr.responseText);
                    if (data.length > 0) {
                        appendDropdownItems(data);
                        offset += data.length; // Update offset
                    } else {
                        dropdown.removeEventListener('scroll', onScroll); // No more data
                    }
                    loading = false; // Reset loading status
                }
            };
            xhr.send();
        }
        
        
        // Append items to the dropdown
        function appendDropdownItems(data) {
            data.forEach(item => {
                const div = document.createElement('div');
                div.textContent = item.serialNumber + ' - ' + item.cardName;// Format
                div.setAttribute('data-serial-number', item.serialNumber); 
                
                div.addEventListener('click', () => {
                	selectedSerialNumber = item.serialNumber;
                	serialNumberInput.value = selectedSerialNumber;
                    inputBox.value = div.textContent; // Set input value to selected item
                    dropdown.style.display = 'none';
                });
                dropdown.appendChild(div);
            });
        }
        
     // Handle scroll event for lazy loading
        function onScroll() {
            if (dropdown.scrollTop + dropdown.clientHeight >= dropdown.scrollHeight) {
            	loadItems(); // Fetch more data when scrolled to the bottom
            }
        }

        // Show dropdown on input box click
        inputBox.addEventListener('click', () => {
            if (dropdown.style.display === 'block') {
                dropdown.style.display = 'none';
            } else {
                dropdown.innerHTML = ''; // Clear previous items
                loadItems(); // Fetch initial data when dropdown is shown
                dropdown.style.display = 'block';
                dropdown.addEventListener('scroll', onScroll); // Add scroll listener
            }
        });
        
        // Close dropdown when clicking outside
        document.addEventListener('click', (event) => {
            if (!inputBox.contains(event.target) && !dropdown.contains(event.target)) {
                dropdown.style.display = 'none';
                dropdown.innerHTML = ''; // Clear items when closed
                offset = 0; // Reset offset
            }
        });
        
     // Button click event to get the selected serial number
        getCardDataBtn.addEventListener('click', () => {
            if (selectedSerialNumber) {
            	fetchCardDetails(selectedSerialNumber);
            } else {
                alert("No card selected."); // Inform the user if no selection has been made
            }
        });
        
	</script>

</body>
</html>