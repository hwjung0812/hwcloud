package net.hwcloud.controller;

import net.hwcloud.dto.User;
import net.hwcloud.dto.UserRepository;
import net.hwcloud.utils.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    @Autowired // UserRepository가 어디에 생성되어 있다고 생각하고 가져다 쓰는 개념
    private UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/login"; // login.html
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession httpSession) {

        User user = userRepository.findByUserId(userId);
        
        // 객체 지향에서 user와 같은 객체에서 값을 긁어오지 말고 일을 시켜라.
        if(user==null || !user.comparePassword(password)) {
            System.out.println("Login failed!!");
            return "redirect:/users/loginForm";
        }

        // 세션에 로그인 사용자 정보 저장
        httpSession.setAttribute("sessionUser", user);
        System.out.println("로그인 한 사용자 :" + user.getUserId());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {

        // 세션 정보 제거
        System.out.println(httpSession.getAttribute("user") + " : 로그아웃한 사용자");
        httpSession.removeAttribute("sessionUser");
        return "redirect:/";
    }

    @GetMapping("/form")
    public String form() {
        return "user/form"; //form.html of mustache
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {

        User sessionUser = HttpSessionUtils.getUserfromSession(httpSession);
        // 사용자가 로그인 했는지 우선 확인
        // 로그인 한 사용자가 아닌 경우
        if(!HttpSessionUtils.isLoginUser(httpSession)) {
            return "user/login"; // 로그인 페이지로 안내
        }
        // 로그인 한 사용자가 수정하려는 사용자의 ID와 일치하지 않는 경우
        else {
            // getId 대신 비교 method를 생성
            if(!sessionUser.compareId(id)) {
                throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
            }
        }

        User user = userRepository.findById(id).get();
        System.out.println(user);
        model.addAttribute("user", user);
        return "user/updateForm"; //updateForm.html of mustache
    }

    @PostMapping("/update")
    public String update(User newUser, HttpSession httpSession) {
        System.out.println(newUser.getId());
        System.out.println(userRepository.findById(newUser.getId()).get());

        // 사용자가 로그인 했는지 우선 확인
        // 로그인 한 사용자가 아닌 경우
        if(!HttpSessionUtils.isLoginUser(httpSession)) {
            return "user/login"; // 로그인 페이지로 안내
        }

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
