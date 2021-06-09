package P3;

import java.util.*;
import java.io.*;

public class Game {
	//fields
	private final String type;//棋的种类
	private final Board board;//棋盘
	private final List<Piece> pieces;//已下棋子
	private final List<Position> positions;//已下位置
	private final Player player1, player2;//玩家
	private final Map<Piece, Player> belong;//棋子与玩家的映射
	
	// Abstraction function:
	// 正在进行的游戏
	//
	// Representation invariant:
	// 棋子数量与位置数量是否一一致
	//
	// Safety from rep exposure:
	// 所有fields都是private且immutable的	
	
	
	//checkRep
	public void checkRep() {
		assert pieces.size() == positions.size();
	}
	
	//constructor
	/**
	 * 产生一局新游戏
	 * 
	 * @param 这盘棋的总类
	 * @param 玩家1的名字
	 * @param 玩家2的名字
	 * @throws IOException
	 */	
	public Game(String type, String player1, String player2) throws IOException {
		this.type = type;
		this.pieces = new ArrayList<>();
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		this.belong = new HashMap<>();
		this.positions = new ArrayList<>();

		if (type.equals("go")) {//围棋
			this.board = new Board(18);
			
		}else if (type.equals("chess")) {//国际象棋
			this.board = new Board(7);
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(new File("src//P3//Chess_Initial_Position.txt")));

				String line = null;
				List<String> words = new ArrayList<>();
				int number = 0;

				while ((line = reader.readLine()) != null) {
					number++;
					words = Arrays.asList(line.split(" "));

					int x = Integer.parseInt(words.get(0));
					int y = Integer.parseInt(words.get(1));
					Piece piece = new Piece(words.get(2));
					Position position = new Position(x, y);

					pieces.add(piece);
					positions.add(position);
					board.setPosition(position, piece);
					if (number <= 16) {
						belong.put(piece, this.player2);
					}else {
						belong.put(piece, this.player1);
					}	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			board = new Board(0);
		}
		checkRep();
	}
	
	//methods
	/**
	 * 查询这盘棋的种类
	 * 
	 * @return 棋的种类
	 */
	public String type() {
		return this.type;
	}

	/**
	 * 查询这盘棋的棋盘
	 * 
	 * @return 棋的棋盘
	 */
	public Board board() {
		return this.board;
	}
	
	/**
	 * 查询这盘棋的棋子
	 * 
	 * @return 棋的棋子
	 */
	public List<Piece> pieces(){
		return this.pieces;
	}
	
	/**
	 * 查询这盘棋占用的位置
	 * 
	 * @return 占用的位置
	 */
	public List<Position> positions(){
		return this.positions;
	}
	
	/**
	 * 查询这盘棋中所有棋子与位置的对应关系
	 * 
	 * @return 所有棋子与位置的对应关系
	 */
	public Map<Piece, Player> belong(){
		return this.belong;
	}

	
	/**
	 * 查询棋子的信息
	 * 
	 * @param 棋子的名字
	 * @return 该棋子的信息
	 */		
	public Piece findPieces(String name) {
		for (Piece i : pieces) {
			if (i.name().equals(name)) {
				return i;
			}
		}
		return null;
	}

