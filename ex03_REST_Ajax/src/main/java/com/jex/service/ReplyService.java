package com.jex.service;

import java.util.List;

import com.jex.domain.Criteria;
import com.jex.domain.ReplyPageDTO;
import com.jex.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long rno);
	
	public List<ReplyVO> getList(Criteria cri, Long Bno);
	
	public ReplyPageDTO getListPage(Criteria cri, Long bno);

}
