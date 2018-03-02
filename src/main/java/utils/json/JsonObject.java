package utils.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * giuseppe.adaldo
 */
@SuppressWarnings("unchecked")
public class JsonObject {

    private final Map<String, Object> jsonMap;

    public JsonObject(Map<String, Object> jsonMap) {
        this.jsonMap = Collections.unmodifiableMap(jsonMap);
    }

	public JsonObject getObject(String key) {
        return new JsonObject((Map<String, Object>) jsonMap.get(key));
    }

    public JsonArray getJsonArray(String key) {
        return new JsonArray((List<Object>) jsonMap.get(key));
    }

    public <T> T getObjectAs(String key) {
        return (T) jsonMap.get(key);
    }

    public <T extends Comparable<T>> T getValue(String key, Class<T> clazz) {
        return getTypedValue(jsonMap.get(key), clazz);
    }

    public String getString(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? String.valueOf(v) : null;
    }

    public Integer getInt(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? Integer.valueOf(String.valueOf(v)) : null;
    }

    public Double getDouble(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? Double.valueOf(String.valueOf(v)) : null;
    }

    public Long getLong(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? Long.valueOf(String.valueOf(v)) : null;
    }

    public Float getFloat(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? Float.valueOf(String.valueOf(v)) : null;
    }

    public Boolean getBoolean(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? Boolean.valueOf(String.valueOf(v)) : null;
    }

    public BigInteger getBigInt(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? BigInteger.valueOf(Long.valueOf(String.valueOf(v))) : null;
    }

    public BigDecimal getBigDecimal(String key) {
        final Object v = jsonMap.get(key);
        return v != null ? new BigDecimal(String.valueOf(v)) : null;
    }

    public Map<String, Object> getJsonObject() {
        return jsonMap;
    }

    private <T extends Comparable<T>> T getTypedValue(Object value, Class<T> clazz) {
        if (value == null)
            return null;
        final String className = clazz.getName();
        final String stringValue = String.valueOf(value);
        switch (className) {
            case "String":
                return (T) stringValue;
            case "java.lang.Integer":
                return (T) Integer.valueOf(stringValue);
            case "java.lang.Double":
                return (T) Double.valueOf(stringValue);
            case "java.lang.Long":
                return (T) Long.valueOf(stringValue);
            case "java.lang.Float":
                return (T) Float.valueOf(stringValue);
            case "java.lang.Boolean":
                return (T) Boolean.valueOf(stringValue);
            case "java.math.BigInteger":
                return (T) BigInteger.valueOf(Long.valueOf(stringValue));
            case "java.math.BigDecimal":
                return (T) new BigDecimal(stringValue);
            default:
                return (T) stringValue;
        }
    }

    /**
     * Sample usage.
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        map2.put("int", 555578);
        map2.put("aString", "some String !");
        map2.put("double-string", "10.02d");
        map2.put("double", 555578.44d);
        map2.put("long", Long.MAX_VALUE);
        map2.put("float", Float.MAX_VALUE);
        map2.put("bigInt", 555578655);
        map2.put("decimal", 0.4476895);
        map2.put("bool", true);
        map2.put("bool-string", "true");
        map.put("map", map2);

        List<Object> list = new ArrayList<>();
        list.add(map2);
        map.put("list", list);

        JsonObject jObj = new JsonObject(map);

        System.out.println(jObj.getObject("map"));
        String aString = jObj.getObject("map").getValue("aString", String.class);
        int i1 = jObj.getObject("map").getInt("int");
        double d1 = jObj.getObject("map").<Double>getValue("double-string", Double.class);
        double d2 = jObj.getObject("map").<Double>getValue("double", Double.class);

        final JsonObject mapJsonObj = jObj.getObject("map"); // another usage, keep the object in memory
        double d3 = mapJsonObj.getDouble("double");
        String s = mapJsonObj.getString("double");
        long l = mapJsonObj.getLong("long");
        float f = mapJsonObj.getFloat("float");
        BigInteger bigInt = mapJsonObj.getBigInt("bigInt");
        BigDecimal bigDec = mapJsonObj.getBigDecimal("decimal");
        Boolean bool = mapJsonObj.getBoolean("bool");
        Boolean boolAsString = mapJsonObj.getBoolean("bool-string");
        String boolString = mapJsonObj.getString("bool-string");

        System.out.println("a string: " + aString);
        System.out.println("an int: " + i1);
        System.out.println("a double from string: " + d1);
        System.out.println("a double as generic: " + d2);
        System.out.println("a double: " + d3);
        System.out.println("a string: " + s);
        System.out.println("a string: " + s);
        System.out.println("a long: " + l);
        System.out.println("a float: " + f);
        System.out.println("a big int: " + bigInt);
        System.out.println("a big dec: " + bigDec);
        System.out.println("a boolean: " + bool);
        System.out.println("a boolean from string value: " + boolAsString);
        System.out.println("a boolean as string: " + boolString);

        double d4 = jObj.getJsonArray("list").getObject(0).getValue("double", Double.class);

        System.out.println("a double from internal list: " + d4);
    }
}
