package wsf.controller;

import wsf.domain.User;
import wsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 用户登录和注销Controller
@Controller
public class UserController {

    // 注入 userService
    @Autowired
    private UserService userService;

    // 用户登录
    @RequestMapping("login")
    public String login(User user, HttpServletRequest request) {
        try {
            User ulogin = userService.login(user);
            //账号和密码查询用户信息 存在存入session 跳转到首页
            // 不存在 Request域中存入提示信息 并跳转到登录页面
            if (ulogin != null) {
                request.getSession().setAttribute("USER_SESSION",ulogin);
                return "redirect:/MainPage";
            }
            request.setAttribute("msg","用户名或密码错误");
            return  "forward:/admin/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return  "forward:/admin/login.jsp";
        }
    }

    // 跳转到系统后台首页的方法
    @RequestMapping("/MainPage")
    public String toMainPage() {
        return "main";
    }

    // 注销登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            // 销毁session
            session.invalidate();
            return "forward:/admin/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","系统错误");
            return  "forward:/admin/login.jsp";
        }
    }
}
