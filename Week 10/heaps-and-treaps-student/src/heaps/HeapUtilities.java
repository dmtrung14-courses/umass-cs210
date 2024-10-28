/*
 * Copyright 2023 Marc Liberatore.
 */

package heaps;

import java.util.Arrays;
import java.util.Random;

public class HeapUtilities {
    /**
     * Returns true iff the subtree of a starting at index i is a max-heap.
     * @param a an array representing a mostly-complete tree, possibly a heap
     * @param i an index into that array representing a subtree rooted at i
     * @return true iff the subtree of a starting at index i is a max-heap
     */
    static boolean isHeap(double[] a, int i) {
        if (2*i +2 < a.length){
            return a[i] > a[2* i+1] && a[i] > a[2*i+2] && isHeap(a, 2*i+1) && isHeap(a, 2*i +2);            
        }else if (2*i + 1< a.length){
            return a[i] > a[2*i +1] && isHeap(a, 2*i +1);
        } else{
            return true;
        }
    }

    /**
     * Perform the heap siftdown operation on index i of the array a. 
     * 
     * This method assumes the subtrees of i are already valid max-heaps.
     * 
     * This operation is bounded by n (exclusive)! In a regular heap, 
     * n = a.length, but in some cases (for example, heapsort), you will 
     * want to stop the sifting at a particular position in the array. 
     * siftDown should stop before n, in other words, it should not 
     * sift down into any index great than (n-1).
     * 
     * @param a the array being sifted
     * @param i the index of the element to sift down
     * @param n the bound on the array (that is, where to stop sifting)
     */
    static void siftDown(double[] a, int i, int n) {
        if (i > n) return ;
        if (2*i +2 < n){
            if (a[i] < Math.max(a[2*i +1], a[2*i +2])){
                if (a[2*i +1] > a[2*i +2]) {
                    double temp = a[i];
                a[i] = a[2*i + 1];
                a[2*i + 1] = temp;
                siftDown(a, 2*i+1, n);
            } else{
                double temp = a[i];
                a[i] = a[2*i + 2];
                a[2*i + 2] = temp;
                siftDown(a, 2*i + 2, n);
                }            
            } 
        }else if (2*i + 1< n){
            if (a[i] < a[2*i +1]){
                double temp = a[i];
                a[i] = a[2*i + 1];
                a[2*i + 1] = temp;
                siftDown(a, 2*i +1, n);
            }
        }
    }

    /**
     * Heapify the array a in-place in linear time as a max-heap.
     * @param a an array of values
     */
    static void heapify(double[] a) {
        int i = a.length;
        while (i >= 0){
            siftDown(a, i, a.length);
            i -=1;
        }
    }

    /**
     * Heapsort the array a in-place, resulting in the elements of
     * a being in ascending order.
     * @param a
     */
    static void heapSort(double[] a) {
        heapify(a);
        int n = a.length;
        double temp = a[0];
        a[0] = a[n -1];
        a[n -1] = temp;
        for (int i = 1; i < n -1; i ++){
            siftDown(a, 0, n-i);
            temp = a[0];
            a[0] = a[n -i - 1];
            a[n -i -1] = temp;
        }
    }
    
    public static void main(String[] args) {
        Random r = new Random(0);
        int length = 15;
        double[] l = new double[length];
        for (int i = 0; i < length; i++) {
            l[i] = r.nextInt(20);
        }
        System.out.println(Arrays.toString(l));

        heapify(l);

        System.out.println(Arrays.toString(l));

        heapSort(l);

        System.out.println(Arrays.toString(l));
    }
}
