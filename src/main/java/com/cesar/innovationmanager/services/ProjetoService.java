package com.cesar.innovationmanager.services;

import com.cesar.innovationmanager.dtos.ProjetoDTO;
import com.cesar.innovationmanager.entities.Projeto;
import com.cesar.innovationmanager.entities.StatusProjeto;
import com.cesar.innovationmanager.repositories.ProjetoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public List<ProjetoDTO> listarProjetos() {
        return projetoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProjetoDTO salvarProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = projetoRepository.save(toEntity(projetoDTO));
        return toDTO(projeto);
    }

    public Optional<ProjetoDTO> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id).map(this::toDTO);
    }

    public ProjetoDTO atualizarProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));

        projeto.setNome(projetoDTO.nome());
        projeto.setDescricao(projetoDTO.descricao());
        projeto.setStatus(StatusProjeto.valueOf(projetoDTO.status().toUpperCase()));
        projeto.setDataInicio(projetoDTO.dataInicio());
        projeto.setDataFim(projetoDTO.dataFim());
        projeto.setOrcamento(projetoDTO.orcamento());
        projeto.setLiderProjeto(projetoDTO.liderProjeto());
        projeto.setIntegrantes(Optional.ofNullable(projetoDTO.integrantes())
                .map(HashSet::new)
                .orElse(new HashSet<>()));

        return toDTO(projetoRepository.save(projeto));
    }

    public void excluirProjeto(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new EntityNotFoundException("Projeto não encontrado");
        }
        projetoRepository.deleteById(id);
    }

    public List<ProjetoDTO> listarPorStatus(String status) {
        return projetoRepository.findByStatus(status).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ProjetoDTO toDTO(Projeto projeto) {
        return new ProjetoDTO(projeto.getId(), projeto.getNome(), projeto.getDescricao(),
                projeto.getStatus().name(), projeto.getDataInicio(), projeto.getDataFim(),
                projeto.getOrcamento(), projeto.getLiderProjeto(), new ArrayList<>(projeto.getIntegrantes())
        );
    }

    private Projeto toEntity(ProjetoDTO dto) {
        return new Projeto(
                dto.id(),
                dto.nome(),
                dto.descricao(),
                dto.status() != null ? StatusProjeto.valueOf(dto.status().toUpperCase()) : StatusProjeto.RASCUNHO,
                dto.dataInicio(),
                dto.dataFim(),
                dto.orcamento(),
                dto.liderProjeto(),
                dto.integrantes() !=null ? new HashSet<>(dto.integrantes()) : new HashSet<>()
                );
        }
}
