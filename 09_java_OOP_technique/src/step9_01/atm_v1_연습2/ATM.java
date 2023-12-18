package step9_01.atm_v1_연습2;

//2023-12-18 계좌 삭제부분 정답예시와 다르게 코딩함.

import java.util.Random;
import java.util.Scanner;

public class ATM {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    UserManager userManager = new UserManager();

    int identifier = -1;

    void printMainMenu() {

        while (true) {

            System.out.println("== MEGA ATM ==");
            System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료] : ");
            int sel = scanner.nextInt();

            if (sel == 1) {
                login();
            } else if (sel == 2) {
                logout();
            } else if (sel == 3) {
                join();
            } else if (sel == 4) {
                leave();
            } else if (sel == 0) {
                break;
            }
        }
        System.out.println("[메시지] 프로그램을 종료합니다.");
    }

    public void join() {
        userManager.addUser();
    }

    public void login() {
        identifier = userManager.logUser();
        if (identifier != -1) {
            printAccountMenu();
        } else System.out.println("[메시지] 로그인 실패!\n");
    }

    public void printAccountMenu() {

        while (true) {

            System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
            int selAcc = scanner.nextInt();

            String makeAccount = random.nextInt(90001) + 10000 + "";

            if (selAcc == 1) {
                //계좌의 수가 0일때
                if (userManager.user[identifier].accCnt == 0) {
                    userManager.user[identifier].accList = new Account[1];
                    userManager.user[identifier].accList[0] = new Account();
                    userManager.user[identifier].accList[0].number = makeAccount;
                }
                //계좌의 수가 1개 이상일 때
                else {
                    Account[] temp = userManager.user[identifier].accList;
                    int tempAccount = userManager.user[identifier].accCnt;
                    userManager.user[identifier].accList = new Account[tempAccount + 1];
                    for (int i = 0; i < tempAccount; i++) {
                        userManager.user[identifier].accList[i] = temp[i];
                    }
                    userManager.user[identifier].accList[tempAccount] = new Account();
                    userManager.user[identifier].accList[tempAccount].number = makeAccount;
                }
                System.out.println("[메시지]'" + makeAccount + "'계좌가 생성되었습니다.\n");
                userManager.user[identifier].accCnt++;

            } else if (selAcc == 2) {
                //계좌 삭제(약간의 수정을 함)

                System.out.print("삭제 하고 싶은 계좌 번호를 입력하세요 : ");
                String deleteAccount = scanner.next();
                int tempAccount = userManager.getuser(identifier).accCnt;
                int delIdx = -1;

                for (int i = 0; i < tempAccount; i++) {
                    if (deleteAccount.equals(userManager.getuser(identifier).accList[i].number)) {
                        delIdx = i;
                        break;// 찾으면 루프 종료
                    }
                }
                if (delIdx == -1) {
                    System.out.println("[메시지] 계좌 번호를 재확인 해주세요!\n");
                } else {
                    System.out.println("[메시지] 계좌 번호 : [" + userManager.getuser(identifier).accList[delIdx].number + "] 가 삭제되었습니다.\n");

                    Account[] temp = userManager.getuser(identifier).accList;
                    userManager.getuser(identifier).accList = new Account[tempAccount - 1];

                    int j = 0;
                    for (int i = 0; i < tempAccount; i++) {
                        if (i != delIdx) {
                            userManager.getuser(identifier).accList[j] = temp[i];
                            j++;
                        }

                    }
                    userManager.getuser(identifier).accCnt--;
                }

            } else if (selAcc == 3) {
                if (userManager.user[identifier].accCnt == 0) {
                    System.out.println("[메시지] 생성된 계좌가 없습니다.\n");
                } else userManager.user[identifier].pintAccount();
                System.out.println("\n");

            } else if (selAcc == 0) {
                logout();
                break;
            }
        }

    }

    public void logout() {
        if (identifier == -1)
            System.out.println("[메시지] 로그인 후 이용해주세요!!");
        else {
            System.out.println("[메시지] 로그아웃 되었습니다.");
        }
    }

    public void leave() {
        userManager.leave();
    }

}
