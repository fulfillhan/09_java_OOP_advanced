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
				join();
			}
			else if (sel == 2) {
				login();
			}
			else if (sel == 3) {
				break;
			}
		}
		
	}
	
	void join() {
		UserManager.getInstance().joinMember();
	}
	
	void login() {
		identifier = um.logUser();// 가입한 아이디의 인덱스값 반환되서 identifier 에 저장한다.
		
		if (identifier == -1) {
			System.out.println("[메세지]아이디와 패스워드를 확인해주세요.");
		}
		else {
			loginMenue();
		}
	}
	//계좌의 로그인기능의 메서드 
	void loginMenue() {
		
	while (true) {
		
		System.out.println("["+ um.userList[identifier].id+ "] 님, 환영합니다! ");
		System.out.println("[1.계좌생성]\n[2.계좌삭제]\n[3.조    회]\n[4.회원탈퇴]\n[0.로그아웃]");
		System.out.print("메뉴 선택 : ");
		int selectMenu = scan.nextInt();
		
		if (selectMenu == 1) {
			AccountManager.getInstance().createAcc(identifier);
			FileManager.getInstance().save();
			
		}else if (selectMenu == 2) {
			AccountManager.getInstance().removeAcc(identifier);
		}
		else if (selectMenu == 3) {
			AccountManager.getInstance().printAcc(identifier);
		}
		else if (selectMenu == 4) {
			// 회원탈퇴
			um.delMemeber(identifier);
			return;
		}
		else if (selectMenu == 0) {
			identifier = -1;
			System.out.println("로그아웃 되었습니다.");
			break;
		}
		
	}
		
		
	}

}
