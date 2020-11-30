
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.math.BigDecimal;
import model.Item;

/**
 * JUNIT4 Testing for Item class
 * 
 * @author Kenneth Ahrens
 * @version Fall 2020
 */

public class ItemTest {

	// testing constants that will be used for item class
	final static String NAME = "Pen";
	final static BigDecimal PRICE = new BigDecimal("1.00");
	final static int BULK_QUANTITY = 4;
	final static BigDecimal BULK_PRICE = new BigDecimal("5.00");

	//instance field for testing item
	private Item myItem;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// params are (String theName, BigDecimal thePrice,
		// int theBulkQuantity, BigDecimal theBulkPrice
		myItem = new Item(NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
	}

	/**
	 * Test of single Item constructor with empty string.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testItemConstructorEmptySting() {
		new Item("", PRICE);
	}

	/**
	 * Test of single Item constructor with negative item price.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testItemConstructorNegativePrice() {
		new Item(NAME, new BigDecimal("-5.00"));
	}

	/**
	 * Test of single Item constructor with null name value.
	 */
	@Test(expected = NullPointerException.class)
	public void testItemConstructorNameNull() {
		new Item(null, PRICE);
	}

	/**
	 * Test of single Item constructor with null name value.
	 */
	@Test(expected = NullPointerException.class)
	public void testItemConstructorPriceNull() {
		new Item(NAME, null);
	}

	/**
	 * Test of bulk Item constructor with negative bulk quantity value.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBulkItemConstructorNegativeQuantity() {
		new Item(NAME, PRICE, -1, BULK_PRICE);
		new Item(NAME, PRICE, 0, BULK_PRICE);
	}

	/**
	 * Test of bulk Item constructor with negative bulk price value.
	 */
	@Test(expected = NullPointerException.class)
	public void testBulkItemConstructorNullPrice() {
		new Item(NAME, PRICE, BULK_QUANTITY, null);
	}

	/**
	 * Test of bulk Item constructor with negative bulk price value.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testBulkItemConstructorNegativePrice() {
		new Item(NAME, PRICE, BULK_QUANTITY, new BigDecimal("-1.00"));
	}

	/**
	 * Test method for {@link model.Item#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Item item2 = new Item("Pen", new BigDecimal("1.00"), 4,
				new BigDecimal("5.00"));
		assertEquals("hashCode() failed to produce identitical values.",
				myItem.hashCode(), item2.hashCode());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for getPrice()
	 */
	@Test
	public void testGetPrice() {
		// price should be "1.00"
		assertEquals("getPrice() does not return correct price.", PRICE,
				myItem.getPrice());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for getBulkQuantity
	@Test
	public void testGetBulkQuantity() {
		assertEquals(
				"getBulkQuantity() does not return the correct bulk quantity.",
				BULK_QUANTITY, myItem.getBulkQuantity());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for getBulkPrice
	 */
	@Test
	public void testGetBulkPrice() {
		assertEquals("getBulkPrice() does not return the correct bulk price.",
				BULK_PRICE, myItem.getBulkPrice());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for when isBulk is true
	 */
	@Test
	public void testIsBulkTrue() {
		// myItem has a bulk quantity so it is true
		assertTrue(myItem.isBulk());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for when IsBulk is false
	 */
	@Test
	public void testIsBulkFalse() {
		// nonBulk has no a bulk quantity/price so it is false
		Item nonBulk = new Item("Happiness", new BigDecimal("299.99"));
		assertFalse(nonBulk.isBulk());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for toString override method
	 */
	@Test
	public void testToString() {
		// System.out.println(myItem.toString());
		String statement = "Pen, $1.00 (4 for $5.00)";
		assertEquals("toString() formatted an incorrect string format.",
				statement, myItem.toString());
		// fail("Not yet implemented");
	}

	/**
	 * Test method for equals reflexive property
	 */
	@Test
	public void testEqualsReflexive() {
		// test if an object is equal to itself
		assertEquals(
				"equals() fails a test of the reflexive property; bulk constructor fail,",
				myItem, myItem);

		Item item2 = new Item("Book", new BigDecimal("50.00"));
		assertEquals(
				"equals() fails a test of the reflexive property; normal constructor fail.",
				item2, item2);

	}

	/**
	 * Test method for equals when given null
	 */
	@Test(expected = NullPointerException.class)
	public void testEqualsNull() {
		// equals should return false if null
		assertNotEquals(
				"equals() fails to return false when passed a null parameter",
				myItem, null);
	}

	/**
	 * Test method for equals when given different type
	 */
	@Test
	public void testEqualsDifferentType() {
		// equals should return false for the wrong parameter type
		assertNotEquals(
				"equals() fails to return false when passed the wrong class type",
				myItem, new BigDecimal("21.00"));
	}

	/**
	 * Test method for equals symetric property
	 */
	@Test
	public void testEqualsSymmetric() {
		// test if symmetric property holds
		Item item2 = new Item(NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
		assertEquals("equals() fails a test of the symmetric property.", myItem,
				item2);
		assertEquals("equals() fails a test of the symmetric property.", item2,
				myItem);

	}

	/**
	 * Test method for equals different item name
	 */
	@Test
	public void testEqualsDiffName() {
		// item with a different name should be unequal return false
		Item item2 = new Item("BAND-MAID", PRICE, BULK_QUANTITY, BULK_PRICE);
		assertFalse(myItem.equals(item2));
		assertFalse(item2.equals(myItem));

	}

	/**
	 * Test method for equals different item price
	 */
	@Test
	public void testEqualsDiffPrice() {
		// item with a different price should be unequal return false
		Item item2 = new Item(NAME, new BigDecimal("21.00"), BULK_QUANTITY,
				BULK_PRICE);
		assertFalse(myItem.equals(item2));
		assertFalse(item2.equals(myItem));

	}

	/**
	 * Test method for equals different item quantity 
	 */
	@Test
	public void testEqualsDiffQuantity() {
		// item with a different quantity should be unequal return false
		Item item2 = new Item(NAME, PRICE, 7, BULK_PRICE);
		assertFalse(myItem.equals(item2));
		assertFalse(item2.equals(myItem));

	}

	/**
	 * Test method for item different item bulk price
	 */
	@Test
	public void testEqualsDiffBulkPrice() {
		// item with a different bulk price should be unequal return false
		Item item2 = new Item(NAME, PRICE, BULK_QUANTITY,
				new BigDecimal("21.00"));
		assertFalse(myItem.equals(item2));
		assertFalse(item2.equals(myItem));

	}

}
