package com.exfantasy.template.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.exfantasy.template.vo.SessionInfo;

public class SessionUtil {

	private static final ThreadLocal<SessionInfo> SESSION_INFO = new ThreadLocal<SessionInfo>();

	private static HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	private static void setSessionInfo(SessionInfo sessionInfo) {
		SESSION_INFO.set(sessionInfo);
	}

	public static void saveLoginInfo(String email, String clientIp) {
		HttpSession session = getSession();

		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(SessionInfo.SESSION_INFO);
		if (null == sessionInfo) {
			sessionInfo = new SessionInfo();
		}
		
		sessionInfo.setEmail(email);
		sessionInfo.setClienIp(clientIp);

		SessionUtil.setSessionInfo(sessionInfo);
	}

	public static SessionInfo getSessionInfo() {
		return SESSION_INFO.get();
	}

	/**
	 * 取ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request) {
		if (null == request)
			return null;

		String clientIp = request.getHeader("X-Forwarded-For");
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("HTTP_CLIENT_IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}
		if ("127.0.0.1".equals(clientIp) || "0:0:0:0:0:0:0:1".equals(clientIp)) {
			try {
				clientIp = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
			}
		}
		return clientIp;
	}
}
