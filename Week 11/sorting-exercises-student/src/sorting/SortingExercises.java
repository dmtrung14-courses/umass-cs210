/*
 * Copyright 2023 Marc Liberatore.
 */

package sorting;

import java.util.Random;

import javax.management.monitor.MonitorSettingException;

public class SortingExercises {

    /**
     * Swap the values at a[i] and a[j].
     */
    static void swap(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * Perform an in-place insertion sort on the array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j - 1] > a[j]) {
                swap(a, j, j - 1);
                j--;
            }
        }
    }

    /**
     * Perform an in-place insertion sort of the range start (inclusive) 
     * through end (exclusive) on array a.
     * The array will be in ascending order (least-to-greatest) after sorting.
     */
    static void insertionSort(double[] a, int start, int end) {
        for (int i = start; i < end; i ++){
            int j = i;
            while (j > start && a[j-1] > a[j]){
                swap(a,j, j-1);
                j--;
            }
        }
    }

    /**
     * Perform a destructive mergesort on the array a.
    
     * The array will be in ascending order (least-to-greatest) after sorting; the original
     * values will be overwritten.
     * Additional array space will be allocated by this method.
     * 
     * For efficiency, this method will *insertion sort* any array of length less than 10.
     */
    public static void mergeSort(double[] a) {
        if (a.length <= 10){
            insertionSort(a);
            return;
        }
        double[] b = getSubArray(a, 0, a.length/2);
        double[] c = getSubArray(a, a.length/2, a.length);
        mergeSort(b);
        mergeSort(c);
        merge(a,b,c);
    }

    public static double[] getSubArray(double[] a, int start, int end){
        double[] b = new double[end-start];
        for (int i = 0; i < end-start; i++){
            b[i] = a[start + i];
        }
        return b;
    }
    /**
     * Merge the sorted arrays l and r into the array a (overwriting its previous contents).
     */
    static void merge(double[] a, double[] l, double[] r) {
        int i = 0;
        int j = 0;
        while (i < l.length && j < r.length){
            if (l[i] < r[j]){
                a[i+j] = l[i];
                i++;
            }
            else{
                a[i+j] = r[j];
                j++;
            }
        }
        if (i == l.length){
            while(j < r.length){
                a[i+j] = r[j];
                j ++;
            }
        }
        else if (j == r.length){
            while (i < l.length){
                a[i+j] = l[i];
                i ++;

            }
        }
    }

    /**
     * Perform an in-place quicksort on the array a.
     */
    static void quickSort(double[] a) {
        quickSort(a, 0, a.length);
    }

    /**
     * Perform an in-place quicksort on the range i (inclusive) to j 
     * (exclusive) of the array a.
     * 
     * For efficiency, this method will *insertion sort* any range of 
     * length less than 10.
     */
    static void quickSort(double[] a, int i, int j) {
        if (j - i <= 10){
            insertionSort(a, i, j);
            return;
        }

        // partitions the array, returns the index of the pivot
        int pivotIndex = partition(a, i, j); 

        quickSort(a, 0, pivotIndex);
        quickSort(a, pivotIndex + 1, j);
    }

    /**
     * Perform an in-place partition on the  range i (inclusive) to j 
     * (exclusive) of the array a, returning the index of the pivot
     * after partitioning.
     * 
     * To cut down on worst case choices, this method will chose the 
     * pivot value at random from among the values in the range.
     * 
     * @return the index of the pivot after the partition
     */
    static int partition(double[] a, int i, int j) {
        Random rand = new Random();
        int m = rand.nextInt(i, j);
        int first = m;
        double pivot = a[m];
        

        j = j - 1;
        do {
            while ((i < j) && (pivot >= a[i])) {
                i++;
            }
            while (pivot < a[j]) {
                j--;
            }
            if (i < j) {
                swap(a, i, j);
            }
        } while (i < j);
        swap(a, first, j);
        return j;
    }
}