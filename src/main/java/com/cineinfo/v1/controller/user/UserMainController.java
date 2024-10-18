package com.cineinfo.v1.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserMainController {

    @GetMapping("/")
    public String userHome() {
        return "user/index";
    }
}
