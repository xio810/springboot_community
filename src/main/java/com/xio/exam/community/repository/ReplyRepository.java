package com.xio.exam.community.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xio.exam.community.vo.Reply;

@Mapper
public interface ReplyRepository {
	@Insert("""
			INSERT INTO reply
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			body = #{body}
			""")
	public void writeReply(int memberId, String relTypeCode, int relId, String body);

	@Select("""
			select last_insert_id()
			""")
	public int getLastInsertId();

	@Select("""
			SELECT R.*,
			M.nickname AS extra__writerName
			FROM reply AS R
			LEFT JOIN `member` AS M
			ON R.memberId = M.id
			WHERE R.relTypeCode = #{relTypeCode}
			AND R.relId = #{relId}
			ORDER BY R.id
			""")
	public List<Reply> getForPrintReplies(String relTypeCode, int relId);
	// List<Reply>는 댓글 리스트 출력 -> order by id desc 였는데 desc 삭제

	@Select("""
			SELECT R.*,
			M.nickname AS extra__writerName
			FROM reply AS R
			LEFT JOIN `member` AS M
			ON R.memberId = M.id
			WHERE R.id = #{id}
			""")
	Reply getForPrintReply(int id);

	@Select("""
			SELECT R.*
			FROM reply AS R
			WHERE R.id = #{id}
			""")
	Reply getReply(int id);

	@Delete("""
			DELETE FROM reply
			WHERE id = #{id}
			""")
	void deleteReply(int id);

	@Update("""
			UPDATE reply
			SET updateDate = NOW(),
			`body` = #{body}
			WHERE id = #{id}
			""")
	public void modifyReply(int id, String body);

}
