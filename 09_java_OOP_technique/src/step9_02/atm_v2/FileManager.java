package step9_02.atm_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
	
	// 싱글턴 패턴으로 생성자 만들기
		private FileManager() {}
		private static FileManager instance = new FileManager();
		public static FileManager getInstance() {
			return instance;
		}
		
		String fileName = "ATM.txt";
		String data = "";
		UserManager um = UserManager.getInstance();
		
		
		void setData() {
			data = "";
			int userCnt = um.userCnt;
			data += userCnt +"\n";
			
			//userList 배열 데이터 입력하기
			//가입한 사용자와 순회하여 입력+각 사용자의 계좌
			for (int i = 0; i < userCnt; i++) {
				data += um.userList[i].id +"\n";
				data += um.userList[i].pw+ "\n";
				data += um.userList[i].accCnt+ "\n";
				
				//계좌 순회하여 데이터 입력하기
				//계좌의 횟수가 0개라면
				if (um.userList[i].accCnt == 0) {
					data += "0\n";// 기존에 입력한 데이터는 나와야함
				}
				//회수가 1개 이상이라면
				else {
					for (int j = 0; j < um.userList[i].accCnt; j++) {
						data += um.userList[i].acc[j].accNumber;
						data += "/";
						data += um.userList[i].acc[j].money;
						// 마지막 인덱스전까지 쉼표 사용
						if (j != um.userList[i].accCnt-1) {
							data += ",";
						}
					}
					data += "\n";
					
				}
			}
		}
		//저장하기
		void save() {
			
			setData();//setData 메서드 호출
			
			FileWriter fw = null;
			try {
				 fw = new FileWriter(fileName);
				 fw.write(data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (fw != null) {try {fw.close();} catch (IOException e) { e.printStackTrace();}}
			}
		}
		//파일에서 데이터 복원하기
		void load() {
			File file = new File(fileName);
			FileReader fr =null;
			BufferedReader br = null;
			
			try {
				if (file.exists()) {
			 fr = new FileReader(file);
			 br = new BufferedReader(fr);
			 
			 while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				//데이터 복원
				data += line;
				data += "\n";
			}
			 //복원된 데이터로부터 사용자 및 계좌정보 설정
			 //읽어온 데이터를 개행문자를 기준으로 분할하여 배열에 저장한다.
			 String[] temp = data.split("\n"); //반환 :  String타입의 배열
			 um.userCnt = Integer.parseInt(temp[0]);// temp 첫번째 요소은 userCnt 저장
			 um.userList = new User[um.userCnt]; //사용자 수에 맞게 user 배열을 생성.
			 
			 //사용자정보 가져와서 user객체 및 계좌 정보 복원
			 //1.user 객체 복원
			 for (int i = 0; i <um.userCnt; i++) {
				um.userList[i] = new User();
			}
			 // -> 여기서부터 다시 보기!!
			 int j = 0;
			 

			 
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
}
