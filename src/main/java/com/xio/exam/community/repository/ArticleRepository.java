package com.xio.exam.community.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xio.exam.community.vo.Article;

@Mapper
public interface ArticleRepository {
	@Insert("insert into article set regdate = now(), updateDate = now(), title = #{title}, body = #{body}")
	public void writeArticle(@Param("title") String title, @Param("body") String body);

	@Select("select * from article where id = #{id}")
	public Article getArticle(@Param("id") int id);

	@Delete("delete from article where id = #{id}")
	public void deleteArticle(@Param("id") int id);

	@Update("UPDATE article SET title = #{title}, `body` = #{body}, updateDate = NOW() WHERE id = #{id}")
	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	@Select("select * from article order by id desc")
	public List<Article> getArticles();

	@Select("select last_insert_id()")
	public int getLastInsertId();
}
