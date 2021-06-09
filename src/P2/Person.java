package P2;

import P2.Person;

public class Person {
	//fields
	private  String name;
	public  int distance;
	

	// Abstraction function:
	// Person是关系网中的一个人
	
	// Representation invariant:
	// 名字不能为空
	
	// Safety from rep exposure:
	// name是private的
	// distance是public的
	
	//checkRep
	void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	/**
	 * 增加一个人
	 * 
	 * @param 该人的名字
	 */
	public Person(String name) {
		this.name = name;
		this.distance = 0;
		checkRep();
	}
	
	//methods
	/**
	 * 获得一个人的名字
	 * 
	 * @return 该人的名字
	 */
	public String name() {
		return this.name;
	}
	
	/**
	 * 获得该人的社交距离
	 * 
	 * @return 该人的社交距离
	 */
	public int distance() {
		return this.distance;
	}
	
}
