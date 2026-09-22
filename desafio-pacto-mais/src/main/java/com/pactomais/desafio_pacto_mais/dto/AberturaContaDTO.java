package com.pactomais.desafio_pacto_mais.dto;

import java.math.BigDecimal;

public class AberturaContaDTO {

    private String numero;
    private Long correntistaId;
    private String tipoConta; // "CORRENTE" ou "POUPANCA"
    private BigDecimal saldoInicial;
    private BigDecimal limite; // Exclusivo para Conta Corrente

    // Construtor padrão (sem argumentos)
    public AberturaContaDTO() {
    }

    // Construtor completo
    public AberturaContaDTO(String numero, Long correntistaId, String tipoConta, BigDecimal saldoInicial, BigDecimal limite) {
        this.numero = numero;
        this.correntistaId = correntistaId;
        this.tipoConta = tipoConta;
        this.saldoInicial = saldoInicial;
        this.limite = limite;
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getCorrentistaId() {
        return correntistaId;
    }

    public void setCorrentistaId(Long correntistaId) {
        this.correntistaId = correntistaId;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}