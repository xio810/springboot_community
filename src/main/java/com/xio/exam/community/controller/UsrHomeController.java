package com.xio.exam.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	private int count;

	public UsrHomeController() {
		count = -1;
	}

	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int showMain4() {
		return count;
	}

	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String showMain5(int count) {
		this.count = count;
		return "count값이 " +this.count +"로 초기화 되었습니다.";
	}

}
