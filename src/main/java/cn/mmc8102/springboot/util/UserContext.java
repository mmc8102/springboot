package cn.mmc8102.springboot.util;

import cn.mmc8102.springboot.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;


/**
 * 用于存放当前用户的上下文
 * @author wangli
 *
 */
public class UserContext {
	public static final String USER_IN_SESSION = "logininfo";
	public static final String VERIFYCODE_IN_SESSION = "verifycode_in_session";

	private UserContext() {}
	
	public static HttpSession getSession() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	public static void setCurrent(Employee current) {
		if(current == null) {
			getSession().invalidate();
		}else {
			getSession().setAttribute(USER_IN_SESSION, current);
		}
	}
	
	public static Employee getCurrent() {
		return (Employee) getSession().getAttribute(USER_IN_SESSION);
	}
	
}
