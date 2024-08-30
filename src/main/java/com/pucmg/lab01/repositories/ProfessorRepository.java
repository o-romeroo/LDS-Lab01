package com.pucmg.lab01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucmg.lab01.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findById(Long id);
    Optional<Professor> findByCPF(String CPF);
}