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
        if(size == 0){
        throw new NoSuchElementException();
        }
        
        return list[0];
    }

    @Override
    public E last() throws NoSuchElementException {
        if(size ==0){
            throw new NoSuchElementException();
        }
        return list[size - 1];
    }

    @Override
    public boolean add(E element) {
            if(size == list.length - 1){
            resize();
        }
            

        
        int index = binarySearch(element);
        
    
        
        if (size > 0 && list[index] != null) {
            if (myCompare(list[index], element) == 0) {
                return false;
            }
        }
        
        
        if (size > 0) {
            E temp1 = list[index];
            E temp2;
            list[index] = element;
            size++;
            for (int i = index + 1; i < size; i += 2) {

                temp2 = list[i];
                list[i] = temp1;
                temp1 = list[i + 1];
                list[i + 1] = temp2;

            } 
            }else{
                list[0] = element;
                size++;
            }
        
        return true;

    
    }


    @Override
    public boolean addAll(Collection<? extends E> elements) {
        if (elements.size() > 0) {
            for(E el: elements){
                add(el);
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        list = (E[])new Object[10];
        size = 0;
    }

    @Override
    public boolean contains(Object element) {
        E other = (E) element;
        int index = binarySearch(other);
        if (index < size) {
            if (myCompare(list[index], other) == 0) {
                return true;
            } 
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> elements) {
        Collection<E> collection = (Collection<E>) elements;
        if(elements.size() > 0) {    
            for(E el: collection){
                if(!contains(el)){
                    return false;
                }
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
                  if(list[iteratorIndex] != null){
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
        E other = (E) element;
        int index = binarySearch(other);
        
        
        if (size > 0) {
            if (index < size) {
                if (myCompare(list[index], other) == 0) {
                    for (int i = index; i < size; i++) {
                        list[i] = list[i + 1];
                    }
                    list[size - 1] = null;
                    size--;
                    return true;
                } else {
                    return false;
                } 
            } else {
                return false;
            }
        } else {
            return false;
        }
        
    }

    @Override
    public boolean removeAll(Collection<?> elements) {
        int removeCount = 0;
        Collection<E> collection = (Collection<E>) elements;
        if (elements.size() > 0) {
            for(E el: collection){
                if(remove(el)){
                    removeCount++;
                }
            }
            if (removeCount > 0) {
                return true;
            }
        }
        
        
        return false;
    }

    
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
            return list;
    }

    
    
    private int binarySearch(E target) {
        int indexLow = 0;
        int indexHigh = size -1;
        
        while(indexLow <= indexHigh) {
            int mediumIndex = (indexLow + indexHigh) / 2;
            if(myCompare(list[mediumIndex], target) == 0) {
                return mediumIndex;
            }
            else if(myCompare(list[mediumIndex], target) < 0) {
                indexLow = mediumIndex +1;
            }
            else{
                indexHigh = mediumIndex -1;
            }
            
        }
        
        if (indexHigh < 0) {
            return 0;
        } else if(indexLow == size){
            return size;
        }
        
        return indexLow;

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