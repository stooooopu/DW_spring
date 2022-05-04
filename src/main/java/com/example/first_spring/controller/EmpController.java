package com.example.first_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/emp/1")
	public EmpVO callEmp() {
		return empService.getEmp();
	}
}
