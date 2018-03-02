package http.tests;

import org.junit.Test;
import utils.http.QueryStringBuilder;

import static org.junit.Assert.*;

/**
 * giuseppe.adaldo
 */
public class HttpQueryStringBuilderTest {

	@Test
	public void testNullParams() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "accountId", "supports", "onlyActive" });

			assertNotNull(query);
			assertTrue(query.isEmpty());
			assertEquals(query, "");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testNormalBehaviour() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "accountId", "supports", "onlyActive" }, 10010011,
					new String[] { "", "" }, true);

			assertNotNull(query);
			assertFalse(query.isEmpty());
			assertEquals(query, "?accountId=10010011&supports=&supports=&onlyActive=true");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testLessParamsThanNames() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "accountId", "supports", "onlyActive" }, 10010011,
					new String[] { "", "" });

			assertNotNull(query);
			assertTrue(query.isEmpty());
			assertEquals(query, "");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testLessNamesThanParams() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "accountId", "supports" }, 10010011, "a", "b", "c");

			assertNotNull(query);
			assertTrue(query.isEmpty());
			assertEquals(query, "");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testArraySingleObj() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "accountId", "supports" }, 10010011, new String[] { "a" });

			assertNotNull(query);
			assertFalse(query.isEmpty());
			assertEquals(query, "?accountId=10010011&supports=a");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmptyArrayObj() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "accountId", "supports" }, 10010011, new String[] {});

			assertNotNull(query);
			assertFalse(query.isEmpty());
			assertEquals(query, "?accountId=10010011");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmptyArrayFirstObj() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "", "supports" }, 10010011, new String[] {});

			assertNotNull(query);
			assertTrue(query.isEmpty());
			assertEquals(query, "");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testEmptyArrayFirstObjNonNullValues() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "", "supports" }, 10010011, new String[] { "hello world" });

			assertNotNull(query);
			assertFalse(query.isEmpty());
			assertEquals(query, "?supports=hello+world");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testLongString() {
		try {
			final String query = QueryStringBuilder.buildURIParams(new String[] { "oneLongStringAsParameterName" },
					"a very   long string to parse and check as query string value!");

			assertNotNull(query);
			assertFalse(query.isEmpty());
			assertEquals(query, "?oneLongStringAsParameterName=a+very+++long+string+to+parse+and+check+as+query+string+value%21");
		} catch (Exception e) {
			fail();
		}
	}

}
