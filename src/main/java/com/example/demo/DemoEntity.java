package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DemoEntity {
    @Id
    private int id;
    private String name;
    private Boolean exist;
}
