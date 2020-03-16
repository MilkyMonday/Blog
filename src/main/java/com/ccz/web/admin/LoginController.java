package com.ccz.web.admin;

import com.ccz.po.User;
import com.ccz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "redirect:/admin/blogs";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUserLogin(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("USER_SESSION", user);
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("USER_SESSION");
        return "redirect:/";
    }
}
