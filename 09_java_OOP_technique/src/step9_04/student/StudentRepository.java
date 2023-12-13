package step9_04.student;

import java.util.HashMap;

//hash-map사용으로 데이터 저장 
//private static HashMap<String, StudentVO> stDB = new HashMap<>();
//static 사용 : 변수(정적변수)와 메서드(정적메서드) 사용가능. 모두 클래스 자체에 속함.
//-> 객체의 인스턴스 생성하지 않고, 클래스로 바로 접근가능.-> 공통된 데이터와 메서드 관리하고 공유 가능

public class StudentRepository {

  private static HashMap<String,StudentVO> stDB = new HashMap<String, StudentVO>();
  
  //제어자 private 사용으로
  //getter 생성자
  public static HashMap<String, StudentVO> getStDB() {
      return stDB;
  }	

  //setter 생성자 : 외부에서 들어온 학생 정보를 설정할 수 있다.
  public static void setStDB(HashMap<String, StudentVO> stDB){
      StudentRepository.stDB = stDB;
  }

}
