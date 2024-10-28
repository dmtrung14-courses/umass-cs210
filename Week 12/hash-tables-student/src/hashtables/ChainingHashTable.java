package hashtables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of HashTable.
 * 
 * This implementation uses chaining to resolve collisions. Chaining means 
 * the underlying array stores references to growable structures (like LinkedLists
 * or ArrayLists) that we expect to remain small in size. When there is a 
 * collision, the element is added to the end of the growable structure. It
 * must search the entire growable structure whenever checking membership
 * or removing elements.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor exceeds 0.75, the next add() triggers a
 * resize by incrementing n (by one). For example, when n=3, then capacity=7.
 * When size=6, then load factor ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15.
 */
public class ChainingHashTable<E> implements HashTable<E> {
    
    int capacity;
    int size;
    ArrayList<E>[] array;
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    public ChainingHashTable() {
        this.capacity = 7;
        this.array = (ArrayList<E>[]) new ArrayList[capacity]; 
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public static int log2(int n){
        int res = 0;
        while (n > 1){
            res += 1;
            n /= 2;
        }
        return res;
    }
    public ChainingHashTable(int n) {
        int temp = log2(n);
        this.capacity = (int) Math.pow(2, temp+1)-1;
        this.array = (ArrayList<E>[]) new ArrayList[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double loadFactor() {
        double res = (double) size/ capacity;
        return res;
    }

    @Override
    public boolean add(E e) {
        int temp = Math.abs(e.hashCode());
        
        if (loadFactor() > 0.75) {
            increment();
        }
        int index = temp % capacity;
        if (array[index] == null){
            array[index] = new ArrayList<E>();            
        }
        for (int i = 0; i < array[index].size(); i ++){
            if (array[index].get(i).equals(e)){
                array[index].remove(i);
                array[index].add(e);
                return false;
            }
            
        }
        array[index].add(e);
        size ++;
        return true;
    }
    public void increment(){
        capacity = capacity * 2+ 1;
        ArrayList<E>[] temp = (ArrayList<E>[]) new ArrayList[capacity];
        for (int i = 0; i < array.length; i ++){
            if (array[i] != null){
                for (int j = 0; j < array[i].size(); j++){
                    E el = array[i].get(j);
                    int tempHash = array[i].get(j).hashCode();
                    int index = tempHash % capacity;
                    if (temp[index] == null){
                        temp[index] = new ArrayList<E>();
                        temp[index].add(el);
                    }
                    else{
                        temp[index].add(el);
                    }
                    
                }
            }                
            
        }
        array = temp;

    }

    @Override
    public boolean remove(E e) {
        int temp = e.hashCode();
        int index = temp % capacity;
        if (array[index] == null){
            return false;
        }
        else{
            for (int i = 0; i < array[index].size(); i++){
                if (array[index].get(i).equals(e)){
                    array[index].remove(i);
                    size--;
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean contains(E e) {
        int temp = e.hashCode();
        int index = temp % capacity;
        if (array[index] == null){
            return false;
        }
        else{
            for (int i = 0; i < array[index].size(); i ++){
                if (array[index].get(i).equals(e)){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public E get(E e) {
        int temp = e.hashCode();
        int index = temp % capacity;
        if (array[index] == null){
            return null;
        }
        else{
            for (int i = 0; i < array[index].size(); i ++){
                if (array[index].get(i).equals(e)){
                    return array[index].get(i);
                }
            }
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new CHTIterator<E>();
    }

    class CHTIterator<E> implements Iterator<E> {
        int current;
        int indexAt; 
        
        public CHTIterator(){
            current = 0;
            indexAt =0 ;
        }
        @Override
        public boolean hasNext() {
            while (current < array.length && array[current] == null){
                current ++;
            }
            if (current == array.length) return false;
            return true;
        }

        @Override
        public E next() {
            
            // E result = null;          
            E result = (E) array[current].get(indexAt);
            if (indexAt ==  array[current].size()-1){                
                indexAt = 0;
                current ++;
            }
            else{
                indexAt ++;
            }
            return result;
        }
    }
}
