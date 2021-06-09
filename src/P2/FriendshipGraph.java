package P2;

import P1.graph.*;

import java.util.*;

public class FriendshipGraph {
	//fields
	private final Graph<Person> graph = Graph.empty();
	
	// Abstraction function:
	// A friend graph which has vertex made by name
	// vertices are linked if they know each other
	
	// Representation invariant:
	// 每个顶点只能出现一次
	
	// Safety from rep exposure:
	// graph是private的
	// considering graph is mutable
	

	public void checkRep() {
		for (Person i : graph.vertices()) {
			for (Person j : graph.vertices()) {
				assert (i.hashCode() == j.hashCode()) || !i.name().equals(j.name());
			}
		}
	}
	
	//methods
	/**
	 * 增加一个人
	 * 
	 * @param 新人
	 * @return 如果这个人已经在关系网中，返回false，否则返回true
	 */
	public boolean addVertex(Person person) {
		if (graph.vertices().contains(person)) {
			return false;
		}
		checkRep();
		graph.add(person);
		return true;
	}

	/**
	 * 增加一个关系
	 * 
	 * @param 起始的人
	 * @param 起始的人所认识的人
	 * @param 权重
	 * @return 如果两个人有人不在关系网中，返回false，否则返回true
	 */
	public boolean addEdge(Person source, Person target, int weight) {
		if (!graph.vertices().contains(source) || !graph.vertices().contains(target)) {
			return false;
		}
		checkRep();
		graph.set(source, target, weight);
		return  true;
	}

	/**
	 * 获取两个人在关系网中的距离
	 * 
	 * @param 起始的人
	 * @param 起始的人所认识的人
	 * @return 两人的距离
	 */
	public int getDistance(Person a, Person b) {
		if(a.name().equals(b.name())) {
			return 0;
		}
		if (!graph.vertices().contains(a) || !graph.vertices().contains(b)) {
			return -1;
		}
		
		Queue<Person> q = new LinkedList<Person>();
		
		for(Person i: graph.vertices()) {
			i.distance = -1;
		}
		
		a.distance = 0;
		q.add(a);

		while (q.isEmpty() == false) {
			Person tmp = q.poll();

			for (Person i : graph.targets(tmp).keySet()) {
				int currentDistance = tmp.distance() + graph.targets(tmp).get(i);

				if (i.distance() == -1 || i.distance() > currentDistance) {
					i.distance = currentDistance;
					q.offer(i);
				}
			}
		}

		return b.distance();
	}
}
