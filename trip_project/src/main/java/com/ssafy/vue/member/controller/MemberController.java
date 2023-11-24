package com.ssafy.vue.member.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.member.dto.MemberDto;
import com.ssafy.vue.member.dto.MemberListDto;
import com.ssafy.vue.member.model.service.MemberService;
import com.ssafy.vue.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Api("유저 컨트롤러  API V1")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

	private final MemberService memberService;
	//	private JWTUtil jwtUtil;


	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto member){
		log.debug("login() 요청 수신, {}", member);
		Map<String, Object> loginInfo = memberService.loginMember(member);

		//로그인 실패 시 401응답
		if(loginInfo==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 

		return ResponseEntity.ok(loginInfo);
	}

	@ApiOperation(value = "로그아웃", notes = "로그아웃합니다.")
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	/*
	 * 
	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		log.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto loginUser = memberService.loginMember(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

				//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

				//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
				log.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
				//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	 */

	@ApiOperation(value = "아이디 중복검사", notes = "회원가입시, 아이디 중복이면 ")
	@GetMapping("/checkUserId/{userId}")
	public ResponseEntity<?> checkUserId(
			@PathVariable("userId")@RequestBody @ApiParam(value = "회원 아이디.", required = true) String userId) {
		log.info("checkId -{}",userId);
		int isCheck=memberService.isCheck(userId);
		return new ResponseEntity<>(isCheck,HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입(등록)", notes = "회원 정보를 받아 회원 가입합니다.")
	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUser(
			@RequestBody @ApiParam(value = "회원 정보.", required = true) MemberDto member) {
		log.info("sign-up -{}",member);
		HttpStatus status = HttpStatus.ACCEPTED;
		memberService.joinMember(member);
		status = HttpStatus.CREATED;
		return new ResponseEntity<>(status);

	}

	@ApiOperation(value = "회원 전체 조회", notes = "회원 전체 조회합니다.", response=MemberDto.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping
	public ResponseEntity<?> listMember(
			) {//Map<String, String> map  ||     @RequestBody @ApiParam(value = "회원 정보.", required = true) MemberDto member
		log.info("회원 전체 조회 {}");
		MemberListDto memberListDto =memberService.listMember();
		memberService.listMember();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(memberListDto);
	}

	@ApiOperation(value = "회원 부분 조회", notes = "해당 회원을 조회합니다.")
	@GetMapping("/{userId}")
	public ResponseEntity<MemberDto> getMember(
			@PathVariable("userId") @RequestBody @ApiParam(value = "회원 정보.", required = true) String userId) {//Map<String, String> map
		log.info("회원 부분 조회 {}");
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(memberService.getMember(userId));
		//		return new ResponseEntity<MemberDto>(memberService.getMember(userId),HttpStatus.OK);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "회원을 정보를 수정한다.",response=MemberDto.class)
	@PutMapping
	public ResponseEntity<?> updateMember(
			@RequestBody @ApiParam(value = "회원 정보.", required = true) MemberDto member) {
		log.info("회원 정보 수정 - {}",member);
		memberService.updateMember(member);
		return ResponseEntity.ok().build();

	}


	@ApiOperation(value = "회원 삭제(탈퇴)", notes = "회원 아이디를 기준으로 회원 정보를 삭제한다.")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteMember(
			@PathVariable("userId") @RequestBody @ApiParam(value = "회원 정보.", required = true) String userId) {
		log.info("deleteMember - 호출");
		memberService.deleteMember(userId);;			
		return ResponseEntity.ok().build();

	}
}
