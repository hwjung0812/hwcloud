package net.hwcloud.controller;

import net.hwcloud.dto.Question;
import net.hwcloud.dto.QuestionRepository;
import net.hwcloud.utils.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired 
    // Repository를 spring framework이 관리하고 있는데
    // 내가 직접 관리하고자 하는 경우 나한테 인자를 전달해달라는 의미
    private QuestionRepository questionRepository;

    @GetMapping("/form")
    public String form(HttpSession httpSession, Model model) {

        if(!HttpSessionUtils.isLoginUser(httpSession)) {
            return "redirect:/users/loginForm";
        }
        model.addAttribute("writer",HttpSessionUtils.getUserfromSession(httpSession));
        return "qna/form"; // form.html
    }

    @PostMapping("/create")
    public String create(String writer, String title, String contents, HttpSession httpSession) {

        if(!HttpSessionUtils.isLoginUser(httpSession)) {
            return "redirect:/users/loginForm"; // http://localhost:8080/users/loginForm
        }
        System.out.println(writer + title + contents);
        Question question = new Question(writer, title, contents);
        questionRepository.save(question);
        return "redirect:/";
    }

}
