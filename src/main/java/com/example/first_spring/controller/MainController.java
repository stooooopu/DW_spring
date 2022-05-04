package com.example.first_spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first_spring.service.MainService;
import com.example.first_spring.vo.UserVO;

// templetController를 사용한다면 controller
// @Controller : 얘는 url을 요청받는 곳이라고 정해주는 것, import필요



@RestController  // templetController 사용 x, 결과를 빨리 보기 위해서 RestController씀
public class MainController {
// 아직 DB연동을 안해서 String값을 리턴해 줄것
// 어노테이션을 적으면 메인함수에서 따로 인스턴스화 안해도 알아서 다 해줌  

	// 전역변수로 사용 가능
	@Autowired
	private MainService service; // import필요
	
	@GetMapping("/index") // /index라는 url(주소)요청이 오면 call()메소드를 실행할게!
	public String call() {
		String word = "정지유 Hello world";
		return word;
	}
	
	@GetMapping("/sum")
	public int callSum() {
		int x = 1000; 
		int y = 200;
		return x + y;
	}
	
	@GetMapping("/stopu")
	public String callName() {
		String name = "Hey 모두들 안녕 내가 누군지 아니?";
		return name;
	}

	@GetMapping("/user")
	public UserVO callUser() {
		UserVO vo = new UserVO("정지유",20,"대전");
		return vo;
	}
	
	@GetMapping("/userlist")
	public List<UserVO> callList(){
		// MainService의 getUserList의 return : List
		// MainController의 callList의 return : List
		// 두개의 return타입이 같아서 
		// 그래서 return에 service.getUerList()가능
		return service.getUserList();
	}
}
