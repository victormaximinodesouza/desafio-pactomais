package com.pactomais.desafio_pacto_mais.service;

import com.pactomais.desafio_pacto_mais.dto.AberturaContaDTO;
import com.pactomais.desafio_pacto_mais.dto.TransacaoDTO;
import com.pactomais.desafio_pacto_mais.entity.*;
import com.pactomais.desafio_pacto_mais.exception.BusinessException;
import com.pactomais.desafio_pacto_mais.exception.ResourceNotFoundException;
import com.pactomais.desafio_pacto_mais.repository.ContaRepository;
import com.pactomais.desafio_pacto_mais.repository.CorrentistaRepository;
import com.pactomais.desafio_pacto_mais.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CorrentistaRepository correntistaRepository;
    private final TransacaoRepository transacaoRepository;

    public ContaService(ContaRepository contaRepository, CorrentistaRepository correntistaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.correntistaRepository = correntistaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public Conta abrirConta(AberturaContaDTO dto) {
        if (contaRepository.existsByNumero(dto.getNumero())) {
            throw new BusinessException("Já existe uma conta cadastrada com este número.");
        }

        Correntista correntista = correntistaRepository.findById(dto.getCorrentistaId())
                .orElseThrow(() -> new ResourceNotFoundException("Correntista não encontrado com ID: " + dto.getCorrentistaId()));

        Conta conta;
        if ("CORRENTE".equalsIgnoreCase(dto.getTipoConta())) {
            BigDecimal limite = dto.getLimite() != null ? dto.getLimite() : BigDecimal.ZERO;
            conta = new ContaCorrente(dto.getNumero(), dto.getSaldoInicial(), correntista, limite);
        } else if ("POUPANCA".equalsIgnoreCase(dto.getTipoConta())) {
            conta = new ContaPoupanca(dto.getNumero(), dto.getSaldoInicial(), correntista);
        } else {
            throw new BusinessException("Tipo de conta inválido. Escolha 'CORRENTE' ou 'POUPANCA'.");
        }

        return contaRepository.save(conta);
    }

    @Transactional
    public TransacaoDTO depositar(String numeroConta, BigDecimal valor) {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.depositar(valor);

        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor, conta);
        transacaoRepository.save(transacao);
        contaRepository.save(conta);

        return toTransacaoDTO(transacao);
    }

    @Transactional
    public TransacaoDTO sacar(String numeroConta, BigDecimal valor) {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.sacar(valor);

        Transacao transacao = new Transacao(TipoTransacao.SAQUE, valor, conta);
        transacaoRepository.save(transacao);
        contaRepository.save(conta);

        return toTransacaoDTO(transacao);
    }

    @Transactional(readOnly = true)
    public List<TransacaoDTO> obterExtrato(String numeroConta) {
        Conta conta = buscarContaPorNumero(numeroConta);
        return transacaoRepository.findByContaIdOrderByDataHoraDesc(conta.getId())
                .stream()
                .map(this::toTransacaoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TransacaoDTO aplicarRendimentoPoupanca(String numeroConta, BigDecimal taxaPercentual) {
        Conta conta = buscarContaPorNumero(numeroConta);

        if (!(conta instanceof ContaPoupanca)) {
            throw new BusinessException("Esta operação só é permitida para Conta Poupança.");
        }

        ContaPoupanca poupanca = (ContaPoupanca) conta;
        BigDecimal saldoAnterior = poupanca.getSaldo();
        poupanca.aplicarRendimento(taxaPercentual);
        BigDecimal valorRendimento = poupanca.getSaldo().subtract(saldoAnterior);

        Transacao transacao = new Transacao(TipoTransacao.RENDIMENTO, valorRendimento, poupanca);
        transacaoRepository.save(transacao);
        contaRepository.save(poupanca);

        return toTransacaoDTO(transacao);
    }

    public Conta buscarContaPorNumero(String numero) {
        return contaRepository.findByNumero(numero)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com o número: " + numero));
    }

    private TransacaoDTO toTransacaoDTO(Transacao transacao) {
        return new TransacaoDTO(
                transacao.getId(),
                transacao.getTipo(),
                transacao.getValor(),
                transacao.getDataHora()
        );
    }
}