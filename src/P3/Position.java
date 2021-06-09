package P3;

public class Position {
	//fields
	private final int x;
	private final int y;
	
	// Abstraction function:
	// ����ռ�õ�λ��
	//
	// Representation invariant:
	// λ�õ�������벻Ϊ��
	//
	// Safety from rep exposure:
	// ����fields����private��immutable��

	//checkRep
	void checkRep() {
		assert this.x >= 0;
		assert this.y >= 0;
	}
	
	//constructor
	/**
	 * ����һ������λ��
	 * 
	 * @param ������
	 * @param ������
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	
	//methods
	/**
	 * ���غ�����
	 * 
	 * @return ������
	 */
	public int x() {
		return this.x;
	}
	
	/**
	 * ����������
	 * 
	 * @return ������
	 */
	public int y() {
		return this.y;
	}
}
