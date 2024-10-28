/*
 * Copyright 2021 Marc Liberatore.
 */

package sequencer;

import java.util.ArrayList;
import java.util.List;

public class Assembler {
	private List<Fragment> list;

	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list will not be
	 * modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */
	public Assembler(List<Fragment> fragments) {
		List<Fragment> copy = new ArrayList<Fragment>(fragments);
		this.list = copy;
	}

	/**
	 * Returns the current list of fragments this assembler contains.
	 * 
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() {
		return this.list;
	}


	public int size(){
		return this.list.size();
	}

	public Fragment at(int a){
		return this.list.get(a);
	}
	/**
	 * Attempts to perform a single assembly, returning true iff an assembly was
	 * performed.
	 * 
	 * This method chooses the best assembly possible, that is, it merges the two
	 * fragments with the largest overlap, breaking ties between merged fragments by
	 * choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted
	 * into the list of fragments in this assembler, and the two original fragments
	 * are removed from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() {
		int m = this.size();
		if (m <2){
			return false;
		}
		int overlap = 0;
		int max1 = -1;
		int max2 = -1;
		int mergesize = 10^9;
		Fragment merge = new Fragment("");
		for(int i = 0; i < m; i++){
			for(int j = 0; j < m; j++){
				if (i == j){
					continue;
				}
				else{
					if (this.at(i).calculateOverlap(this.at(j)) > overlap) {
						max1 = i;
						max2 = j;
						overlap = this.at(i).calculateOverlap(this.at(j));
						mergesize = this.at(i).mergedWith(this.at(j)).getLength();
						merge = this.at(i).mergedWith(this.at(j));
					} else if (this.at(i).calculateOverlap(this.at(j)) == overlap) {
						if (this.at(i).mergedWith(this.at(j)).getLength() < mergesize) {
							max1 = i;
							max2 = j;
							overlap = this.at(i).calculateOverlap(this.at(j));
							mergesize = this.at(i).mergedWith(this.at(j)).getLength();
							merge = this.at(i).mergedWith(this.at(j));
						}
					}
				}
				
			}
		}
		if (max1 != -1 && max2 !=-1){
			this.list.remove(max1);
			this.list.add(max1, merge);
			this.list.remove(max2);
			
		}
		
		return overlap!=0; 
	}

	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() {
		while(this.assembleOnce()){
			this.assembleOnce();
		}
	}
}
