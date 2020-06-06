package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("demoEntities", demoService.getAll());
        model.addAttribute("newEntity", new DemoEntity());
        return "demo.html";
    }

    @PostMapping
    public String post(DemoEntity demoEntity, Model model) {
        demoService.set(demoEntity);
        return get(model);
    }
}
