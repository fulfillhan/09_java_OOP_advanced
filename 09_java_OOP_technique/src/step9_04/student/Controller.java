package step9_04.student;

public class Controller {
	
	private StudentDAO stDAO;
	private StudentInsert insert;
	private StudentSelect select;	
	private StudentSelectAll selectAll;
	
	public Controller() { // 기본 생성자로 하고
		//각각의 맴버 변수 초기화 
		stDAO = new StudentDAO();// StudentDAO객체를 stDAO할당하고
		insert = new StudentInsert(stDAO);//이를 이용해서 각 객체들을 생성하여 생성자에게 전달
		select = new StudentSelect(stDAO);
		selectAll = new StudentSelectAll(stDAO);
	}
	
	public StudentDAO getStDAO() {
		return stDAO;
	}
	
	public void setStDAO(StudentDAO stDAO) {
		this.stDAO = stDAO;
	}
	
	public StudentInsert getInsert() {
		return insert;
	}
	
	public void setInsert(StudentInsert insert) {
		this.insert = insert;
	}
	
	public StudentSelect getSelect() {
		return select;
	}
	
	public void setSelect(StudentSelect select) {
		this.select = select;
	}
	
	public StudentSelectAll getSelectAll() {
		return selectAll;
	}
	
	public void setSelectAll(StudentSelectAll selectAll) {
		this.selectAll = selectAll;
	}
	
}
