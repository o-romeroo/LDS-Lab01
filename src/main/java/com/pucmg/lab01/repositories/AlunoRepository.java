package com.pucmg.lab01.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pucmg.lab01.models.Aluno;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findById(Long id);
    Optional<Aluno> findByCPF(String cPF);
}
