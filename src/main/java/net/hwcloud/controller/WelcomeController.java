package net.hwcloud.controller;

import net.hwcloud.dto.MyModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
//@RestController
public class WelcomeController {

    // GET 방식
    @GetMapping("/hello")
    public String welcome(String name, int age, Model model) { // Model은 MVC 패턴 사용 목적
        System.out.println("name : " + name + " age : " + age);
        // Controller -> Model -> View(welcome.html)
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "welcome"; // welcome 뒤에 .html을 붙인다. 즉, 파일명만 명시하면 된다.
    }

    // GET 방식
    @GetMapping("/helloworld")
    public String welcome(Model model) { // Model은 MVC 패턴 사용 목적

        List<MyModel> repo = Arrays.asList(
                new MyModel("hwjung"),
                new MyModel("test")
        );
        model.addAttribute("repo", repo);
        return "mymodel"; // welcome 뒤에 .html을 붙인다. 즉, 파일명만 명시하면 된다.
    }



}
