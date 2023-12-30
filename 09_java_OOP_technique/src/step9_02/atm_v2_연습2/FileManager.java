package step9_02.atm_v2_연습2;
// load메서드 연습이 많이 필요하다. 파일에 저장된 데이터를 하나씩 분리하여 새로운 배열에 넣는 과정..고난이도.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;

public class FileManager {

    private FileManager() {
    }

    private static FileManager instance = new FileManager();

    public static FileManager getInstance() {
        return instance;
    }

    String fileName = "ATM2.txt";
    String data = "";
    UserManager um = UserManager.getInstance();

    void setData() {


        int userCnt = um.userCnt;
        data += userCnt;
        data += "\n";

        for (int i = 0; i < userCnt; i++) {
            data += um.user[i].id;
            data += "\n";
            data += um.user[i].pw;
            data += "\n";
            data += um.user[i].accCnt + "\n";

            //계좌 파일 입력하기
            if (um.user[i].accCnt == 0) {
                data += "0\n";
            } else {
                for (int j = 0; j < um.user[i].accCnt; j++) {
                    data += um.user[i].acc[j].accNumber;
                    data += "/";
                    data += um.user[i].acc[j].money;
                    //마지막 라인에서 마지막 위치에는 "/" 안함
                    if (j != um.user[i].accCnt - 1) {
                        data += ",";
                    }
                }
                data += "\n";
            }

        }
    }

    void save() {
        //fileName에 저장하기
        setData();

        FileWriter fW = null;

        try {
            fW = new FileWriter(fileName);
            fW.write(data);//데이터 쓰기
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fW != null) {
                try {
                    fW.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    void load() {
        //파일에 있는 내용을 가져와서 로드하기
        File file = new File(fileName);
        FileReader fr = null;
        BufferedReader br = null;
        try {
            if (file.exists()) {
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                while (true) {
                    String line = br.readLine();//한 줄을 읽는 라인
                    if (line == null) {
                        break;
                    }
                    data += line;
                    data += "\n";
                }
                //파일에서 유저정보 가져오기
                String[] temp = data.split("\n");//반한값은 배열
                um.userCnt = Integer.parseInt(temp[0]);
                um.user = new User[um.userCnt];

                for (int i = 0; i < um.userCnt; i++) {
                    um.user[i] = new User();
                }
                //기존 temp배열에서 데이터 값을 가지고오기(유저정보)
                // i 는 temp배열 기반으로 정보 추출
                //j 는 새로운 User 객체 복원하기 위한 배열
                int j = 0;
                for (int i = 0; i < temp.length; i += 4) {
                    String id = temp[i];
                    String pw = temp[i + 1];
                    int accCnt = Integer.parseInt(temp[i + 2]);

                    //가져온 유저 정보를 배열에 저장하기
                    um.user[j].id = id;
                    um.user[j].pw = pw;
                    um.user[j].accCnt = accCnt;

                    //남은 파일 data에서 계좌정보 가져오고 배열에 저장하기
                    String accInfo = temp[i + 3];
                    if (accCnt == 1) {// 계좌가 한개라면
                        String[] accTemp = data.split("/");

                        String accNumber = accTemp[0];
                        int money = Integer.parseInt(accTemp[1]);

                        um.user[j].acc[0] = new Account();
                        um.user[j].acc[0].accNumber = accNumber;
                        um.user[j].acc[0].money = money;

                    } else if (accCnt > 1) {// 1개 이상이라면
                        String[] accTemp = data.split(",");
                        // k는 기존 temp의 계좌 정보 추출하기 위한 배열
                        for (int k = 0; k < accTemp.length; k++) {
                            String[] accTemp1 = data.split("/");
                            String accNumber = accTemp1[0];
                            int money = Integer.parseInt(accTemp1[1]);

                            um.user[j].acc[k] = new Account();
                            um.user[j].acc[k].money = money;
                            um.user[j].acc[k].accNumber = accNumber;
                        }
                    }
                    j++;
                }
            } else {//만약 파일이 존재하지 않았다면
                setData();
                save();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
