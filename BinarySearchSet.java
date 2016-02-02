package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
	
	
	private E[] list;
	private Comparator<? super E> comparator;
	private int size;
	
	// Creates a BinarySearchSet Object with types that implement Comparable
	public BinarySearchSet () {
		
		list = (E[])new Object[10];	
		size = 0;
	}
	// Creates a BinarySearchSet Object with types that implement Comparator
	public BinarySearchSet(Comparator<? super E> comparator) {
		this.comparator = comparator;

		list = (E[]) new Object[10];
		size = 0;

	}


	@Override
	public Comparator<? super E> comparator() {
		if(comparator != null){
			return comparator;
		}
		return null;
	}

	@Override
	public E first() throws NoSuchElementException {
		return list[0];
	}

	@Override
	public E last() throws NoSuchElementException {
		return list[size - 1];
	}

	@Override
	public boolean add(E element) {
		if (!contains(element)) {
			if(size == list.length){
				resize();
			}
			
			int index = binarySearch(element);
			for (int i = list.length - 1; i > index; i--) {
				list[i + 1] = list[i];
			}
			list[index] = element;
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> elements) {
		if (elements.iterator().hasNext()) {
			while (elements.iterator().hasNext()) {
				add(elements.iterator().next());
			}
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		list = (E[])new Object[10];
	}

	@Override
	public boolean contains(Object element) {
		E other = (E) element;
		int index = binarySearch(other);
		if(myCompare(list[index], other) == 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> elements) {
		while(elements.iterator().hasNext()){
			if(!contains(elements.iterator().next())){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		   return new Iterator<E>() {
			  int iteratorIndex = 0;
			  boolean canCallRemove = false;
		      public boolean hasNext() {
		          if(list[iteratorIndex + 1] != null){
		        	  return true;
		          }
		          return false;
		      }
		      public void remove() {
		        if (canCallRemove) {
					BinarySearchSet.this.remove(list[iteratorIndex - 1]);
					iteratorIndex--;
					canCallRemove = false;
				}else{
		          throw new IllegalStateException();
				}
		      }
		      public E next() {
		    	  if(hasNext()){
		    		  canCallRemove = true;
		    		  return list[iteratorIndex++];
		    	  }
		    	  throw new NoSuchElementException();
		      }
		   };
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
		return size;
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
	
	private int binarySearch(E target) {
		int index = this.size / 2;
		for (int i = 0; i < this.size; i *= 2) {
			if (myCompare(list[index], target) > 0) {
				index += index / 2;
			} else {
				index -= index / 2;
			}
		}

		return index;

	}
	
	public int myCompare(E left, E right) {
		if (comparator != null) {
			return comparator.compare(left, right);
		}
		
		return ((Comparable<E>)left).compareTo(right);
	}
	
	/**********************************************************
	 * Doubles the capacity of the list when called.
	 *********************************************************/
	private void resize(){
		E[] newList = (E[])new Object[list.length * 2];
		for(int i = 0; i < size; i++){
			newList[i] = list[i];
		}
		list = newList;
	}

}
