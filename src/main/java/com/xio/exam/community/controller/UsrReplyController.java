package com.xio.exam.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.exam.community.service.ReplyService;
import com.xio.exam.community.util.Ut;
import com.xio.exam.community.vo.ResultData;
import com.xio.exam.community.vo.Rq;

@Controller
public class UsrReplyController {
	private Rq rq;
	private ReplyService replyService;

	public UsrReplyController(Rq rq, ReplyService replyService) {
		this.rq = rq;
		this.replyService = replyService;
	}

	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
		if (Ut.empty(relTypeCode)) {
			return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
		}

		if (Ut.empty(relId)) {
			return rq.jsHistoryBack("relId(을)를 입력해주세요.");
		}

		if (Ut.empty(body)) {
			return rq.jsHistoryBack("body(을)를 입력해주세요.");
		}

		ResultData<Integer> writeReplyRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);
		int id = writeReplyRd.getData1();

		if (Ut.empty(replaceUri)) {
			switch (relTypeCode) {
			case "article":
				replaceUri = Ut.f("../article/detail?id=%d", id);
				break;
			}
		}

		return rq.jsReplace(writeReplyRd.getMsg(), replaceUri);
	}

}