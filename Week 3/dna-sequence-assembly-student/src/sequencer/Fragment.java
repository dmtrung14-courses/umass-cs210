/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.Arrays;
import java.util.List;

public class Fragment {
	private String string;
	/**
	 * Creates a new Fragment based upon a String representing a sequence of
	 * nucleotides, containing only the uppercase characters G, C, A and T.
	 * 
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of
	 *                                  nucleotides
	 */
	public Fragment(String nucleotides) throws IllegalArgumentException {
		this.string = nucleotides;
		int n = nucleotides.length();
		Character[] legal = {'G','A','C','T'};
		List<Character> legalList = Arrays.asList(legal);
		for (int i = 0; i < n; i++){
			if (!legalList.contains(this.string.charAt(i))){
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return this.string.length();
	}

	/**
	 * Returns a String representation of this fragment, exactly as was passed to
	 * the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return this.string.toString();
	}

	public String getString(){
		return this.string;
	}

	public int getLength(){
		return this.string.length();
	}

	/**
	 * Return true if and only if this fragment contains the same sequence of
	 * nucleotides as another sequence.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Fragment)) {
			return false;
		}

		Fragment f = (Fragment) o;
		return this.string.equals(f.string);

		// Don't unconditionally return false; check that
		// the relevant instances variables in this and f 
		// are semantically equal
	}

	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment
	 * and the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an overlap of 2,
	 * not 1.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {
		int m = this.length();
		int n = f.length();
		int k = Math.min(m, n);
		int i = 1;
		int res= 0;
		while (i <= k){
			if (this.string.substring(m-i).equals(f.string.substring(0,i))){
				res = i;
			}
			i +=1;
		}
		return res;
	}


	/**
	 * Returns a new fragment based upon merging this fragment with another fragment
	 * f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right;
	 * the fragments will be overlapped as much as possible during the merge.
	 * 
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment
	 */
	public Fragment mergedWith(Fragment f) {
		Fragment res = new Fragment("");
		int a = this.calculateOverlap(f);
		res.string = this.string + f.string.substring(a);
		return res;
	}
}
