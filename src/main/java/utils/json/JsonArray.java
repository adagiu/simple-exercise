package utils.json;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * giuseppe.adaldo
 */
public class JsonArray implements Iterable<Object> {

    private final List<Object> jsonArray;

    public JsonArray(List<Object> jsonArray) {
        this.jsonArray = Collections.unmodifiableList(jsonArray);
    }

    @SuppressWarnings("unchecked")
	public JsonObject getObject(int key) {
		return new JsonObject((Map<String, Object>) jsonArray.get(key));
    }

    @Override
    public Iterator<Object> iterator() {
        return jsonArray.iterator();
    }
}
