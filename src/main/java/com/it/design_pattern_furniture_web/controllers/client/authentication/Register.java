package com.it.design_pattern_furniture_web.controllers.client.authentication;

import com.it.design_pattern_furniture_web.common.user.UserUtils;
import com.it.design_pattern_furniture_web.models.services.mail_verify_token.VerifyTokenService;
import com.it.design_pattern_furniture_web.models.services.role.RoleService;
import com.it.design_pattern_furniture_web.models.services.user.UserService;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.roles.RoleViewModel;
import com.it.design_pattern_furniture_web.models.view_models.users.UserCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.users.UserViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.USER_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Register", value = "/register")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserViewModel user = (UserViewModel) session.getAttribute("user");
        if (user != null) {
            ServletUtils.redirect(response, request.getContextPath() + "/home");
        } else
            ServletUtils.forward(request, response, "/views/client/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        UserCreateRequest createReq = UserUtils.CreateRegisterRequest(request);
        createReq.setStatus(USER_STATUS.UN_CONFIRM);
        RoleGetPagingRequest reqRole = new RoleGetPagingRequest();
        ArrayList<RoleViewModel> roles = RoleService.getInstance().retrieveAllRole(reqRole);

        roles.removeIf(x -> !x.getRoleName().equalsIgnoreCase("khách hàng"));
        createReq.setRoleIds(new ArrayList<Integer>() {
            {
                add(roles.get(0).getRoleId());
            }
        });

        int userId = UserService.getInstance().insertUser(createReq);
        String status = "";
        if (userId < 1) {
            status = "?error=true";
        } else {
            boolean res = VerifyTokenService.getInstance().sendVerifyTokenMail(userId, request);
            if (res) {
                status = "?register=success";
            } else {
                status = "?error=true";
            }
        }
        ServletUtils.redirect(response, request.getContextPath() + "/signin" + status);
    }
}
