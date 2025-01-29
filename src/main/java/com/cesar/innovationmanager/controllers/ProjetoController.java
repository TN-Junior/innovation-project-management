package com.cesar.innovationmanager.controllers;


import com.cesar.innovationmanager.entities.Projeto;
import com.cesar.innovationmanager.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjeto(@PathVariable Long id){
        return projetoService.buscarProjetoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    @PutMapping("/{id}")
    public ResponseEntity<Projeto>atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) {
        return projetoService.atualizarProjeto(id, projetoAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable Long id) {
        projetoService.excluirProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<Projeto> listarProjetosPorStatus(@PathVariable String status){
        return projetoService.listarPorStatus(status);
    }

}
