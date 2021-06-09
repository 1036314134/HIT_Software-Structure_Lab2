package P3;

import java.util.*;

public class Board {
	//fields
	private final int size;
	private final Map<Position, Piece> board;
	
	// Abstraction function:
	// 正在使用的棋盘
	//
	// Representation invariant:
	// 棋盘的大小不为负
	//
	// Safety from rep exposure:
	// 所有fields都是private且immutable的
	
	//checkRep
	public void checkRep() {
		assert size >= 0;
	}
	
	//constructor
	/**
	 * 新建一个棋盘
	 * 
	 * @param 棋盘大小
	 */
	public Board(int size) {
		this.size = size;
		this.board = new HashMap<>();
		checkRep();
	}
	
	//methods
	/**
	 * 查询棋盘的大小
	 * 
	 * @return 棋盘大小
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * 查询棋子与位置的映射
	 * 
	 * @return 棋子与位置的映射
	 */
	public Map<Position, Piece> getboard() {
		return this.board;
	}
	
	/**
	 * 查询棋子与位置的映射
	 * 
	 * @param 位置
	 * @param 棋子
	 * @return 如果该棋子已有映射则返回false，否则返回true
	 */
	public boolean setPosition(Position position, Piece piece) {
		if(!board.containsValue(piece)) {
			board.put(position, piece);
			return true;
		}
		return false;
	}
	
	/**
	 * 删除棋子与位置的映射
	 * 
	 * @param 位置
	 * @return 如果该位置没有与棋子构成映射则返回false，否则返回true
	 */
	public boolean removePosition(Position position) {
		if(board.containsKey(position)){
			board.remove(position);
			return true;
		}
		return false;
	}
	
	/**
	 * 查询位置是否不合法
	 * 
	 * @param 横坐标
	 * @param 纵坐标
	 * @return 如果该位置在棋盘外则返回false，否则返回true
	 */
	public boolean isPositionAvailable(int x, int y) {
		if (x < 0 || x > size || y < 0 || y > size) {
			return false;
		}
		return true;
	}
}
