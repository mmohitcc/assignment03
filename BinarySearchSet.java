package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
	
	private E[] list;
	private Comparator comparator;
	
	// Creates a BinarySearchSet Object with types that implement Comparable
	public BinarySearchSet (Comparable<? super E> comparable ) {
		@SuppressWarnings("unchecked")
		E[] list = (E[])new Object[10];	
	}
	// Creates a BinarySearchSet Object with types that implement Comparator
public BinarySearchSet(Comparator<? super E> comparator) {
	this.comparator = comparator;
	@SuppressWarnings("unchecked")
	E[] list = (E[])new Object[10];
		
	}


	@Override
	public Comparator<? super E> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> elements) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> elements) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> elements) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private int binarySearch(Comparator<? super E> elementToSearchFor){
		
		
		
		
		return 1;
		
	}

}
