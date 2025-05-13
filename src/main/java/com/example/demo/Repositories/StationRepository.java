package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Models.*;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Station findByName(String name);
}
