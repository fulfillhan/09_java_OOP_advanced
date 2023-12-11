package step9_03.atm_v3;

import java.util.Scanner;

public class UserManager {
    Scanner scan = new Scanner(System.in);

    private UserManager (){}
    private static UserManager instance = new UserManager();
    public static UserManager getInstance(){
        return instance;
    }

    User[] userList = null;
    int userCnt = 0;
    int identifier = -1;

    void printAllUserInfo(){
        System.out.println("아이/t패스워드/t계좌정보");
        for (int i = 0; i < userCnt; i++) {
            userList[i].printOneUserAllAccount();
        }
        System.out.println("--------------------------");
    }
    int checkId(String id){
        int check = -1;
        for (int i = 0; i < userCnt; i++) {
            if (userList[i].id.equals(id)){ //중복된다면
                check = i; //중복되는 인덱스 i 를 check 에 저장한다.
            }
        }
        return check;
    }

    void joinUser(){
        System.out.print("[가입]아이디를 입력하세요 : ");
        String id = scan.next();

        //아이디를 입력하고나서 유효성 검사
        int checkId = checkId(id); // 중복되는 인덱스 값
        if (identifier != -1){
            System.out.println("[메시지] 아이디가 중복 됩니다.");
            return;
        }
        System.out.print("[가입]비밀 번호를 입력하세요 : ");
        String pw = scan.next();

        // 계좌갯수 확인 후 배열 저장 
        if (userCnt == 0){
            userList = new User[1];
            userList[0] = new User(id,pw);
        } else if (userCnt > 0) {
            User[] temp = userList;

            userList = new User[userCnt+1];
            for (int i = 0; i < userCnt; i++) {
                userList[i]=temp[i];
            }
            userList[userCnt] = new User(id,pw); //  메서드 호출하여 userList[마지막값]에 저장.
            temp = null;
        }
        userCnt++;
        System.out.println("[메세지]회원가입을 완료하였습니다.\n");
        //파일 저장하기
        //FileManager
        printAllUserInfo();

    }
    void loginUser(){
        System.out.print("[로그인]아이디를 입력하세요 : ");
        String id = scan.next();
        System.out.print("[로그인]패스워드를 입력하세요 : ");
        String pw = scan.next();

        for (int i = 0; i < userCnt; i++) {
            if (userList[i].id.equals(id) && userList[i].pw.equals(pw)){
                identifier = i; // 로그인 된 인덱스 : identifier
            }
        }
        if (identifier == -1){
            System.out.println("아이디와 패스워드를 재확인해주세요.");
        }
        else {
            System.out.println(userList[identifier].id+ "님, 환영합니다.");
            accLoginMenu();
        }

    }
    void accLoginMenu(){

        while (true){

            System.out.println("["+ userList[identifier].id+ "님, 로그인]");
            System.out.println("[1]계좌생성 [2]입금하기 [3]출금하기 [4]이체하기 [5]계좌조회 "
                               + "[6]계좌삭제 [7]회원탈퇴 [0]뒤로가기");
            System.out.print("메뉴를 선택하세요 : ");
            int sel = scan.nextInt();

            if (sel == 1){
                AccountManager.getInstance().createAccount();
            } else if (sel == 2) {
            //-> 여기서 부터 하기!!
            } else if (sel == 3) {

            } else if (sel == 4) {

            } else if (sel == 5) {

            } else if (sel == 6) {

            } else if (sel == 7) {

            } else if (sel == 0) {
                System.out.println("==뒤로 가기==");
                //로그아웃
                break;
            }
        }
    }


}
