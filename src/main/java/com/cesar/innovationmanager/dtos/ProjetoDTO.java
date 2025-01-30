package com.cesar.innovationmanager.dtos;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public record ProjetoDTO(
        Long id,
        String nome,
        String descricao,
        String status,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        BigDecimal orcamento,
        String liderProjeto,
        List<String> integrantes
) {}
