package com.pactomais.desafio_pacto_mais.repository;

import com.pactomais.desafio_pacto_mais.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumero(String numero);
    boolean existsByNumero(String numero);
}