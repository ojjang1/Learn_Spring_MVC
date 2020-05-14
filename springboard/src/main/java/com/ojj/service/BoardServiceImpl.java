package com.ojj.service;

import java.util.List;
// 여기서 import 되는 List는 클래스가 아니고 인터페이스다
// 예를 구현 받은 애가 ArrayList, LinkedList 인덱스, 순서 존재
// Map 도 인터페이스 
// 구현받은애가 HashMap  인덱스없고 순서도 없고, key/value 로 구성
// Set 도 인터페이스
// 구현받은애가 HashSet 인덱스없고, 순서도 없고, 값만 있음, 그래서 꺼낼때 map으로 바꿔서 끄내줌..열거타입으로 해서.

import org.springframework.beans.factory.annotation.Autowired;
// 빈객체들을 자동주입 시키는데 어노테이션으로 시키겠다.
import org.springframework.stereotype.Service;
// Service 영역을 담당하는 클래스라고 어노테이션 달려주기 위해

import com.ojj.domain.BoardVO;
// 데이터 받을때 가장 기본적은 데이터 타입
import com.ojj.mapper.BoardMapper;
// 실제로 쿼리를 갖고 있는 클래스

import lombok.AllArgsConstructor;
// 롬복으로 전체 값들을 다 받는 생성자(매개변수에 따라 생성자를 중복생성)를 자동생성하기위해 
import lombok.Setter;
// 롬복으로 자동 setter 만들어줘서 주입하는.
import lombok.extern.log4j.Log4j;
// 로그값 찍어주기 위해.

@Log4j
@Service
@AllArgsConstructor   //롬복이 자동으로 최대 매개변수를 받을수 있는 생성자 만들어줌.
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	
	@Override
	public void register(BoardVO board) {
		log.info("register......" + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get......" + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......" + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove......" + bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("getList......");
		return mapper.getList();
	}

}
