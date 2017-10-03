
/**
 * Name: Ashkan Vafaee
 * EID: av28837
 * Class to implement Stable Matching algorithms
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Assignment1 {

	// Part1: Implement a Brute Force Solution
	public static ArrayList<Integer> stableMatchBruteForce(Preferences preferences) {
		ArrayList<ArrayList<Integer>> professor_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> student_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> matchings = new ArrayList<ArrayList<Integer>>();
		int size = preferences.getNumberOfProfessors();
		int rows = 1;

		/* Zero-indexing Preferences */
		for (int i = 0; i < size; i++) {
			professor_list.add(new ArrayList<Integer>());
			student_list.add(new ArrayList<Integer>());
			
			for (int j = 0; j < size; j++) {
				professor_list.get(i).add(new Integer(preferences.getProfessors_preference().get(i).get(j) - 1));
				student_list.get(i).add(new Integer(preferences.getStudents_preference().get(i).get(j) - 1));
			}
		}

		/* Number of possible sets = size! */
		for (int i = size; i > 1; i--) {
			rows *= i;
		}

		for (int i = 0; i < rows; i++) {
			matchings.add(new ArrayList<Integer>(size));
		}

		ArrayList<Integer> temp = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			temp.add(0);
		}

		int row = 0;
		while (row < rows) {
			boolean found = true;
			for (int i = 0; i < size; i++) {
				if (!temp.contains(i)) {
					found = false;
				}
			}

			if (found) {
				matchings.set(row, new ArrayList<Integer>(temp));
				row += 1;
			}

			temp.set(0, temp.get(0) + 1);

			for (int i = 0; i < size; i++) {
				if (temp.get(i) == size) {
					temp.set(i, temp.get(0) % size);
					temp.set(i + 1, temp.get(i + 1) + 1);
				}

			}

		}

		for (row = 0; row < rows; row++) {
			boolean stable = true;
			for (int col = 0; col < size; col++) {
				/* Searching through all students that the professor ranks higher */
				int num_higher = professor_list.get(col).indexOf((matchings.get(row).get(col)));
				for (int i = 0; i < num_higher; i++) {
					int check_student = professor_list.get(col).get(i);
					int current_professor = matchings.get(row).indexOf(check_student);
					/*
					 * If the student ranks the new professor higher than the current professor ->
					 * Instability
					 */
					if (student_list.get(check_student).indexOf(col) < student_list.get(check_student).indexOf(current_professor)) {
						stable = false;
					}
				}
			}

			if (stable) {
				return matchings.get(row);
			}

		}

		return null;

	}

	// Part2: Implement Gale-Shapley Algorithm
	public static ArrayList<Integer> stableMatchGaleShapley(Preferences preferences) {		
		
		LinkedList<LinkedList<Integer>> professor_list = new LinkedList<LinkedList<Integer>>();
		LinkedList<LinkedList<Integer>> student_list = new LinkedList<LinkedList<Integer>>();
		LinkedList<LinkedList<Integer>> inverse_student = new LinkedList<LinkedList<Integer>>();
		int size = preferences.getNumberOfProfessors();

		ArrayList<Integer> matching = new ArrayList<Integer>();
		ArrayList<Integer> inverse_matching = new ArrayList<Integer>();
		ArrayList<Integer> queue = new ArrayList<Integer>();

		/* Zero-indexing Preferences */
		for (int i = 0; i < size; i++) {
			professor_list.add(new LinkedList<Integer>());
			student_list.add(new LinkedList<Integer>());
			inverse_student.add(new LinkedList<Integer>());
			for (int j = 0; j < size; j++) {
				professor_list.get(i).add(new Integer(preferences.getProfessors_preference().get(i).get(j) - 1));
				student_list.get(i).add(new Integer(preferences.getStudents_preference().get(i).get(j) - 1));
				
				inverse_student.get(i).add(0);
			}
		}
		
		/* Initializing inverse student list for O(1) comparisons */
		for(int i=0; i < size; i++) {
			for(int j=0; j<size; j++) {
				inverse_student.get(i).set(student_list.get(i).get(j), j);
			}
		}
		
		

		/* Initialize queue with all professors */
		for (int i = 0; i < size; i++) {
			queue.add(i);
			matching.add(-1);
			inverse_matching.add(0);
		}

		while (queue.size() > 0) {

			int student = professor_list.get(queue.get(0)).get(0);

			// Student not yet chosen
			if (!matching.contains(student)) {

				matching.set(queue.get(0), student); // Get top choice in professor's list
				inverse_matching.set(student, queue.get(0));
				professor_list.get(queue.get(0)).remove(0);
				queue.remove(0);

			}

			// Student already chosen
			else {
				// Student prefers current professor
				if(inverse_student.get(student).get(queue.get(0)) > inverse_student.get(student).get(inverse_matching.get(student))) {
					professor_list.get(queue.get(0)).remove(0);
				}
				
				// Student prefers new professor
				else {
					queue.add(inverse_matching.get(student));
					matching.set(inverse_matching.get(student), -1);
					matching.set(queue.get(0), student);
					inverse_matching.set(student, queue.get(0));
					professor_list.get(queue.get(0)).remove(0);
					queue.remove(0);

				}

			}

		}

		return matching;

	}

	// Part3: Matching with Costs
	public static ArrayList<Cost> stableMatchCosts(Preferences preferences) {
		ArrayList<Integer> matching = new ArrayList<Integer>(stableMatchGaleShapley(preferences));
		ArrayList<ArrayList<Integer>> professor_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> student_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Cost> costs = new ArrayList<Cost>();
		int size = preferences.getNumberOfProfessors();

		/* Zero-indexing Preferences */
		for (int i = 0; i < size; i++) {
			professor_list.add(new ArrayList<Integer>());
			student_list.add(new ArrayList<Integer>());
			for (int j = 0; j < size; j++) {
				professor_list.get(i).add(new Integer(preferences.getProfessors_preference().get(i).get(j) - 1));
				student_list.get(i).add(new Integer(preferences.getStudents_preference().get(i).get(j) - 1));
			}
		}

		for (int i = 0; i < size; i++) {
			Cost temp = new Cost(i, matching.get(i), professor_list.get(i).indexOf(matching.get(i)),student_list.get(matching.get(i)).indexOf(i));
			costs.add(temp);
		}

		return costs;

	}

	public static ArrayList<Cost> stableMatchCostsStudent(Preferences preferences) {
		
		ArrayList<ArrayList<Integer>> professor_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> student_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Cost> costs = new ArrayList<Cost>();
		int size = preferences.getNumberOfProfessors();

		ArrayList<Integer> matching = new ArrayList<Integer>(stableMatchGaleShapley(preferences));

		/* Zero-indexing Preferences */
		for (int i = 0; i < size; i++) {
			professor_list.add(new ArrayList<Integer>());
			student_list.add(new ArrayList<Integer>());
			for (int j = 0; j < size; j++) {
				professor_list.get(i).add(new Integer(preferences.getProfessors_preference().get(i).get(j)) - 1);
				student_list.get(i).add(new Integer(preferences.getStudents_preference().get(i).get(j)) - 1);
			}
		}
		
		
		
		for (int i = 0; i < size; i++) {
			Cost temp = new Cost(i, matching.get(i), student_list.get(i).indexOf(matching.get(i)),professor_list.get(matching.get(i)).indexOf(i));
			costs.add(temp);
		}

		return costs;

	}

}
