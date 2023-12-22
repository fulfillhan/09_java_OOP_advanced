package step9_02.atm_v2_연습2;
// 2023-12-19 계좌 생성 부분 수정
// remove메서드 오류 수정함

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

        if (accCntByUser >= um.ACC_MAX_CNT) {
            System.out.println("[메시지] 더 이상 계좌를 생성할 수 없습니다!\n");
            return;
        }
        String makeAccount = "";
        while (true) {
            makeAccount = Integer.toString(ran.nextInt(90001) + 10000);
            // 계좌 중복여부 확인 필요
            if (um.getCheckAcc(makeAccount)) {
                System.out.println("[메시지] 계좌 번호가 중복됩니다. 재확인 필요!!\n");
                continue;
            } else if (!um.getCheckAcc(makeAccount)) {
                break;
            }

        }
        um.user[identifier].acc[accCntByUser] = new Account();
        um.user[identifier].acc[accCntByUser].accNumber = makeAccount;
        um.user[identifier].accCnt++;
        System.out.println("[메시지] '" + makeAccount + "'계좌가 생성되었습니다.");

    }

    //계좌 삭제
    void removeAcc(int identifier) {
        System.out.print("삭제할 계좌번호를 입력하세요 : ");
        String deleteAcc = scan.next();

        int accCntByUser = um.user[identifier].accCnt;

        if (accCntByUser == 0) {
            System.out.println("[메시지] 더이상 삭제할 계좌가 없습니다.\n");
            return;
        }

        if (accCntByUser == 1) {
            if (um.getCheckAcc(deleteAcc)) { //삭제할 계좌가 있다면 true
                System.out.println("[메시지] 계좌번호 : " + deleteAcc + "가 삭제되었습니다.\n");
                um.user[identifier].acc[0] = null;
                um.user[identifier].accCnt = 0;// accCntByUser=0; 오류발생으로 수정함
            } else {
                System.out.println("[메시지] 계좌 번호를 재확인 해주세요!\n");
            }
        } else if (accCntByUser > 1) {
            if (!um.getCheckAcc(deleteAcc)) {
                System.out.println("[메시지] 계좌 번호를 재확인 해주세요!\n");
                return;
            }
            System.out.println("[메시지] 계좌번호 : " + deleteAcc + "가 삭제되었습니다.\n");
            Account[] temp = um.user[identifier].acc;
            um.user[identifier].acc = new Account[accCntByUser - 1];
            int j = 0;
            for (int i = 0; i < accCntByUser; i++) {
                if (!deleteAcc.equals(temp[i].accNumber)) {
                    um.user[identifier].acc[j] = temp[i];
                    j++;
                }
            }
            temp = null;
            um.user[identifier].accCnt--;//유저의 계좌수 accCntByUser--; 오류 발생으로 수정함
        }

    }


    void printAcc(int identifier) {
        User temp = um.user[identifier];
        System.out.println("=========================");
        System.out.println("id : " + um.user[identifier].id);
        System.out.println("=========================");
        for (int i = 0; i < temp.accCnt; i++) {
            System.out.println("계좌 번호 : " + temp.acc[i].accNumber + " / money : " + temp.acc[i].money);
        }
        System.out.println("==========================\n");
    }
}
