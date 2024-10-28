/*
 * Copyright 2021 Marc Liberatore.
 */

package list.exercises;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ExtendedArrayList<E> extends ArrayList<E> {
	
	/**
	 * Counts the number of elements in this list that are equal to e.
	 * 
	 * Uses .equals to check for equality. 
	 * 
	 * @param e the element to count
	 * @return the number of elements equal to e
	 */
	public int count(E e) {
		int res = 0;
		for (int i = 0; i < this.size(); i++){
			if (this.get(i).equals(e)){
				res += 1;
			}
		}
		return res;
	}
	
	/**
	 * Rotates the list right n places.
	 * 
	 * Rotating a list right means moving the last item to the front of the list:
	 * 
	 * 1, 2, 3, 4, 5 when rotated right once becomes 5, 1, 2, 3, 4
	 * 
	 * A list is rotated right two places as follows:
	 * 
	 * 4, 5, 1, 2, 3
	 * 
	 * and so on.
	 * 
	 * @param n the distance to rotate the list right
	 */
	public void rotateRight(int n) {
		int m = this.size();
		if(m>0){
			for (int i = 0; i < n; i++){
				E temp = this.get(m-1);
				this.remove(m-1);
				this.add(0, temp);
			} 
		}
		
	}	
	
	/**
	 * Intersperses e between each existing element of the list.
	 * 
	 * For example, given the list: "hey", "ho", "hi", if we intersperse "yo" we get:
	 * "hey", "yo", "ho", "yo", "hi"
	 * 
	 * @param e the element to intersperse
	 */
	public void intersperse(E e) {
		int m = this.size();
		if (m>= 2){
			for (int i = 0; i <= m-2; i++){
				this.add(2*i+1, e);
			}
		}
	}
	
	/**
	 * Returns a copy of this list in reverse order.
	 * 
	 * This list is unmodified by this operation.
	 * 
	 * @return a reversed copy of the list
	 */
	public List<E> reversed() {
		List<E> res = new ArrayList<E>();
		for(int i= 0; i < this.size(); i++){
			res.add(0, this.get(i));
		}
		return res;
	}
}
