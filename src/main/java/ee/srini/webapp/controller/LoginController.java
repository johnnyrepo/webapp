package ee.srini.webapp.controller;

import ee.srini.webapp.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final AppUserService appUserService;

    @GetMapping("/")
    public String index() {
        return login();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginHandle(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        Long appUserId = appUserService.login(username, password);
        if (appUserId != null) {
            session.setAttribute("appUserId", appUserId);

            return "redirect:/clients";
        }

        return "login";
    }

}
