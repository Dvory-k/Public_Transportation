package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Models.*;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

    Line findByNumber(String number);

}
