package com.pactomais.desafio_pacto_mais.repository;

import com.pactomais.desafio_pacto_mais.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaIdOrderByDataHoraDesc(Long contaId);
}