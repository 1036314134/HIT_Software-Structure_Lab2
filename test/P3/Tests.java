package P3;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;
import org.junit.Test;


public class Tests {

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void testTakeActionGo() throws IOException {
		Action action = new Action("go", "Zhang3", "Li4");
		action.printBoard();

		assertEquals(true, action.takeActionGo("Zhang3", 1, 1, 1));
		assertEquals(true, action.takeActionGo("Li4", 1, 2, 1));
		
		assertEquals(1, action.game().queryplayerpiecesNumber("Zhang3"));
		assertEquals(1, action.game().queryplayerpiecesNumber("Li4"));
		assertEquals(Arrays.asList("1 1"), action.queryRecord("Zhang3"));
		assertEquals(Arrays.asList("1 2"), action.queryRecord("Li4"));
		
		assertEquals(true, action.takeActionGo("Zhang3", 2, 2, 2));
		assertEquals(true, action.takeActionGo("Li4", 2, 3, 2));
		assertEquals(true, action.takeActionGo("Zhang3", 3, 3, 3));
		assertEquals(true, action.takeActionGo("Li4", 3, 4, 3));
		
		assertEquals(3, action.game().queryplayerpiecesNumber("Zhang3"));
		assertEquals(3, action.game().queryplayerpiecesNumber("Li4"));
	}

	@Test
	public void testTakeActionCheer() throws IOException {
		Action action = new Action("chess", "Zhang3", "Li4");
		action.printBoard();

		assertEquals(true, action.takeActionChess("Zhang3", 1, 6, 1, 4));
		assertEquals(true, action.takeActionChess("Li4", 1, 1, 1, 3));
		
		assertEquals(16, action.game().queryplayerpiecesNumber("Zhang3"));
		assertEquals(16, action.game().queryplayerpiecesNumber("Li4"));
		assertEquals(Arrays.asList("1 6 1 4"), action.queryRecord("Zhang3"));
		assertEquals(Arrays.asList("1 1 1 3"), action.queryRecord("Li4"));
		
		assertEquals(true, action.takeActionChess("Zhang3", 1, 4, 1, 3));
		assertEquals(15, action.game().queryplayerpiecesNumber("Li4"));
		
	}

}
