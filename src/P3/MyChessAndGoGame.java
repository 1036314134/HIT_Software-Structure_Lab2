package P3;

import java.util.*;
import java.io.*;

public class MyChessAndGoGame {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);

		String board = null;
				
		while(true) {
			System.out.println("�����������ࣺ(chess ���� go)");
			board = in.nextLine();
			if(!board.equals("go") && !board.equals("chess")) {
				System.out.println("�������");
			}else {
				break;
			}
		}
		
		System.out.println("�������1�����壩��");
		String player1 = in.nextLine();
		System.out.println("�������2�����壩��");
		String player2 = in.nextLine();
		
		String playernow = player1;

		Action action = new Action(board, player1, player2);
		action.printBoard();
		
		
		System.out.println("����1����");
		System.out.println("����2��ѯ���Ӹ���");
		System.out.println("����3��ѯ��ʷ����");
		System.out.println("����4����");
		System.out.println("����end�˳�");
		System.out.println();
		int index1 = 1;
		int index2 = 1;
		
		while (true) {
			System.out.println("�ֵ�" + playernow + "�ˣ����������ж���(1��2��3��4��end)");	
			String line = in.nextLine();
			List<String> words = null;
			if (line.equals("1")) {
				if(board.equals("go")) {
					System.out.println("���� : x y ���������ڣ�x, y�����򽫸ô����ֵ������Ƴ�");
					line = in.nextLine();
					words = Arrays.asList(line.split(" "));
					int x = Integer.parseInt(words.get(0));
					int y = Integer.parseInt(words.get(1));
					if(playernow.equals(player1)) {
						boolean flag = action.takeActionGo(playernow, x, y, index1);
						if(flag) {
							index1++;
							System.out.println("�ж��ɹ�");
							playernow = action.game().anotherPlayer(playernow).name();
							action.printBoard();
						}else {
							System.out.println("�ж�ʧ�ܣ�������");
						}
					}else {
						boolean flag = action.takeActionGo(playernow, x, y, index2);
						if(flag) {
							index2++;
							System.out.println("�ж��ɹ�");
							playernow = action.game().anotherPlayer(playernow).name();
							action.printBoard();
						}else {
							System.out.println("�ж�ʧ�ܣ�������");
						}
					}
					
					
				}else if(board.equals("chess")) {
					System.out.println("���� : x1 y1 x2 y2����x1, y1�����������ƶ�����x2, y2��");
					line = in.nextLine();
					words = Arrays.asList(line.split(" "));
					int x1 = Integer.parseInt(words.get(0));
					int y1 = Integer.parseInt(words.get(1));
					int x2 = Integer.parseInt(words.get(2));
					int y2 = Integer.parseInt(words.get(3));
					boolean flag = action.takeActionChess(playernow, x1, y1, x2, y2);
					if(flag) {
						System.out.println("�ж��ɹ�");
						playernow = action.game().anotherPlayer(playernow).name();
						action.printBoard();
					}else {
						System.out.println("�ж�ʧ�ܣ�������");
					}
					
				}
				
			}else if (line.equals("2")) {
				System.out.println("������Ҫ��ѯ˭����������");
				System.out.println("����1��ѯ���1������2��ѯ���2������3��ѯ����������");
				line = in.nextLine();
				if(line.equals("1")) {
					System.out.println(player1 + "��������Ϊ��" + action.game().queryplayerpiecesNumber(player1));
				}else if(line.equals("2")) {
					System.out.println(player2 + "��������Ϊ��" + action.game().queryplayerpiecesNumber(player2));
				}else if(line.equals("3")) {
					System.out.println("��������Ϊ��" + action.game().queryallpiecesNumber());
				}else {
					System.out.println("�������");
				}
				
			}else if (line.equals("3")) {
				System.out.println("������Ҫ��ѯ˭����ʷ���裿");
				System.out.println("����1��ѯ���1������2��ѯ���2��");
				line = in.nextLine();
				if(line.equals("1")) {
					System.out.println(player1 + "����ʷ����Ϊ��" + action.queryRecord(player1));
				}else if(line.equals("2")) {
					System.out.println(player2 + "����ʷ����Ϊ��" + action.queryRecord(player2));
				}else {
					System.out.println("�������");
				}
			}else if (line.equals("4")) {
				System.out.println("�������˸ûغ�");
				playernow = action.game().anotherPlayer(playernow).name();
				
			}else if (line.equals("end")) {
				System.out.println("��Ϸ����");
				break;
			}else {
				System.out.println("��������������룺");
			}
		}
		in.close();
	}

}
