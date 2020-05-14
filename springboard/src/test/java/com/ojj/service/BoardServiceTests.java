package com.ojj.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ojj.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private BoardService service;
	
	// 자동주입이 잘되는지 테스트하기 위한 메서드
	// 잘 만들어져있으면 service 객체값과 내부에 db 접속 하는 객체값들이 다 나옴.
//	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
		
	}
	
	// register 테스트
//	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 테스트");
		board.setContent("새로 작성하는 내용 테스트");
		board.setWriter("newbie test");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	// 하나의 레코드를 가져오는 메서드 테스트
//	@Test
	public void testGet() {
		log.info(service.get(1L));
	}
	
	
	// 삭제 테스트
//	@Test
	public void testDelete() {
		
		//게시물 번호의 존재 여부를 확인하고 테스트 할것
		log.info("REMOVE RESULT: " + service.remove(11L));
	}
	
	// 수정 테스트
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(10L);
		
		if (board == null) {
			return;
		}
		
		board.setTitle("제목을 수정 테스트합니다.");
		log.info("MODIFY RESULT: " + service.modify(board));
	}
}
