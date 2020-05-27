package com.jex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jex.domain.BoardVO;
import com.jex.domain.Criteria;
import com.jex.domain.PageDTO;
import com.jex.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		System.out.println("/board/list GET으로 list(Model model) 호출됨 ");
		log.info("list" + cri);
		
		model.addAttribute("list", service.getList(cri));
		//model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total= service.getTotal(cri);
		
		log.info("----total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	@GetMapping("/register")
	public void register() {
		System.out.println("/board/register GET으로 호출됨 ");
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		System.out.println("/board/register POST로 호출됨");
		log.info("register(board, rttr): " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());	
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("들어온 cri 파라미터 확인:" + cri);
		System.out.println("/board/get or /modify GET으로 호출됨");
		log.info("get(bno, model) bno는 @RequestParam으로 받음 "+ bno);
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		System.out.println("board/modify POST로 호출됨 ");
		log.info("modify : " + board);
		
		if(service.modify(board)) rttr.addFlashAttribute("result","success");
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		System.out.println("board/remove POST로 호출됨 ");
		log.info("remove.... bno : " + bno);
		
		if(service.remove(bno)) rttr.addFlashAttribute("result","success");
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}

}
