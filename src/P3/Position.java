package P3;

public class Position {
	//fields
	private final int x;
	private final int y;
	
	// Abstraction function:
	// 棋子占用的位置
	//
	// Representation invariant:
	// 位置的坐标必须不为负
	//
	// Safety from rep exposure:
	// 所有fields都是private且immutable的

	//checkRep
	void checkRep() {
		assert this.x >= 0;
		assert this.y >= 0;
	}
	
	//constructor
	/**
	 * 增加一个已用位置
	 * 
	 * @param 横坐标
	 * @param 纵坐标
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	
	//methods
	/**
	 * 返回横坐标
	 * 
	 * @return 横坐标
	 */
	public int x() {
		return this.x;
	}
	
	/**
	 * 返回纵坐标
	 * 
	 * @return 纵坐标
	 */
	public int y() {
		return this.y;
	}
}
