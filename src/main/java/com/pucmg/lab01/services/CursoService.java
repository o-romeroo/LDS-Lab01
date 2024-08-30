package com.pucmg.lab01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.pucmg.lab01.models.Curso;
import com.pucmg.lab01.repositories.CursoRepository;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;
    
    public List <Curso> getAllCursos() {    
        return cursoRepository.findAll();
    }

}
