package P3;

public class Player {
	//fields
	private final String name;
	
	// Abstraction function:
	// 参与游戏的玩家
	//
	// Representation invariant:
	// 玩家的名字不为空
	//
	// Safety from rep exposure:
	// name是private且immutable的
	
	//checkRep
	void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	/**
	 * 增加一个玩家
	 * 
	 * @param 名字
	 */
	public Player(String name) {
		this.name = name;
		checkRep();
	}
	
	//methons
	/**
	 * 返回玩家的名字
	 * 
	 * @return 名字
	 */
	public String name() {
		return this.name;
	}
}
