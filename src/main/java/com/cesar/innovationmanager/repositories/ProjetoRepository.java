package com.cesar.innovationmanager.repositories;

import com.cesar.innovationmanager.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
