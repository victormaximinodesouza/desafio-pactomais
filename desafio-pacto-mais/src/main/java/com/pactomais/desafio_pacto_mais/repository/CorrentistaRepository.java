package com.pactomais.desafio_pacto_mais.repository;

import com.pactomais.desafio_pacto_mais.entity.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Long> {
    Optional<Correntista> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
}