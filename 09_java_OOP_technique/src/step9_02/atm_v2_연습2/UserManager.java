package step9_02.atm_v2_연습2;

import java.util.Scanner;

import javax.xml.transform.Templates;

public class UserManager {
    Scanner scanner = new Scanner(System.in);

    private UserManager (){} // 외부에서 직접 인스턴스 생성방지 = private
    private static UserManager instance = new UserManager();//인스턴스를 저장할 정적 변수
    public static UserManager getInstance(){
        return instance;
    }

    User[] user = null;
    int userCnt = 0;
    final int ACC_MAX_CNT = 3;// 상수 :  한번 값을 저장하면 변경 불가
    
    void printAllUser() {
    	for (int i = 0; i < userCnt; i++) {
			System.out.println((i+1)+"ID("+user[i].id+")/tPw("+user[i].pw+")\t");
			if (user[i].accCnt != 0) {
				for (int j = 0; j < user[i].accCnt; j++) {
					System.out.println("("+user[i].acc[j].accNumber+" : "+ user[i].acc[j].money+")");
				}
			}
			System.out.println();
		}
    }


    boolean checkId(String id){

        boolean isDuple = false;
        for (int i = 0; i < userCnt; i++) {
            if (id.equals(user[i].id)){
                isDuple = true;
            }
        }
        return isDuple;
    }

    boolean getCheckAcc(String accountNum){ 
        boolean isDuple = false;
        for (int i = 0; i < userCnt; i++) {
            for (int j = 0; j < user[i].accCnt; j++) {
                if (accountNum.equals(user[i].acc[j].accNumber)){
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
        boolean checkId = checkId(myId);

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
            user[userCnt] = new User();

            for (int i = 0; i < userCnt; i++) {
                user[i] = temp[i];
            }
            temp = null;
        }
            user[userCnt].id = myId;
            user[userCnt].pw = myPw;

        System.out.println("[메시지] "+ user[userCnt].id + "님 회원가입을 축하드립니다.\n");
        userCnt++;

        FileManager.getInstance().save();

    }

    int logUser(){ 
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
    int deleteUser(int identifier) {
    	
    	if (identifier == -1) {
			System.out.println("[메시지] 로그인 후 이용하세요!\n");
		}
    	else {// 로그인이 된 상태라면
    		
			User[] temp = user;
			user = new User[userCnt - 1];
			
			int j = 0;
			for (int i = 0; i < userCnt; i++) {// 삭제하려는 위치가 해당 배열에 없어야함
				if(i != identifier) {
				user[j] = temp[i];
				j++;
				}
			}
    		temp=null;
    		userCnt--;
    		
		}
    	System.out.println("[메시지] 탈퇴되었습니다!\n");
    	identifier = -1;//탈퇴하자마자 로그아웃 됨
    	FileManager.getInstance().save();
    	return identifier;
    }




}
