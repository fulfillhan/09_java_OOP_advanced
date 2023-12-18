package step9_02.atm_v2_연습2;

import java.util.Scanner;

public class ATM {
	Scanner scan =  new Scanner(System.in);
	
	void play() {
		
		while (true) {
			
			System.out.println("[ATM]");
			System.out.println("[1.회원가입]/n[2.로그인]/n[0.종료]");
			System.out.println("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				//join();
			}
			else if (sel == 2) {
				//login();
			}
			else if (sel == 0) {
				System.out.println(" == 시스템 종료 ==");
				break;
			}
			
		}
	}
	
	void join() {
		
	}
	

}
