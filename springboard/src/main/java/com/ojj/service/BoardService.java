package com.ojj.service;

import java.util.List;

import com.ojj.domain.BoardVO;

public interface BoardService {
	
	//새 글을 등록할때 사용하는 메서드
	public void register(BoardVO board);
	// BoardMapper 에  insert(BoardVO board)
	
	
	// 지정한 글 본호의 record를 꺼내욜때
	public BoardVO get(Long bno);
	// BoardMapper 의 public BoardVO read(Long bno); 와 매핑
	
	// 기존 글을 수정할 때
	public boolean modify(BoardVO board);
	// BoardMapper 의 public int update(BoardVO board); 와 매핑
	
	
	// 글 번호를 이용해서 해당 글을 삭제할때
	public boolean remove(Long bno);
	// BoardMapper 의 public int delete(Long bno); 와 매핑
	
	// 전체 데이터를 조회할 때
	public List<BoardVO> getList();
	// BoardMapper 의 public List<BoardVO> getList(); 와 매핑
	

}
