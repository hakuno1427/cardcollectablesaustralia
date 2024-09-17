package com.cardstore.controller.cart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.cardstore.dao.ListingDAO;
import com.cardstore.entity.Listing;

public class ShoppingCart {
	private Map<Listing, Integer> cart = new HashMap<>();

	public void addItem(Listing listing) {
		if (cart.containsKey(listing)) {
			Integer quantity = cart.get(listing) + 1;
			cart.put(listing, quantity);
		} else {
			cart.put(listing, 1);
		}
	}

	public void removeItem(Listing listing) {
		cart.remove(listing);
	}

	public int getTotalQuantity() {
		int total = 0;

		Iterator<Listing> iterator = cart.keySet().iterator();

		while (iterator.hasNext()) {
			Listing next = iterator.next();
			Integer quantity = cart.get(next);
			total += quantity;
		}

		return total;
	}

	public float getTotalAmount() {
		float total = 0.0f;

		Iterator<Listing> iterator = cart.keySet().iterator();

		while (iterator.hasNext()) {
			Listing listing = iterator.next();
			Integer quantity = cart.get(listing);
			double subTotal = quantity * listing.getPrice();
			total += subTotal;
		}

		return total;
	}

	public void updateCart(int[] listingIds, int[] quantities) {
		ListingDAO listingDAO = new ListingDAO();

		for (int i = 0; i < listingIds.length; i++) {
			Listing key = listingDAO.get(listingIds[i]);
			Integer value = quantities[i];
			cart.put(key, value);
		}
	}

	public void clear() {
		cart.clear();
	}

	public int getTotalItems() {
		return cart.size();
	}

	public Map<Listing, Integer> getItems() {
		return this.cart;
	}

}
