package com.jex.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jex.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { com.jex.config.RootConfig.class })
@Log4j
public class TimeMapperTests {
	
	@Setter(onMethod_ = @Autowired )
	private TimeMapper timeMapper;
	
//	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testGetTiem2() {
		
		log.info("getTime2");
		log.info(timeMapper.getTime2());
	}
	

}
