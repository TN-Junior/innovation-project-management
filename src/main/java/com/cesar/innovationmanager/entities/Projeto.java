package com.cesar.innovationmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do projeto é obrigatório")
    private String nome;

    @Size(max = 500, message = "A descrição não pode ter mais que 500 caracteres")
    private String descricao;

    @NotBlank(message = "O status do projeto é obrigatório")
    private String status;

    @PastOrPresent(message = "A data de início deve ser no passado ou presente")
    private LocalDateTime dataInicio;

    @FutureOrPresent(message = "A data de fim deve ser no futuro ou presente")
    private LocalDateTime dataFim;

    @Positive(message = "O orçamento deve ser um valor positivo")
    private BigDecimal orcamento;

    @NotBlank(message = "O líder do projeto é obrigatório")
    private String liderProjeto;

    @ElementCollection
    private List<@NotBlank String> integrantes;
}
