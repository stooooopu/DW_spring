package com.example.first_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first_spring.mapper.MainMapperDept;
import com.example.first_spring.vo.DeptVO;

@Service
public class DeptService {

	@Autowired
	private MainMapperDept mainMapperDept;
	
	public List<DeptVO> getDeptAll(){
		return mainMapperDept.getDeptList();
	}
	
}