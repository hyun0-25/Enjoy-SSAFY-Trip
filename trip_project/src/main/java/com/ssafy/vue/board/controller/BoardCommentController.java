package com.ssafy.vue.board.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.board.dto.BoardCommentDto;
import com.ssafy.vue.board.dto.BoardDto;
import com.ssafy.vue.board.dto.BoardListDto;
import com.ssafy.vue.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment")
@Api("게시판 댓글 컨트롤러  API V1")
@Slf4j
public class BoardCommentController {
	private BoardService boardService;

	public BoardCommentController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
//	게시판 댓글 API
	
	@ApiOperation(value = "게시판 모든 댓글목록", notes = "게시글의 모든 댓글 정보를 반환한다.", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "모든댓글목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping
	public ResponseEntity<?> listAllComment() {
		log.info("listArticleComment - 호출 ");
		try {
			List<BoardCommentDto> boardCommentDto = boardService.listAllComment();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardCommentDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "게시판 댓글목록", notes = "모든 게시글의 댓글 정보를 반환한다.", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "댓글목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/{boardid}")
	public ResponseEntity<?> listArticleComment(
			@PathVariable("boardid") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardid) {
		log.info("listArticleComment - 호출 : " +" "+boardid);
		try {
			List<BoardCommentDto> boardCommentDto = boardService.listArticleComment(boardid);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardCommentDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "게시판 댓글작성", notes = "새로운 게시글 댓글 정보를 입력한다.")
	@PostMapping("/{boardid}")
	public ResponseEntity<?> writeArticleComment(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardCommentDto boardCommentDto) {
		log.info("writeArticleComment boardCommentDto - {}", boardCommentDto);
		try {
			boardService.writeArticleComment(boardCommentDto);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "게시판 댓글수정", notes = "수정할 게시글 댓글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{boardid}/{commentid}")
	public ResponseEntity<String> modifyArticleComment(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardCommentDto boardCommentDto) throws Exception {
		log.info("modifyArticleComment - 호출 {}", boardCommentDto);

		boardService.modifyArticleComment(boardCommentDto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "게시판 댓글삭제", notes = "글번호에 해당하는 게시글의 댓글 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardid}/{commentid}")
	public ResponseEntity<String> deleteArticleComment(
			@PathVariable("commentid") @ApiParam(value = "삭제할 글의 글번호.", required = true) int commentid) throws Exception {
		log.info("deleteArticleComment - 호출");
		boardService.deleteArticleComment(commentid);
		return ResponseEntity.ok().build();
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
