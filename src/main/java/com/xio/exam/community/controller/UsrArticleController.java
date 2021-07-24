package com.xio.exam.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xio.exam.community.vo.Article;

@Controller
public class UsrArticleController {

	private int articlesLastId;
	private List<Article> articles;

	public UsrArticleController() {
		articlesLastId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}

	private void makeTestData() {

		for (int i = 1; i <= 10; i++) {
			int id = articlesLastId + 1;
			String title = "제목 " + i;
			String body = "내용 " + i;
			
			Article article = new Article(id,title,body);
			articles.add(article);
			articlesLastId = id;
		}

	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		int id = articlesLastId + 1;
		articlesLastId = id;
		Article article = new Article(id, title, body);

		articles.add(article);

		return article;
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Article doModify(int id, String title, String body) {

	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public Article doDelete(int id) {

	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}

}
