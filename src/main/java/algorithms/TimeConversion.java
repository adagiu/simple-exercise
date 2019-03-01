package algorithms;

import utils.test.Test;

public class TimeConversion {

	private static String convertTime(String s) {
		int hours = Integer.valueOf(s.split(":")[0]);
		if (s.contains("AM") && hours == 12)
			return timeToString(0, s.split(":")[1], s.split(":")[2]);

		if (s.contains("PM") && hours != 12)
			return timeToString(hours + 12, s.split(":")[1], s.split(":")[2]);

		return timeToString(hours, s.split(":")[1], s.split(":")[2]);
	}

	private static String timeToString(Object... t) {
		return String.format("%02d:%s:%s", t).replaceAll("[A-Z]", "");
	}

	public static void main(String[] args) {
		Test.equals(convertTime("07:05:45PM"), "19:05:45");
		Test.equals(convertTime("12:05:45PM"), "12:05:45");
		Test.equals(convertTime("12:05:45AM"), "00:05:45");
		Test.equals(convertTime("06:40:03AM"), "06:40:03");
		Test.equals(convertTime("04:59:59AM"), "04:59:59");
	}

}
