package com.ojj.mapper;

import java.util.List;
//VO 들을 묶어서 이동 시키기 위해 List 를 사용한다.

//import org.apache.ibatis.annotations.Select;  //xml 에 쿼리 넣었기 때문에 주석 처리
// ibates 에서 제공하는 Select 어노테이션
import com.ojj.domain.BoardVO;
// 레코드 한 줄을 하나의 객체로 만들기 위한 VO


public interface BoardMapper {
	
	// 레코드들을 반환하려면 select 가 필요하고
	// 묶어서 내보내니까 반환타입은 List, 각각 데이터타입은 BoardVO 타입
	// 간단한 쿼리니까 어노테이션 사용..(기본 4대 쿼리..중에서도 간단한 쿼리만)
//	@Select("select * from tbl_board where bno > 0")   // 어노테이션 형식이 아니고 xml 형식으로 하기위해 주석처리
	public List<BoardVO> getList();
	// 인터페이스니까 메서드 선언만.
	
	// 단순히 inset 만 됨. 
	public void insert(BoardVO board);
	
	// insert 가 됬을때 해당 bno 값을 꺼낼 수 있는 메서드
	public void insertSelectKey(BoardVO board);
	
	// 글 번호로 하나만 읽어들이는 메서드
	public BoardVO read(Long bno);
	
	//글 삭제 메서드
	public int delete(Long bno);
	
	//글 수정 메서드
	public int update(BoardVO board);
	

}
