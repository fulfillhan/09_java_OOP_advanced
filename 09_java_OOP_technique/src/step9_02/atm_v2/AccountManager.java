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
    void removeAcc(int delIdentifier) {
    	
    	System.out.print("[메시지] 삭제할 계좌번호를 입력하세요 : ");
    	String myAccNum = scan.next();
    	 int tempAccCnt = um.userList[delIdentifier].accCnt;
    	 int delIdx = -1;
    	 
    	
    	if (tempAccCnt == 0) {
			System.out.println("[메시지] 더 이상 삭제할 수 없습니다.\n");
			return;
		}
    	if(tempAccCnt == 1) {
			System.out.println("[메시지] 계좌번호 : "+ um.userList[delIdentifier].acc[0].accNumber+"] 계좌가 삭제되었습니다.");
			um.userList[delIdentifier].acc = null;
		}
    	else {
    		//계좌가 2개 이상일때
    		
				//삭제하고자 하는 인덱스를 찾기
    		int i = 0;
    		while (i < tempAccCnt) {
				
    			if (um.userList[delIdentifier].acc[i].equals(myAccNum)) {
					 delIdx = i;
					 i++;
				}
    			else {
					System.out.println("[메시지] 계좌번호를 재확인 하세요.");
					delIdx = -1;
				}
			}
    		
//			for (int delIdx = 0; i < tempAccCnt; delIdx++) {
//				if (um.userList[delIdentifier].acc[i].equals(myAccNum)) {// 만약에 삭제하는 계좌와 같은 요소가 있다면 -> 취소인덱스를 찾은거고 이게 아니면 요소저장
//					delIdx = i;
//				}
//				else {
//					delIdx = -1;
//					System.out.println("[메시지] 계좌번호를 재확인 하세요.");
//					return;
//				}
				

				}
			// 삭제할 계좌를 제외한 나머지 계좌를 담을 새로운 배열 생성.
			Account[] temp = um.userList[delIdentifier].acc;
			um.userList[delIdentifier].acc = new Account[tempAccCnt -1];
			
			 int j = 0;
				for (int i = 0; i < tempAccCnt; i++) {
					if (i != delIdx) { // 삭제하는 변수와 i 가 같지않다면 (중복체크)
						um.userList[delIdentifier].acc[j] = temp[i];
						j++;
					}
				}
				tempAccCnt--;
				temp = null;
				FileManager.getInstance().save();
				
				System.out.println("[메시지]\n"+myAccNum+"계좌가 삭제되었습니다.");
				
				}
    	   
    
	    void printAcc(int identifier) {
		     User temp = um.userList[identifier];// userList[] 특정 요소를 temp 배열에 저장
		     System.out.println("\n=========================");
		     System.out.println("ID : "+ temp.id);
		     System.out.println("=========================");
		     for (int i = 0; i < temp.accCnt; i++) {
				System.out.println("계좌 번호 : "+temp.acc[i].accNumber+ " / "+ temp.acc[i].money);
		     }
		     System.out.println("=========================\n");
	    }
}

    
    

