package com.pactomais.desafio_pacto_mais.controller;

import com.pactomais.desafio_pacto_mais.dto.AberturaContaDTO;
import com.pactomais.desafio_pacto_mais.dto.TransacaoDTO;
import com.pactomais.desafio_pacto_mais.entity.Conta;
import com.pactomais.desafio_pacto_mais.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> abrirConta(@RequestBody AberturaContaDTO dto) {
        Conta contaCriada = contaService.abrirConta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
    }

    @PostMapping("/{numero}/deposito")
    public ResponseEntity<TransacaoDTO> depositar(@PathVariable String numero, @RequestParam BigDecimal valor) {
        TransacaoDTO transacao = contaService.depositar(numero, valor);
        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/{numero}/saque")
    public ResponseEntity<TransacaoDTO> sacar(@PathVariable String numero, @RequestParam BigDecimal valor) {
        TransacaoDTO transacao = contaService.sacar(numero, valor);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/{numero}/extrato")
    public ResponseEntity<List<TransacaoDTO>> obterExtrato(@PathVariable String numero) {
        List<TransacaoDTO> extrato = contaService.obterExtrato(numero);
        return ResponseEntity.ok(extrato);
    }

    @PostMapping("/{numero}/rendimento")
    public ResponseEntity<TransacaoDTO> aplicarRendimento(@PathVariable String numero, @RequestParam BigDecimal taxaPercentual) {
        TransacaoDTO transacao = contaService.aplicarRendimentoPoupanca(numero, taxaPercentual);
        return ResponseEntity.ok(transacao);
    }
}