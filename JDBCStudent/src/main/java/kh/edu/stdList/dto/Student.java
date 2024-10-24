package kh.edu.stdList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
	
	private int stdNo; // 학생 번호
	private String stdName; // 학생 이름
	private int stdAge; // 학생 나이
	private String stdGender; // 학생 성별
	private String stdScore; // 학생 성적
	private String detail; // 학생 상세 내용.
}
