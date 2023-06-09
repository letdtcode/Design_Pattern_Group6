package com.it.design_pattern_furniture_web.controllers.admin.user;

import com.it.design_pattern_furniture_web.models.services.role.RoleService;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserDetails", value = "/admin/user/detail")
public class UserDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = StringUtils.toInt(request.getParameter("userId"));

        UserViewModel user = UserService.getInstance().retrieveUserById(userId);

        request.setAttribute("user", user);
        RoleGetPagingRequest reqRole = new RoleGetPagingRequest();
        ArrayList<RoleViewModel> roles = RoleService.getInstance().retrieveAllRole(reqRole);
        request.setAttribute("roles", roles);
        ServletUtils.forward(request, response, "/views/admin/user/user-detail.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
