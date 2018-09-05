package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import utils.test.Test;

// https://raw.githubusercontent.com/openfootball/football.json/master/2014-15/en.1.json

public class FootballStat {

	public static void main(String[] args) {
		Test.equals(getScore("arsenal", getRounds()), 71);
		Test.equals(getScore("sunderland", getRounds()), 31);
		Test.equals(getScore("Burnley", getRounds()), 28);
		Test.equals(getScore("Everton", getRounds()), 48);
		Test.equals(getScore("chelsea", getRounds()), 73);
	}

	private static int getScore(String teamKey, JsonArray rounds) {
		int goals = 0;
		for (JsonElement matchDay : rounds) {
			JsonArray matches = matchDay.getAsJsonObject().get("matches").getAsJsonArray();
			for (JsonElement match : matches) {
				if (teamKey.equalsIgnoreCase(getTeamName(match, "team1"))) {
					goals += match.getAsJsonObject().get("score1").getAsInt();
				} else if (teamKey.equalsIgnoreCase(getTeamName(match, "team2"))) {
					goals += match.getAsJsonObject().get("score2").getAsInt();
				}
			}
		}
		return goals;
	}

	private static String getTeamName(JsonElement el, String team) {
		return el.getAsJsonObject().get(team).getAsJsonObject().get("name").getAsString();
	}

	private static JsonArray getRounds() {
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL("https://raw.githubusercontent.com/openfootball/football.json/master/2014-15/en.1.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String line;
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			}
			return new JsonParser().parse(result.toString()).getAsJsonObject().getAsJsonArray("rounds");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
