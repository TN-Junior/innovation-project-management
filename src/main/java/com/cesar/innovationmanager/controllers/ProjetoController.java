package com.cesar.innovationmanager.controllers;


import com.cesar.innovationmanager.entities.Projeto;
import com.cesar.innovationmanager.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public List<Projeto> listarProjetos(){
        return projetoService.listarProjetos();
    }

    @PostMapping
    public Projeto criarProjeto(@RequestBody Projeto projeto){
        return projetoService.salvarProjeto(projeto);
    }
}
