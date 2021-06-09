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
	// ÿ������ֻ�ܳ���һ��
	
	// Safety from rep exposure:
	// graph��private��
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
	 * ����һ����
	 * 
	 * @param ����
	 * @return ���������Ѿ��ڹ�ϵ���У�����false�����򷵻�true
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
	 * ����һ����ϵ
	 * 
	 * @param ��ʼ����
	 * @param ��ʼ��������ʶ����
	 * @param Ȩ��
	 * @return ������������˲��ڹ�ϵ���У�����false�����򷵻�true
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
	 * ��ȡ�������ڹ�ϵ���еľ���
	 * 
	 * @param ��ʼ����
	 * @param ��ʼ��������ʶ����
	 * @return ���˵ľ���
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
