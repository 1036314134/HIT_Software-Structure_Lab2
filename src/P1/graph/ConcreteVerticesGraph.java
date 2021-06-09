/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    //fields
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
 	// 由许多条有向边组成的图
    // 储存每个顶点的信息
 	
 	// Representation invariant:
 	// 边不为空，顶点不为空，顶点不能有重复
 	
 	// Safety from rep exposure:
 	// 所有fields都是private的
    // 顶点和边是可变的
    
    //checkRep
    private void checkRep() {
    	for(int i = 0; i < vertices.size(); i++) {
    		for(int j = i + 1; j < vertices.size(); j++) {
    			assert !(vertices.get(i).name().equals(vertices.get(j).name()));
    		}
    		assert vertices.get(i) != null;
    	}
    	for(Vertex<L> i:vertices) {
        	assert i != null;
        }
    }
    
    //methods
    @Override public boolean add(L vertex) {
    	if(vertices().contains(vertex)) {
    		return false;
    	}
    	Vertex<L> verticeinside = new Vertex<L>(vertex);
    	vertices.add(verticeinside);
    	checkRep();
    	return true;
    }
    
    /**
	 * 找到顶点在顶点列表中的位置
	 * 
	 * @param 顶点的名字
	 * @return 该顶点在顶点列表中的位置
	 */
    public int findvertice(L vertice) {
    	int index = -1;
    	for(int i = 0; i <= vertices.size(); i++) {
    		if(vertices.get(i).name().equals(vertice)) {
    			index = i;
    			break;
    		}
    	}
    	return index;
    }
    
    
    @Override public int set(L source, L target, int weight) {
    	if(weight < 0) {
    		throw new RuntimeException("wrong");
    	}
    	add(source);
    	add(target);
    	
    	int oldweight = 0;
    	int sourceindex = findvertice(source);
    	int targetindex = findvertice(target);
    	
    	if(vertices.get(sourceindex).gettarget().containsKey(target)) {
    		oldweight = vertices.get(sourceindex).gettarget().get(target);
    	}else {
    		oldweight = 0;
    	}
    	
    	vertices.get(sourceindex).settarget(target, weight);
    	vertices.get(targetindex).setsource(source, weight);
		checkRep();
		return oldweight;
    }
    
    
    @Override public boolean remove(L vertex) {
    	if(vertices().contains(vertex)) {
    		int index = findvertice(vertex);
    		for (Vertex<L> i: vertices) {
				if (i.getsource().containsKey(vertex)) {
					i.getsource().remove(vertex);
				}
				if (i.gettarget().containsKey(vertex)) {
					i.gettarget().remove(vertex);
				}
			}
    		vertices.remove(index);
    		checkRep();
    		return true;
    	}else {
    		checkRep();
    		return false;
    	}
    }
    
    
    @Override public Set<L> vertices() {
    	Set<L> ans = new HashSet<L>();
    	for(Vertex<L> i: vertices) {
    		ans.add(i.name());
    	}
    	return ans;
    }
    
    
    @Override public Map<L, Integer> sources(L target) {
    	Map<L, Integer> map = new HashMap<L, Integer>();
    	for(Vertex<L> i: vertices) {
    		if(i.name().equals(target)) {
    			map =  i.getsource();
    			break;
    		}
    	}
		return map;
    }
    
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L, Integer> map = new HashMap<L, Integer>();
    	for(Vertex<L> i: vertices) {
    		if(i.name().equals(source)) {
    			map =  i.gettarget();
    			break;
    		}
    	}
		return map;
    }

    //toString()
    @Override
	public String toString() {
		if(vertices.isEmpty()) {
			return "Empty Graph";
		}
		String graph = "";
		for(int i = 0; i < vertices.size(); i++) {
    		graph = graph.concat(vertices.get(i).toString() + "\n");
    	}
    	return graph;
	}
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex <L>{
	//fields
    private L name;
    private Map<L, Integer> allsource = new HashMap<>();//以当前点为终点
    private Map<L, Integer> alltarget = new HashMap<>();//以当前点为起点
    
    // Abstraction function:
    // Vertex是一个顶点，储存自己的名字、和自己相连的所有顶点
    
    // Representation invariant:
    // 顶点的名字不为空
    // 与该顶点相连的顶点不为空
    
    // Safety from rep exposure:
    // 所有fields都是private的
    // name是不可变的，其余均可变
    
    //constructor
    /**
	 * 增加一个顶点
	 * 
	 * @param 顶点的名字
	 */
    public Vertex(L name){
    	this.name = name;
    }
	
    //checkRep
    public void checkRep() {
    	assert allsource != null;
    	assert alltarget != null;
    	assert name != null;
    }
    
    //methods
    /**
	 * 返回顶点的名字
	 * 
	 * @return 顶点的名字
	 */
    public L name() {
    	return this.name;
    }
    
    /**
	 * 返回所有能通向该顶点的边的映射
	 * 
	 * @return 所有能通向该顶点的边的映射
	 */
    public Map<L, Integer> getsource(){
    	return allsource;
    }
    
    /**
	 * 返回所有从顶点出发的边的映射
	 * 
	 * @return 所有从顶点出发的边的映射
	 */
    public Map<L, Integer> gettarget(){
    	return alltarget;
    }
    
    /**
	 * 增加一条通向该顶点的边
	 * 
	 * @param 通向该顶点的顶点
	 * @param 边的权重
	 */
    public void setsource(L source, int weight) {
    	boolean flag = allsource.containsKey(source);
    	if(weight == 0 && flag) {
    		allsource.remove(source);
    	}else if(weight > 0 && !flag) {
    		allsource.put(source, weight);
    	}else if(weight > 0 && flag) {
    		allsource.replace(source, weight);
    	}
    	checkRep();
    }
    
    /**
	 * 增加一条从顶点出发的边
	 * 
	 * @param 该顶点能道达的顶点
	 * @param 边的权重
	 */
    public void settarget(L target, int weight) {
    	boolean flag = alltarget.containsKey(target);
    	if(weight == 0 && flag) {
    		alltarget.remove(target);
    	}else if(weight > 0 && flag) {
    		alltarget.replace(target, weight);
    	}else if(weight > 0  && !flag) {
    		alltarget.put(target, weight);
    	}
    	checkRep();
    }
    
    //toString()
    @Override
	public String toString() {
		return "Vertex [name=" + name + ", allsource=" + allsource + ", alltarget=" + alltarget + "]";
	}   
}
