package com.jex.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jex.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jex.config.RootConfig.class} )
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
//	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
		System.out.println("TestExist() text method called");
	}
	
//	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("서비스로 테스트");
		board.setContent("서비스로 테스트");
		board.setWriter("register");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
//	@Test
	public void testGetList() {
		
		System.out.println("testGetList called.....");
		service.getList().forEach(board -> log.info(board));
	}
	
//	@Test
	public void testGet() {
		
		System.out.println("testGet() 호출됨 ");
		log.info(service.get(30L));
		
	}
	
//	@Test
	public void testUpdate() {
		
		System.out.println("testUpdate() 호출됨");
		BoardVO board = service.get(31L);
		
		if (board==null) return;
		
		board.setTitle("제목 수정" );
		log.info("Modify Result: " + service.modify(board));
		
	}
	
//	@Test
	public void testDelete() {
		
		System.out.println("testDelete() 호출됨");
		//게시물 번호의 존재 여부를 확인하고 테스트 할것
		log.info("Remove result: " + service.remove(30L));
		
	}

}
