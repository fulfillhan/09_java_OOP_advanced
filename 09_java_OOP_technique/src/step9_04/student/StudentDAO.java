package step9_04.student;

import java.util.Map;

public class StudentDAO { // 데이터 저장소에 접근하여 학생 정보를 관리하는 메서드(Database Access Object)
    //'StudentRespository' 에 저장된 학생 정보 추가,조회하거나 전체학생 데이터를 얻을 수 있다.


    //학생 정보를 저장하는 HasMap에 학생의 id를 key로 하여 외부의 st정보를 추가하는 메서드
    public void insert(StudentVO st){
        StudentRepository.getStDB().put(st.getId(),st);
     }

    public StudentVO select(String id){
    //학생정보를 저장한 hashMap에서 주어진 id에 해당하는 학생의 정보를 조회하여 반환한다.
        return StudentRepository.getStDB().get(id);
    }
    public Map<String,StudentVO> getStudentDB(){
     // 전체 학생 정보의 얻어내는 HashMap 자체를 반환
     return StudentRepository.getStDB();
    }
}
