package step9_02.atm_v2_연습2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.processing.Filer;

public class FileManager {
	
	private FileManager() {}
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
	
	String fileName = "ATM2.txt";
	String data="";
	UserManager um = UserManager.getInstance();
	
	void setData() {
		
		
		int userCnt = um.userCnt;
		data += userCnt;
		data += "\n";
		
		for (int i = 0; i <userCnt; i++) {
			data += um.user[i].id;
			data += "\n";
			data += um.user[i].pw;
			data += "\n";
			data += um.user[i].accCnt+"\n";
			
			//계좌 파일 입력하기
			if (um.user[i].accCnt == 0) {
				data += "0\n";
			}
			else {
				for (int j = 0; j < um.user[i].accCnt; j++) {
					data += um.user[i].acc[j].accNumber;
					data += "/";
					data +=um.user[i].acc[j].money;
					//마지막 라인에서 마지막 위치에는 "/" 안함
					if (j != um.user[i].accCnt-1) {
						data += ",";
					}
				}
				data +="\n";
			}
			
		}
	}
	void save() {
		//fileName에 저장하기
		setData();
		
		FileWriter fW= null;
	
		try {
			 fW = new FileWriter(fileName);
			 fW.write(data);//데이터 쓰기
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
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
			 String[] temp= data.split("\n");//반한값은 배열
			 um.userCnt = Integer.parseInt(temp[0]);
			 //-> 여기서 부터
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	

}
