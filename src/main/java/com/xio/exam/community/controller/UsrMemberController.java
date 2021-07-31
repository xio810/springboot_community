package com.xio.exam.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.exam.community.service.MemberService;
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
		if (loginId == null) {
			return "login id를 입력해 주세요.";
		}
		if (loginPw == null) {
			return "login pw를 입력해 주세요.";
		}
		if (name == null) {
			return "name을 입력해 주세요.";
		}
		if (nickname == null) {
			return "nickname을 입력해 주세요.";
		}
		if (cellphoneNo == null) {
			return "cellphoneNo를 입력해 주세요.";
		}
		if (email == null) {
			return "email을 입력해 주세요.";
		}
		//

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (id == -1) {
			return "해당 아이디는 사용중 입니다.";
		}

		Member member = memberService.getMemberById(id);

		return member;
	}
}
