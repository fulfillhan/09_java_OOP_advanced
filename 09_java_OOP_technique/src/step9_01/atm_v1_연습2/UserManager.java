package step9_01.atm_v1_연습2;

import java.util.Scanner;

public class UserManager {
    // 유저 관리용 클래스
    Scanner scanner = new Scanner(System.in);
    User[] user = null;
    int userCnt = 0;

    //메서드 : addUser, logUser, leaveUser, printAllUser

    User getuser(int identifier) {
        return user[identifier];
    }

    public void addUser() {
        // 회원가입시
        System.out.print("[가입] 아이디를 입력하세요 : ");
        String id = scanner.next();

        // 조건 :  1. 유저가 0이였을때 2. 유저가 1이상이였을 때
        if (userCnt == 0) {
            user = new User[1];// 크기가 +1인 user배열 선언
            user[userCnt] = new User();//마지막 자리에 객체 생성
            user[userCnt].id = id;// 입력한 id 값 저장
            System.out.println("[메시지] ID : " + id + "님, 가입되었습니다.\n");
            userCnt++;

        } else {
            // 중복 확인
            boolean isDuple = false;
            for (int i = 0; i < userCnt; i++) {
                if (user[i].id.equals(id)) {
                    isDuple = true;
                }

            }
            if (!isDuple) {
                User[] temp = user;
                user = new User[userCnt + 1];
                for (int i = 0; i < userCnt; i++) {
                    user[i] = temp[i]; // 기존배열이 저장된 temp배열을 user[i]저장
                }
                user[userCnt] = new User();
                user[userCnt].id = id;
                System.out.println("[메시지] ID : " + id + "님, 가입되었습니다.\n");
                userCnt++;
            } else System.out.println("[메시지] ID : " + id + "은 이미 가입된 아이디 입니다.\n");
        }
    }

    public int logUser() {
        //로그인 유무확인 메서드
        System.out.print("[로그인] id를 입력하세요 : ");
        String id = scanner.next();
        int identifier = -1;
        for (int i = 0; i < userCnt; i++) {
            if (user[i].id.equals(id)) {
                identifier = i; // 중복되어 로그인 되어 있는 상태의 인덱스값을 identifier저장
                System.out.println("[" + id + "] 님 로그인 되었습니다.\n");
            }
        }
        return identifier;
    }

    public void leave() {
        int identifier = -1;

        System.out.print("[입력] 삭제할 아이디를 입력하세요 : ");
        String id = scanner.next();

        // 먼저 중복하기!
        for (int i = 0; i < userCnt; i++) {
            if (id.equals(user[i].id)) {
                identifier = i; // 삭제할 요소의 인덱스 i 값을 identifier 로 저장
            }
        }
        if (identifier == -1) {
            System.out.println("[메시지] 아이디를 다시 확인해주세요.\n");
            return;
        }
        System.out.println("ID : '" + user[identifier].id + "' 가 탈퇴되었습니다.\n");

        User[] temp = user;
        user = new User[userCnt - 1];

        for (int i = 0; i < identifier; i++) {
            user[i] = temp[i];
        }
        for (int i = identifier; i < userCnt - 1; i++) {
            user[i] = temp[i + 1];
        }

        userCnt--;
    }
}




