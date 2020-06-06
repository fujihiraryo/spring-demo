package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demo_table")
public class DemoEntity {
    @Id
    private Integer id;
    private String name;
}
