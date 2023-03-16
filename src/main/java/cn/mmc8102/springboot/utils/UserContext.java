package cn.mmc8102.springboot.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


/**
 * 用于存放当前用户的上下文
 * @author wangli
 */
public class UserContext {
	public static final String USER_ID_IN_SESSION = "userId";
	public static final String ACCESS_TOKEN_SESSION = "userId";
	private static final ThreadLocal<String> USER_ID_THREADLOCAL = new ThreadLocal<>();
	private static final ThreadLocal<String> ACCESS_TOKEN_THREADLOCAL = new ThreadLocal<>();

	private UserContext() {
	}

	public static HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	public static void setUserId(String userId) {
		//getSession().setAttribute(USER_ID_IN_SESSION, userId);
		USER_ID_THREADLOCAL.set(userId);
	}

	public static String getUserId() {
		return USER_ID_THREADLOCAL.get();
	}

	public static void setAccessToken(String accessToken) {
		//getSession().setAttribute(ACCESS_TOKEN_SESSION, accessToken);
		ACCESS_TOKEN_THREADLOCAL.set(accessToken);
	}

	public static String getAccessToken() {
		//return (String) getSession().getAttribute(ACCESS_TOKEN_SESSION);
		return ACCESS_TOKEN_THREADLOCAL.get();
	}


	/**
	 * 清除所有设置的threadlocal
	 */
	public static void removeAll(){
		USER_ID_THREADLOCAL.remove();
		ACCESS_TOKEN_THREADLOCAL.remove();
	}
	
}
