/*
 * Copyright 2023 Marc Liberatore.
 */

package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 * @author liberato
 *
 * @param <T> the type for each vertex in the search graph
 */
public class Searcher<T> {
	private final SearchProblem<T> searchProblem;
	
	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public List<T> findSolution() {		
		// TODO
		T initialState = searchProblem.getInitialState();
		Set<T> visited = new HashSet<>();
		Queue<T> frontier = new LinkedList<>();
		visited.add(initialState);
		frontier.add(initialState);
		Map<T,T> predecessor = new HashMap<>();
		predecessor.put(initialState, null);
		List<T> solution = new LinkedList<>();
		while (!frontier.isEmpty()){
			T current = frontier.remove();
			visited.add(current);
			if (searchProblem.isGoal(current)){
				
				while (current != null){
					solution.add(0, current);
					current = predecessor.get(current);
				}
				break;
			}

			for (T next: searchProblem.getSuccessors(current)){
				if (!visited.contains(next)){
					frontier.add(next);
					predecessor.put(next, current);
				}
			}
		}

		
		return solution;
	}

	/**
	 * Checks that a solution is valid.
	 * 
	 * THIS METHOD DOES NOT PERFORM SEARCH! It only checks if a provided solution
	 * is valid!
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValidSolution(List<T> solution) {
		if (solution == null){
			throw new NullPointerException();
		}
		if (solution.size() == 0){
			return false;
		}
		if (!solution.get(0).equals(searchProblem.getInitialState())){
			return false;
		}
		for (int i = 0; i < solution.size()-1; i ++){
			if (!searchProblem.getSuccessors(solution.get(i)).contains(solution.get(i+1))){
				return false;
			}
		}
		if (!searchProblem.isGoal(solution.get(solution.size()-1))){
			return false;
		}
		return true;
	}
}
