package P3;

import java.util.*;
import java.io.*;

public class Action {
	//fields
	private final Game game;
	private final String player1;//���1
	private final String player2;//���2
	private final List<String> player1record;//���1��ʷ����
	private final List<String> player2record;//���2��ʷ����
	
	// Abstraction function:
	// ��ҽ��е��ж�
	
	// Representation invariant:
	// �����岻Ϊ��
	// ���1����
	// ���2����
	
	// Safety from rep exposure:
	// ����fields����private��immutable��	
	
	//checkRep
	public void checkRep() {
		assert this.game != null;
		assert this.player1 != null;
		assert this.player2 != null;
	}
	
	//constructor
	/**
	 * ����һ���µ����嶯��
	 * 
	 * @param �����������
	 * @param ���1������
	 * @param ���2������
	 * @throws IOException
	 */
	public Action(String type, String play1, String play2) throws IOException {
		this.game = new Game(type, play1, play2);
		this.player1 = play1;
		this.player2 = play2;
		this.player1record = new LinkedList<>();
		this.player2record = new LinkedList<>();
		checkRep();
	}
	
	//methods
	/**
	 * ��ѯ��������Ϣ
	 * 
	 * @return ��������Ϣ
	 */
	public Game game() {
		return this.game;
	}

	
	/**
	 * �ڹ��������н���һ���ж�
	 *
	 * @param ִ���ⲽ���������
	 * @param ���������ӵ�λ�õĺ�����
	 * @param ���������ӵ�λ�õ�������
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @return ִ�гɹ��򷵻�true,��֮����false
	 */	
	public boolean takeActionChess(String playername, int X1, int Y1, int X2, int Y2) {
		boolean flag = false;
		
		Position p = game.findPositions(X2, Y2);
		
		if (p == null) {//�ƶ�����
			flag = game.movePiece(playername, X1, Y1, X2, Y2);
		}else{//����
			flag = game.setPiece(playername, X1, Y1, X2, Y2);	
		}
		checkRep();
		
		//������ʷ����
		String actionwords = X1 + " " + Y1 + " " + X2 + " " + Y2;
		if(flag) {
			if (playername.equals(player1)) {
				player1record.add(actionwords);
			}else {
				player2record.add(actionwords);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * ��Χ���н���һ���ж�
	 *
	 * @param ִ���ⲽ���������
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @param ��������Ӹ���
	 * @return ִ�гɹ��򷵻�true,��֮����false
	 */	
	public boolean takeActionGo(String playername, int X, int Y, int index) {
		boolean flag = false;
		
		String piecename = null;
		if (playername.equals(player1)) {
			piecename = "B"+index;
		}else {
			piecename = "W"+index;
		}
		
		Position p = game.findPositions(X, Y);
		Piece piece = game.queryPiece(X, Y);
		
		if (p == null) {//�������
			flag = game.addPiece(playername, piecename, X, Y);
		}else if(p != null && !game.belong().get(piece).name().equals(playername)){//ɾ������
			flag = game.removePiece(playername, X, Y);
		} 

		checkRep();
		//������ʷ����
		String actionwords = X + " " + Y;
		if(flag) {
			if (playername.equals(player1)) {
				player1record.add(actionwords);
			}else {
				player2record.add(actionwords);
			}
			return true;
		}
				
		return false;
	}
	
	/**
	 * ��ѯ��ʷ����
	 *
	 * @param Ҫ��ѯ���������
	 * @return ����ҵ���ʷ����
	 */	
	public List<String> queryRecord(String player) {
		if (player.equals(player1)) {
			return player1record;
		}	
		if (player.equals(player2)) {
			return player2record;
		}
		return null;
	}
	
	/**
	 * ��ӡ����
	 *
	 * @return ����
	 */	
	public void printBoard() {//��ӡ����
		int size = game.board().size();
		
		System.out.print("\t");
		for(int i = 0; i < size; i++) {
			System.out.print("--------");
		}
		System.out.println("-");

		for (int j = size; j >= 0; j--) {
			System.out.print(j + "\t");
			
			for (int i = 0; i <= size; i++) {
				Position position = game.findPositions(i, j);

				if (position == null) {
					System.out.print("*" + "\t");
				}else {
					System.out.print(game.board().getboard().get(position).name() + "\t");
				}	
			}
			System.out.println();
		}
		System.out.print("\t");
		for(int i = 0; i < size; i++) {
			if(i <= 9) {
				System.out.print(i + "-------");
			}else {
				System.out.print(i + "------");
			}
		}
		System.out.println(size);
	}
	
}
