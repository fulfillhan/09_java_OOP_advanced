package step9_03.atm_v3;

public class User {

    String id;
    String pw;
    Account[] accList;
    int accCnt;

    //한 사용자가 가지고있는 계좌정보 출력하기
    void printOneUserAllAccount(){
        //계좌가 0개 일때
        if (accCnt == 0){
            System.out.println(id+"\t"+ pw + "\t님 계좌를 개설해주세요");
        }
        // 그 이상일때
        else if (accCnt > 0) {
            System.out.println(id+"\t"+pw+"\t");
            for (int i = 0; i < accCnt; i++) {
                System.out.println(accList[i].number+"\t"+accList[i].money+"원");
            }
            System.out.println();
        }
    }

    User(String id, String pw){
        this.id = id;
        this.pw = pw;
    }



}
