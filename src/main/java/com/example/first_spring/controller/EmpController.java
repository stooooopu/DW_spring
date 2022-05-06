package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.service.EmpService;
import com.example.first_spring.vo.EmpVO;

// html에 결과를 보낼게 아니고 화면에서 json으로만 볼 것이기 때문에 restcontroller사용
@RestController
public class EmpController {

	@Autowired
	private EmpService empService;
	
	@GetMapping("/emp")
	public List<EmpVO> callEmpList(){
		return empService.getAllempList();
	}
	// 현재 @GetMapping("/emp/1")에 1을 사원번호 파라미터로 넘겨주기
	// {} 안에 있는 값을 파라미터로 넘기겠다
	@GetMapping("/emp/no/{empNo}")
	// 이때 중괄호 안에있는 변수이름과 @PathVariable("변수이름")은 같아야함
	public EmpVO callEmp(@PathVariable("empNo") int empNo) {
		return empService.getEmp(empNo);
	}
	
	@GetMapping("/emp/name")
	public List<EmpVO> callDoubleLName(){
		return empService.getDoubleLName();
	}
	
	@GetMapping("/emp/comm")
	public List<EmpVO> callEmpComm(){
		return empService.getComm();
	}
	
	@GetMapping("/emp/hiredate")
	public List<EmpVO> callHiredate(){
		return empService.getHiredate();
	}
	
	@GetMapping("/emp/job/{jobName}/sal/{sal}")
	public List<EmpVO> callJobManager(@PathVariable("jobName") String job,
			@PathVariable("sal") int sal) {
		return empService.getJobManager(job, sal);
	}
	
	//문제 0. 급여 1500을 파라미터로 받고 부서가 10, 30에 속하는 사원 중 급여가 1500을 넘는 사원의 이름 및 급여 조회.
	@GetMapping("/emp/sal/{sal}")
	public List<EmpVO> callSalDeptno(@PathVariable("sal") int sal) {
		return empService.getSalDeptno(sal);
	}
	//문제 1. emp에서 사수가 없는 사원 조회
	@GetMapping("/emp/mgr")
	public List<EmpVO> callNotMgr(){
		return empService.getNotMgr();
	}
	//문제 2. 1987년도를 파리미터로 받고 해당 년도에 입사한 사원 조회 
	@GetMapping("/emp/hiredate/year/{year}")
	public List<EmpVO> callHiredateYear(@PathVariable("year") String year){
		return empService.getHiredateYear(year);
	}
	//문제 3. 12월를 파라미터로 받고 해당 월에 입사한 사원 중 급여가 가장 많은 사원 조회
	@GetMapping("/emp/hiredate/month/{month}")
	public List<EmpVO> callMaxSal(@PathVariable("month") String month) {
		return empService.getMaxSal(month);
	}
	//문제 4. MANAGER를 파라미터로 받고 job이 MANAGER 중 입사날짜가 가장 빠른 사원의 이름, 입사날짜, 급여 조회 
	@GetMapping("/emp/job/{jobName}")
	public EmpVO callFirstHiredate(@PathVariable("jobName") String job) {
		
		return empService.getFirstHiredate(job);
	}
	//(join 문제)*문제 5. 사원번호 7782를 파라미터로 받고 해당 사원의 모든 정보(부서번호, 부서이름, 부서위치 포함) 조회
	@GetMapping("/emp/empno/{empno}")
	public EmpVO callEmpnoAllData(@PathVariable("empno") int empno) {
		return empService.getEmpnoAllData(empno);
	}
	
}
