package com.pactomais.desafio_pacto_mais.dto;

public class CorrentistaDTO {

    private Long id;
    private String nome;
    private String documento;
    private String contato;

    // Construtor padrão (sem argumentos)
    public CorrentistaDTO() {
    }

    // Construtor completo
    public CorrentistaDTO(Long id, String nome, String documento, String contato) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.contato = contato;
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
}