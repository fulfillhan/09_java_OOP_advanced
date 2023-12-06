package step9_02.atm_v2;

import java.util.Scanner;

public class UserManager {
	
	Scanner scan = new Scanner(System.in);
	
	//싱글턴 패턴으로 생성자 만들기
	 private UserManager() {}
	 static private UserManager instance = new UserManager();
	 static public UserManager getInstance() {
		 return instance;
	}
	 
	 final int ACC_MAX_CNT = 3;			// 최대 개설 가능한 계좌 수
		User[] userList = null;				// 전체 회원정보
		int userCnt = 0;					// 전체 회원 수
		
		void printAllUser() { 
			//사용자의 수 반복하여 그만큼의 사용자의 계좌 번호도 출력
			for (int i = 0; i < userCnt; i++) {  // 사용자의 수만큼 반복
				System.out.println((i + 1) + "ID ("+ userList[i].id+ ") pw ("+ userList[i].pw+ ")pw\t" );
				if (userList[i].accCnt != 0) {  // 사용자의 배열에서 계좌수가 0이 아니라면
					for (int j = 0; j < userList[i].accCnt; j++) {
						System.out.println("(" + userList[i].acc[j].accNumber + ":" + userList[i].acc[j].money + ")");
					}
				}
				System.out.println();
			}
		}
		// 아이디가 중복되는 지 확인하는 메서드
		boolean checkId(String id) {
			
			boolean isDuple = false;
			for (int i = 0; i < userCnt; i++) {
				if (userList[i].id.equals(id)) {
					isDuple = true;
				}
			}
			return isDuple;
		}
		
		
		
		void joinMember() {
			//회원가입
			
			System.out.print("[회원가입] 아이디를 입력하세요 : ");
			String id = scan.next();
			System.out.println("[회원가입] 패스워드를 입력하세요 : ");
			String pw = scan.next();
			
			boolean isresult = checkId(id); // 중복결과 result 변수에 저장
			
			if (isresult) {
				System.out.println("[메시지] 아이디가 중복됩니다.");
				return;  //메서드 자체를 종료.
			}
			
			if (userCnt == 0) {
				userList = new User[userCnt + 1];
				userList[userCnt] = new User();
			}
			else {
				User[] temp = userList;   //기존 배열의 참조를 가지고 있는 userlist를  temp 배열 저장.
				userList = new User[userCnt + 1]; //배열의 크기가 +1 증가한 새로운 배열 생성.
				userList[userCnt] = new User();// userList 의 마지막 인덱스에 새로운 User 객체 생성.
				
				for (int i = 0; i <userCnt; i++) {
					userList[i] = temp[i];  // 임시배열의 요소들을 새로운 배열에 복사하여 기존데이터 저장.
				}
				temp = null;
			}
			//마지막인덱스에 요소 저장
			userList[userCnt].id = id;
			userList[userCnt].pw = pw;
			
			userCnt++;
			System.out.println("[메시지] 회원 가입을 축하합니다.");
			
			//파일저장하기 FileManager 생성필요
			
			
			
		}
		

}
