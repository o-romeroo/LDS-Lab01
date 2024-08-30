package com.pucmg.lab01.repositories;

import org.springframework.stereotype.Repository;

import com.pucmg.lab01.models.Curso;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    List<Curso> findAll();
    
}
