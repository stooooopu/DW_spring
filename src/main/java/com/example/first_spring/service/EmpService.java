package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.first_spring.mapper.EmpMapper;
import com.example.first_spring.vo.EmpVO;
import com.example.first_spring.vo.UserVO;

@Service
public class EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	public List<EmpVO> getAllempList(){
		return empMapper.getEmpList();
	}
	
	public EmpVO getEmp(int empNo) {
		return empMapper.getEmp(empNo);
	}
	
	public List<EmpVO> getDoubleLName(){
		return empMapper.getdoubleLName();
	}
	
	public List<EmpVO> getComm(){
		return empMapper.getComm();
	}
	
	public List<EmpVO> getHiredate() {
		
		return empMapper.getHiredate();
	}
	
	
	
	// 문제 0.
	public List<EmpVO> getSalDeptno(int sal){
		return empMapper.getSalDeptno(sal);
	}
	
	// 문제 1.
	public List<EmpVO> getNotMgr(){
		return empMapper.getNotMgr();
	}
	
	// 문제 2.
	public List<EmpVO> getHiredateYear(String year){
		return empMapper.getHiredateYear(year);
	}
	
	// 문제 3.
	public List<EmpVO> getMaxSal(String month) {
		// 문제 3. 급여가 가장 높은 사원 조회
		List<EmpVO> list = empMapper.getMaxSal(month);
		List<EmpVO> newList = new ArrayList<EmpVO>();
		int max = 0;
		int index = 0;
		
		for(int i=0; i<list.size(); i++) {
			int sal = list.get(i).getSal();
			if(max < sal) {
				max = sal;
				index = i;
			}
		}
		newList.add(list.get(index));
		return newList;
	}
	
	// 문제 4.
	public EmpVO getFirstHiredate(String job) {
		//문제 4. 입사날짜 빠른 사원 조회 (쿼리 2개필요 OR 쿼리하나로 해결 가능)
		return empMapper.getFirstHiredate(job);
	}
	
	// 문제 5.
	public EmpVO getEmpnoAllData(int empno) {
		return empMapper.getEmpnoAllData(empno);
	}
	
	
	// insert와 update는 비슷
	@Transactional(rollbackFor = {Exception.class})
	public int setEmp(EmpVO vo) {
		int rows = empMapper.insertEmp(vo); // 총 몇 행이 insert되었는지 return
		return rows;
	}
	
	
	
	// {NullPointerException.class}) : null일 때만 오류 잡기 
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpRemoveCount(int empno) { // 총 몇 행이 delete되었는지 return
		return empMapper.deleteEmp(empno);
	}
	
//	public int getEmpUpdateCount(EmpVO vo) { // 총 몇 행이 update되었는지 return
//		return empMapper.updateEmp(vo);
//	}
	
	
	// sql결과가 없으면 null뜸
	// 강제로 오류를 낸 결과
	// 이전 commit한 시점으로 돌아감
	// rollbackFor : 이전 history로 넘어감
	@Transactional(rollbackFor = {Exception.class})
	public int getEmpUpdateCount(EmpVO vo) { 
		int rows = empMapper.updateEmp(vo);
		
		// 밑에 결과는 error
		UserVO user = null;
		String name = user.getName();
		System.out.println(name);
		// 현재 서버는 에러 -> 하지만 데이터는 업데이트는 됨
		// status: 500 (자바에서 에러)
		// trace에서 맨 첫줄을 해석
		return rows;
	}
		
	@Transactional(rollbackFor = {Exception.class})
	public List<EmpVO> getJobManager(String job, int sal){
		//getJobManager의 결과를 list에 담음 -> 이미 걸러진 결과에 for문으로 set만 해줌
		List<EmpVO> list =empMapper.getJobManager(job, sal);
		
		int comm = 500;
		int rows = 0;
		
		for(int i=0; i<list.size(); i++) {
			// 만약 이전에 comm을 받았던 사람에게 추가 500을 comm으로 주려면
			int PlusComm = list.get(i).getComm();
			list.get(i).setComm(comm + PlusComm);
			EmpVO vo = list.get(i);
			
			// update가 실행될 때마다 rows 증가
			rows += empMapper.updateEmp(vo);
		}
		
		if(rows > 0) {
			return empMapper.getJobManager(job, sal);
		}
		return null;
	}
	
	
	
	@Transactional(rollbackFor = {Exception.class})
	public int setNotDeptno(EmpVO vo) {
		int deptno = empMapper.getDeptno();
		vo.setDeptno(deptno);
		return empMapper.insertNotDeptno(vo);
	}
	
	
	@Transactional(rollbackFor = {Exception.class})
	public int deletePracSal(int empno) {
		EmpVO vo = empMapper.getPracSal(empno);
		if(vo.getSal()>=3000) {
			return empMapper.deleteEmp(empno);
		}
		return 0;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public int getCountAName(String firstWorld) {
		List<EmpVO> list = empMapper.getCountAName(firstWorld);
		for(int i=0; i<list.size(); i++) {
			String name = list.get(i).getEname();
			System.out.println(name);
		}
		return empMapper.getCountAName(firstWorld).size();
	}
}
