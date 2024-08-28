package org.beko.spring.http.controller;

import org.beko.spring.database.entity.Role;
import org.beko.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request,
                        @ModelAttribute("user") UserReadDto userReadDto) {
        model.addAttribute("user", new UserReadDto(1L, "Beko"));

        return "greeting/hello";
    }

    @GetMapping("bye")
    public String bye(ModelAndView modelAndView,
                      @SessionAttribute("user") UserReadDto userReadDto,
                      Model model) {
        return "greeting/bye";
    }

//    @RequestMapping(value = "hello", method = RequestMethod.GET) -> @GetMapping("/hello")
    @GetMapping("/hello/{id}")
    public String hello2(ModelAndView modelAndView, HttpServletRequest request,
                              @RequestParam(value = "age", required = false) Integer age,
                              @RequestHeader() String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        return "greeting/hello";
    }
}




