package com.example.first_spring.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmpVO {
// 컬럼이름이 필드변수가 됨
	private int empno;
	private String ename;
	private String job;
}