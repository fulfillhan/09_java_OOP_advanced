package step9_02.atm_v2;
import java.util.Random;
import java.util.Scanner;

public class AccountManager {
     Random ran = new Random(); 
     Scanner scan = new Scanner(System.in);
     
	// AccountManger의 싱글패턴 생성자 호출
    private AccountManager() {}
    private static AccountManager instancne = new AccountManager();
    public static AccountManager getInstance () {
    	return instancne;
    }
    
    UserManager um = UserManager.getInstance();
    
    //계좌 생성의 메서드
    void createAcc(int identifier) {
    	
    	//사용자가 가지고 있는 계좌 갯수를 accCntBYUser변수에 저장한다.
    	int accCntByUser = um.userList[identifier].accCnt;
    	
    	// 최대 갯수를 초과한경우
    	if (accCntByUser == um.ACC_MAX_CNT) {
			System.out.println("[메세지]계좌개설은 3개까지만 가능합니다.");
			return;
		}
    	
    	String makeAccount = "";
    	
    	while (true) {
			
    	makeAccount = Integer.toString(ran.nextInt(900001)+ 10000);
		// 계좌번호 중복 체크
		if (!um.getCheckAcc(makeAccount)) {
			break;
			}
    	}
    	//사용자의 계좌목록에 새로 생성된 계좌 번호 저장.
    	um.userList[identifier].acc[accCntByUser] = new Account(); 
    	um.userList[identifier].acc[accCntByUser].accNumber = makeAccount;
    	um.userList[identifier].accCnt++;
    	System.out.println("[메세지]'" + makeAccount + "'계좌가 생성되었습니다.\n");
    	
    }
    void removeAcc(int identifier) {
    	
    	int tempAccCnt = um.userList[identifier].accCnt;
    	
    	if (tempAccCnt == 0) {
			System.out.println("[메시지] 더 이상 삭제할 수 없습니다.\n");
			return;
		}
    	if(tempAccCnt == 1) {
			System.out.println("[메시지] 계좌번호 : "+ um.userList[identifier].acc[0].accNumber+"] 계좌가 삭제되었습니다.");
			um.userList[identifier].acc = null;
		}
    	else {
			System.out.print("[메시지] 삭제할 계좌번호를 입력하세요 : ");
			String myAccNum = scan.next();
			int delIdx = -1;
			
			Account[] tempAccounts = um.userList[identifier].acc;
			um.userList[identifier].acc = new Account[tempAccCnt-1];
			
			//삭제하고자 하는 인덱스를 찾기
			for (int i = 0; i < tempAccCnt; i++) {
				if (userList[identifier].acc[i].accNumber.equals(myAccNum)) {
					delIdx = i;
					return;
				}
			}
			if (delIdx == -1) {
				System.out.println("[메시지] 계좌번호를 재확인 하세요.");
				
			}
			else {
				int j = 0;
				for (int i = 0; i < tempAccCnt; i++) {
					if (i != delIdx) { // 삭제하는 변수와 i 가 같지않다면 (중복체크)
						um.userList[identifier].acc[j] = tempAccounts[i];
						j++;
					}
				}
				tempAccCnt--;
				tempAccounts = null;
				delIdx = -1;
				
				System.out.println("[메시지] "+ um.getInstance().userList[identifier].id+ "님 탈퇴되었습니다.");
				
				FileManager.getInstance().save();
				}
    	   } 
			
	}
}
    
    

