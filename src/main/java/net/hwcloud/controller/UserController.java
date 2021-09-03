package net.hwcloud.controller;

import net.hwcloud.dto.User;
import net.hwcloud.dto.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    @Autowired // UserRepository가 어디에 생성되어 있다고 생각하고 가져다 쓰는 개념
    private UserRepository userRepository;

    @GetMapping("/form")
    public String form() {
        return "user/form"; //form.html of mustache
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).get();
        System.out.println(user);
        model.addAttribute("user", user);
        return "/user/updateForm"; //updateForm.html of mustache
    }

    @PostMapping("/update")
    public String update(User newUser) {
        System.out.println(newUser.getId());
        System.out.println(userRepository.findById(newUser.getId()).get());
        User user = userRepository.findById(newUser.getId()).get();
        user.update(newUser);
        userRepository.save(user);
        return "redirect:/users/list";
    }

    @PostMapping("/create")
    public String create(User user) {
        System.out.println("User : " + user);
        //users.add(user);
        userRepository.save(user);
        return "redirect:/users/list"; // http://localhost:8080/users/list
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list"; //list.html of mustache
    }
}
