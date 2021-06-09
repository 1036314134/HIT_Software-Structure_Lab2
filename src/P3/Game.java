package P3;

import java.util.*;
import java.io.*;

public class Game {
	//fields
	private final String type;//�������
	private final Board board;//����
	private final List<Piece> pieces;//��������
	private final List<Position> positions;//����λ��
	private final Player player1, player2;//���
	private final Map<Piece, Player> belong;//��������ҵ�ӳ��
	
	// Abstraction function:
	// ���ڽ��е���Ϸ
	//
	// Representation invariant:
	// ����������λ�������Ƿ�һһ��
	//
	// Safety from rep exposure:
	// ����fields����private��immutable��	
	
	
	//checkRep
	public void checkRep() {
		assert pieces.size() == positions.size();
	}
	
	//constructor
	/**
	 * ����һ������Ϸ
	 * 
	 * @param �����������
	 * @param ���1������
	 * @param ���2������
	 * @throws IOException
	 */	
	public Game(String type, String player1, String player2) throws IOException {
		this.type = type;
		this.pieces = new ArrayList<>();
		this.player1 = new Player(player1);
		this.player2 = new Player(player2);
		this.belong = new HashMap<>();
		this.positions = new ArrayList<>();

		if (type.equals("go")) {//Χ��
			this.board = new Board(18);
			
		}else if (type.equals("chess")) {//��������
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
	 * ��ѯ�����������
	 * 
	 * @return �������
	 */
	public String type() {
		return this.type;
	}

	/**
	 * ��ѯ�����������
	 * 
	 * @return �������
	 */
	public Board board() {
		return this.board;
	}
	
	/**
	 * ��ѯ�����������
	 * 
	 * @return �������
	 */
	public List<Piece> pieces(){
		return this.pieces;
	}
	
	/**
	 * ��ѯ������ռ�õ�λ��
	 * 
	 * @return ռ�õ�λ��
	 */
	public List<Position> positions(){
		return this.positions;
	}
	
	/**
	 * ��ѯ������������������λ�õĶ�Ӧ��ϵ
	 * 
	 * @return ����������λ�õĶ�Ӧ��ϵ
	 */
	public Map<Piece, Player> belong(){
		return this.belong;
	}

	
	/**
	 * ��ѯ���ӵ���Ϣ
	 * 
	 * @param ���ӵ�����
	 * @return �����ӵ���Ϣ
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
	 * ��ѯλ�õ���Ϣ
	 * 
	 * @param ������
	 * @param ������
	 * @return λ�õ���Ϣ
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
	 * ��ѯĳһλ���ϵ�����
	 *
	 * @param ������
	 * @param ������
	 * @return ��λ���ϵ�����
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
	 * ����������������Ϣ
	 *
	 * @param �������
	 * @return �����Ϣ
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
	 * ����������Ҷ��������Ϣ
	 *
	 * @param �������
	 * @return ���������Ϣ
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
	 * ����������������
	 *
	 * @param ִ���ⲽ���������
	 * @param ���ӵ����ӵ�����
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @return ��ӳɹ��򷵻�true,��֮����false
	 */	
	public boolean addPiece(String playername, String name, int x, int y) {
		if (!board.isPositionAvailable(x, y)) {//λ���Ƿ���������
			return false;
		}
		if (findPieces(name) != null) {//�����Ƿ��Ѿ�����
			return false;
		}
		if (findPositions(x, y) != null) {//λ���Ƿ��Ѿ�������
			return false;
		}
			
		Piece piece = new Piece(name);
		Position position = new Position(x, y);
		Player player = findPlayer(playername);

		if (player == null) {//����Ƿ񲻴���
			return false;
		}
			
		//�������
		belong.put(piece, player);
		board.setPosition(position, piece);
		positions.add(position);
		pieces.add(piece);

		checkRep();
		return true;
	}
	
	/**
	 * ���������ƶ�����
	 *
	 * @param ִ���ⲽ���������
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @return �ƶ��ɹ��򷵻�true,��֮����false
	 */	
	public boolean movePiece(String playername, int X1, int Y1, int X2, int Y2) {
		if (!board.isPositionAvailable(X1, Y1) || !board.isPositionAvailable(X2, Y2)) {//λ���Ƿ���������
			return false;
		}		
		if (findPositions(X2, Y2) != null) {//Ŀ��λ���Ƿ��Ѿ�������
			return false;
		}
		
		Player player =  findPlayer(playername);
		Position P1 = findPositions(X1, Y1);
		Piece piece = board.getboard().get(P1);

		if (P1 == null) {//��ʼλ��û������
			return false;
		}
		if (player == null || !belong.get(piece).equals(player)) {//��Ҳ����ڻ��߸����Ӳ����ڸ����
			return false;
		}
		
		removePiece(anotherPlayer(playername).name(), X1, Y1);//��ɾ��
		addPiece(playername, piece.name(), X2, Y2);//�����
		checkRep();
		
		return true;
	}
	
	/**
	 * ��������ɾ������
	 *
	 * @param ִ���ⲽ���������
	 * @param ɾ��λ�õĺ�����
	 * @param ɾ��λ�õ�������
	 * @return ɾ���ɹ��򷵻�true,��֮����false
	 */	
	public boolean removePiece(String playername, int x, int y) {
		if (!board.isPositionAvailable(x, y)) {//λ�ò���������
			return false;
		}
		
		Player player =  findPlayer(playername);
		Position position = findPositions(x, y);
		Piece piece = board.getboard().get(position);

		if (position == null) {//ɾ��λ��û������
			return false;
		}
		if (player == null || belong.get(piece).name().equals(playername)) {//��Ҳ����ڻ��߸����Ӳ����ڸ����
			return false;

		}
			
		//ɾ������
		board.removePosition(position);
		positions.remove(position);
		pieces.remove(piece);
		belong.remove(piece);

		checkRep();
		return true;
	}
	
	/**
	 * �������ϳԵ�����
	 *
	 * @param ִ���ⲽ���������
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @param ����λ�õĺ�����
	 * @param ����λ�õ�������
	 * @return ���ӳɹ��򷵻�true,��֮����false
	 */	
	public boolean setPiece(String playername, int X1, int Y1, int X2, int Y2) {
		if (!board.isPositionAvailable(X1, Y1) || !board.isPositionAvailable(X2, Y2)) {//λ����������
			return false;
		}
		
		Player Player1 =  findPlayer(playername);//���
		Player Player2 = anotherPlayer(playername);//����
		
		if (Player1 == null || Player2 == null) {//��һ���ֲ�����
			return false;
		}
		
		Position P1 = findPositions(X1, Y1);
		Position P2 = findPositions(X2, Y2);
		if (P1 == null || P2 == null) {//��ʼ���Ŀ���û������
			return false;
		}

		Piece Piece1 = board.getboard().get(P1);
		Piece Piece2 = board.getboard().get(P2);
		if (!belong.get(Piece1).equals(Player1) || !belong.get(Piece2).equals(Player2)) {//�����ƶ��Է�������
			return false;
		}

		removePiece(playername, X2, Y2);//��ɾ��
		movePiece(playername, X1, Y1, X2, Y2);//���ƶ�
		
		checkRep();
		return true;
	}
	
	public int queryallpiecesNumber() {//�������������ӵ�����
		return pieces().size();
	}
	
	public int queryplayerpiecesNumber(String playername) {//ĳ�������������
		int number = 0;
		for (Piece p : pieces) {
			if (belong.get(p).name().equals(playername)) {
				number++;
			}
		}	
		return number;
	}
}
