package stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GuitarShopService {

	private static final String SOURCE_PATH = "/META-INF/data/guitar-shop.csv";

	private List<String[]> data;

	public GuitarShopService() {
		try {
			data = loadData();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getGuitarList() {
		return data.stream()
				.map(GuitarShopService::asString)
				.collect(Collectors.toList());
	}

	public double getAvgPrice() {
		return data.stream()
				.map(GuitarShopService::getPrice)
				.collect(Collectors.averagingDouble(d -> d));
	}

	public List<String> listExpensiveGuitar() {
		double max = 0;
		final List<List<String>> stack = new ArrayList<>(1);
		stack.add(new ArrayList<>());
		for (String[] s : data) {
			double price = getPrice(s);
			if (price >= max) {
				if (price > max)
					stack.get(0).removeAll(stack.get(0));
				stack.get(0).add(asString(s));
				max = price;
			}
		}
		return stack.get(0);
	}

	public List<String> listCheaperGuitar() {
		double min = Double.MAX_VALUE;
		final List<List<String>> stack = new ArrayList<>(1);
		stack.add(new ArrayList<>());
		for (String[] s : data) {
			double price = getPrice(s);
			if (price <= min) {
				if (price < min)
					stack.get(0).removeAll(stack.get(0));
				stack.get(0).add(asString(s));
				min = price;
			}
		}
		return stack.get(0);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private List<String> getExpensiveGuitarDeprecated() {
		double max = data.stream()
				.mapToDouble(GuitarShopService::getPrice)
				.max()
				.getAsDouble();
		return data.stream()
				.filter(g -> getPrice(g).equals(max))
				.map(GuitarShopService::asString)
				.collect(Collectors.toList());
	}

	public List<String> getDiscountedGuitarList() {
		return data.stream()
				.filter(g -> Double.valueOf(g[5]) > 0)
				.map(GuitarShopService::asString)
				.collect(Collectors.toList());
	}

	public List<String> listGuitarsByFilter(Predicate<String[]> p) {
		return data.stream()
				.filter(p::test)
				.map(GuitarShopService::asString)
				.collect(Collectors.toList());
	}

	public double getAvgPriceByType(String type) {
		if (data == null)
			return 0;

		Predicate<String[]> filter = row -> type == null || type.isEmpty() ? true : getType(row).equals(type);

		return data.stream()
				.filter(filter)
				.mapToDouble(item -> Double.valueOf(getPrice(item)))
				.average()
				.orElse(0);
	}

	// ==================== utility methods ====================

	public static Double getPrice(String[] g) {
		return Double.valueOf(g[4]);
	}

	public static String getType(String[] g) {
		return g[2].trim();
	}

	public static String getName(String[] g) {
		return g[0].trim();
	}

	public static String getBrand(String[] g) {
		return g[1].trim();
	}

	public static Predicate<String[]> getUpperThen(double n) {
		return (a) -> GuitarShopService.getPrice(a) > n;
	}

	public static Predicate<String[]> getLowerThen(double n) {
		return (a) -> GuitarShopService.getPrice(a) < n;
	}

	public static Predicate<String[]> getByType(String t) {
		return (a) -> GuitarShopService.getType(a).equals(t);
	}

	public static Predicate<String[]> getByName(String t) {
		return (a) -> GuitarShopService.getName(a).equals(t);
	}

	public static String asString(String[] g) {
		return Stream.of(g)
				.collect(Collectors.joining(";"));
	}

	public static List<String> joinResult(List<String> l1, List<String> l2) {
		l1.addAll(l2);
		return l1;
	}

	private List<String[]> loadData() throws Exception {
		return Files.lines(Paths.get(this.getClass().getResource(SOURCE_PATH).toURI()))
				.skip(1)
				.map(s -> s.split(";"))
				.collect(Collectors.toList());
	}

	// ======================== sample main ========================

	public static void main(String[] args) {
		final GuitarShopService service = new GuitarShopService();
		System.out.println("List of guitars: " + service.getGuitarList());
		System.out.println("Avarage price: " + service.getAvgPrice());
		System.out.println("Avarage price for acoustic: " + service.getAvgPriceByType("acoustic"));
		System.out.println("Avarage price for electric: " + service.getAvgPriceByType("electric"));
		System.out.println("Avarage price for classical: " + service.getAvgPriceByType("classic"));
		System.out.println("Avarage price for all shop: " + service.getAvgPriceByType(""));
		System.out.println("List of guitars with discount: " + service.getDiscountedGuitarList());
		System.out.println("List of most expensive guitars: " + service.listExpensiveGuitar());
		System.out.println("List of cheapest guitars: " + service.listCheaperGuitar());
	}

}
