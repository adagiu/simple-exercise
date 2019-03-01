package algorithms;

import java.util.ArrayList;
import java.util.List;

public class ListOfPairsWhichSumIs8 {

	public static List<Integer[]> getPairWhoseSumIsN(int n) {
		List<Integer[]> pairs = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			pairs.add(new Integer[] { i, 8 - i });
		}
		return pairs;
	}

	public static void main(String[] args) {
		getPairWhoseSumIsN(8).forEach(p -> System.out.println(p[0] + " " + p[1]));
	}

}
