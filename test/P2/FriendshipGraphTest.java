package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P1.graph.ConcreteEdgesGraph;
import P2.Person;
import P2.FriendshipGraph;

public class FriendshipGraphTest extends ConcreteEdgesGraph<Person>{
	
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	@Test
    public void getDistanceTest(){
		
		FriendshipGraph graph = new FriendshipGraph();
		Person p1 = new Person("P1");
		Person p2 = new Person("P2"); 
		Person p3 = new Person("P3");
		Person p4 = new Person("P4");
		Person p5 = new Person("P5");
		 
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		graph.addVertex(p4);
		graph.addVertex(p5);
		
		graph.addEdge(p1, p2, 1);
		graph.addEdge(p1, p4, 1);
		graph.addEdge(p1, p5, 1);
		graph.addEdge(p2, p4, 1);
		graph.addEdge(p3, p1, 1);
		graph.addEdge(p3, p2, 1);
		
		assertEquals(0, graph.getDistance(p1, p1));
		assertEquals(1, graph.getDistance(p1, p5));
		assertEquals(1, graph.getDistance(p2, p4));
		assertEquals(2, graph.getDistance(p3, p5));
		assertEquals(-1, graph.getDistance(p5, p3));
		assertEquals(-1, graph.getDistance(p4, p5));
    }

}
