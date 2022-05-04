package com.example.first_spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.first_spring.vo.EmpVO;

/**
 * @author 21
 * comment : emp테이블 전체사원 조회
 */
@Mapper
public interface MainMapper {
	// emp테이블의 데이터를 list로 받아옴
	// mybatis에 이 메소드를 알려줘야함
	public List<EmpVO> getEmpList();
	public EmpVO getEmp();	
}
