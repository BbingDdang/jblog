package com.poscodx.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. handler 종류 확인 
		if (!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적자원, /assets/**, mapping이 안되어있는 자원)  
			return true;
		}
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method의 @Auth 가져오기 
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Handler Method에 @Auth가 없으면 Type(Class)에 붙어 있는지 확인
		if (auth == null) {
			auth = handlerMethod
					.getMethod()
					.getDeclaringClass()
					.getAnnotation(Auth.class);
		}
		
		// 5. Type이나 Method에 @Auth가 없는 경우
		if (auth == null) {
			return true;
		}
		
		
		// 5. @Auth가 붙어 있기 때문에 인증(Authentication) 여부 확인 
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		// 5-1. 인증이 안되어 있는 경우 
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			// 뒤로 가면 핸들러가 있기때문에 큰일나서 return false 
			return false;
		}
		
		// 6. auth로 session과 url의 userId 비교
		String requestURI = request.getRequestURI();
		String[] adminIdArray = requestURI.split("/");
		if (adminIdArray.length > 1) {
			String adminId = adminIdArray[2];
			String userId = authUser.getId();
			if (!adminId.equals(userId)) {
				response.sendRedirect(request.getContextPath() + "/" + adminId);
			}
		}
		
		// 6. 권한(Authorization) 체크를 위해 @Auth의 role을 가져오기("USER","ADMIN")
//		String role = auth.role();
		
		// 7. @Auth role이 "USER"인 경우, authUser의 role은 상관없다.
//		if ("USER".equals(role)) {
//			return true;
//		}
		
		// 8. @Auth의 role이 "ADMIN"인 경우, authUser의 role은 반드시 "ADMIN"
//		if (!"ADMIN".equals(authUser.getRole())) {
//			response.sendRedirect(request.getContextPath());
//			return false;
//		}
		
		// 9. 옳은 관리자 권한 @Auth(role="ADMIN"), authUser.getRole() == "ADMIN"
		return true;
	}
	
}
