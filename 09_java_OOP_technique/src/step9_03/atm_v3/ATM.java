package step9_03.atm_v3;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ATM {
    Scanner scan = new Scanner(System.in);

    void showMenu(){
        while (true){

            printAllDataByUser();

            System.out.println("[MEGA ATM]");
            System.out.println("[1]회원가입\n[2]로그인\n[0]종료");
            System.out.print("메뉴를 선택하세요 : ");
            int sel = scan.nextInt();

            //회원 관리
            if (sel == 1){
                join();
            }
            else if (sel == 2) {
                login();
            }
            else if (sel == 0) {
                System.out.println("==프로그램 종료==");
                break;
            }
        }
    }
    void printAllDataByUser(){
        UserManager.getInstance().printAllUserInfo();
      }
    void join(){
        UserManager.getInstance().joinUser();
    }
    void login(){
        UserManager.getInstance().loginUser();
     }
}
