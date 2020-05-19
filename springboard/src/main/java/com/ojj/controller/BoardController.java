package com.ojj.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
// 컨트롤러 역활을 하는거다 라고 알려주기 위해
import org.springframework.ui.Model;
// 스프링은 modelAndView 가 아니고 Model 이다.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ojj.domain.BoardVO;
import com.ojj.service.BoardService;
// BoardService를 불러와서 연결하기 때문에 

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller  // 이 클래스틑 컨트롤러 역활 클래스다
@Log4j  // 로그값을 찍어줘
@RequestMapping("/board/*")  //  "/board/*" 요청 url이 /board/로 시작하면 여기로 매핑
@AllArgsConstructor  // 롬복으로 생성자를 만들어 생성자를 통한 자동주입
public class BoardController {

	private BoardService service;
	
	//  /board/로 들어오는 요청중 /list로 끝나면 여기로 매핑
	@GetMapping("/list")
	public void list(Model model) {
		
		log.info("list");
		model.addAttribute("list", service.getList());
//		public List<BoardVO> getList();
//		service 의 getList() 의 반환값을 확인 할것.
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		// FlashAttribute 딱 1전만 전송됨. 만약 다른메서드로 전달한다면
		// 새로고침할때마다 데이터가 전달되는것을 방지.
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@GetMapping({"/get","/modify"})  //수정페이지 호출 위해 url패턴에 /modify 추가함
	public void get(@RequestParam("bno") Long bno, Model model) {
		//파라미터 명 과 같이 잡아주면 바로 매개변수로 넣어준다.
		// request.getParameter('bno') 를 대체해주는것.
		// board/get?bno='  '  로 들어오는것을 캐치해줌.
		// url 패턴명이 바로 Model 객체 내부로 들어가서 자동 리턴해준다.
		
		log.info("/get or modify");  //수정페이지 호출 위해 url패턴에 /modify 추가함
		 model.addAttribute("board",service.get(bno));
		 // public BoardVO get(Long bno);
		 // 즉 get(bno) 의 반환타입은 BoardVO 다 라는것을 확인할것
		 // addAttribute 로 되어있는거 주의 새로고침해도 넘겨줘야하기때문에.
	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify :" + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		log.info("remove..." + bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
}
