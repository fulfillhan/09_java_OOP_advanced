package step9_02.atm_v2;

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
		//저장하기 메서드
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
}
