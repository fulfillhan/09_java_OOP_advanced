package step9_02.atm_v2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

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
				//계좌의 회수가 1개 이상이라면
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
			 int j = 0;  //um.userCnt 의 인덱스
			 for (int i = 0; i < temp.length; i+=4) {  //temp배열에서 현재 처리중인 라인의 인덱스
				
				 String id = temp[i]; //id = temp[0],temp[3]
				 String pw = temp[i+1];//pw = temp[1],temp[4]
				 int accCnt = Integer.parseInt(temp[i+2]);//accCnt = temp[2],temp[5]
				 
				 um.userList[j].id = id;
				 um.userList[j].pw = pw;
				 um.userList[j].accCnt = accCnt;
				 
				 String accInfo = temp[i+3];  // 계좌 정보
				 if (accCnt == 1) {
					 String[] temp1 = accInfo.split("/");
					 
					 // 계좌와 돈 초기화
					 String acc = temp1[0];
					 int money = Integer.parseInt(temp[1]);
					 
					 um.userList[j].acc[0]= new Account(); // 특정 사용자의 첫번째 계정 초기화
					 um.userList[j].acc[0].accNumber =  acc;
					 um.userList[j].acc[0].money = money;
				
				}
				 else if (accCnt > 1) {
					String[] temp1 = accInfo.split(",");
					
					for (int k = 0; k < temp1.length; k++) {
						String[] parse = temp[k].split("/");
						String acc = parse[0];
						int money = Integer.parseInt(parse[1]);
						
						um.userList[j].acc[k] = new Account();
						um.userList[j].acc[k].accNumber = acc;
						um.userList[j].acc[k].money = money;
					}
				}
				 j++;
				 
			}
		}   //파일이 존재 하지 않다면
				else {
					setData();//데이터 설정메서드 호출
					save();//저장하기 메서드 호출
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (br != null) {try {br.close();} catch (IOException e) {}}
				if (fr != null) {
					try {fr.close();} catch (IOException e) {}}
			}
		}
}
