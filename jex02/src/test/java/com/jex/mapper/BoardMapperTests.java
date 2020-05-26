package com.jex.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jex.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jex.config.RootConfig.class})
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
//	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
//	@Test
	public void testInsert() {
		
		BoardVO board = new BoardVO();
		board.setTitle("0525테스트 제목");
		board.setContent("0525 테스트 내용");
		board.setWriter("0525");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
//	@Test
	public void testInsertSelectKey() {
		
		BoardVO board = new BoardVO();
		board.setTitle("0525테스트 제목 sk");
		board.setContent("0525 테스트 내용 sk");
		board.setWriter("0525");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
//	@Test
	public void testRead() {
		
		//존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(7L);
		
		log.info(board);
	}
	
//	@Test
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(5L));
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		// 실행전 존재하는 번호인지 확인할 것
		board.setBno(7L);
		board.setTitle("0525 수정 제목");
		board.setContent("0525 수정 내용");
		board.setWriter("0525mm");
		
		int count = mapper.update(board);
		
		
		log.info("UPDATE COUNT: " + count);
	}
}
