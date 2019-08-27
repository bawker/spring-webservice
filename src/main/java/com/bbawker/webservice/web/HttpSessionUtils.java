package com.bbawker.webservice.web;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "sessionedUser";

    public static boolean loginCheck(HttpSession session){
        Object tempUser = session.getAttribute(HttpSessionUtils.USER_SESSION_KEY);

        if(tempUser == null) {
            return false;
        }

        return true;
    }
}
