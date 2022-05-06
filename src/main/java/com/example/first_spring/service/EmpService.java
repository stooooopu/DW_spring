package com.example.first_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first_spring.mapper.EmpMapper;
import com.example.first_spring.vo.EmpVO;

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
	
	public List<EmpVO> getJobManager(String job, int sal){
		if(job.equals("SALESMAN")) {
			return null;
		}
		return empMapper.getJobManager(job, sal);
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
}
