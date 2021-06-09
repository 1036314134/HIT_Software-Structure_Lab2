/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void test() throws IOException {
		GraphPoet graphPoet = new GraphPoet(new File("test/P1/poet/test.txt"));
		String input = "Seek to explore new and exciting synergies!";

		assertEquals("Seek to explore strange new life and exciting synergies!", graphPoet.poem(input));
	}
    
}
