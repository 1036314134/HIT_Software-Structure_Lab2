package P3;

import java.util.*;
import java.io.*;

public class Action {
	//fields
	private final Game game;
	private final String player1;//玩家1
	private final String player2;//玩家2
	private final List<String> player1record;//玩家1历史步骤
	private final List<String> player2record;//玩家2历史步骤
	
	// Abstraction function:
	// 玩家进行的行动
	
	// Representation invariant:
	// 这盘棋不为空
	// 玩家1存在
	// 玩家2存在
	
	// Safety from rep exposure:
	// 所有fields都是private且immutable的	
	
	//checkRep
	public void checkRep() {
		assert this.game != null;
		assert this.player1 != null;
		assert this.player2 != null;
	}
	
	//constructor
	/**
	 * 产生一个新的下棋动作
	 * 
	 * @param 这盘棋的种类
	 * @param 玩家1的名字
	 * @param 玩家2的名字
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
	 * 查询这局棋的信息
	 * 
	 * @return 这局棋的信息
	 */
	public Game game() {
		return this.game;
	}

	
	/**
	 * 在国际象棋中进行一次行动
	 *
	 * @param 执行这步操作的玩家
	 * @param 操作的棋子的位置的横坐标
	 * @param 操作的棋子的位置的纵坐标
	 * @param 落子位置的横坐标
	 * @param 落子位置的纵坐标
	 * @return 执行成功则返回true,反之返回false
	 */	
	public boolean takeActionChess(String playername, int X1, int Y1, int X2, int Y2) {
		boolean flag = false;
		
		Position p = game.findPositions(X2, Y2);
		
		if (p == null) {//移动棋子
			flag = game.movePiece(playername, X1, Y1, X2, Y2);
		}else{//吃子
			flag = game.setPiece(playername, X1, Y1, X2, Y2);	
		}
		checkRep();
		
		//更新历史步骤
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
	 * 在围棋中进行一次行动
	 *
	 * @param 执行这步操作的玩家
	 * @param 操作位置的横坐标
	 * @param 操作位置的纵坐标
	 * @param 该玩家落子个数
	 * @return 执行成功则返回true,反之返回false
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
		
		if (p == null) {//添加棋子
			flag = game.addPiece(playername, piecename, X, Y);
		}else if(p != null && !game.belong().get(piece).name().equals(playername)){//删除棋子
			flag = game.removePiece(playername, X, Y);
		} 

		checkRep();
		//更新历史步骤
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
	 * 查询历史步骤
	 *
	 * @param 要查询的玩家名字
	 * @return 该玩家的历史步骤
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
	 * 打印棋盘
	 *
	 * @return 棋盘
	 */	
	public void printBoard() {//打印棋盘
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
