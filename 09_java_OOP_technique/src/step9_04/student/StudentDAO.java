package step9_04.student;

import java.util.Map;

public class StudentDAO {// Database Access Object 디비 접근 클래스 : 저장송 입출력
	
	public void insert(StudentVO st) {
		StudentRepository.getStDB().put(st.getId(), st);
	}
	
	public StudentVO select(String id) {
		return StudentRepository.getStDB().get(id);
	}
	
	public Map<String , StudentVO> getStudentDB(){
		return StudentRepository.getStDB();
	}

}
