package step9_02.atm_v2;

import java.util.Scanner;

public class ATM {
	Scanner scan = new Scanner(System.in);
	UserManager um = UserManager.getInstance();// 싱글톤 패턴의 UserManager 클래스의 객체 호출
	int identifier = -1;
	
	void play() {
		
		UserManager.getInstance().printAllUser();
		
		while (true) {
			
			System.out.println("[ATM]");
			System.out.println("[1.회원가입]\n[2.로그인]\n[0.종료]");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if (sel == 1) {
				
			}
			else if (sel == 2) {
				
			}
			else if (sel == 3) {
				break;
			}
		}
		
	}
	
	void join() {
		
	}

}
