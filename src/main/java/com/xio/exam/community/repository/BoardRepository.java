package com.xio.exam.community.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xio.exam.community.vo.Board;
@Mapper
public interface BoardRepository {
	
	@Select ("""
			SELECT *
 			FROM board AS B
 			WHERE B.id = #{id}
 			AND B.delStatus = 0
			""")
	public Board getBoardById(@Param("id") int id);

}
