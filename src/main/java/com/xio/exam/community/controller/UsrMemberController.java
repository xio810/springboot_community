package com.xio.exam.community.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.exam.community.service.MemberService;
import com.xio.exam.community.util.Ut;
import com.xio.exam.community.vo.Member;
import com.xio.exam.community.vo.ResultData;
import com.xio.exam.community.vo.Rq;

@Controller
public class UsrMemberController {
	private MemberService memberService;
	private Rq rq;

	public UsrMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
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
		return ResultData.newData(joinRd, "member", member);
	}

	@RequestMapping("/usr/member/login")
	public String showLogin(HttpSession httpSession) {
		return "/usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw) {

//		if (rq.isLogined()) {
//			return Ut.jsHistoryBack("이미 로그인 상태입니다.");
//		}

		if (Ut.empty(loginId)) {
			return Ut.jsHistoryBack("loginId(을)를 입력해주세요.");
		}

		if (Ut.empty(loginPw)) {
			return Ut.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("존재하지 않는 아이디 입니다.");
		}
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		rq.login(member);

		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout() {

//		if (!rq.isLogined()) {
//			return Ut.jsHistoryBack("이미 로그아웃 상태 입니다.");
//		}

		rq.logout();

		return Ut.jsReplace("로그아웃 되었습니다.", "/");
	}

	@RequestMapping("/usr/member/myPage")
	public String showMyPage() {
		return "usr/member/myPage";
	}

	@RequestMapping("/usr/member/checkPassword")
	public String showCheckPassword() {
		return "usr/member/checkPassword";
	}

	@RequestMapping("/usr/member/doCheckPassword")
	@ResponseBody
	public String doCheckPassword(String loginPw, String replaceUri) {
		if (Ut.empty(loginPw)) {
			return rq.jsHistoryBack("loginPw(을)를 입력해주세요.");
		}

		if (rq.getLoginedMember().getLoginPw().equals(loginPw) == false) {
			return rq.jsHistoryBack("비밀번호가 일치하지 않습니다.");
		}

		return rq.jsReplace("", replaceUri);
	}

	@RequestMapping("/usr/member/modify")
	public String showModify() {
		return "usr/member/modify";
	}

	@RequestMapping("/usr/member/doModify")
	@ResponseBody
	public String doModify(String loginPw, String name, String nickname, String email, String cellphoneNo) {
		if (Ut.empty(loginPw)) {
			loginPw = null;
		}

		if (Ut.empty(name)) {
			return rq.jsHistoryBack("name(을)를 입력해주세요.");
		}

		if (Ut.empty(nickname)) {
			return rq.jsHistoryBack("nickname(을)를 입력해주세요.");
		}

		if (Ut.empty(email)) {
			return rq.jsHistoryBack("email(을)를 입력해주세요.");
		}

		if (Ut.empty(cellphoneNo)) {
			return rq.jsHistoryBack("cellphoneNo(을)를 입력해주세요.");
		}

		ResultData modifyRd = memberService.modify(rq.getLoginedMemberId(), loginPw, name, nickname, email,
				cellphoneNo);

		return rq.jsReplace(modifyRd.getMsg(), "/");
	}

}
