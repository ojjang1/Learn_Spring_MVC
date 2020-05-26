package com.jex.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.jex.domain.BoardVO;
import com.jex.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	
	//spring 4.3 이상에서 자동 처리
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {

		log.info("register() called... " + board);
		
		mapper.insertSelectKey(board);
		
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get() called.....mapper.read(bno) 실행하고 반환예정"); 
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify() called....."+ board);
		System.out.println("mapper.update(board) 실행하고 결과를 T/F로 반환 예정");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove() called...."+bno);
		System.out.println("mapper.delete(bno) 실행하고 결과값 T/F반환 예정 ");
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("getList() called..mapper.getList(); 호출하고 끝나면 결과값 반환 예정");
		return mapper.getList();
	}

}
