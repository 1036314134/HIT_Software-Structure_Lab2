/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 * @param <L>
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }
    
    
    @Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    @Test
	public void testtoString() {
		Graph<String> graph = emptyInstance();
		assertEquals("Empty Graph", graph.toString());
		graph.set("A", "B", 1);
    	assertEquals("Edge [weight=" + 1 + ", source=A, target=B]" + "\n", graph.toString());
	}
    
    @Test
	public void testEdge() {
		Edge<String> e = new Edge<>("A", "B", 1);
		assertEquals("A", e.source());
		assertEquals("B", e.target());
		assertEquals(1, e.weight());
		e.setweight(2);
		assertEquals(2, e.weight());
	}
    
}
