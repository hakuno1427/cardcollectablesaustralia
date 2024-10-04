package com.cardstore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Card;
import com.cardstore.entity.Listing;
import com.cardstore.entity.Order;
import com.cardstore.entity.OrderItem;
import com.cardstore.entity.User;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Payout;
import com.paypal.api.payments.PayoutBatch;
import com.paypal.api.payments.PayoutItem;
import com.paypal.api.payments.PayoutSenderBatchHeader;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PaymentService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private UserDAO userDAO;
	
	public PaymentService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.userDAO = new UserDAO();
	}
	
	public void authorizePayment(Order order) throws ServletException, IOException {
		Payer payer = getPayerInformation(order);
		RedirectUrls redirectUrls = getRedirectURLs();		
			
		List<Transaction> transactions = getTransactionInformation(order);
		
		Payment requestPayment = new Payment();
		requestPayment.setPayer(payer)
					  .setRedirectUrls(redirectUrls)
					  .setIntent("authorize")
					  .setTransactions(transactions);
		
		System.out.println("====== REQUEST PAYMENT: ======");
		System.out.println(requestPayment);		
		
		APIContext apiContext = new APIContext(PaypalConfig.getClientId(), PaypalConfig.getClientSecret(), PaypalConfig.getMode());
		
		try {
			Payment authorizedPayment = requestPayment.create(apiContext);
			System.out.println("====== AUTHORIZED PAYMENT: ======");
			System.out.println(authorizedPayment);
			
			String approvalURL = getApprovalURL(authorizedPayment);
			
			response.sendRedirect(approvalURL);
			
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			throw new ServletException("Error in authorizing payment.");
		}
		
		
		// get approval link
		
		// redirect to Paypal's payment page
	}
	
	private String getApprovalURL(Payment authorizedPayment) {
		String approvalURL = null;
		
		List<Links> links = authorizedPayment.getLinks();
		
		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalURL = link.getHref();
				break;
			}
		}
		
		return approvalURL;
	}

	private List<Transaction> getTransactionInformation(Order order) {
		Transaction transaction = new Transaction();
		transaction.setDescription("Trading cards");
		Amount amount = getAmountDetails(order);
		transaction.setAmount(amount);
		
		ItemList itemList = new ItemList();
		ShippingAddress shippingAddress = (ShippingAddress) request.getSession().getAttribute("paypalShippingAddress4Paypal");
		itemList.setShippingAddress(shippingAddress);
		
		List<Item> paypalItems = new ArrayList<>();
		Iterator<OrderItem> iterator = order.getOrderItems().iterator();
		
		while (iterator.hasNext()) {
			OrderItem orderItem = iterator.next();
			Listing listing = orderItem.getListing();
			Card card = listing.getCard();
			Integer quantity = orderItem.getQuantity();
			
			Item paypalItem = new Item();
			paypalItem.setCurrency("AUD")
					  .setName(card.getCardName())
					  .setQuantity(String.valueOf(quantity))
					  .setPrice(String.format("%.2f", listing.getPrice()));
			
			paypalItems.add(paypalItem);			
		}
		
		itemList.setItems(paypalItems);
		transaction.setItemList(itemList);
		
		List<Transaction> listTransaction = new ArrayList<>();
		listTransaction.add(transaction);
		
		return listTransaction;
	}
	
	private Payer getPayerInformation(Order order) {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
				
		User buyer = userDAO.get(order.getBuyerId());
		
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(buyer.getFirstName());
		payerInfo.setLastName(buyer.getLastName());
		payerInfo.setEmail(buyer.getEmail());
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	
	private RedirectUrls getRedirectURLs() {
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();
		String baseURL = requestURL.replace(requestURI, "").concat(request.getContextPath());
		
		RedirectUrls redirectUrls = new RedirectUrls();
		String cancelUrl = baseURL.concat("/view_cart");
		String returnUrl = baseURL.concat("/review_payment");
		
		System.out.println("Return URL: " + returnUrl);
		System.out.println("Cancel URL: " + cancelUrl);
		
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(returnUrl);
		
		return redirectUrls;
	}
	
	private Amount getAmountDetails(Order order) {
		Details details = new Details();
		details.setShipping(String.format("%.2f", order.getShippingFee()));
		details.setTax(String.format("%.2f", 0.0));
		details.setSubtotal(String.format("%.2f", order.getSubtotal()));
		
		Amount amount =  new Amount();
		amount.setCurrency("AUD");
		amount.setDetails(details);
		amount.setTotal(String.format("%.2f", order.getTotalPrice()));
		
		return amount;
	}

	public void reviewPayment() throws ServletException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		if (paymentId == null || payerId == null) {
			throw new ServletException("Error in displaying payment review");
		}
		
		APIContext apiContext = new APIContext(PaypalConfig.getClientId(), PaypalConfig.getClientSecret(), PaypalConfig.getMode());
		
		try {
			Payment payment = Payment.get(apiContext, paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("recipient", shippingAddress);
			request.setAttribute("transaction", transaction);
			
			String reviewPage = "frontend/review_payment.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
			request.getRequestDispatcher(reviewPage).forward(request, response);
			
		} catch (PayPalRESTException | IOException e) {
			e.printStackTrace();
			throw new ServletException("Error in getting payment details from PayPal.");
		}
	}

	public Payment executePayment() throws PayPalRESTException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		
		Payment payment = new Payment().setId(paymentId);
		
		APIContext apiContext = new APIContext(PaypalConfig.getClientId(), PaypalConfig.getClientSecret(), PaypalConfig.getMode());
		
		return payment.execute(apiContext, paymentExecution);
		
	}
	
	public void sendPayout(String receiverEmail, double amount, String message) throws Exception {
	    APIContext apiContext = new APIContext(PaypalConfig.getClientId(), PaypalConfig.getClientSecret(), PaypalConfig.getMode());

	    // Create a payout item
	    PayoutItem payoutItem = new PayoutItem()
	            .setRecipientType("EMAIL")
	            .setAmount(new Currency().setValue(String.format("%.2f",amount)).setCurrency("AUD"))
	            .setReceiver(receiverEmail)
	            .setNote(message)
	            .setSenderItemId("item_" + System.currentTimeMillis());

	    // Create payout
	    Payout payout = new Payout()
	            .setSenderBatchHeader(new PayoutSenderBatchHeader()
	                    .setSenderBatchId("batch_" + System.currentTimeMillis())
	                    .setEmailSubject("You have a payment"))
	            .setItems(Arrays.asList(payoutItem));

	    // Send payout
	    PayoutBatch payoutBatch = payout.create(apiContext, null);
	    System.out.println("Payout Batch ID: " + payoutBatch.getBatchHeader().getPayoutBatchId());
	}
}
