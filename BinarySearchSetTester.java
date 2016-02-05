/**
 * This class extensively tests our BinarySearchSet class and all of its methods
 * 
 * Authors: Kent Allen & Mohit Chaudhary
 * Last Modified: Feb. 4, 2016
 */
package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;


import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void testFirstWithNonEmptySet() {
        stringSet.add("yo");
        stringSet.add("heya");
        intSetComp.add(2);
        intSetComp.add(34);
        assertEquals((Integer)34, intSetComp.first());
        assertEquals("heya", stringSet.first());
        
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testLastWithEmptySet() {
        stringSet.last();
    }
    
    @Test
    public void testLastWithNonEmptySet() {
        stringSet.add("yo");
        stringSet.add("heya");
        intSetComp.add(2);
        intSetComp.add(34);
        assertEquals((Integer)2, intSetComp.last());
        assertEquals("yo", stringSet.last());
        
    }
    
    @Test
    public void testAddToEmptySet() {
        String testString = "hello";
        stringSet.add(testString);
        assertEquals(1, stringSet.size());
        assertEquals("hello", stringSet.toArray()[0]);
    }
    
    @Test
    public void testAddWithExsistingElement() {
        String testString = "hello";
        stringSet.add(testString);
        assertFalse(stringSet.add(testString));
    }

    @Test
    public void testAddToNonEmptyStringSet() {
        stringSet.add("hello");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        assertEquals(4, stringSet.size());
        assertEquals("aardvark", stringSet.toArray()[0]);
        assertEquals("are", stringSet.toArray()[1]);
        assertEquals("hello", stringSet.toArray()[2]);
        assertEquals("how", stringSet.toArray()[3]);
        
    }

    @Test
    public void testComparatorAddToNonEmptyStringSetComp() {
        stringSetComp.add("hello");
        stringSetComp.add("how");
        stringSetComp.add("are");
        stringSetComp.add("aardvark");
        assertEquals(4, stringSetComp.size());
        assertEquals("aardvark", stringSetComp.toArray()[3]);
        assertEquals("are", stringSetComp.toArray()[2]);
        assertEquals("hello", stringSetComp.toArray()[1]);
        assertEquals("how", stringSetComp.toArray()[0]);
        
    }
    
    @Test
    public void testAddToNonEmptyIntSet() {
        intSet.add(37);
        intSet.add(5);
        intSet.add(2);
        intSet.add(-24);
        assertEquals(4, intSet.size());
        assertEquals(-24, intSet.toArray()[0]);
        assertEquals(2, intSet.toArray()[1]);
        assertEquals(5, intSet.toArray()[2]);
        assertEquals(37, intSet.toArray()[3]);
        
    }

    @Test
    public void testComparatorAddToNonEmptyIntSetComp() {
        intSetComp.add(37);
        intSetComp.add(5);
        intSetComp.add(2);
        intSetComp.add(-24);
        assertEquals(4, intSetComp.size());
        assertEquals(37, intSetComp.toArray()[0]);
        assertEquals(5, intSetComp.toArray()[1]);
        assertEquals(2, intSetComp.toArray()[2]);
        assertEquals(-24, intSetComp.toArray()[3]);
        
    }
    
    @Test
    public void testAddAllToStringSet(){
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("This");
        stringList.add("is");
        stringList.add("a");
        stringList.add("headache");
        
        stringSet.addAll(stringList);
        assertTrue(stringSet.addAll(stringList));
        
        assertEquals(4, stringSet.size());
        assertEquals("This", stringSet.toArray()[0]);
        assertEquals("a", stringSet.toArray()[1]);
        assertEquals("headache", stringSet.toArray()[2]);
        assertEquals("is", stringSet.toArray()[3]);
        
    }
    
    @Test
    public void clearSetTest(){
        stringSet.add("hello");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        
        stringSet.clear();
        assertEquals(0, stringSet.size());
    }
    
    @Test
    public void testContainsWithTargetInSet(){
        stringSet.add("woo");
        stringSet.add("WOOOOO");
        stringSet.add("dog");
        stringSet.add("boolean");
        
        assertTrue(stringSet.contains("woo"));
        
        
    }
    
    @Test
    public void testContainsWithTargetNotInSet(){
        stringSet.add("woo");
        stringSet.add("WOOOOO");
        stringSet.add("dog");
        stringSet.add("boolean");
        
        assertFalse(stringSet.contains("woes"));
        
        
    }
    
    @Test
    public void testContainsAllwithTargetInSet(){
        ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("hello");
        myArr.add("OMG");
        myArr.add("heehee");
        myArr.add("ok");
        
        stringSet.add("hello");
        stringSet.add("OMG");
        stringSet.add("heehee");
        stringSet.add("ok");
        
        assertTrue(stringSet.containsAll(myArr));
        
        
    }
    
    @Test
    public void testContainsAllwithOutTargetInSet(){
        ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("hello");
        myArr.add("OMG");
        myArr.add("heehee");
        myArr.add("ok");
        
        stringSet.add("helloz");
        stringSet.add("OMGz");
        stringSet.add("heeheez");
        stringSet.add("okz");
        
        assertFalse(stringSet.containsAll(myArr));
        
        
    }
    
    @Test
    public void testContainsAllwithOneTargetInSet(){
        ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("hello");
        myArr.add("OMG");
        myArr.add("heehee");
        myArr.add("ok");
        
        stringSet.add("helloz");
        stringSet.add("OMG");
        stringSet.add("heeheez");
        stringSet.add("okz");
        
        assertFalse(stringSet.containsAll(myArr));
        
        
    }
    
    @Test
    public void testIsEmptyOnEmptySet(){
        assertTrue(stringSetComp.isEmpty());
    }
    
    @Test
    public void testIsEmptyOnNonEmptySet(){
        stringSetComp.add("woo");
        stringSetComp.add("WOOOOO");
        stringSetComp.add("dog");
        stringSetComp.add("boolean");
        
        assertFalse(stringSetComp.isEmpty());
    }
    
    @Test
    public void testRemoveOnEmptySet(){
        assertFalse(stringSet.remove("whatever"));
    }
    
    @Test
    public void testRemoveOnNonEmptySet(){
        stringSet.add("woo");
        stringSet.add("WOOOOO");
        stringSet.add("dog");
        stringSet.add("boolean");
        stringSet.add("hello");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        
        assertTrue(stringSet.remove("boolean"));
        assertFalse(stringSet.contains("boolean"));
        assertEquals(7, stringSet.size());
    }
    
    @Test
    public void testRemoveNonExistingElementFromSet(){
        stringSet.add("yo");
        stringSet.add("hello");
        stringSet.add("YOOOO");
        assertFalse(stringSet.remove("whatever"));
    }    
    
    @Test
    public void testRemoveAll(){
        ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("hello");
        myArr.add("OMG");
        myArr.add("heehee");
        myArr.add("ok");
        
        stringSet.add("hello");
        stringSet.add("OMG");
        stringSet.add("heehee");
        stringSet.add("boolean");
        stringSet.add("ok");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        
        assertEquals(8, stringSet.size());
        assertTrue(stringSet.removeAll(myArr));
        assertEquals(4, stringSet.size());
        assertFalse(stringSet.contains("hello"));
        assertFalse(stringSet.contains("OMG"));
        assertFalse(stringSet.contains("heehee"));
        assertFalse(stringSet.contains("ok"));
        
    }
    
    @Test
    public void testRemoveAllWithCollectionOnlyHavingOneElementInSet(){
        ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("hello");
        myArr.add("OMGz");
        myArr.add("heeheez");
        myArr.add("okz");
        
        stringSet.add("hello");
        stringSet.add("OMG");
        stringSet.add("heehee");
        stringSet.add("boolean");
        stringSet.add("ok");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        
        assertEquals(8, stringSet.size());
        assertTrue(stringSet.removeAll(myArr));
        assertEquals(7, stringSet.size());
        assertFalse(stringSet.contains("hello"));
        
    }
    
    @Test
    public void testRemoveAllWithCollectionNotHavingElementsInSet(){
        ArrayList<String> myArr = new ArrayList<String>();
        myArr.add("hellozz");
        myArr.add("OMGzzz");
        myArr.add("heeheezzz");
        myArr.add("okzzz");
        
        stringSet.add("hello");
        stringSet.add("OMG");
        stringSet.add("heehee");
        stringSet.add("boolean");
        stringSet.add("ok");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        
        assertEquals(8, stringSet.size());
        assertFalse(stringSet.removeAll(myArr));
        assertEquals(8, stringSet.size());
    }
    
    @Test
    public void testIteratorHasNextWithEmptySet(){
        assertFalse(stringSet.iterator().hasNext());
    }
    
    @Test
    public void testIteratorHasNextWithNonEmptySet(){
        stringSet.add("woes");
        stringSet.add("i got em");
        assertTrue(stringSet.iterator().hasNext());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextWithEmptySet(){
        stringSet.iterator().next();
    }
    
    @Test 
    public void testIteratorNextWithNonEmptySet(){
        Iterator<String> it = stringSet.iterator();
        stringSet.add("woes");
        stringSet.add("zoes");
        assertEquals("woes", it.next());
        assertEquals("zoes", it.next());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testIteratorRemoveWithEmpty(){
        stringSet.iterator().remove();
    }
    
    @Test
    public void testIteratorRemoveWith1Element(){
        Iterator<String> it = stringSet.iterator();
        stringSet.add("woes");
        stringSet.add("woes2");
        it.next();
        it.remove();
        assertEquals(1, stringSet.size());
    }
    
    @Test(expected = IllegalStateException.class)
    public void testIteratorCallingRemoveAfterCallingRemoveWithoutCallingNext(){
        Iterator<String> it = stringSet.iterator();
        stringSet.add("woes");
        stringSet.add("woes2");
        it.next();
        it.remove();
        it.remove();
    }
    
    @Test
    public void testResizeingWithSmallSet(){
        stringSet.add("woo");
        stringSet.add("WOOOOO");
        stringSet.add("dog");
        stringSet.add("boolean");
        stringSet.add("hello");
        stringSet.add("how");
        stringSet.add("are");
        stringSet.add("aardvark");
        stringSet.add("woh");
        stringSet.add("WOOO");
        stringSet.add("dogy");
        stringSet.add("booleanAHHH");
        stringSet.add("hello!?");
        stringSet.add("ho");
        stringSet.add("HOWDYHOWNEIGHBOREENO");
        stringSet.add("zSTUPIDFLANDERS");
        
        assertEquals(16, stringSet.size());
        assertEquals("zSTUPIDFLANDERS", stringSet.last());
        
    }
    
    @Test
    public void testResizingwithLargeSet(){
        for(int i = 0; i < 10000; i++){
            assertTrue(intSet.add(i));
        }
    }

    
}