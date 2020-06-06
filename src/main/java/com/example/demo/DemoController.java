package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private DemoRepository demoRepository;

    @GetMapping
    public String findAll(Model model) {
        List<DemoEntity> demoEntities = demoRepository.findAll();
        model.addAttribute("demoEntities", demoEntities);
        return "demo";
    }
}
