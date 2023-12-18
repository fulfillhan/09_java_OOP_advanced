package step9_01.atm_v1_연습2;

public class User {

    String id;
    int accCnt = 0;
    Account[] accList;// Account 객체 배열 선언(해당 클래스를 배열로 가져와서 사용한다)

    void pintAccount(){
        for (int i = 0; i < accCnt; i++) {
            accList[i].printOwnAccount(); // 순환하여 Account 배열을 순환한다.
        }
    }

}
