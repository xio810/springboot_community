package com.xio.exam.community.service;

import org.springframework.stereotype.Service;

import com.xio.exam.community.repository.BoardRepository;
import com.xio.exam.community.vo.Board;
@Service
public class BoardService {

	private BoardRepository boardRepository;

	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public Board getBoardById(int id) {
		
		return boardRepository.getBoardById(id);
	}

}
