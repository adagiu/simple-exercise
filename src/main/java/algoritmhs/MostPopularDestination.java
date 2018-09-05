package algoritmhs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MostPopularDestination {

	static void OutputMostPopularDestination(int count, Scanner in) {
		Map<String, Integer> des = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String city = in.next();
            if (des.containsKey(city)) {
                int tot = des.get(city);
                des.put(city, tot + 1);
            }
            else {
                des.put(city, 1);
            }
        }

        int max = 0;
        String destination = "";
        for (String s : des.keySet()) {
            if (des.get(s) > max) {
                max = des.get(s);
                destination = s;
            }
        }
        System.out.println(destination);
	}

	public static void main(String args[]) throws Exception {
		Scanner in = new Scanner(System.in);
		int _count;
		_count = Integer.parseInt(in.nextLine());

		OutputMostPopularDestination(_count, in);
	}

}
