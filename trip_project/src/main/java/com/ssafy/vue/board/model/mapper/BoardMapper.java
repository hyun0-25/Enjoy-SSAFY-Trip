package com.ssafy.vue.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.board.dto.BoardCommentDto;
import com.ssafy.vue.board.dto.BoardDto;
import com.ssafy.vue.board.dto.BoardLikeDto;
import com.ssafy.vue.board.dto.FileInfoDto;

@Mapper
public interface BoardMapper {

	void writeArticle(BoardDto boardDto) throws SQLException;

	void registerFile(BoardDto boardDto) throws Exception;

	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	
	List<BoardDto> listTypeArticle( Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	BoardDto getArticle(String boardtype, int boardId) throws SQLException;

	void updateHit(int boardId) throws SQLException;

	void modifyArticle(BoardDto boardDto) throws SQLException;

	void deleteFile(int boardId) throws Exception;

	void deleteArticle(int boardId) throws SQLException;

	List<FileInfoDto> fileInfoList(int boardId) throws Exception;

//------------------------------------------------------------------------
	List<BoardCommentDto> listArticleComment(int boardId) throws Exception;

	BoardCommentDto getArticleComment(int boardId, int commentId) throws Exception;

	void writeArticleComment(BoardCommentDto boardCommentDto) throws Exception;

	List<BoardCommentDto> listAllComment() throws Exception;

	void modifyArticleComment(BoardCommentDto boardCommentDto) throws Exception;

	void deleteArticleComment(int commentId) throws Exception;
	
	int likeArticle(BoardLikeDto boardLikeDto) throws Exception;

	int deletelikeArticle(BoardLikeDto boardLikeDto) throws Exception;

	void addTotalLike(int boardId) throws Exception;
	
	void deleteTotalLike(int boardId) throws Exception;

	void deleteComment(int boardId) throws Exception;
}
