// Finish and comment me!

package model;

/**
 * Cart class of assignment 1. Used to put item orders into a cart and calculate the total price.
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class Cart {

	// instance variables

	/** Data structure to store ItemOrder info in a map for ease of use */
	private Map<Item, Integer> myCartOrders;

	/**
	 * Data structure to store an item object, and the total price calculated
	 * from its corresponding ItemOrder into a map
	 */
	private Map<Item, BigDecimal> myCartPrices;

	/** Boolean to check if is a member and for applying discounts */
	private boolean myMembership;

	/**
	 * Constructor to initialize the Cart fields
	 */
	public Cart() {
		// using 2 similar maps with Item keys to store important cart
		// information and calculations
		myCartOrders = new HashMap<Item, Integer>();
		myCartPrices = new HashMap<Item, BigDecimal>();
		myMembership = false;

	}

	/**
	 * Takes out key information from an ItemOrder and fills out two instance
	 * field maps. This method fills in 2 maps, myCartOrders and myCartPrices.
	 * Both maps have the same item key, but link to different values. This
	 * method adds both the item and value to the map. Also if the item key
	 * being added already exists in the map, but has a different value then the
	 * value in the map will be replaced with the new one.
	 * 
	 * @param theOrder the ItemOrder that is added to a cart
	 */
	public void add(final ItemOrder theOrder) {

		Objects.requireNonNull(theOrder, "theOrder can't be null!");
		Item anItem = theOrder.getItem();
		int quantity = theOrder.getQuantity();

		// in case an order is being replaced it will be removed if it is
		// already in the map. Readded with new info later on!
		if (myCartOrders.containsKey(anItem)) {
			myCartOrders.remove(anItem);
			myCartPrices.remove(anItem);
		}

		// place item and quantity in a collection
		myCartOrders.put(anItem, quantity);

		// place item and final order price into a collection
		myCartPrices.put(anItem, calculateOrderPrice(anItem, quantity));

	}

	/**
	 * Private helper method to calculate what the total price is based on the
	 * information of an ItemOrder class. Prices will be different depending on
	 * various factors such as membership discount.
	 * 
	 * @param theItem     the item class added to cart
	 * @param theQuantity the number of theItem that is being ordered in the
	 *                    cart
	 * @return a BigDecimal of the corresponding price of the ItemOrder
	 */
	private BigDecimal calculateOrderPrice(Item theItem, int theQuantity) {

		BigDecimal orderPrice = BigDecimal.ZERO;
		BigDecimal normPrice = theItem.getPrice();

		// if membership is true then apply bulk price discount

		// check if item has a bulk and has a membership, if so then apply
		// discount
		if (myMembership == true && theItem.isBulk()) {

			int bulkQuantity = theItem.getBulkQuantity();
			BigDecimal bulkPrice = theItem.getBulkPrice();

			// if quantity is less then bulk quantity then charge normal price
			if (theQuantity < bulkQuantity) {
				orderPrice = normPrice.multiply(new BigDecimal(theQuantity));

				// enough quantity for bulk, decide how much to charge
				// considering extras
			} else {

				// variables to check how to calculate order price
				final int leftover = theQuantity % bulkQuantity;
				final int numBulks = (int) (theQuantity / bulkQuantity);

				// no leftovers means charge only bulk price
				if (leftover == 0) {
					orderPrice = new BigDecimal(numBulks).multiply(bulkPrice);
				}

				// means there are leftovers so charge bulk price + singles
				// prices
				if (leftover > 0) {
					BigDecimal bulkOrders = new BigDecimal(numBulks)
							.multiply(bulkPrice);
					BigDecimal singleOrders = new BigDecimal(leftover)
							.multiply(normPrice);
					orderPrice = bulkOrders.add(singleOrders);
				}

			}

			// not a member or not a bulk item, just charge normal pricing
		} else {
			orderPrice = normPrice.multiply(new BigDecimal(theQuantity));
		}

		return orderPrice;

	}

	/**
	 * Sets whether this cart will have discounts applied or not. Will replace
	 * existing prices in data collection in case discount needs to be added or
	 * removed. Initializes myMembership field.
	 * 
	 * @param theMembership boolean that dictates if the cart is a member
	 */
	public void setMembership(final boolean theMembership) {

		myMembership = theMembership;

		// checking if there were items in the cart before the setMembership
		// if so then change prices if they are applicable
		if (!(myCartOrders.isEmpty())) {
			replaceExistingCartPrices();
		}
	}

	/**
	 * In the cart GUI, if membership is selected after items are in the cart
	 * the prices need to be updated for the discount. This method replaces
	 * myCartPrices values with the correct prices.
	 * 
	 */
	private void replaceExistingCartPrices() {
		final Iterator<Item> itr = myCartPrices.keySet().iterator();

		while (itr.hasNext()) {
			Item anItem = itr.next();
			int quantity = myCartOrders.get(anItem);

			BigDecimal newPrice = calculateOrderPrice(anItem, quantity);

			myCartPrices.replace(anItem, newPrice);
		}
	}

	/**
	 * Totals up all the ItemOrder prices to calculate the total of the cart.
	 * 
	 * @return BigDecimal of the the total price of the cart
	 */
	public BigDecimal calculateTotal() {
		BigDecimal cartTotal = BigDecimal.ZERO;

		final Iterator<Item> itr = myCartPrices.keySet().iterator();

		while (itr.hasNext()) {
			Item anItem = itr.next();
			final BigDecimal orderPrice = myCartPrices.get(anItem);

			cartTotal = cartTotal.add(orderPrice);

		}

		return cartTotal.setScale(2, RoundingMode.HALF_EVEN);
	}

	/**
	 * Removes all keys and values from cart data structures
	 * 
	 */
	public void clear() {
		myCartOrders.clear();
		myCartPrices.clear();
	}

	/**
	 * Scans the map of the cart and counts how many unique ItemOrders were in
	 * the cart.
	 * 
	 * @return int of how many item orders are in the cart
	 */
	public int getCartSize() {
		// the cart is essentially an itemOrder per key in the map
		int total = 0; // start with no items in cart
		Iterator<Item> itr = myCartOrders.keySet().iterator();

		while (itr.hasNext()) {
			itr.next();
			total++;
		}

		return total;

	}

	/**
	 * Formats how to represent a cart object in a string.
	 * 
	 * @return String of cart object
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		boolean isMember = false;

		if (myMembership) {
			isMember = true;
		}
		sb.append("Eligible for bulk discount (is a member) = " + isMember
				+ "\n");
		sb.append("Cart = \n");

		sb.append("Items Order(s): \n");

		final Iterator<Item> itr = myCartOrders.keySet().iterator();
		int counter = 1;

		while (itr.hasNext()) {
			Item anItem = itr.next();
			int quantity = myCartOrders.get(anItem);
			BigDecimal price = myCartPrices.get(anItem);

			sb.append("Order " + counter + ". [");
			sb.append(anItem.toString() + ", ");
			sb.append("Quantity = " + quantity + ", ");
			sb.append("Price of order(s) = $" + price + "]\n");

			counter++;

		}

		sb.append("\n");
		sb.append("Total of cart: $" + calculateTotal());

		return sb.toString();

	}

}
