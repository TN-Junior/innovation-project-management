package com.cesar.innovationmanager.services;

import com.cesar.innovationmanager.entities.Projeto;
import com.cesar.innovationmanager.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> listarProjetos(){
        return projetoRepository.findAll();
    }

    public Projeto salvarProjeto(Projeto projeto){
        return projetoRepository.save(projeto);
    }
}
