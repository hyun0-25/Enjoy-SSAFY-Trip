package com.ssafy.vue.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.board.dto.BoardCommentDto;
import com.ssafy.vue.board.dto.BoardDto;
import com.ssafy.vue.board.dto.BoardLikeDto;
import com.ssafy.vue.board.dto.BoardListDto;

public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	BoardListDto listArticle(Map<String, String> map) throws Exception;
	BoardListDto listTypeArticle(String boardType, Map<String, String> map) throws Exception;
//	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(String boardtype, int boardId) throws Exception;
	void updateHit(int boardId) throws Exception;
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int boardId) throws Exception;
	
	//
	List<BoardCommentDto> listAllComment() throws Exception;
	List<BoardCommentDto> listArticleComment(int boardid) throws Exception;
	BoardCommentDto getArticleComment(int boardid, int commentid) throws Exception;
	void writeArticleComment(BoardCommentDto boardCommentDto) throws Exception;
	void modifyArticleComment(BoardCommentDto boardCommentDto) throws Exception;
	void deleteArticleComment(int commentId) throws Exception;
	void likeArticle(BoardLikeDto boardLikeDto) throws Exception;
	void deletelikeArticle(BoardLikeDto boardLikeDto) throws Exception;
}
