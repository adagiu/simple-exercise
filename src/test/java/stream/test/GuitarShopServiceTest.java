package stream.test;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import stream.GuitarShopService;

public class GuitarShopServiceTest {

	private GuitarShopService obj = new GuitarShopService();

	@Before
	public void setUp() {
		System.out.println("test start ---");
	}

	@After
	public void destroy() {
		System.out.println("test completed ---");
	}

	@Test
	public void getGuitarList() {
		List<String> guitars = obj.getGuitarList();
		Assert.assertNotNull(guitars);
		Assert.assertEquals(guitars.size(), 12);
		guitars.forEach(g -> System.out.println(g));
	}

	@Test
	public void getAvgPrice() {
		Assert.assertEquals(2165.5358333333334, obj.getAvgPrice(), 0.0);
	}

	@Test
	public void listExpensiveGuitar() {
		List<String> expensive = obj.listExpensiveGuitar();
		Assert.assertNotNull(expensive);
		Assert.assertNotNull(expensive.get(0));
		Assert.assertEquals(1, expensive.size());
		Assert.assertEquals(12131.58, Double.valueOf(expensive.get(0).split(";")[4]), 0.0);
	}

	@Test
	public void listCheaperGuitar() {
		List<String> cheaper = obj.listCheaperGuitar();
		Assert.assertNotNull(cheaper);
		Assert.assertNotNull(cheaper.get(0));
		Assert.assertEquals(2, cheaper.size());
		Assert.assertEquals(556.58, Double.valueOf(cheaper.get(0).split(";")[4]), 0.0);
		Assert.assertEquals(556.58, Double.valueOf(cheaper.get(1).split(";")[4]), 0.0);
	}

	@Test
	public void getDiscountedGuitarList() {
		List<String> expensive = obj.getDiscountedGuitarList();
		Assert.assertNotNull(expensive);
		Assert.assertNotNull(expensive.get(0));
		Assert.assertEquals(9, expensive.size());
	}

	@Test
	public void listAllGuitarsUpperPrice() {
		List<String> guitars = obj.listGuitarsByFilter(GuitarShopService.getUpperThen(10.0d));
		Assert.assertNotNull(guitars);
		Assert.assertEquals(12, guitars.size());
	}

	@Test
	public void listGuitarsUpperThanPrice() {
		List<String> guitars = obj.listGuitarsByFilter(GuitarShopService.getUpperThen(1000.0d));
		Assert.assertNotNull(guitars);
		Assert.assertEquals(6, guitars.size());
		double min = guitars.stream().mapToDouble(s -> GuitarShopService.getPrice(s.split(";"))).min().getAsDouble();
		Assert.assertTrue(min > 1000.0);
		Assert.assertTrue(guitars.stream().filter(s -> GuitarShopService.getPrice(s.split(";")) < min).collect(Collectors.toList()).isEmpty());
		guitars.forEach(System.out::println);
	}

	@Test
	public void listGuitarsLowerThanPrice() {
		List<String> guitars = obj.listGuitarsByFilter(GuitarShopService.getLowerThen(1000.0d));
		Assert.assertNotNull(guitars);
		Assert.assertEquals(6, guitars.size());
		double max = guitars.stream().mapToDouble(s -> GuitarShopService.getPrice(s.split(";"))).max().getAsDouble();
		Assert.assertTrue(max < 1000.0);
		Assert.assertTrue(guitars.stream().filter(s -> GuitarShopService.getPrice(s.split(";")) > max).collect(Collectors.toList()).isEmpty());
		guitars.forEach(System.out::println);
	}

	@Test
	public void listGuitarsByType() {
		List<String> guitars = obj.listGuitarsByFilter(GuitarShopService.getByType("acoustic"));
		Assert.assertNotNull(guitars);
		Assert.assertEquals(2, guitars.size());
		guitars.forEach(System.out::println);
	}

	@Test
	public void listGuitarsJoiningResultsOnePredicateNull() {
		GuitarShopService.joinResult(
				obj.listGuitarsByFilter(GuitarShopService.getByType("acoustic")),
				obj.listGuitarsByFilter(GuitarShopService.getByName("notExisting")));
		List<String> guitars = obj.listGuitarsByFilter(GuitarShopService.getByType("acoustic"));
		Assert.assertNotNull(guitars);
		Assert.assertEquals(2, guitars.size());
		guitars.forEach(System.out::println);
	}

	@Test
	public void listGuitarsJoiningResults() {
		List<String> guitars = GuitarShopService.joinResult(
				obj.listGuitarsByFilter(GuitarShopService.getByType("acoustic")),
				obj.listGuitarsByFilter(GuitarShopService.getByName("D18")));
		Assert.assertNotNull(guitars);
		Assert.assertEquals(3, guitars.size());
		guitars.forEach(System.out::println);
	}

}
