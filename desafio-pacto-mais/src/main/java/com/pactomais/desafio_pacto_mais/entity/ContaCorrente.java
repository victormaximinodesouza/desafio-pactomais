package com.pactomais.desafio_pacto_mais.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "conta_corrente")
@PrimaryKeyJoinColumn(name = "conta_id")
public class ContaCorrente extends Conta {

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal limite = BigDecimal.ZERO;

    public ContaCorrente() {
        super();
    }

    public ContaCorrente(String numero, BigDecimal saldo, Correntista correntista, BigDecimal limite) {
        super(numero, saldo);
        setCorrentista(correntista);
        this.limite = limite != null ? limite : BigDecimal.ZERO;
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }

        BigDecimal saldoDisponivel = getSaldo().add(this.limite);

        if (valor.compareTo(saldoDisponivel) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente, incluindo o limite disponível.");
        }

        setSaldo(getSaldo().subtract(valor));
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
}