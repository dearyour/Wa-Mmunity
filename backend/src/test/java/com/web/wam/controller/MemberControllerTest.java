package com.web.wam.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.web.wam.config.security.JwtTokenProvider;
import com.web.wam.model.dto.member.SignupRequest;
import com.web.wam.model.service.MemberService;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JwtTokenProvider jwtTokenProvider;

	@MockBean
	private PasswordEncoder passwordEncoder;

	@MockBean
	private MemberService memberService;

	// http://localhost:8080/member/signup
	@Test
	@DisplayName("signup sucess")
	void signupSucess() throws Exception {
		// given
		final SignupRequest signupRequest = initSignupRequest();

		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/member/signup")
				.contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(signupRequest))
		// .content(new ObjectMapper().writeValueAsString(signupRequest))
		).andExpect(status().isOk()).andExpect(jsonPath("$.status").exists()).andExpect(jsonPath("$.object").exists())
				.andDo(print());

		verify(memberService).signup(signupRequest);
	}

	private SignupRequest initSignupRequest() {
		return new SignupRequest("nickname", "email@gmail.com", "pass123123", 1);
	}
}
