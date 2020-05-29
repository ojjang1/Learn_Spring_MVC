package com.jex.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.jex.domain.Criteria;
import com.jex.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {com.jex.config.RootConfig.class})
@Log4j
public class ReplyMapperTests {
	
	//테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인할 것
	private Long[] bnoArr = { 192L, 191L, 190L, 189L, 188L };
	
	@Setter(onMethod_= {@Autowired})
	private ReplyMapper mapper;
	
//	@Test
	public void testCreate() {
		log.info("---- testCreat() 호출됨 "); 
		IntStream.rangeClosed(1, 10).forEach(i -> {
		
			ReplyVO vo = new ReplyVO();
			
			// 게시물의 번호
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer" +i);
			
			mapper.insert(vo);	
		});
	}
	
//	@Test
	public void testMapper() {
		
		log.info("---- mapper 주입 테스트" +mapper);
	}
	
//	@Test
	public void testRead() {
		Long targetRno = 5L;
		
		ReplyVO vo = mapper.read(targetRno);
		
		log.info("---- testRead() 에서 얻어오기 :" +vo);
	}
	
//	@Test
	public void testDelete() {
		
		Long targetRno = 1L;
		
		log.info("---- testDelete 호출 "+mapper.delete(targetRno) );
		
	}
	
//	@Test
	public void testUpdate() {
		
		ReplyVO vo= mapper.read(10L);
		
		vo.setReply("업데이트 테스트");
		
		log.info("--- testUpdate 호출 count: "+ mapper.update(vo));
		
	}
	
//	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		// 192L 글번호
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		
		replies.forEach(reply -> log.info("---- 댓글 리스트 테스트" + reply));
	}
	
	
	@Test
	public void testList2() {
		
		Criteria cri = new Criteria(1,10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 190L);
		
		replies.forEach(reply -> log.info(reply));
		
	}
	

}
