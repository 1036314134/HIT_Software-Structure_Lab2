package P3;

public class Piece {
	//fields
	private final String name;
	
	// Abstraction function:
	// 在棋盘上的棋子
	//
	// Representation invariant:
	// 棋子的名字不为空
	//
	// Safety from rep exposure:
	// name是private且immutable的	

	//checkRep
	void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	/**
	 * 增加一个棋子
	 * 
	 * @return 棋子名字
	 */
	public Piece(String name) {
		this.name = name;
		checkRep();
	}
	
	//methods
	/**
	 * 返回棋子名字
	 * 
	 * @return 棋子名字
	 */
	public String name() {
		return this.name;
	}
}
