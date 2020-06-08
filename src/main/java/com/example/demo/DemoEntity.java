package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "demo")
@Getter
@Setter
public class DemoEntity {
    @Id
    private int id;
    private String name;
    private boolean exist;
}
