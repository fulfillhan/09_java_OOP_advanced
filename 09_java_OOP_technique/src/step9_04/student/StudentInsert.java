package step9_04.student;
public class StudentInsert {
	
	private StudentDAO studentDAO;// 맴버변수 : 외부에서 들어온 'StudentDAO' 객체 참조
	
	//생성자 만들기 :  외부에서 들어온 StudentDAO 객체를 받아와서 클래스내의 맴버변수에 할당하는 생성자
	public StudentInsert(StudentDAO stDAO) {
		this.studentDAO = stDAO;
	}	
	
	public void insert(StudentVO studentVO) { // StudentVO객체를 매개변수로 가지고와서
		String id = studentVO.getId();//sutdentVO에서 id를 가져와 id변수에 할당
		if (checkId(id)) {//checkId메서드를 통해 id중복 여부 확인 (true = null/중복이 아니다)
			studentDAO.insert(studentVO);// 만약 중복되지 않으면 StudentDAO를 통해 insert매서드 호출하여 학생 정보 삽입
		}
		else {//false: 중복이다
			System.out.println("중복아이디 입니다");
		}
	}
	
	
	public boolean checkId(String id) {
		StudentVO studentVO = studentDAO.select(id);// id 정보를 가지고와서
		return studentVO == null? true:false;//null이면 true/ !null 이면 false
		//삼항연산자 result = (condition)? value1(true) : value2(false)
	}
	
}
