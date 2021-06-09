package P2;

public class Main {
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		
		//lab1里现成的，直接套用了
		
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross, 1);
		graph.addEdge(ross, rachel, 1);
		graph.addEdge(ross, ben, 1);
		graph.addEdge(ben, ross, 1);
		 
		System.out.println(graph.getDistance(rachel, ross));  //should print 1 
		System.out.println(graph.getDistance(rachel, ben));  //should print 2 
		System.out.println(graph.getDistance(rachel, rachel));  //should print 0  
		System.out.println(graph.getDistance(rachel, kramer));  //should print -1 
		
	}
}
