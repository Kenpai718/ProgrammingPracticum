package model;

/**
 * Item class of assignment 1. Establishes what an item object is.
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */

import java.math.BigDecimal;
import java.util.Objects;
import java.text.NumberFormat;
import java.util.Locale;

public final class Item {

	// instance fields

	/** The name of the item. */
	private String myItemName;

	/** The price of the item. */
	private BigDecimal myPrice;

	/** The bulk quantity of the item. */
	private int myBulkQuantity;

	/** The bulk price of the item. */
	private BigDecimal myBulkPrice;

	/**
	 * Constructs a item with a name and price
	 * 
	 * @param theName  the string name of the item
	 * @param thePrice the normal price of the item as a BigDecimal
	 */
	public Item(final String theName, final BigDecimal thePrice) {

		if (theName.isEmpty()) {
			throw new IllegalArgumentException("theName cannot be empty!");
		}

		if (thePrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(
					"thePrice cannot be less than zero!");
		}

		myItemName = Objects.requireNonNull(theName, "theName cannot be null!");
		// make sure the BigDecimal object isn't null
		myPrice = Objects.requireNonNull(thePrice,
				"theBulkPrice cannot be null.");
	}

	/**
	 * Constructs a item with a name and price and bulk information
	 * 
	 * @param theName         the string name of the item
	 * @param thePrice        the normal price of the item as a BigDecimal
	 * @param theBulkQuantity how many is considered a bulk quantity
	 * @param theBulkPrice    how much the price is for 1 bulk quantity
	 */
	public Item(final String theName, final BigDecimal thePrice,
			final int theBulkQuantity, final BigDecimal theBulkPrice) {

		this(theName, thePrice); // send to first constructor

		if (theBulkQuantity < 0) {
			throw new IllegalArgumentException(
					"theBulkQuantity cannot be less than zero!");
		}

		if (theBulkPrice.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException(
					"theBulkPrice cannot be less than zero!");
		}

		myBulkQuantity = theBulkQuantity;
		myBulkPrice = Objects.requireNonNull(theBulkPrice,
				"theBulkPrice cannot be null.");
	}

	/**
	 * Getter method to retrieve myPrice field from Item
	 * 
	 */
	public BigDecimal getPrice() {
		return myPrice;
	}

	/**
	 * Getter method to retrieve myBulkQuantity from Item
	 * 
	 */
	public int getBulkQuantity() {
		return myBulkQuantity;
	}

	/**
	 * Getter method to retrieve myBulkPrice from Item
	 * 
	 */
	public BigDecimal getBulkPrice() {
		return myBulkPrice;
	}

	/**
	 * Method to check if item object meets bulk criteria
	 * 
	 * @return boolean returns true if it meets criteria, false otherwise
	 */
	public boolean isBulk() {
		boolean result = false;
		// if item has bulk pricing return true
		if ((myBulkQuantity != 0) && !(myBulkQuantity < 0)
				&& (myBulkPrice != null)) {
			result = true;
		}

		return result;
	}

	// overridden methods from class Object

	/**
	 * Returns a String containing the item with all its information. As well as
	 * converting the BigDecimal to US dollar currency.
	 * 
	 * @return String representing the item object
	 */

	@Override
	public String toString() {
		// currency format in US dollars $
		final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

		final StringBuilder sb = new StringBuilder();
		sb.append(myItemName + ", " + nf.format(myPrice));

		// check for bulk items
		if (isBulk()) {
			sb.append(" (" + myBulkQuantity + " for " + nf.format(myBulkPrice)
					+ ")");
		}

		return sb.toString();
	}

	/**
	 * Compares two objects for equality for an item
	 * 
	 * @return boolean returns true if the two objects are considered equal
	 */
	@Override
	public boolean equals(final Object theOther) {

		boolean result = false;

		if ((theOther.getClass() == this.getClass()) && (theOther != null)) {

			final Item otherItem = (Item) theOther;

			// bulk item check
			if (isBulk()) {

				result = myItemName.equals(otherItem.myItemName)
						&& myPrice.equals(otherItem.myPrice)
						&& myBulkQuantity == otherItem.myBulkQuantity
						&& myBulkPrice.equals(otherItem.myBulkPrice);

				// single item check
			} else {
				result = myItemName.equals(otherItem.myItemName)
						&& myPrice.equals(otherItem.myPrice);
			}
		}

		return result;
	}

	/**
	 * gives hashcode for a item object
	 * 
	 * @return int of hashcode of object
	 */
	@Override
	public int hashCode() {

		// single item
		int hashResult = Objects.hash(myItemName, myPrice);

		// bulk item
		if (isBulk()) {
			hashResult = Objects.hash(myItemName, myPrice, myBulkQuantity,
					myBulkPrice);
		}

		return hashResult;

	}

}
