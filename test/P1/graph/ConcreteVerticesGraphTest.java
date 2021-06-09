/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }
    
    @Test(expected = AssertionError.class)
   	public void testAssertionsEnabled() {
   		assert false;
   	}
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    @Test
	public void testToStringEmptyGraph() {
		Graph<String> graph = emptyInstance();
		assertEquals("Empty Graph", graph.toString());
		Vertex<String> vertexA = new Vertex<>("A");
		Vertex<String> vertexB = new Vertex<>("B");

		graph.set("A", "B", 1);
		vertexA.settarget("B", 1);
		vertexB.setsource("A", 1);
		String ans = vertexA.toString() + "\n"+ vertexB.toString() + "\n";

		assertEquals(ans, graph.toString());
	}
    
    /*
     * Testing Vertex...
     */
    
    @Test
	public void testsetsource() {
		Vertex<String> A = new Vertex<>("A");
		
		A.setsource("B", 1);
		assertEquals(1, A.getsource().size());
		
		A.setsource("c", 0);
		assertEquals(1, A.getsource().size());
		
		assertEquals(1,(int) A.getsource().get("B"));
		A.setsource("B", 2);
		assertEquals(2,(int) A.getsource().get("B"));
		
		A.setsource("B", 0);
		assertEquals(0, A.getsource().size());	
	}
    
    @Test
   	public void testsettarget() {
   		Vertex<String> A = new Vertex<>("A");
   		
   		A.settarget("B", 1);
   		assertEquals(1, A.gettarget().size());
   		
   		A.settarget("c", 0);
   		assertEquals(1, A.gettarget().size());
   		
   		assertEquals(1,(int) A.gettarget().get("B"));
   		A.settarget("B", 2);
   		assertEquals(2,(int) A.gettarget().get("B"));
   		
   		A.settarget("B", 0);
   		assertEquals(0, A.gettarget().size());	
   	}
   
    
    
}
