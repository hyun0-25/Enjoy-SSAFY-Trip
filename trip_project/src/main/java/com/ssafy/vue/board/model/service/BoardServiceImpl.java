package com.ssafy.vue.board.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.board.dto.BoardCommentDto;
import com.ssafy.vue.board.dto.BoardDto;
import com.ssafy.vue.board.dto.BoardLikeDto;
import com.ssafy.vue.board.dto.BoardListDto;
import com.ssafy.vue.board.dto.FileInfoDto;
import com.ssafy.vue.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		boardMapper.writeArticle(boardDto);
		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
		if (fileInfos != null && !fileInfos.isEmpty()) {
			boardMapper.registerFile(boardDto);
		}
	}

	@Override
	public BoardListDto listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
//		int start = currentPage * sizePerPage - sizePerPage;
//		param.put("start", start);
//		param.put("listsize", sizePerPage);

//		String key = map.get("key");
//		param.put("key", key == null ? "" : key);
//		if ("user_id".equals(key))
//			param.put("key", key == null ? "" : "b.user_id");
		List<BoardDto> list = boardMapper.listArticle(param);

//		if ("user_id".equals(key))
//			param.put("key", key == null ? "" : "user_id");
//		int totalArticleCount = boardMapper.getTotalArticleCount(param);
//		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
//		boardListDto.setCurrentPage(currentPage);
//		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}
	
	@Override
	public BoardListDto listTypeArticle(String boardType, Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
//		int start = currentPage * sizePerPage - sizePerPage;
//		param.put("start", start);
//		param.put("listsize", sizePerPage);
		param.put("type", boardType);

		List<BoardDto> list = boardMapper.listTypeArticle(param);

//		int totalArticleCount = boardMapper.getTotalArticleCount(param);
//		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
//		boardListDto.setCurrentPage(currentPage);
//		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}


	@Override
	public BoardDto getArticle(String boardtype, int boardId) throws Exception {
		return boardMapper.getArticle(boardtype, boardId);
	}

	@Override
	public void updateHit(int boardId) throws Exception {
		boardMapper.updateHit(boardId);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int boardId) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		List<FileInfoDto> fileList = boardMapper.fileInfoList(boardId);
		boardMapper.deleteFile(boardId);
		boardMapper.deleteComment(boardId);
		boardMapper.deleteArticle(boardId);
		for(FileInfoDto fileInfoDto : fileList) {
			File file = new File(File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
			file.delete();
		}
	}

//	@Override
//	public void deleteArticle(int boardId) throws Exception {
//		// TODO : BoardDaoImpl의 deleteArticle 호출
//		boardMapper.deleteArticle(boardId);
//	}
	
	// comment
	
	@Override
	public List<BoardCommentDto> listAllComment() throws Exception {
		return boardMapper.listAllComment();
	}
	
	@Override
	public List<BoardCommentDto> listArticleComment(int boardId) throws Exception {
		return boardMapper.listArticleComment(boardId);
	}
	
	@Override
	public BoardCommentDto getArticleComment(int boardId, int commentId) throws Exception {
		return boardMapper.getArticleComment(boardId, commentId);
	}
	
	@Override
	@Transactional
	public void writeArticleComment(BoardCommentDto boardCommentDto) throws Exception {
		boardMapper.writeArticleComment(boardCommentDto);
	}
	
	@Override
	public void modifyArticleComment(BoardCommentDto boardCommentDto) throws Exception {
		boardMapper.modifyArticleComment(boardCommentDto);
	}
	
	@Override
	public void deleteArticleComment(int commentId) throws Exception {
		boardMapper.deleteArticleComment(commentId);
	}

	@Override
	@Transactional
	public void likeArticle(BoardLikeDto boardLikeDto) throws Exception {
		int cnt = boardMapper.likeArticle(boardLikeDto);
		if(cnt==1) {
			boardMapper.addTotalLike(boardLikeDto.getBoardId());
		}
	}

	@Override
	public void deletelikeArticle(BoardLikeDto boardLikeDto) throws Exception {
		int cnt = boardMapper.deletelikeArticle(boardLikeDto);
		if(cnt==1) {
			boardMapper.deleteTotalLike(boardLikeDto.getBoardId());
		}
	}

}
