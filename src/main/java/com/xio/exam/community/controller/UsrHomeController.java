package com.xio.exam.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
//	@RequestMapping("/usr/home/getArticle")
//	@ResponseBody
//	public Article getArticle() {
//		Article article = new Article(1, "1", "1");
//		Article article2 = new Article(2, "2", "2");
//		
//		return article2;
//	}
//	
//	@RequestMapping("/usr/home/getArticles")
//	@ResponseBody
//	public List<Article> getArticles() {
//		Article article1 = new Article(1, "1", "1");
//		Article article2 = new Article(2, "2", "2");
//		
//		List<Article> list = new ArrayList<>();
//		list.add(article1);
//		
//		return list;
//	}
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
}
