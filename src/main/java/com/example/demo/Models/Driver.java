package com.example.demo.Models;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "drivers")
public class Driver {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;

    //לכל נהג יש הרבה נסיעות
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Travel> travels;
}
