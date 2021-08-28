package net.hwcloud.controller;

import net.hwcloud.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private List<User> users = new ArrayList<>();

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("User : " + user);
        users.add(user);
        // http://localhost:8080/list로 이동하기 위해서 사용
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", users);
        return "list";
    }



}
