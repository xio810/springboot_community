package com.xio.exam.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.exam.community.service.MemberService;
import com.xio.exam.community.util.Ut;
import com.xio.exam.community.vo.Member;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		//
		if (Ut.empty(loginId)) {
			return "login id를 입력해 주세요.";
		}
		if (Ut.empty(loginPw)) {
			return "login pw를 입력해 주세요.";
		}
		if (Ut.empty(name)) {
			return "name을 입력해 주세요.";
		}
		if (Ut.empty(nickname)) {
			return "nickname을 입력해 주세요.";
		}
		if (Ut.empty(cellphoneNo)) {
			return "cellphoneNo를 입력해 주세요.";
		}
		if (Ut.empty(email)) {
			return "email을 입력해 주세요.";
		}
		//

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (id == -1) {
			return "해당 아이디는 사용중 입니다.";
		}
		
		if(id == -2) {
			return "해당 이름과 이메일은 사용중 입니다.";
		}

		Member member = memberService.getMemberById(id);

		return member;
	}
}
