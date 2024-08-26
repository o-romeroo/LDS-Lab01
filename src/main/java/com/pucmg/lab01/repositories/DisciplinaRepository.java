package com.pucmg.lab01.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pucmg.lab01.models.Disciplina;



@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
    // buscar disciplina pelo nome
    Optional<Disciplina> findByNome(String nome);

}
