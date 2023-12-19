package step9_02.atm_v2;


import java.util.Random;
import java.util.Scanner;

public class AccountManager {
	Random ran = new Random();
	Scanner scan = new Scanner(System.in);

	// AccountManger의 싱글패턴 생성자 호출
	private AccountManager() {
	}

	private static AccountManager instancne = new AccountManager();

	public static AccountManager getInstance() {
		return instancne;
	}

	UserManager um = UserManager.getInstance();

	// 계좌 생성의 메서드
	void createAcc(int identifier) {

		int accCntByUser = um.userList[identifier].accCnt;

		if (accCntByUser == um.ACC_MAX_CNT) {
			System.out.println("[메세지]계좌개설은 3개까지만 가능합니다.");
			return;
		}

		um.userList[identifier].acc[accCntByUser] = new Account();

		String makeAccount = "";
		while (true) {
			makeAccount = ran.nextInt(9000000) + 1000001 + "";
			if (!um.getCheckAcc(makeAccount)) {
				break;
			}
		}
		um.userList[identifier].acc[accCntByUser].accNumber = makeAccount;
		um.userList[identifier].accCnt++;
		System.out.println("[메세지]'" + makeAccount + "'계좌가 생성되었습니다.\n");

	}
	//여기에서 문제점 :  마지막남은 계좌번호를 삭제시 기존에 삭제되었던 번호 입력하게 되면
	// 이미 삭제되었다고 출력되어야 하나, 마지막 남은 번호가 삭제됨.
	void removeAcc(int identifier) {

		System.out.print("삭제할 계좌번호를 입력하세요 : ");
		String myAccount = scan.next();

		int accCntByUser = um.userList[identifier].accCnt;

		if (accCntByUser == 0) {
			System.out.println("[메시지] 더 이상 삭제할 수 없습니다.\n");
			return;
		}
		if (accCntByUser == 1) {
			System.out.println("[메시지] 계좌번호 : " + um.userList[identifier].acc[0].accNumber + "계좌정보가 삭제되었습니다.");
			System.out.println("계좌 정보가 모두 삭제되었습니다.");
			um.userList[identifier].acc = null;//-> 여기가 잘못되엇나..?
			accCntByUser = 0;
			return;
		}
		
		int delIdx = -1;
		for (int i = 0; i < accCntByUser; i++) {
			if (myAccount.equals(um.userList[identifier].acc[i].accNumber)) {
				delIdx = i;
				break;
			}
		}
		if (delIdx == -1) {
			System.out.println("[메시지] 계좌번호를 재확인하세요.");
			return;
		} else {
			System.out.println("[메시지]\n[" + myAccount + "] 계좌정보가 삭제되었습니다.");

			Account[] tempAccount = um.userList[identifier].acc;
			um.userList[identifier].acc = new Account[accCntByUser - 1];

			int j = 0;
			for (int i = 0; i < accCntByUser; i++) {
				if (i != delIdx) {
					um.userList[identifier].acc[j] = tempAccount[i];
					j++;
				}

			}
			um.userList[identifier].accCnt--;
		}

	}

	void printAcc(int identifier) {

		User temp = um.userList[identifier];
		System.out.println("====================");
		System.out.println("ID : " + temp.id);
		System.out.println("====================");
		for (int i = 0; i < temp.accCnt; i++) {
			System.out.println("accNumber:" + temp.acc[i].accNumber + " / money: " + temp.acc[i].money);
		}
		System.out.println("=============================\n");

	}
}
