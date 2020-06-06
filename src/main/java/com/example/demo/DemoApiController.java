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

    private DemoRepository demoRepository;

    @GetMapping
    public List<DemoEntity> findAll() {
        return demoRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody DemoEntity demoEntity) {
        demoRepository.save(demoEntity);
    }
}
