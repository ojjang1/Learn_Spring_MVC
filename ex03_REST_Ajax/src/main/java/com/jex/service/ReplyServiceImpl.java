package com.jex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jex.domain.Criteria;
import com.jex.domain.ReplyPageDTO;
import com.jex.domain.ReplyVO;
import com.jex.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;

	@Override
	public int register(ReplyVO vo) {

		log.info("---- resister service :" + vo);
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {

		log.info("---- get service :" +rno);
		
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {

		log.info("---- modify service: " + vo);
		
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("---- remove service: "+ rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("---- get Reply list of Board bno service: "+ bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		
		log.info("---- getListPage service 댓글 카운트, 페이징사용 목록 반환");
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}

}
