/*
 * Copyright 2021 Marc Liberatore.
 */

package sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtilities {
	/**
	 * Returns a new set representing the union of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the union of s and t
	 */
	public static <E> Set<E> union(Set<E> s, Set<E> t) {
		Set<E> result = new HashSet<E>();
		for (E i: s){
			result.add(i);
		}
		for (E i: t){
			result.add(i);
		}
		return result;
	}

	/**
	 * Returns a new set representing the intersection of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the intersection of s and t
	 */
	public static <E> Set<E> intersection(Set<E> s, Set<E> t) {
		Set<E> result = new HashSet<E>();
		for (E i: s){
			if (t.contains(i)){
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * Returns a new set representing the set difference s and t,
	 * that is, s \ t (or s - t).
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the difference of s and t
	 */
	public static <E> Set<E> setDifference(Set<E> s, Set<E> t) {
		Set<E> result = new HashSet<E>();
		for (E i: s){
			if (!t.contains(i)){
				result.add(i);
			}
		}
		return result;
	}
	
	/**
	 * Returns the Jaccard index of the two sets s and t.
	 * 
	 * It is defined as 1 if both sets are empty.
     *
	 * Otherwise, it is defined as the size of the intersection of the sets, 
	 * divided by the size of the union of the sets.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return the Jaccard index of s and t
	 */
	public static <E> double jaccardIndex(Set<E> s, Set<E> t) {
		if (s.size() == 0 && t.size() ==0){
			return 1;
		}
		double result = (double) intersection(s, t).size()/union(s, t).size();
		return result;
	}
}
