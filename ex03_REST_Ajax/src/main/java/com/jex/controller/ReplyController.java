package com.jex.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jex.domain.Criteria;
import com.jex.domain.ReplyVO;
import com.jex.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	@PostMapping(value="/new", consumes= "application/json",
				produces={MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		
		log.info("---- ReplyVO(JSON 타입으로 요청받고 ReplyVO객체로 변환): " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("---- Reply INSERT COUNT: " + insertCount);
		
		// 반환 타입으로 성공 문자열과, 서버 상태 코드 발송
		return insertCount ==1 
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					
	}
	
	//댓글 조회
	@GetMapping(value="/{rno}",
					produces= { MediaType.APPLICATION_XML_VALUE,
								MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno) {
		
		log.info("---- /replies/{rno} GET으로 요청 rno : "+ rno);
		log.info("---- ReplyVO 객체를 REST 방식으로 반환" );
		return new ResponseEntity<>(service.get(rno), HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping(value="/{rno}", produces= { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {

		log.info("---- /replies/{rno}  DELETE 로 요청 rno : "+ rno);
		
		return service.remove(rno) ==1 
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
	
	
	@GetMapping(value = "/pages/{bno}/{page}",
				produces= { MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> getList(
								@PathVariable("page") int page,
								@PathVariable("bno") Long bno) {
		
		log.info("---- /pages/{bno}/{page} 로 getList 요청");
		Criteria cri = new Criteria(page,10);
		
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
	}
	
	
	// 댓글 수정
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH} ,
					value = "/{rno}" ,
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
					@RequestBody ReplyVO vo,
					@PathVariable("rno") Long rno) {
		vo.setRno(rno);
		
		log.info("---- /{rno} 로 ReplyVO를 json 형태로 PUT,PATCH로 요청 rno: " + rno);
		
		log.info("---- ReplyVO 객체로 변환하여 수정: " + vo);
		
		return service.modify(vo) ==1 
				? new ResponseEntity<>("SUCCESS", HttpStatus.OK) 
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	

}
