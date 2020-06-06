package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoRepository demoRepository;

    public List<DemoEntity> getAll() {
        return demoRepository.findAll();
    }

    public void set(DemoEntity demoEntity) {
        demoRepository.save(demoEntity);
    }
}
