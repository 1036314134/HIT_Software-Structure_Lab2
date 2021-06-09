/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    //fields
    protected final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
 	// 由许多条有向边组成的图
    // 边储存起始顶点、目标顶点和边权重
 	
 	// Representation invariant:
 	// 边不为空，顶点不为空，顶点不能有重复
 	
 	// Safety from rep exposure:
 	// 所有fields都是private的
    // 顶点和边是可变的

    //checkRep
    private void checkRep() {
    	for(int i = 0; i < edges.size(); i++) {
    		for(int j = i + 1;j<edges.size();j++){
				assert !(edges.get(i).source().equals(edges.get(j).source()) && edges.get(i).target().equals(edges.get(j).target()));
			}
    		assert edges.get(i) != null;
    	}
    	for(L i: vertices) {
    		assert i != null;
    	}
    }
  
    //methods
    @Override public boolean add(L vertex) {
    	if(vertices.contains(vertex)){
    		return false;
    	}
    	vertices.add(vertex);
    	checkRep();
    	return true;
    }
    
    /**
	 * 找到边在顶点列表中的位置
	 * 
	 * @param 边的起点
	 * @param 边的终点
	 * @return 该边在边列表中的位置
	 */
    public int findedge(L source, L target) {
    	int index = -1;
    	for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).source().equals(source) && edges.get(i).target().equals(target)) {
				index = i;
				break;
			}
		}
    	return index;
    }
    
    @Override
	public int set(L source, L target, int weight) {
		int index = findedge(source, target);
		int oldWeight = 0;
			
		if (weight > 0) {
			if (index < 0) {
				add(source);
				add(target);
				edges.add(new Edge<>(source, target, weight));
			} else {
				oldWeight = edges.get(index).weight();
				edges.set(index, new Edge<>(source, target, weight));
			}
		} else if (weight == 0 && index >= 0) {
			oldWeight = edges.get(index).weight();
			edges.remove(index);
		}

		checkRep();
		return oldWeight;
	}
    
    
    @Override public boolean remove(L vertex) {
    	if(!vertices.contains(vertex)) {
    		return false;
    	}
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).source().equals(vertex) || edges.get(i).target().equals(vertex)) {
				edges.remove(i);
			}
		}
    	vertices.remove(vertex);
    	checkRep();
    	return true;
    	
    }

    
    @Override public Set<L> vertices() {
        return Collections.unmodifiableSet(vertices);
    }
    
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> map = new HashMap<L, Integer>();
    	for(Edge<L> i: edges) {
    		if(i.target().equals(target)) {
    			map.put((L) i.source(), i.weight());
    		}
    	}
    	checkRep();
    	return map;
    	
    }
    
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> map = new HashMap<L, Integer>();
    	for(Edge<L> i: edges) {
    		if(i.source().equals(source)) {
    			map.put((L) i.target(), i.weight());
    		}
    	}
    	checkRep();
    	return map;
    }
    
    @Override
    public String toString() {
    	if(edges.isEmpty()) {
    		return "Empty Graph";
    	}
    	String graph = "";
    	for(int i = 0; i < edges.size(); i++) {
    		graph = graph.concat(edges.get(i).toString() + "\n");
    	}
    	return graph;
    }
 
}

/**
 * specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */

class Edge<L> {
	//fields
	private int weight;
	private L source;
	private L target;
	
	// Abstraction function:
	// Edge是一个有向边，储存起始顶点、目标顶点和边权重
	
	// Representation invariant:
	// 起始顶点不为空
	// 目标顶点不为空
	// 权重为正
	
	// Safety from rep exposure:
	// 所有fields均为private
	// L是mutable的
	
	//constructor
	/**
	 * 增加一条有向边
	 * 
	 * @param 起始顶点
	 * @param 目标顶点
	 * @param 边权重
	 */
	public Edge(L source, L target, int weight){
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}
	
	//methods
	/**
	 * 改变边的权重
	 * 
	 * @param 边权重 
	 */
	public void setweight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * 获得边的起始顶点
	 * 
	 * @return 边的起始顶点
	 */
	public L source() {
		return this.source;
	}
	
	/**
	 * 获得边的目标顶点
	 * 
	 * @return 边的目标顶点
	 */
	public L target() {
		return this.target;
	}
	
	/**
	 * 获得边的权重
	 * 
	 * @return 边的权重
	 */
	public int weight() {
		return this.weight;
	}

	//checkRep
	public void checkRep() {
		assert source != null;
		assert target != null;
		assert weight >= 0;
	}

	//toString()
	/**
	 * 返回用字符串形式表示的边
	 * 
	 * @return 字符串Edge [weight= , source= , target= ]
	 */
	@Override
	public String toString() {
		return "Edge [weight=" + weight + ", source=" + source + ", target=" + target + "]";
	}
}
	
