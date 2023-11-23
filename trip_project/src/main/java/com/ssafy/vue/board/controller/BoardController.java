package com.ssafy.vue.board.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.vue.board.dto.BoardDto;
import com.ssafy.vue.board.dto.BoardLikeDto;
import com.ssafy.vue.board.dto.BoardListDto;
import com.ssafy.vue.board.dto.FileInfoDto;
import com.ssafy.vue.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러  API V1")
@Slf4j
public class BoardController {

	
	private BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}


	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping
	public ResponseEntity<?> listArticle(
			@RequestParam @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
		log.info("listArticle map - {}", map);
		try {
			BoardListDto boardListDto = boardService.listArticle(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardListDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "게시판 종류별 글목록", notes = "종류별 게시글의 정보를 반환한다.", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
			@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/{boardtype}")
	public ResponseEntity<?> listTypeArticle(
			@PathVariable("boardtype") @ApiParam(value = "얻어올 글의 종류.", required = true) String boardtype,
			@RequestParam @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) Map<String, String> map) {
		log.info("listTypeArticle map - {}", map);
		try {
			BoardListDto boardListDto = boardService.listTypeArticle(boardtype, map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(boardListDto);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{boardtype}/{boardid}")
	public ResponseEntity<BoardDto> getArticle(
			@PathVariable("boardtype") @ApiParam(value = "얻어올 글의 종류.", required = true) String boardtype,
			@PathVariable("boardid") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardid)
			throws Exception {
		log.info("getArticle - 호출 : " + boardtype +" "+boardid);
		boardService.updateHit(boardid);
		return new ResponseEntity<BoardDto>(boardService.getArticle(boardtype, boardid), HttpStatus.OK);
	}

	@ApiOperation(value = "수정 할 글 얻기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/modify/{boardtype}/{boardid}")
	public ResponseEntity<BoardDto> getModifyArticle(
			@PathVariable("boardtype") @ApiParam(value = "얻어올 글의 종류.", required = true) String boardtype,
			@PathVariable("boardid") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardid)
			throws Exception {
		log.info("getModifyArticle - 호출 : " + boardtype +" "+ boardid);
		return new ResponseEntity<BoardDto>(boardService.getArticle(boardtype, boardid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다.")
	@PostMapping("/{boardtype}")
//	public ResponseEntity<?> writeArticle(
//			@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) {
	public ResponseEntity<?> writeArticle(
			@RequestParam(required = false) MultipartFile[] files, @ModelAttribute BoardDto boardDto) throws IllegalStateException, IOException {
		log.info("writeArticle boardDto - {}", boardDto);
		List<FileInfoDto> fileInfoList = new ArrayList<>();
		if(files != null) {
			for(MultipartFile file : files ) {
				System.out.println("파일 정보 : "+file);
				String saveFolder = "C://ssafy/";
				String originalFileName = file.getOriginalFilename();
				String saveFileName = UUID.randomUUID() + "_" + originalFileName;
				String filePath = saveFolder + saveFileName;
				file.transferTo(new File(filePath));
				
				// FileInfoDto 생성 및 리스트에 추가
	            FileInfoDto fileInfoDto = new FileInfoDto();
	            fileInfoDto.setSaveFolder(saveFolder);
	            fileInfoDto.setOriginalFile(originalFileName);
	            fileInfoDto.setSaveFile(saveFileName);
	            fileInfoList.add(fileInfoDto);
			}
		}
		try {
//			if(fileInfoList.size()>0) {
				boardDto.setFileInfos(fileInfoList);
//			}
			boardService.writeArticle(boardDto);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{boardtype}/{boardid}")
	public ResponseEntity<String> modifyArticle(
			@RequestParam(required = false) MultipartFile[] files,@ModelAttribute BoardDto boardDto) throws Exception {
		log.info("modifyArticle - 호출 {}", boardDto);
		List<FileInfoDto> fileInfoList = new ArrayList<>();
		if(files != null) {
			for(MultipartFile file : files ) {
				System.out.println("파일 정보 : "+file);
				String saveFolder = "C://ssafy/";
				String originalFileName = file.getOriginalFilename();
				String saveFileName = UUID.randomUUID() + "_" + originalFileName;
				String filePath = saveFolder + saveFileName;
				file.transferTo(new File(filePath));
				
				// FileInfoDto 생성 및 리스트에 추가
	            FileInfoDto fileInfoDto = new FileInfoDto();
	            fileInfoDto.setSaveFolder(saveFolder);
	            fileInfoDto.setOriginalFile(originalFileName);
	            fileInfoDto.setSaveFile(saveFileName);
	            fileInfoList.add(fileInfoDto);
			}
		}
		boardDto.setFileInfos(fileInfoList);
		boardService.modifyArticle(boardDto);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardtype}/{boardid}")
	public ResponseEntity<String> deleteArticle(
			@PathVariable("boardid") @ApiParam(value = "삭제할 글의 글번호.", required = true) int boardid) throws Exception {
		log.info("deleteArticle - 호출");
		boardService.deleteArticle(boardid);
		return ResponseEntity.ok().build();
	}
	
	// 게시판 좋아요
	@ApiOperation(value = "게시판 좋아요 등록", notes = "새로운 게시글 정보를 입력한다.")
	@PostMapping("/{boardtype}/{boardid}/like")
	public ResponseEntity<?> likeArticle(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardLikeDto boardLikeDto) {
		log.info("likeArticle boardLikeDto - {}", boardLikeDto);
		try {
			boardService.likeArticle(boardLikeDto);
//			return ResponseEntity.ok().build();
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	@ApiOperation(value = "게시판 좋아요 취소", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardtype}/{boardid}/like")
	public ResponseEntity<String> deletelikeArticle(
			@RequestBody @ApiParam(value = "좋아요 취소 게시글 정보.", required = true) BoardLikeDto boardLikeDto) throws Exception{
		log.info("deletelikeArticle - 호출");
		boardService.deletelikeArticle(boardLikeDto);
		return ResponseEntity.ok().build();
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}