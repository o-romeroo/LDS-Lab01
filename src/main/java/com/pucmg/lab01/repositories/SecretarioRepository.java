package com.pucmg.lab01.repositories;

import com.pucmg.lab01.models.Secretario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SecretarioRepository extends JpaRepository<Secretario, Long> {
    
}
