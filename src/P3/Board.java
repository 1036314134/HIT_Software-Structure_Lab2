package P3;

import java.util.*;

public class Board {
	//fields
	private final int size;
	private final Map<Position, Piece> board;
	
	// Abstraction function:
	// ����ʹ�õ�����
	//
	// Representation invariant:
	// ���̵Ĵ�С��Ϊ��
	//
	// Safety from rep exposure:
	// ����fields����private��immutable��
	
	//checkRep
	public void checkRep() {
		assert size >= 0;
	}
	
	//constructor
	/**
	 * �½�һ������
	 * 
	 * @param ���̴�С
	 */
	public Board(int size) {
		this.size = size;
		this.board = new HashMap<>();
		checkRep();
	}
	
	//methods
	/**
	 * ��ѯ���̵Ĵ�С
	 * 
	 * @return ���̴�С
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * ��ѯ������λ�õ�ӳ��
	 * 
	 * @return ������λ�õ�ӳ��
	 */
	public Map<Position, Piece> getboard() {
		return this.board;
	}
	
	/**
	 * ��ѯ������λ�õ�ӳ��
	 * 
	 * @param λ��
	 * @param ����
	 * @return �������������ӳ���򷵻�false�����򷵻�true
	 */
	public boolean setPosition(Position position, Piece piece) {
		if(!board.containsValue(piece)) {
			board.put(position, piece);
			return true;
		}
		return false;
	}
	
	/**
	 * ɾ��������λ�õ�ӳ��
	 * 
	 * @param λ��
	 * @return �����λ��û�������ӹ���ӳ���򷵻�false�����򷵻�true
	 */
	public boolean removePosition(Position position) {
		if(board.containsKey(position)){
			board.remove(position);
			return true;
		}
		return false;
	}
	
	/**
	 * ��ѯλ���Ƿ񲻺Ϸ�
	 * 
	 * @param ������
	 * @param ������
	 * @return �����λ�����������򷵻�false�����򷵻�true
	 */
	public boolean isPositionAvailable(int x, int y) {
		if (x < 0 || x > size || y < 0 || y > size) {
			return false;
		}
		return true;
	}
}
