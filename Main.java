import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int t1 = 4;
		int t2 = 2;
		int t3 = 1;
		int t4 = 7;
		
		ArrayList<ArrayList<Integer>> test1P_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test1S_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test2P_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test2S_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test3P_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test3S_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test4P_list = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> test4S_list = new ArrayList<ArrayList<Integer>>();

		for(int i=0; i<t1; i++) {
			test1P_list.add(new ArrayList<Integer>());
			test1S_list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<t2; i++) {
			test2P_list.add(new ArrayList<Integer>());
			test2S_list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<t3; i++) {
			test3P_list.add(new ArrayList<Integer>());
			test3S_list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<t4; i++) {
			test4P_list.add(new ArrayList<Integer>());
			test4S_list.add(new ArrayList<Integer>());
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
		
		test2P_list.get(0).add(1);
		test2P_list.get(0).add(2);
		
		test2P_list.get(1).add(2);
		test2P_list.get(1).add(1);
		
		test2S_list.get(0).add(2);
		test2S_list.get(0).add(1);
		
		test2S_list.get(1).add(1);
		test2S_list.get(1).add(2);
		


		Preferences test2 = new Preferences(2,2,test2P_list, test2S_list);
		
		test3P_list.get(0).add(1);
		test3S_list.get(0).add(1);
		
		Preferences test3 = new Preferences(1,1,test3P_list,test3S_list);
		
		test4P_list.get(0).addAll(Arrays.asList(3,4,2,1,6,7,5));
		test4P_list.get(1).addAll(Arrays.asList(6,4,2,3,5,1,7));
		test4P_list.get(2).addAll(Arrays.asList(6,3,5,7,2,4,1));
		test4P_list.get(3).addAll(Arrays.asList(1,6,3,2,4,7,5));
		test4P_list.get(4).addAll(Arrays.asList(1,6,5,3,4,7,2));
		test4P_list.get(5).addAll(Arrays.asList(1,7,3,4,5,6,2));
		test4P_list.get(6).addAll(Arrays.asList(5,6,2,4,3,7,1));
		
		test4S_list.get(0).addAll(Arrays.asList(4,5,3,7,2,6,1));
		test4S_list.get(1).addAll(Arrays.asList(5,6,4,7,3,2,1));
		test4S_list.get(2).addAll(Arrays.asList(1,6,5,4,3,7,2));
		test4S_list.get(3).addAll(Arrays.asList(3,5,6,7,2,4,1));
		test4S_list.get(4).addAll(Arrays.asList(1,7,6,4,3,5,2));
		test4S_list.get(5).addAll(Arrays.asList(6,3,7,5,2,4,1));
		test4S_list.get(6).addAll(Arrays.asList(1,7,4,2,6,5,3));
		

		Preferences test4 = new Preferences(7,7,test4P_list, test4S_list);
		
		
		ArrayList<Integer> temp = new ArrayList<>();
		ArrayList<Cost> cost = new ArrayList<>();
		
		temp = Assignment1.stableMatchBruteForce(test4);
		System.out.println(temp);

		temp = Assignment1.stableMatchGaleShapley(test4);
		System.out.println(temp);
		
			
		cost = Assignment1.stableMatchCosts(test4);
		cost = Assignment1.stableMatchCostsStudent(test4);
		cost = Assignment1.stableMatchCostsStudent(test4);
		
		
		
		
		temp = Assignment1.stableMatchBruteForce(test1);
		System.out.println(temp);

		temp = Assignment1.stableMatchGaleShapley(test1);	
		System.out.println(temp);

		cost = Assignment1.stableMatchCosts(test1);
		cost = Assignment1.stableMatchCostsStudent(test1);
		cost = Assignment1.stableMatchCostsStudent(test1);
		
		
		temp = Assignment1.stableMatchBruteForce(test2);
		System.out.println(temp);

		temp = Assignment1.stableMatchGaleShapley(test2);
		System.out.println(temp);

		cost = Assignment1.stableMatchCosts(test2);
		cost = Assignment1.stableMatchCostsStudent(test2);
		cost = Assignment1.stableMatchCostsStudent(test2);
		
		temp = Assignment1.stableMatchBruteForce(test3);
		System.out.println(temp);

		temp = Assignment1.stableMatchGaleShapley(test3);
		System.out.println(temp);

		cost = Assignment1.stableMatchCosts(test3);
		cost = Assignment1.stableMatchCostsStudent(test3);
		cost = Assignment1.stableMatchCostsStudent(test3);

	}
	
	

}
