package kr.co.communityJh.auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class CustomAuthorizationFilter {
//	extends OncePerRequestFilter {
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String servletPath = request.getServletPath();
// 		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
////		System.out.println(servletPath);
//
//		// 로그인, 리프레시 요청이라면 토큰 검사하지 않음
////		if (servletPath.equals("/api/login") || servletPath.equals("/api/refresh")) {
////			filterChain.doFilter(request, response);
////		} else
//		if(authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_HEADER_PREFIX)) {
//			// 토큰이 없거나 정상적이지 않다면 에러
//			log.info("CustomAuthorizationFilter : JWT Token이 존재하지 않습니다.");
//			response.setStatus(SC_BAD_REQUEST);
//			response.setContentType(APPLICATION_JSON_VALUE);
//			response.setCharacterEncoding("utf-8");
//			ErrorResponse errorResponse = new ErrorResponse(400, "JWT Token이 존재하지 않습니다.");
////			new ObjectMapper().writeValue(response.getWriter(), errorResponse);
//			filterChain.doFilter(request, response);
//		} else {
//			try {
//				// Access Token만 꺼내옴
//				String accessToken = authorizationHeader.substring(TOKEN_HEADER_PREFIX.length());
//
//				// === Access Token 검증 === //
//				JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
//				DecodedJWT decodedJWT = verifier.verify(accessToken);
//
//				// === Access Token 내 Claim에서 Authorities 꺼내 Authentication 객체 생성 & SecurityContext에 저장 === //
//				List<String> strAuthorities = decodedJWT.getClaim("roles").asList(String.class);
//				List<SimpleGrantedAuthority> authorities = strAuthorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//				String email = decodedJWT.getSubject();
//				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//						email, null, authorities);
//				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//				// 인증 처리 후 정상적으로 다음 Filter 수행
//				filterChain.doFilter(request, response);
//			} catch (TokenExpiredException e) {
//				log.info("CustomAuthorizationFilter : Access Token이 만료되었습니다.");
//				response.setStatus(SC_UNAUTHORIZED);
//				response.setContentType(APPLICATION_JSON_VALUE);
//				response.setCharacterEncoding("utf-8");
//				ErrorResponse errorResponse = new ErrorResponse(401, "Access Token이 만료되었습니다.");
//				new ObjectMapper().writeValue(response.getWriter(), errorResponse);
//			} catch (Exception e) {
//				log.info("CustomAuthorizationFilter : JWT 토큰이 잘못되었습니다. message : {}", e.getMessage());
//				response.setStatus(SC_BAD_REQUEST);
//				response.setContentType(APPLICATION_JSON_VALUE);
//				response.setCharacterEncoding("utf-8");
//				ErrorResponse errorResponse = new ErrorResponse(400, "잘못된 JWT Token 입니다.");
//				new ObjectMapper().writeValue(response.getWriter(), errorResponse);
//			}
//		}
//
//	}
}
