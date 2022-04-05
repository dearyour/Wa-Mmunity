package com.web.wam.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().disable().cors().configurationSource(corsConfig()) // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
				.and().csrf().disable() // csrf 보안 토큰 disable처리.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 역시 사용하지
				// 않습니다.
				.and().authorizeRequests() // 요청에 대한 사용권한 체크
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers("/*/signin", "/*/signup","/*/signupConfirm").permitAll() // 가입 및 인증 주소는 누구나 접근가능
				.antMatchers(
						"*"
						, "/*"
						, "/api/member/signup"
						, "/api/member/signin"
						, "/api/member/ismember/**"
						, "/api/member/password"
						, "/api/member/idcheck/**"
						, "/api/member/{email}"
						, "/api/wine"
						, "/api/wine/**"
						, "/api/freeboard/**"
						, "/api/resellboard/**"
						, "/api/freeboard"
						, "/api/resellboard"
						, "/api/bigdata/**"
						, "/api/bigdata"
						, "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/security", "/swagger-ui.html", "/webjars/**"
						//"*", "/*", "/*/*", "/*/*/*", "/*/*/*", "/*/*/*/*", "/*/*/*/*/*", "/*/*/*/*/*/*", "/*/*/*/*/*/*/*", "/*/*/*/*/*/*/*/*"
				).permitAll() // 가입 및 인증 주소는 누구나 접근가능
				.anyRequest().hasRole("USER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
//                .anyRequest().permitAll() // 그외 나머지 요청은 누구나 접근 가능
				.and().addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
						UsernamePasswordAuthenticationFilter.class)
				.cors();
		// JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.httpBasic().disable().cors().configurationSource(corsConfig())
//				.and().csrf().disable().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().addFilter(new JwtAuthenticationFilter(jwtTokenProvider))
//				.authorizeRequests()
//				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Bean
	public CorsConfigurationSource corsConfig() {



		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return source;
	}
}