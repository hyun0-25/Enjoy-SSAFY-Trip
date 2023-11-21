package com.ssafy.vue.map.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.vue.board.dto.BoardDto;
import com.ssafy.vue.map.dto.AttractionDto;
import com.ssafy.vue.map.dto.MyLocationDto;
import com.ssafy.vue.map.dto.SidoGugunCodeDto;
import com.ssafy.vue.map.model.service.MapService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/map")
@Api("Map 컨트롤러  API V1")
@Slf4j
public class MapController {
	
	private MapService mapService;

	public MapController(MapService mapService) {
		super();
		this.mapService = mapService;
	}

	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		log.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "구군 정보", notes = "시도에 속한 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(
			@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) int sidogugun) throws Exception {
		log.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getGugunInSido(sidogugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관광지 단일 정보", notes = "관광지 번호에 해당하는 관광지의 정보를 반환한다.", response = AttractionDto.class)
	@GetMapping("/{contentid}")
	public ResponseEntity<AttractionDto> getAttraction(
			@PathVariable("contentid") @ApiParam(value = "얻어올 관광지의 번호.", required = true) int contentid)
			throws Exception {
		log.info("getAttraction - 호출 : " + contentid);
		return new ResponseEntity<AttractionDto>(mapService.getAttraction(contentid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관광지 목록 정보", notes = "관광지 번호에 해당하는 관광지의 정보를 반환한다.", response = AttractionDto.class)
	@GetMapping("/attraction")
	public ResponseEntity<?> listAttractions(
			@RequestParam @ApiParam(value = "관광지 리스트를 얻기위한 시도구군.", required = true) Map<String, Integer> map)
			throws Exception {
		log.info("listAttractions - {} ", map);
		try {
			List<AttractionDto> attractionlist = mapService.listAttractions(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(attractionlist);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	//여행 경로
	@ApiOperation(value = "여행 경로 작성", notes = "새로운 게시글 정보를 입력한다.")
	@PostMapping("/{boardtype}")
	public ResponseEntity<?> writeMyLocation(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) MyLocationDto mylocationDto) {
		log.info("writeMyLocation mylocationDto - {}", mylocationDto);
		try {
			mapService.writeMyLocation(mylocationDto);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
