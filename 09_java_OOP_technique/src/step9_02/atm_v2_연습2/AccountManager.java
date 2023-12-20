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
            makeAccount =Integer.toString(ran.nextInt(90001)+10000);
            // 계좌 중복여부 확인 필요
            if (um.getCheckAcc(makeAccount)) {
                System.out.println("[메시지] 계좌 번호가 중복됩니다. 재확인 필요!!\n");
                continue;
            } else if (!um.getCheckAcc(makeAccount)) {
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
    //계좌 삭제
    //-> 오류 발생 : 계좌번호가 다 삭제된후 다시 생성할시 ' java.lang.NullPointerException ' 발생.
    void remove(int identifier){
        System.out.print("삭제할 계좌번호를 입력하세요 : ");
        String deleteAcc = scan.next();

        int accCntByUser = um.user[identifier].accCnt;

        if (um.getCheckAcc(deleteAcc)){
            System.out.println("[메시지] 계좌 번호를 재확인 해주세요.");
            return;
        }

        if (accCntByUser == 0){
            System.out.println("[메시지] 더이상 삭제할 계좌가 없습니다.\n");
            return;
        }

        if (accCntByUser == 1){

            System.out.println("[메시지] 계좌번호 : "+deleteAcc+"가 삭제되었습니다.");
            accCntByUser = 0;
            um.user[identifier].acc[0] = null;

        } else if (1 < accCntByUser && accCntByUser <= 3) {
            System.out.println("[메시지] 계좌번호 : "+ deleteAcc+"가 삭제되었습니다.");

            Account[] temp = um.user[identifier].acc;
            um.user[identifier].acc = new Account[accCntByUser-1];
           // 여기서부터 다시하기
            int j = 0;
            for (int i = 0; i < accCntByUser ; i++) {
            	if (um.getCheckAcc(deleteAcc)) { // 삭제할 계좌번호 중복 여부
            		um.user[identifier].acc[j] = temp[i];//중복이 아니면 기존배열 temp에서 복사하기
            	}
            	
            	um.user[identifier].acc[accCntByUser] = new Account();
               
          }



        }


    }



}
