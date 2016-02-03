package assignment03;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment02.LibraryBookGeneric;

public class BinarySearchSetTester {

	 protected class ComparatorStrings implements Comparator<String> {

		  /**
		   * Returns a negative value if lhs is smaller than rhs. Returns a positive
		   * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
		   */
		  

		@Override
		public int compare(String o1, String o2) {
			
			return o1.compareTo(o2) * -1;
		}
	 }
		  
		
	protected class ComparatorInts implements Comparator<Integer> {

		/**
		 * Evaluates opposite of natural ordering returns negative if o2 is less
		 * than o1 returns positive if o2 is greater than o1 returns zero if o2
		 * equals o1
		 */

		@Override
		public int compare(Integer o1, Integer o2) {

			return o2 - o1;
		}

	}
	
	BinarySearchSet<String> stringSet;
	BinarySearchSet<Integer> intSet;
	BinarySearchSet<String> stringSetComp;
	BinarySearchSet<Integer> intSetComp;
	
	@Before
	public void setUp() throws Exception {
		stringSet = new BinarySearchSet<String>();
		intSet = new BinarySearchSet<Integer>();
		stringSetComp = new BinarySearchSet<String>(new ComparatorStrings());
		intSetComp = new BinarySearchSet<Integer>(new ComparatorInts());
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void constructorTestWithoutComparator() {
		BinarySearchSet<String> stringConstructorWithComparator = new BinarySearchSet<String>();
		assertNull(stringConstructorWithComparator.comparator());
	}
	
	@Test
	public void constructorTestWithComparator() {
	BinarySearchSet<String> intConstructorWithComparatorTest = new BinarySearchSet<String>(new ComparatorStrings());
	assertNotNull(intConstructorWithComparatorTest.comparator());
	}
	
	@Test
	public void sizeOfEmptySet() {
		assertEquals(0, intSet.size());
		assertEquals(0, stringSet.size());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFirstWithEmptySet() {
		stringSet.first();
	}
	
	
	@Test
	public void testAddToEmptySet() {
		String testString = "hello";
		stringSet.add(testString);
		assertEquals(1, stringSet.size());
		//assertEquals("hello", stringSet.toArray()[0]);
	}
	

	
	@Test
	public void testComparableAddToNonEmptySet() {
		String testString = "hello";
		stringSet.add(testString);
		stringSet.add("how");
		stringSet.add("are");
		stringSet.add("aardvark");
		assertEquals(3, stringSet.size());
		assertEquals("aardvark", stringSet.toArray()[0]);
		assertEquals("hello", stringSet.toArray()[1]);
		assertEquals("how", stringSet.toArray()[2]);
		assertEquals("are", stringSet.toArray()[3]);
	}

	@Test
	public void testComparatorAddToNonEmptySet() {
		String testString = "hello";
		stringSetComp.add(testString);
		stringSetComp.add("how");
		stringSetComp.add("are");
		stringSetComp.add("aardvark");
		assertEquals(3, stringSetComp.size());
		assertEquals("aardvark", stringSetComp.toArray()[0]);
		assertEquals("hello", stringSetComp.toArray()[1]);
		assertEquals("how", stringSetComp.toArray()[2]);
		assertEquals("are", stringSetComp.toArray()[3]);
	}
}
