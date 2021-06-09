/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    
    // Tests for graph.add()
 	@Test
 	public void testadd() {
 		Graph<String> graph = emptyInstance();
 		String A = "A";
 		String B = "B";
 		graph.add(A);
 		assertEquals(1, graph.vertices().size());
 		graph.add(B);
 		assertEquals(2, graph.vertices().size());
 		assertFalse(graph.add(B));
 		assertEquals(2, graph.vertices().size());
 	}

 	
 	// Tests for graph.set()
 	@Test
 	public void testset() {
 		Graph<String> graph = emptyInstance();
 		String A = "A";
 		String B = "B";
 		String C = "C";
 		String D = "D";

 		int oldweight = graph.set(A, B, 1);
 		assertEquals(0, oldweight);
 		
 		assertTrue(graph.sources(B).containsKey(A));
 		assertTrue(graph.targets(A).containsKey(B));

 		graph.add(C);
 		graph.add(D);
 		oldweight = graph.set(C, D, 1);
 		assertEquals(0, oldweight);
 		
 		assertTrue(graph.sources(D).containsKey(C));
 		assertTrue(graph.targets(C).containsKey(D));
 		
 		oldweight = graph.set(C, D, 2);
 		assertEquals(1, oldweight);
 		
 		oldweight = graph.set(C, D, 0);
 		assertEquals(2, oldweight);
 		
 		assertFalse(graph.sources(D).containsKey(C));
 		assertFalse(graph.targets(C).containsKey(D));
 	}

 	// Tests for graph.remove()

 	@Test
 	public void testremove() {
 		Graph<String> graph = emptyInstance();
 		String A = "A";
 		String B = "B";
 		
 		graph.remove(A);
 		assertEquals(0, graph.vertices().size());
 		
 		graph.add(A);
 		assertEquals(1, graph.vertices().size());
 		
 		graph.remove(A);
 		assertEquals(0, graph.vertices().size());

 		graph.add(A);
 		graph.add(B);
 		assertEquals(2, graph.vertices().size());
 		
 		graph.remove(B);
 		assertEquals(1, graph.vertices().size());
 		assertTrue( graph.vertices().contains(A));
 		assertFalse(graph.vertices().contains(B));
 	
 		graph.add(B);
 		graph.set(A, B, 1);
 		
 		graph.remove(B);
 		assertFalse(graph.vertices().contains(B));
 		assertFalse(graph.targets(A).containsKey(B));
 	}

 	
 	// Tests for graph.vertices()
 	@Test
 	public void testvertices() {
 		Graph<String> graph = emptyInstance();
 		assertEquals("No element is contained", Collections.emptySet(), graph.vertices());
 		String A = "A";
 		String B = "B";

 		graph.add(A);
 		assertTrue(graph.vertices().contains(A));
 		
 		graph.add(B);		
 		assertTrue(graph.vertices().contains(B));
 	}
 	

 	// Tests for graph.sources()
 	@Test
 	public void testSources() {
 		Graph<String> graph = emptyInstance();
 		String A = "A";
 		String B = "B";
 		String C = "C";

 		graph.add(A);
 		graph.add(B);
 		graph.add(C);
 		graph.set(B, A, 1);
 		assertTrue(graph.sources(A).containsKey(B));
 		assertEquals(1, graph.sources(A).size());
 		
 		graph.set(C, A, 1);
 		assertTrue(graph.sources(A).containsKey(C));
 		assertEquals(2, graph.sources(A).size());
 	}

 	// Tests for graph.targets()

 	@Test
 	public void testTargets() {
 		Graph<String> graph = emptyInstance();
 		String A = "A";
 		String B = "B";
 		String C = "C";

 		graph.add(A);
 		graph.add(B);
 		graph.add(C);
 		graph.set(A, B, 1);
 		assertTrue(graph.targets(A).containsKey(B));
 		assertEquals(1, graph.targets(A).size());
 		
 		graph.set(A, C, 1);
 		assertTrue(graph.targets(A).containsKey(C));
 		assertEquals(2, graph.targets(A).size());
 	}
}
