package com.pactomais.desafio_pacto_mais.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "correntista")
public class Correntista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String contato;

    // Adicionado: Relacionamento de 1 correntista para N contas
    @OneToMany(mappedBy = "correntista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas = new ArrayList<>();

    // Construtor vazio (obrigatório para o Hibernate)
    public Correntista() {
    }

    // Construtor com argumentos
    public Correntista(String nome, String documento, String contato) {
        this.nome = nome;
        this.documento = documento;
        this.contato = contato;
    }

    // Método utilitário
    public void adicionarConta(Conta conta) {
        contas.add(conta);
        conta.setCorrentista(this);
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}