	/**
	 * 查询位置的信息
	 * 
	 * @param 横坐标
	 * @param 纵坐标
	 * @return 位置的信息
	 */	
	Position findPositions(int x, int y) {
		for (Position i: positions) {
			if (i.x() == x && i.y() == y) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * 查询某一位置上的棋子
	 *
	 * @param 横坐标
	 * @param 纵坐标
	 * @return 该位置上的棋子
	 */	
	public Piece queryPiece(int x, int y) {
		for (Position i : positions) {
			if (i.x() == x && i.y() == y) {
				return board.getboard().get(i);
			}
		}		
		return null;
	}

	/**
	 * 用玩家名字找玩家信息
	 *
	 * @param 玩家名字
	 * @return 玩家信息
	 */	
	private Player findPlayer(String player) {
		if (player1.name().equals(player)) {
			return player1;
		}
		if (player2.name().equals(player)) {
			return player2;
		}
		return null;
	}
	
	/**
	 * 用玩家名字找对手玩家信息
	 *
	 * @param 玩家名字
	 * @return 对手玩家信息
	 */	
	public Player anotherPlayer(String player) {
		if (player1.name().equals(player)) {
			return player2;
		}	
		if (player2.name().equals(player)) {
			return player1;
		}
		return null;
	}	

	/**
	 * 在棋盘上增加棋子
	 *
	 * @param 执行这步操作的玩家
	 * @param 增加的棋子的名字
	 * @param 落子位置的横坐标
	 * @param 落子位置的纵坐标
	 * @return 添加成功则返回true,反之返回false
	 */	
	public boolean addPiece(String playername, String name, int x, int y) {
		if (!board.isPositionAvailable(x, y)) {//位置是否在棋盘内
			return false;
		}
		if (findPieces(name) != null) {//棋子是否已经存在
			return false;
		}
		if (findPositions(x, y) != null) {//位置是否已经有棋子
			return false;
		}
			
		Piece piece = new Piece(name);
		Position position = new Position(x, y);
		Player player = findPlayer(playername);

		if (player == null) {//玩家是否不存在
			return false;
		}
			
		//添加棋子
		belong.put(piece, player);
		board.setPosition(position, piece);
		positions.add(position);
		pieces.add(piece);

		checkRep();
		return true;
	}
	
	/**
	 * 在棋盘上移动棋子
	 *
	 * @param 执行这步操作的玩家
	 * @param 棋子位置的横坐标
	 * @param 棋子位置的纵坐标
	 * @param 落子位置的横坐标
	 * @param 落子位置的纵坐标
	 * @return 移动成功则返回true,反之返回false
	 */	
	public boolean movePiece(String playername, int X1, int Y1, int X2, int Y2) {
		if (!board.isPositionAvailable(X1, Y1) || !board.isPositionAvailable(X2, Y2)) {//位置是否在棋盘外
			return false;
		}		
		if (findPositions(X2, Y2) != null) {//目标位置是否已经有棋子
			return false;
		}
		
		Player player =  findPlayer(playername);
		Position P1 = findPositions(X1, Y1);
		Piece piece = board.getboard().get(P1);

		if (P1 == null) {//起始位置没有棋子
			return false;
		}
		if (player == null || !belong.get(piece).equals(player)) {//玩家不存在或者该棋子不属于该玩家
			return false;
		}
		
		removePiece(anotherPlayer(playername).name(), X1, Y1);//先删除
		addPiece(playername, piece.name(), X2, Y2);//再添加
		checkRep();
		
		return true;
	}
	
	/**
	 * 在棋盘上删除棋子
	 *
	 * @param 执行这步操作的玩家
	 * @param 删除位置的横坐标
	 * @param 删除位置的纵坐标
	 * @return 删除成功则返回true,反之返回false
	 */	
	public boolean removePiece(String playername, int x, int y) {
		if (!board.isPositionAvailable(x, y)) {//位置不在棋盘内
			return false;
		}
		
		Player player =  findPlayer(playername);
		Position position = findPositions(x, y);
		Piece piece = board.getboard().get(position);

		if (position == null) {//删除位置没有棋子
			return false;
		}
		if (player == null || belong.get(piece).name().equals(playername)) {//玩家不存在或者该棋子不属于该玩家
			return false;

		}
			
		//删除棋子
		board.removePosition(position);
		positions.remove(position);
		pieces.remove(piece);
		belong.remove(piece);

		checkRep();
		return true;
	}
	
	/**
	 * 在棋盘上吃掉棋子
	 *
	 * @param 执行这步操作的玩家
	 * @param 棋子位置的横坐标
	 * @param 棋子位置的纵坐标
	 * @param 吃子位置的横坐标
	 * @param 吃子位置的纵坐标
	 * @return 吃子成功则返回true,反之返回false
	 */	
	public boolean setPiece(String playername, int X1, int Y1, int X2, int Y2) {
		if (!board.isPositionAvailable(X1, Y1) || !board.isPositionAvailable(X2, Y2)) {//位置在棋盘外
			return false;
		}
		
		Player Player1 =  findPlayer(playername);//玩家
		Player Player2 = anotherPlayer(playername);//对手
		
		if (Player1 == null || Player2 == null) {//玩家或对手不存在
			return false;
		}
		
		Position P1 = findPositions(X1, Y1);
		Position P2 = findPositions(X2, Y2);
		if (P1 == null || P2 == null) {//起始点或目标点没有棋子
			return false;
		}

		Piece Piece1 = board.getboard().get(P1);
		Piece Piece2 = board.getboard().get(P2);
		if (!belong.get(Piece1).equals(Player1) || !belong.get(Piece2).equals(Player2)) {//不能移动对方的棋子
			return false;
		}

		removePiece(playername, X2, Y2);//先删除
		movePiece(playername, X1, Y1, X2, Y2);//再移动
		
		checkRep();
		return true;
	}
	
	public int queryallpiecesNumber() {//棋盘上所有棋子的数量
		return pieces().size();
	}
	
	public int queryplayerpiecesNumber(String playername) {//某个玩家棋子数量
		int number = 0;
		for (Piece p : pieces) {
			if (belong.get(p).name().equals(playername)) {
				number++;
			}
		}	
		return number;
	}
}
