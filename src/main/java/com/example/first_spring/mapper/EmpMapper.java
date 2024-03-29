package com.example.first_spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.first_spring.vo.EmpVO;

/**
 * @author 21
 * comment : emp테이블 전체사원 조회
 */
@Mapper
public interface EmpMapper {
	// emp테이블의 데이터를 list로 받아옴
	// mybatis에 이 메소드를 알려줘야함
	public List<EmpVO> getEmpList();
	public EmpVO getEmp(int empNo);	
	public List<EmpVO> getdoubleLName();
	public List<EmpVO> getComm();
	public List<EmpVO> getHiredate();
	
	// 문제 0.
	public List<EmpVO> getSalDeptno(@Param("sal") int sal);
	
	// 문제 1.
	public List<EmpVO> getNotMgr();
	
	// 문제 2.
	public List<EmpVO> getHiredateYear(String year);
	
	// 문제 3.
	public List<EmpVO> getMaxSal(String month);
	
	// 문제 4.
	public EmpVO getFirstHiredate(String job);
	
	// 문제 5.
	public EmpVO getEmpnoAllData(int empno);
	
	// 삽입과 수정은 객체를 파라미터로 넘김
	
	public int insertEmp(EmpVO empVo); // 데이터 삽입
	
	
	public int deleteEmp(int empno); // 데이터 삭제
	
	
	// 복수일 경우 mybatis에서 찾지를 못해서 파라미터를 밑에처럼 받아야 함
	public List<EmpVO> getJobManager(
			@Param("job") String job,
			@Param("sal") int sal
			);
	
	public int getDeptno();
	public int insertNotDeptno(EmpVO vo);

	public EmpVO getPracSal(int empno);
	public int deletePracSal(int empno);
	
	public List<EmpVO> getCountAName(String firstWorld);
	
	public List<EmpVO> selectEmpMgr(@Param("isMgr") String isMgr);
	
	
	public int updateEmp(EmpVO empVo); // 데이터 수정
	public int updateEmpno(EmpVO vo);
	
	public List<EmpVO> getEmpComm(String isComm);
	
	public EmpVO selectEmpCommSal(@Param("empno") int empno);
	public int updateEmpSal(EmpVO vo);
	
	public List<Map<String,Object>> selectEmpMapList();
	
	public int updateApi(EmpVO vo);
	
}
