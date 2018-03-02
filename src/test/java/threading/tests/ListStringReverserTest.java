package threading.tests;

import org.junit.Test;
import threading.ListStringReverser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * giuseppe.adaldo
 */
public class ListStringReverserTest {

    private ListStringReverser listStringReverser = new ListStringReverser();

    @Test
    public void reverseNullTest() {
        final List<String> ret = listStringReverser.execute(null);
        assertNull(ret);
    }

    @Test
    public void reverseEmptyTest() {
        final List<String> ret = listStringReverser.execute(Collections.singletonList(""));
        assertNotNull(ret);
        assertTrue(ret.size() == 1);
        assertTrue(ret.get(0).equals(""));
    }

    @Test
    public void reverseEmptyListTest() {
        final List<String> ret = listStringReverser.execute(Collections.emptyList());
        assertNotNull(ret);
        assertTrue(ret.isEmpty());
    }

    @Test
    public void reverseString() {
        final List<String> wordsToReverse = Arrays.asList("word1", "word2", "anotherWord",
                "some", "any", "etc", "word1", "word2", "anotherWord", "some", "any", "etc", "word1", "word2",
                "anotherWord", "some", "any", "etc", "word1", "word2", "anotherWord", "some", "any", "etc",
                "word1", "word2", "anotherWord", "some", "any", "etc", "also", "this", "is", "a", "test");

        final List<String> reversed = listStringReverser.execute(wordsToReverse);

        assertNotEquals(wordsToReverse, reversed);
        assertEquals(wordsToReverse.size(), reversed.size());
    }

}
