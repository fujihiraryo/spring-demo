package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("demo/api")
@RequiredArgsConstructor
public class DemoApiController {

    private DemoService demoService;

    @GetMapping
    public List<DemoEntity> get() {
        return demoService.getAll();
    }

    @PostMapping
    public void save(@RequestBody DemoEntity demoEntity) {
        demoService.set(demoEntity);
    }
}
