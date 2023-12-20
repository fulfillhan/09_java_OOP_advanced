package step9_02.atm_v2_연습2;

import java.util.Scanner;

public class UserManager {
    Scanner scanner = new Scanner(System.in);

    private UserManager (){} // 외부에서 직접 인스턴스 생성방지 = private
    private static UserManager instance;//인스턴스를 저장할 정적 변수
    public static UserManager getInstance(){
        return instance;
    }

    User[] user = null;
    int userCnt = 0;
    final int ACC_MAX_CNT = 3;// 상수 :  한번 값을 저장하면 변경 불가


    boolean checkId(String id){

        boolean isDuple = false;
        for (int i = 0; i < userCnt; i++) {
            if (id.equals(user[i].id)){
                isDuple = true;
            }
        }
        return isDuple;
    }

    boolean getCheckAcc(String AccountNum){
        boolean isDuple = false;
        for (int i = 0; i < userCnt; i++) {
            for (int j = 0; j < user[i].accCnt; j++) {
                if (AccountNum.equals(user[i].acc[j].accNumber)){
                    isDuple = true;
                }
            }
        }
        return isDuple;
    }
    void joinMember(){

        System.out.print("[회원가입] 이이디를 입력하세요 : ");
        String myId = scanner.next();
        System.out.print("[회원가입] 패스워드를 입력하세요 : ");
        String myPw = scanner.next();

        //아이디 중복확인(참, 거짓 조건으로)
        boolean checkId = getCheckAcc(myId);

        if (checkId){ //true 이면 -> 중복된다는것
            System.out.println("[메시지] 아이디가 중복됩니다.\n");
            return;
        }
        if (userCnt == 0){
            user = new User[1];
            user[userCnt] = new User();
        }
        else {
            User[] temp = user;
            user = new User[userCnt+ 1];
            for (int i = 0; i < userCnt; i++) {
                user[i] = temp[i];
            }
            temp = null;
        }
            user[userCnt] = new User();
            user[userCnt].id = myId;
            user[userCnt].pw = myPw;

        userCnt++;
        System.out.println("[메시지] "+ user[userCnt].id+ "님 회원가입을 축하드립니다.\n");

        //파일 저장하기

    }

    int logUser(){ //-> 여기서부터 다시하기!!
        //로그인가능한 유저인지 검사 메서드
        int identifier = -1;

        System.out.print("[로그인] 아이디를 입력하세요 : ");
        String myId = scanner.next();
        System.out.print("[로그인] 패스워드를 입력하세요 : ");
        String myPw = scanner.next();

        for (int i = 0; i < userCnt; i++) {
            if (myId.equals(user[i].id)){
                //중복된다면
                identifier = i;// 중복 인덱스 값
            }
        }
        return identifier;

    }


}
