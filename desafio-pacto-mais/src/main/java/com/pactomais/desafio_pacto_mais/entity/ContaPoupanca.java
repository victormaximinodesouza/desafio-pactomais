package com.pactomais.desafio_pacto_mais.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "conta_poupanca")
@PrimaryKeyJoinColumn(name = "conta_id")
public class ContaPoupanca extends Conta {

    public ContaPoupanca() {
        super();
    }

    public ContaPoupanca(String numero, BigDecimal saldo, Correntista correntista) {
        super(numero, saldo);
        setCorrentista(correntista);
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }

        if (valor.compareTo(getSaldo()) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }

        setSaldo(getSaldo().subtract(valor));
    }

    public void aplicarRendimento(BigDecimal taxaPercentual) {
        if (taxaPercentual == null || taxaPercentual.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A taxa de rendimento deve ser maior que zero.");
        }

        BigDecimal fator = taxaPercentual.divide(new BigDecimal("100"));
        BigDecimal rendimento = getSaldo().multiply(fator);
        depositar(rendimento);
    }
}