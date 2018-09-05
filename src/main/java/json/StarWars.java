package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class StarWars {

	public static void main(String[] args) {
		// System.out.println(run("Luke Skywalker"));
		// System.out.println(run("R2-D2"));
		// System.out.println(run("C-3PO"));
		// System.out.println(run("Tarfful"));
		// System.out.println(run("Chewbacca"));
		System.out.println(run("none"));
	}

	public static int run(String character) {
		int films = 0;
		films = getTotFilmsPaginated("http://challenges.hackajob.co/swapi/api/people/?format=json", character);

		return films;
	}

	private static int getTotFilmsPaginated(String link, String character) {
		try {
			StringBuilder result = new StringBuilder();
			if (link == null)
				return 0;
			URL url = new URL(link);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("GET");
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String line;
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			}
			JsonArray people = new JsonParser().parse(result.toString()).getAsJsonObject().getAsJsonArray("results");
			for (JsonElement e : people) {
				if (character.equalsIgnoreCase(e.getAsJsonObject().get("name").getAsString())) {
					return e.getAsJsonObject().get("films").getAsJsonArray().size();
				}
			}
			Object next = new JsonParser().parse(result.toString()).getAsJsonObject().get("next");
			if (next == null) {
				return 0;
			} else {
				return getTotFilmsPaginated(next.toString(), character);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
