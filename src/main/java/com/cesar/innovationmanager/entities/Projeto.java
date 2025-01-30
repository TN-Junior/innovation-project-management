package com.cesar.innovationmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projetos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do projeto é obrigatório")
    @Column(nullable = false, length = 150)
    private String nome;

    @Size(max = 500, message = "A descrição não pode ter mais que 500 caracteres")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProjeto status = StatusProjeto.RASCUNHO;


    @PastOrPresent(message = "A data de início deve ser no passado ou presente")
    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @FutureOrPresent(message = "A data de fim deve ser no futuro ou presente")
    @Column(nullable = false)
    private LocalDateTime dataFim;

    @Positive(message = "O orçamento deve ser um valor positivo")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal orcamento;

    @NotBlank(message = "O líder do projeto é obrigatório")
    @Column(nullable = false)
    private String liderProjeto;

    @ElementCollection(fetch = FetchType.EAGER) // carrega uma lista de integrantes junto ao projeto
    @CollectionTable(name = "projeto_integrantes", joinColumns = @JoinColumn(name = "projeto_id"))
    @Column(name = "integrante", nullable = false)
    private Set<@NotBlank String> integrantes;

    @CreationTimestamp // timestamp automático na criação
    @Column(updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp // Atualiza quando há modificações
    private LocalDateTime atualizadoEm;

    @PrePersist
    protected void PrePersist() {
        if (dataInicio == null) {
            dataInicio = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void preUpdade(){
        if (dataInicio.isAfter(dataFim)){
            throw new IllegalStateException("A data de início não pode ser depois da data de fim");
        }
    }

    public boolean isAprovado(){
        return this.status == StatusProjeto.APROVADO;
    }
}
