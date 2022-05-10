package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	// empTable에 insert
	@PostMapping("/emp")
	// count해주기 때문에 return intType
	// @RequestBody : 파라미터로 넘어오는 VO를 대신 new해줌
	public int callEmpSet(@RequestBody EmpVO empVo) {
		System.out.println("사원이름은 : "+empVo.getEname());
		System.out.println("사원번호는 : "+empVo.getEmpno());
		System.out.println("직업은 : "+empVo.getJob());
		System.out.println("사수번호는 : "+empVo.getMgr());
		System.out.println("입사날짜는 : "+empVo.getHiredate());
		System.out.println("급여는 : "+empVo.getSal());
		System.out.println("보너스는 : "+empVo.getComm());
		System.out.println("부서번호는 : "+empVo.getDeptno());
		return empService.setEmp(empVo);
	} // 이렇게 받은걸 제이쿼리로 보냄
	
	// @DeleteMapping : 자원 삭제
	@DeleteMapping("/emp/empno/{empno}")
	public int callEmpRemove(@PathVariable("empno") int empno) {
		return empService.getEmpRemoveCount(empno);
	}
	
	// @PatchMapping : 자원 수정
	@PatchMapping("/emp")
	public int callEmpUpdate(@RequestBody EmpVO empVo) {
		return empService.getEmpUpdateCount(empVo);
	}
	
	// job = MANAGER / sal >=2500 사원의 comm 500으로 update
	// 이름 직업 커미션 조회
	@GetMapping("/emp/job/{jobName}/sal/{sal}")
	public List<EmpVO> callJobManager(@PathVariable("jobName") String job,
			@PathVariable("sal") int sal) {
		
		return empService.getJobManager(job, sal);
	}	
	
	
	// QueryString으로 getMapping
	@GetMapping("/tier")
	// 파라미터로 ? 를 받을거임
	// tier?region=kr (region에 kr을 대입하겠다)
	// name도 함께 받고싶으면 주소에 &로 표시 (파라미터로 name을 받아줘야 함) 
	// tier?region=kr&name=jiyoo
	// 검색할 때 많이 사용
	public String callTier(@RequestParam("region") String region, @RequestParam("name") String name) {
		return region+" ,"+name;
	}
	
	@GetMapping("/board")
	// 현재 내가 클릭한 pageNum이 옴
	// 게시판마다 한 페이지에 보여주는 행의 수가 다름
	// board?page=1&pageSize=10
	// 작성자 검색
	// board?page=1&pageSize=10&writer=정지유
	// pageSize는 query로 limit을 사용 원하는 만큼 보여줌 하지만 우선 모든 정보를 조회한 뒤 자른 것임
	// 만일 데이터가 많다면 between으로 처음부터 지정 해 줘서 원하는 결과만 사용
	public int cllBoard(@RequestParam("page")int page, 
			@RequestParam("pageSize")int pageSize, @RequestParam("writer")String writer) {
		System.out.println("현재 페이지는? "+page);
		System.out.println("한 페이지에 보여주는 row수는? "+pageSize);
		System.out.println("작성자는? "+writer);
		return 0;
	}
		
	
	// 문제 1. A로 시작하는 사람 수 구하기
	@GetMapping("/emp/names")
	// /emp/name?firstWorld=A
	public int callCountAName(@RequestParam("firstWorld")String firstWorld) {
		return empService.getCountAName(firstWorld);
	}

	
	
	@PostMapping("/emp/empty/deptno")
	public int callNotDeptno(@RequestBody EmpVO vo) {
		return empService.setNotDeptno(vo);
	}
	
	@DeleteMapping("/emp/prac/empno/{empno}")
	public int deletePracSal(@PathVariable("empno")int empno) {
		return empService.deletePracSal(empno);
	}
	
	
	
}
