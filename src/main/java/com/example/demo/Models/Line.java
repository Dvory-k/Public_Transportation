package com.example.demo.Models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "lines")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String source;
    private String destination;

    //לכל קו יש הרבה נסיעות
    @OneToMany(mappedBy = "line", fetch = FetchType.LAZY)
    private List<Travel> travels;

    //  @ManyToMany(mappedBy = "lines")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "line", joinColumns = @JoinColumn(name = "line_id"), inverseJoinColumns = @JoinColumn(name = "station_id"))
    private List<StationLine> stationsLines;

}
