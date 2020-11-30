package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import model.Item;
import model.ItemOrder;

/**
 * JUNIT4 Testing for ItemOrder class
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */


public class ItemOrderTest {

	// testing constants for item class
	final static String NAME = "Pen";
	final static BigDecimal PRICE = new BigDecimal("1.00");
	final static int BULK_QUANTITY = 4;
	final static BigDecimal BULK_PRICE = new BigDecimal("5.00");

	// testing constants for ItemOrder class
	final static Item AN_ITEM = new Item(NAME, PRICE, BULK_QUANTITY,
			BULK_PRICE);
	final static int ORDER_QUANTITY = 5;

	//instance fields to test constructor
	private ItemOrder myOrder;

	@Before
	public void setUp() throws Exception {
		myOrder = new ItemOrder(AN_ITEM, ORDER_QUANTITY);
	}

	/**
	 * Test of single ItemOrder constructor with invalid quantity
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testItemOrderConstructorQuantityLessThan0() {
		new ItemOrder(AN_ITEM, -1);
	}

	/**
	 * Test of single Item constructor with null name value.
	 */
	@Test(expected = NullPointerException.class)
	public void testItemOrderConstructorNameNull() {
		new ItemOrder(null, ORDER_QUANTITY);
	}
	/**
	 * Test of ItemOrder constructor
	 */
	@Test
	public void testItemOrder() {
		ItemOrder order2 = new ItemOrder(AN_ITEM, 10);
	}

	/**
	 * Test of ItemOrder get item
	 */
	@Test
	public void testGetItem() {
		// System.out.println(AN_ITEM.toString());
		// get method for item should give "Pen, $1.00 (4 for $5.00)"
		String str = "Pen, $1.00 (4 for $5.00)";
		assertEquals("getItem() does not return the correct item",
				myOrder.getItem().toString(), str);
		// fail("Not yet implemented");
	}

	/**
	 * Test of ItemOrder get quantity
	 */
	@Test
	public void testGetQuantity() {
		// order quantity should be 5
		assertEquals("getQuantity is returning an incorrect quantity.",
				myOrder.getQuantity(), ORDER_QUANTITY);
		// fail("Not yet implemented");
	}

	/**
	 * Test of ItemOrder tostring
	 */
	@Test
	public void testToString() {
		//System.out.println(myOrder.toString());
		String str = "Item: [Pen, $1.00 (4 for $5.00)], Total Quantity: 5";
		assertEquals("toString() has produced an unexpected result.", myOrder.toString(), str);
		// fail("Not yet implemented");
	}

}
