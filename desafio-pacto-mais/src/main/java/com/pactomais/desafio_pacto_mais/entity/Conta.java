package com.pactomais.desafio_pacto_mais.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED) // Adicionado para mapear as subclasses corretamente
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO; // Alterado de Double para BigDecimal

    // Relacionamento: Várias contas pertencem a um único correntista
    @ManyToOne
    @JoinColumn(name = "correntista_id", nullable = false)
    private Correntista correntista;

    // Construtor vazio (obrigatório para o Hibernate)
    public Conta() {
    }

    // Construtor com argumentos
    public Conta(String numero, BigDecimal saldo) {
        this.numero = numero;
        this.saldo = saldo != null ? saldo : BigDecimal.ZERO;
    }

    // Métodos de Regra de Negócio que serão usados no Service
    public void depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        this.saldo = this.saldo.add(valor);
    }

    public abstract void sacar(BigDecimal valor);

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    protected void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public void setCorrentista(Correntista correntista) {
        this.correntista = correntista;
    }
}