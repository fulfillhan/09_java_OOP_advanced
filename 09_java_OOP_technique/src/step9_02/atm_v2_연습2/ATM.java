package step9_02.atm_v2_연습2;

import java.util.Scanner;

public class ATM {
	Scanner scan =  new Scanner(System.in);

	UserManager um = UserManager.getInstance();
	AccountManager am = AccountManager.getInstance();
	int identifier = -1;
	
	void play() {
		
		while (true) {
			
			System.out.println("[ATM]");
			System.out.println("[1.회원가입] [2.로그인] [0.종료]");
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if(sel == 1) {
				join();
			}
			else if (sel == 2) {
				login();
			}
			else if (sel == 0) {
				System.out.println(" == 시스템 종료 ==");
				break;
			}
			
		}
	}
	
	void join() {
		um.joinMember();
	}

	void login(){
		 identifier = um.logUser();

		if (identifier == -1){
			System.out.println("[메시지] 아이디와 패스워드를 이용해주세요.\n");
			return;
		}
		else {//identifier = i
			System.out.println("로그인 되었습니다.\n");
			loginAccMenu();
		}
	}

	void loginAccMenu(){
		//tkdgml] 님, 환영합니다!
		//[1.계좌생성]
		//[2.계좌삭제]
		//[3.조    회]
		//[4.회원탈퇴]
		//[0.로그아웃]
		//메뉴 선택 : 1
		//[메세지]'8426443'계좌가 생성되었습니다.

		while(true){
		System.out.println("["+um.user[identifier].id+"] 님, 환영합니다.");
			System.out.println("[1.계좌생성]\n[2.계좌삭제]\n[3.조    회]\n[4.회원탈퇴]\n[0.로그아웃]");
			System.out.print("메뉴 선택 : ");
			int menu = scan.nextInt();

			if (menu == 1){
				am.createAcc(identifier);
				//파일 저장
			} else if (menu == 2) {
				am.removeAcc(identifier);
			} else if (menu == 3) {
				am.printAcc(identifier);
			} else if (menu == 4) {
				um.deleteUser(identifier);
				return;
			} else if (menu == 0) {
				break;
			}
		}
	}

}
