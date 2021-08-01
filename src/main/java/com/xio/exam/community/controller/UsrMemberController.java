package com.xio.exam.community.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.exam.community.service.MemberService;
import com.xio.exam.community.util.Ut;
import com.xio.exam.community.vo.Member;
import com.xio.exam.community.vo.ResultData;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		//
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId를 입력해 주세요");
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw를 입력해 주세요");
		}
		if (Ut.empty(name)) {
			return ResultData.from("F-3", "name을 입력해 주세요");
		}
		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "nickname을 입력해 주세요");
		}
		if (Ut.empty(cellphoneNo)) {
			return ResultData.from("F-5", "cellphoneNo를 입력해 주세요");
		}
		if (Ut.empty(email)) {
			return ResultData.from("F-6", "e-mail을 입력해 주세요");
		}
		//

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		Member member = memberService.getMemberById(joinRd.getData1());
		return ResultData.newData(joinRd, member);
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession httpSession, String loginId, String loginPw) {
		boolean isLogined = false; // 변수

		// 세션
		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return ResultData.from("F-5", "이미 로그인 되었습니다.");
		}

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "loginId를 입력해 주세요.");
		}

		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "loginPw를 입력해 주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return ResultData.from("F-3", "존재하지 않는 아이디 입니다.");
		}
		if (member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", "비밀번호가 일치하지 않습니다.");
		}

		httpSession.setAttribute("loginedMemberId", member.getId());

		return ResultData.from("S-1", Ut.f("%S님 로그인 되었습니다.", member.getNickname()));
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession httpSession) {
		boolean isLogined = false;
		
		if (httpSession.getAttribute("loginedMemberId") == null) {
			isLogined = true;
		}
		if (isLogined) {
			return ResultData.from("S-1", "이미 로그아웃 상태 입니다.");
		}
		httpSession.removeAttribute("loginedMemberId");

		return ResultData.from("S-2", "로그아웃 되었습니다.");
	}
}
