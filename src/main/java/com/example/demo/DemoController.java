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
    private final DemoRepository demoRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("demoEntities", demoRepository.findAll());
        model.addAttribute("newEntity", new DemoEntity());
        return "demo.html";
    }

    @PostMapping
    public String post(DemoEntity demoEntity, Model model) {
        demoRepository.save(demoEntity);
        return get(model);
    }
}
