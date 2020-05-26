package com.jex.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {com.jex.config.RootConfig.class, com.jex.config.ServletConfig.class})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		System.out.println("@befor setUP() 호출됨. mockMvc 객체 생성");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
//	@Test
	public void testList() throws Exception {
		System.out.println("testList() 호출됨");
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
		System.out.println("testList() 끝");
	}
	
//	@Test
	public void testRegister() throws Exception{
		System.out.println("testRegister() 호출됨");
		
		String resultPage=mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "controller 테스트")
				.param("content", "controller 테스트")
				.param("writer","cont")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
				
	}
	
	@Test
	public void testGet() throws Exception{
		System.out.println("testGet() 호출됨 ");
		
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get")
				.param("bno","32"))
				.andReturn().getModelAndView().getModelMap());
	}
	
//	@Test
	public void testModify() throws Exception {
		
		System.out.println("TestModify() 호출됨 ");
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
						.param("bno", "24")
						.param("title", "수정 테스트")
						.param("content","수정 테스트")
						.param("writer","수정핡"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
//	@Test
	public void testRemove() throws Exception {
		//삭제전 데이터베이스에 게시물 번호 확인할 것
		System.out.println("testremove() 호출됨 ");
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "25")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}

}
