package nal.vn.demorestapispringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkController {
    @RequestMapping("work")
    @ResponseBody
    public String home() {
        return "My work";
    }
}
