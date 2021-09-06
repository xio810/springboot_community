package com.xio.exam.community.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xio.exam.community.repository.ReplyRepository;
import com.xio.exam.community.util.Ut;
import com.xio.exam.community.vo.Member;
import com.xio.exam.community.vo.Reply;
import com.xio.exam.community.vo.ResultData;

@Service
public class ReplyService {

	private ReplyRepository replyRepository;

	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}

	public ResultData<Integer> writeReply(int actorId, String relTypeCode, int relId, String body) {
		replyRepository.writeReply(actorId, relTypeCode, relId, body);

		int id = replyRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다.", id), "id", id);
	}

	public List<Reply> getForPrintReplies(Member actor, String relTypeCode, int relId) {
		return replyRepository.getForPrintReplies(relTypeCode, relId);
	}

}
