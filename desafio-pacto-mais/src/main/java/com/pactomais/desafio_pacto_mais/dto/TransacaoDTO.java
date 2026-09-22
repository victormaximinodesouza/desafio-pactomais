package com.pactomais.desafio_pacto_mais.dto;

import com.pactomais.desafio_pacto_mais.entity.TipoTransacao;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDTO {

    private Long id;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    // Construtor padrão (sem argumentos)
    public TransacaoDTO() {
    }

    // Construtor completo
    public TransacaoDTO(Long id, TipoTransacao tipo, BigDecimal valor, LocalDateTime dataHora) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}