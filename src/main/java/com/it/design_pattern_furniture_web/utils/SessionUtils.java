package com.it.design_pattern_furniture_web.utils;

import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static int getUserIdLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if (user == null)
            return -1;
        return user.getId();
    }
}
