package com.cesar.innovationmanager.services;

import com.cesar.innovationmanager.entities.Projeto;
import com.cesar.innovationmanager.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

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

    public Optional<Projeto> buscarProjetoPorId(Long id){
        return projetoRepository.findById(id);
    }

    public Optional<Projeto> atualizarProjeto(Long id, Projeto projetoAtualizado) {
        return projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoAtualizado.getNome());
            projeto.setDescricao(projetoAtualizado.getDescricao());
            projeto.setStatus(projetoAtualizado.getStatus());
            projeto.setDataInicio(projetoAtualizado.getDataInicio());
            projeto.setDataFim(projetoAtualizado.getDataFim());
            projeto.setOrcamento(projetoAtualizado.getOrcamento());
            projeto.setLiderProjeto(projetoAtualizado.getLiderProjeto());
            projeto.setIntegrantes(projetoAtualizado.getIntegrantes());
            return projetoRepository.save(projeto);

        });

    }
    public void excluirProjeto(Long id){
        projetoRepository.deleteById(id);
    }

    public List<Projeto> listarPorStatus(String status){
        return projetoRepository.findByStatus(status);
    }
}
