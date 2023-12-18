package step9_04.student;

import java.util.Map;

public class StudentSelectAll {
	
	private StudentDAO studentDAO; // StudentDAO 객체 사용하여 해당 클래스에서 사용
	
	public StudentSelectAll(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	
	public Map<String , StudentVO> allSelect(){
		return studentDAO.getStudentDB();
	}
	
	
	public void printAll() {
		
		Map<String , StudentVO> map = studentDAO.getStudentDB();
		
		for (String key : map.keySet()) {
			map.get(key).printOneInfo();
		}
		
	}
	
}
