package com.ojj.mapper;

import org.junit.Test;
// 단위 테스트를 위한 임포트
import org.junit.runner.RunWith;
// 실행 하면서 확인 하기위한 임포트
import org.springframework.beans.factory.annotation.Autowired;
// 어노테이션을 사용하기위한 임포트 방식은 autowise
import org.springframework.test.context.ContextConfiguration;
// mapper 위치 지정으이 root-context에 있으니까 이를 불러오기 위해 임포트
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ojj.domain.BoardVO;
//VO 객체를 받아 테스트 하기위해 임포트

import lombok.Setter;
// lombok 의 setter를 이용해 자동주입
// VO 클래스 위에 @data 가 없으면 오류 떨어진다 자동주입할수 없기 때문에
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class) //실행하며 단위테스트
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") //context파일 위치지정
@Log4j  //로그값 찍어주기
public class BoardMapperTests {
	
	// setter 를 이용한 인터페이스 자동주입
	@Setter(onMethod_ = @Autowired)
	BoardMapper mapper;
	
//	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
		// getList 에서 list를 반환하면 거기서 board객체를 하나하나 꺼낸다
		// 이것이 forEach()  그중 board 객체를 print를 하면 자동 호출되는 toStirng으로 찍으면
		// 주소값이 나와서 예전에 내부 필드값을 꺼낼수 있게 재정의 해줬지만
		// lombok 는 알아서 필드값을 꺼내주기 때문에 확인 할 수 있다.
	}
	
//	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
		// Lombok이 만들어주는 toString()을 이용하여	bno  멤버 변수(인스턴스 변수)의 값을 알아보기 위함.
	}
	
//	@Test // 후에 junit 테스트를 할때 제외하려면 이 어노테이션만 지우면 된다.
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
	}
	
//	@Test
	public void testRead() {
		
		//존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(5L);  //Long 타입이기때문에 5 에 L 붙여준다.
		
		log.info(board);
	}
	
	
//	@Test
	public void testDelete() {
		log.info("DELETE COUNT :" +mapper.delete(6L));  //번화 존재하면 데이터를 삭네하고 1이 출력 아니라면 0 이 출력
	}
	
	
	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("User00");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT :" +count);
				
	}
	
}
