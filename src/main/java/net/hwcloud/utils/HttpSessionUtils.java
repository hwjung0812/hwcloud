package net.hwcloud.utils;

import net.hwcloud.dto.User;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String USER_SESSION_KEY = "sessionUser"; // 상수 처리

    public static boolean isLoginUser(HttpSession httpSession) {
        if(httpSession.getAttribute(USER_SESSION_KEY) != null) {
            return true;
        }
        return false;
    }

    public static User getUserfromSession(HttpSession httpSession) {
        if(isLoginUser(httpSession)) {
            return (User)httpSession.getAttribute(USER_SESSION_KEY);
        }
        return null;
    }
}
