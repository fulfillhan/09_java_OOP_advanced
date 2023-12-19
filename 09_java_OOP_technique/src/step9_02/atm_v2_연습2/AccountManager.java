package step9_02.atm_v2_연습2;
// 2023-12-19 계좌 생성 부분 수정

import java.util.Random;
import java.util.Scanner;

public class AccountManager {
    Scanner scan = new Scanner(System.in);
    Random ran = new Random();

    private AccountManager() {
    }

    private static AccountManager instance = new AccountManager();

    public static AccountManager getInstance() {
        return instance;
    }

    UserManager um = UserManager.getInstance();

    // 계좌 생성하기
    void createAcc(int identifier) {
        int accCntByUser = um.user[identifier].accCnt;// 유저당 가지고있는 계좌의 수

        if (accCntByUser > um.ACC_MAX_CNT) {
            System.out.println("[메시지] 더 이상 계좌를 생성할 수 없습니다!\n");
            return;
        }

        String makeAccount = "";
        while (true) {
            makeAccount = ran.nextInt(90001) + 10000 + "";
            // 계좌 중복여부 확인 필요
            if (um.checkId(makeAccount)) {
                System.out.println("[메시지] 계좌 번호가 중복됩니다. 재확인 필요!!\n");
                continue;
            } else if (!um.checkId(makeAccount)) {
                break;
            }

        }

        if (accCntByUser == 0) {
            um.user[identifier].acc = new Account[1];
            um.user[identifier].acc[0] = new Account();
            um.user[identifier].acc[0].accNumber = makeAccount;
            System.out.println("[메시지] '" + makeAccount + "' 계좌가 생성되었습니다.\n");
        }
        if (accCntByUser > 0) {
            Account[] temp = um.user[identifier].acc;
            um.user[identifier].acc = new Account[accCntByUser + 1];
            for (int i = 0; i < accCntByUser; i++) {
                um.user[identifier].acc[i] = temp[i];
            }
            um.user[identifier].acc[accCntByUser] = new Account();
            um.user[identifier].acc[accCntByUser].accNumber = makeAccount;
            System.out.println("\"[메시지] '" + makeAccount + "' 계좌가 생성되었습니다.\n");
        }

    }
    //계좌 삭제 -> 업데이트 필요
    void remove(int identifier){
    	
    	System.out.print("삭제할 계좌 번호를 입력하세요 : ");
    	String myAccount = scan.next();
    	
    	int accCntByUser = um.user[identifier].accCnt;
    	
    	
    	
    	
    	

    }



}
