package P3;

public class Piece {
	//fields
	private final String name;
	
	// Abstraction function:
	// �������ϵ�����
	//
	// Representation invariant:
	// ���ӵ����ֲ�Ϊ��
	//
	// Safety from rep exposure:
	// name��private��immutable��	

	//checkRep
	void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	/**
	 * ����һ������
	 * 
	 * @return ��������
	 */
	public Piece(String name) {
		this.name = name;
		checkRep();
	}
	
	//methods
	/**
	 * ������������
	 * 
	 * @return ��������
	 */
	public String name() {
		return this.name;
	}
}
