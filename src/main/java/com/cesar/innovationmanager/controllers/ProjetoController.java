package com.cesar.innovationmanager.controllers;


import com.cesar.innovationmanager.dtos.ProjetoDTO;
import com.cesar.innovationmanager.entities.Projeto;
import com.cesar.innovationmanager.services.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> listarProjetos() {
        List<ProjetoDTO> projetos = projetoService.listarProjetos();
        return ResponseEntity.ok(projetos);
    }

    @PostMapping
    public ResponseEntity<ProjetoDTO> criarProjeto(@RequestBody @Valid ProjetoDTO projetoDTO) {
        ProjetoDTO novoProjeto = projetoService.salvarProjeto(projetoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProjeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDTO> buscarProjeto(@PathVariable Long id) {
        return projetoService.buscarProjetoPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoDTO> atualizarProjeto(@PathVariable Long id, @RequestBody @Valid ProjetoDTO projetoDTO) {
        return ResponseEntity.ok(projetoService.atualizarProjeto(id, projetoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable Long id) {
        projetoService.excluirProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProjetoDTO>> listarProjetosPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(projetoService.listarPorStatus(status));
    }
}
