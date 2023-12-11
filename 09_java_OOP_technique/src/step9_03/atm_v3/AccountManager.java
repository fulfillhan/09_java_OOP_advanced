package step9_03.atm_v3;


import java.util.Random;
import java.util.Scanner;

public class AccountManager {
    Random ran = new Random();
    Scanner scan =  new Scanner(System.in);

    private AccountManager(){}
    private static AccountManager instance = new AccountManager();
    public static AccountManager getInstance(){
        return instance;
    }
    UserManager um = UserManager.getInstance();


    //계좌 생성하기
    void createAccount(){
       User loginUser =um.userList[um.identifier];

       if (loginUser.accCnt == 3){
           System.out.println("[메세지]더 이상 계좌를 생성할 수 없습니다.\n");
           return;
       }
       // 유저당 소지한 계좌의 갯수가 0개
       if (loginUser.accCnt == 0){
           loginUser.accList = new Account[1];
           loginUser.accList[um.userCnt] = new Account();
       }
       // 계좌의 갯수가 1개 이상
       else if (loginUser.accCnt > 0) {
           Account[] temp = loginUser.accList;
           loginUser.accList = new Account[loginUser.accCnt+1];
           for (int i = 0; i < loginUser.accCnt; i++) {
               loginUser.accList[i] = temp[i];
           }
           temp = null;
        }
            loginUser.accList[loginUser.accCnt] = new Account();

        String makeAccount = ran.nextInt(90000)+10000+"";
        loginUser.accList[loginUser.accCnt].number = makeAccount;
        loginUser.accList[loginUser.accCnt].money = 0;

        loginUser.accCnt++;
        System.out.println("[메세지]계좌가 생성되었습니다.\n");

        //파일 메니저 setData 생성
        }

        //계좌를 보여주는 메서드?
        int showAccList(String msg){
        int loginAccIdx = -1;
        User loginUser =um.userList[um.identifier];

        //로그인한 유저의 계좌 갯수가 0보다 클때 
        if (loginUser.accCnt > 0){
            for (int i = 0; i < loginUser.accCnt; i++) {
                System.out.println("[" + (i+1) + "]" + loginUser.accList[i].number);
            }
            System.out.println("[" + msg + "]내 계좌를 메뉴에서 선택하세요 : ");
            loginAccIdx =scan.nextInt();
            loginAccIdx--;
        	}
        	return loginAccIdx;
        }	
        void deleteAcc(){

        	System.out.print("삭제할 계좌 번호를 입력하세요 : ");
            String delAccNum = scan.next();
        }

    }
