package edu.kh.todoList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Getter / @Setter / @ToString 포함
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

	private int todoNo;			// todo 번호
	private String title;		// todo 제목
	private String detail;		// todo 상세내
	private boolean complete;	// todo 완료 여부
	private String regDate;		// todo 등록일
	
}
