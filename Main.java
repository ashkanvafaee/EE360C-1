import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int n = 4;
		ArrayList<ArrayList<Integer>> test1P_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test1S_list = new ArrayList<ArrayList<Integer>>();

		for(int i=0; i<n; i++) {
			test1P_list.add(new ArrayList<Integer>());
			test1S_list.add(new ArrayList<Integer>());
		}
		
		test1P_list.get(0).add(4);
		test1P_list.get(0).add(3);
		test1P_list.get(0).add(1);
		test1P_list.get(0).add(2);
		
		test1P_list.get(1).add(2);
		test1P_list.get(1).add(1);
		test1P_list.get(1).add(3);
		test1P_list.get(1).add(4);
		
		test1P_list.get(2).add(1);
		test1P_list.get(2).add(3);
		test1P_list.get(2).add(4);
		test1P_list.get(2).add(2);
		
		test1P_list.get(3).add(4);
		test1P_list.get(3).add(3);
		test1P_list.get(3).add(1);
		test1P_list.get(3).add(2);
		
		test1S_list.get(0).add(3);
		test1S_list.get(0).add(2);
		test1S_list.get(0).add(4);
		test1S_list.get(0).add(1);
		
		test1S_list.get(1).add(2);
		test1S_list.get(1).add(3);
		test1S_list.get(1).add(1);
		test1S_list.get(1).add(4);
		
		test1S_list.get(2).add(3);
		test1S_list.get(2).add(1);
		test1S_list.get(2).add(2);
		test1S_list.get(2).add(4);
		
		test1S_list.get(3).add(3);
		test1S_list.get(3).add(2);
		test1S_list.get(3).add(4);
		test1S_list.get(3).add(1);
		
		
		
		Preferences test1 = new Preferences(4, 4, test1P_list, test1S_list);
		
		ArrayList<Integer> temp = new ArrayList<>();
		ArrayList<Cost> cost = new ArrayList<>();
		
		temp = Assignment1.stableMatchBruteForce(test1);
		
		
		temp = Assignment1.stableMatchGaleShapley(test1);
		//temp = Assignment1.stableMatchGaleShapley(test1);
		
		cost = Assignment1.stableMatchCosts(test1);
		cost = Assignment1.stableMatchCostsStudent(test1);
		cost = Assignment1.stableMatchCostsStudent(test1);

		

	}

}
