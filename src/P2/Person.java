package P2;

import P2.Person;

public class Person {
	//fields
	private  String name;
	public  int distance;
	

	// Abstraction function:
	// Person�ǹ�ϵ���е�һ����
	
	// Representation invariant:
	// ���ֲ���Ϊ��
	
	// Safety from rep exposure:
	// name��private��
	// distance��public��
	
	//checkRep
	void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	/**
	 * ����һ����
	 * 
	 * @param ���˵�����
	 */
	public Person(String name) {
		this.name = name;
		this.distance = 0;
		checkRep();
	}
	
	//methods
	/**
	 * ���һ���˵�����
	 * 
	 * @return ���˵�����
	 */
	public String name() {
		return this.name;
	}
	
	/**
	 * ��ø��˵��罻����
	 * 
	 * @return ���˵��罻����
	 */
	public int distance() {
		return this.distance;
	}
	
}
