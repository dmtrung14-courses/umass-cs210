/*
 * Copyright 2023 Marc Liberatore.
 */

package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search.SearchProblem;
import search.Searcher;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */

	List<Integer> states;
	List<Integer> initialState;
	public EightPuzzle(List<Integer> startingValues) {
		if (startingValues.size() != 9){
			throw new IllegalArgumentException();
		}
		for (int i =0; i < 9; i++){
			if (!startingValues.contains(i)){
				throw new IllegalArgumentException();
			}
		}
		states = startingValues;
		initialState = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		return initialState;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		//Check to see if it is possible to swap left, swap right, swap up, swap down

		//get index of current 0
		int vacant = currentState.indexOf(0);
		// initialize the result
		List<List<Integer>> result = new ArrayList<>();	

		// check swap left
		if (vacant%3 != 0){
			List<Integer> newState = new ArrayList<>(currentState);
			int temp = newState.get(vacant-1);
			newState.set(vacant-1, 0);
			newState.set(vacant, temp);
			result.add(newState);
		}
		
		// check swap right
		if (vacant%3 != 2){
			List<Integer> newState = new ArrayList<>(currentState);
			int temp = newState.get(vacant+1);
			newState.set(vacant+1, 0);
			newState.set(vacant, temp);
			result.add(newState);
		}

		//check swap up
		if (vacant >= 3){
			List<Integer> newState = new ArrayList<>(currentState);
			int temp = newState.get(vacant-3);
			newState.set(vacant-3, 0);
			newState.set(vacant, temp);
			result.add(newState);
		}

		//check swap down
		if (vacant <= 5){
			List<Integer> newState = new ArrayList<>(currentState);
			int temp = newState.get(vacant+3);
			newState.set(vacant+3, 0);
			newState.set(vacant, temp);
			result.add(newState);
		}
		return result;
	}


	@Override
	public boolean isGoal(List<Integer> state) {
		List<Integer> goal = Arrays.asList(1,2,3,4,5,6,7,8,0);
		return state.equals(goal);
	}

	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Searcher<List<Integer>>(eightPuzzle).findSolution();
		for (List<Integer> state : solution) {
			System.out.println(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
