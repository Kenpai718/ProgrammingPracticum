package model;

/**
 * ItemOrder class of assignment 1. Works of the "Item" class object, but adds an additional field for
 * how many quantities of that item are being ordered.
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.math.BigDecimal;

public final class ItemOrder {

	// instance fields
	/** Item object for this ItemOrder */
	private final Item myItem;
	/** How many quantity of this item are being ordered */
	private final int myQuantity;

	/**
	 * Constructs an ItemOrder and the item and quantity
	 * 
	 * @param theItem     the Item object
	 * @param theQuantity the quantity of the items ordered
	 */
	public ItemOrder(final Item theItem, final int theQuantity) {

		if (theQuantity < 0) {
			throw new IllegalArgumentException(
					"theQuantity cannot be less than zero!");
		}

		myItem = Objects.requireNonNull(theItem, "myItem can't be null");
		myQuantity = theQuantity;

	}

	/**
	 * Getter method to retrieve a copy of the Item object.
	 * 
	 */
	public Item getItem() {
		Item itemCopy = myItem;
		return itemCopy;
	}

	/**
	 * Getter method to retrieve quantity of items ordered
	 * 
	 */
	public int getQuantity() {
		return myQuantity;
	}

	/**
	 * Returns a String formatting the ItemOrder
	 * 
	 * @return String representing the ItemOrder object
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Item: [" + myItem + "], ");
		sb.append("Total Quantity = " + myQuantity);

		return sb.toString();
	}

}
