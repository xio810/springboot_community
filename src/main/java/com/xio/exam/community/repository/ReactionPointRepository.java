package com.xio.exam.community.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {
	@Select("""
			<script>
			SELECT IFNULL(SUM(RP.point), 0) AS s
			FROM reactionPoint AS RP
			WHERE RP.relTypeCode = #{relTypeCode}
			AND RP.relId = #{relId}
			AND RP.memberId = #{memberId}
			</script>
			""")
	int getSumReactionPointByMemberId(String relTypeCode, int relId, int memberId);

	@Insert("""
			insert into reactionPoint
			set regDate = now(),
			updateDate = now(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{memberId},
			`point` = 1
			""")
	void addGoodReactionPoint(int memberId, String relTypeCode, int relId);
	
	@Insert("""
			insert into reactionPoint
			set regDate = now(),
			updateDate = now(),
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			memberId = #{memberId},
			`point` = -1
			""")
	void addBadReactionPoint(int memberId, String relTypeCode, int relId);

}