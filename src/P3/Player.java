package P3;

public class Player {
	//fields
	private final String name;
	
	// Abstraction function:
	// ������Ϸ�����
	//
	// Representation invariant:
	// ��ҵ����ֲ�Ϊ��
	//
	// Safety from rep exposure:
	// name��private��immutable��
	
	//checkRep
	void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	/**
	 * ����һ�����
	 * 
	 * @param ����
	 */
	public Player(String name) {
		this.name = name;
		checkRep();
	}
	
	//methons
	/**
	 * ������ҵ�����
	 * 
	 * @return ����
	 */
	public String name() {
		return this.name;
	}
}
