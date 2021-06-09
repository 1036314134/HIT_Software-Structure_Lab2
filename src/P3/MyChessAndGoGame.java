package P3;

import java.util.*;
import java.io.*;

public class MyChessAndGoGame {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		String board = null;
				
		while(true) {
			System.out.println("输入棋盘种类：(chess 或者 go)");
			board = in.nextLine();
			if(!board.equals("go") && !board.equals("chess")) {
				System.out.println("输入错误");
			}else {
				break;
			}
		}
		
		System.out.println("输入玩家1（黑棋）：");
		String player1 = in.nextLine();
		System.out.println("输入玩家2（白棋）：");
		String player2 = in.nextLine();
		
		String playernow = player1;

		Action action = new Action(board, player1, player2);
		action.printBoard();
		
		
		System.out.println("输入1下棋");
		System.out.println("输入2查询棋子个数");
		System.out.println("输入3查询历史步骤");
		System.out.println("输入4跳过");
		System.out.println("输入end退出");
		System.out.println();
		int index1 = 1;
		int index2 = 1;
		
		while (true) {
			System.out.println("轮到" + playernow + "了，请给出你的行动：(1、2、3、4、end)");	
			String line = in.nextLine();
			List<String> words = null;
			if (line.equals("1")) {
				if(board.equals("go")) {
					System.out.println("输入 : x y 将棋子下在（x, y）处或将该处对手的棋子移除");
					line = in.nextLine();
					words = Arrays.asList(line.split(" "));
					int x = Integer.parseInt(words.get(0));
					int y = Integer.parseInt(words.get(1));
					if(playernow.equals(player1)) {
						boolean flag = action.takeActionGo(playernow, x, y, index1);
						if(flag) {
							index1++;
							System.out.println("行动成功");
							playernow = action.game().anotherPlayer(playernow).name();
							action.printBoard();
						}else {
							System.out.println("行动失败，重新来");
						}
					}else {
						boolean flag = action.takeActionGo(playernow, x, y, index2);
						if(flag) {
							index2++;
							System.out.println("行动成功");
							playernow = action.game().anotherPlayer(playernow).name();
							action.printBoard();
						}else {
							System.out.println("行动失败，重新来");
						}
					}
					
					
				}else if(board.equals("chess")) {
					System.out.println("输入 : x1 y1 x2 y2将（x1, y1）处的棋子移动到（x2, y2）");
					line = in.nextLine();
					words = Arrays.asList(line.split(" "));
					int x1 = Integer.parseInt(words.get(0));
					int y1 = Integer.parseInt(words.get(1));
					int x2 = Integer.parseInt(words.get(2));
					int y2 = Integer.parseInt(words.get(3));
					boolean flag = action.takeActionChess(playernow, x1, y1, x2, y2);
					if(flag) {
						System.out.println("行动成功");
						playernow = action.game().anotherPlayer(playernow).name();
						action.printBoard();
					}else {
						System.out.println("行动失败，重新来");
					}
					
				}
				
			}else if (line.equals("2")) {
				System.out.println("请问需要查询谁的棋子数？");
				System.out.println("输入1查询玩家1，输入2查询玩家2，输入3查询棋子总数：");
				line = in.nextLine();
				if(line.equals("1")) {
					System.out.println(player1 + "的棋子数为：" + action.game().queryplayerpiecesNumber(player1));
				}else if(line.equals("2")) {
					System.out.println(player2 + "的棋子数为：" + action.game().queryplayerpiecesNumber(player2));
				}else if(line.equals("3")) {
					System.out.println("总棋子数为：" + action.game().queryallpiecesNumber());
				}else {
					System.out.println("输入错误！");
				}
				
			}else if (line.equals("3")) {
				System.out.println("请问需要查询谁的历史步骤？");
				System.out.println("输入1查询玩家1，输入2查询玩家2：");
				line = in.nextLine();
				if(line.equals("1")) {
					System.out.println(player1 + "的历史步骤为：" + action.queryRecord(player1));
				}else if(line.equals("2")) {
					System.out.println(player2 + "的历史步骤为：" + action.queryRecord(player2));
				}else {
					System.out.println("输入错误！");
				}
			}else if (line.equals("4")) {
				System.out.println("你跳过了该回合");
				playernow = action.game().anotherPlayer(playernow).name();
				
			}else if (line.equals("end")) {
				System.out.println("游戏结束");
				break;
			}else {
				System.out.println("输入错误，重新输入：");
			}
		}
		in.close();
	}

}
