package com.pactomais.desafio_pacto_mais.controller;

import com.pactomais.desafio_pacto_mais.dto.CorrentistaDTO;
import com.pactomais.desafio_pacto_mais.service.CorrentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/correntistas")
public class CorrentistaController {

    private final CorrentistaService correntistaService;

    public CorrentistaController(CorrentistaService correntistaService) {
        this.correntistaService = correntistaService;
    }

    @PostMapping
    public ResponseEntity<CorrentistaDTO> cadastrar(@RequestBody CorrentistaDTO dto) {
        CorrentistaDTO correntistaSalvo = correntistaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(correntistaSalvo);
    }

    @GetMapping
    public ResponseEntity<List<CorrentistaDTO>> listarTodos() {
        return ResponseEntity.ok(correntistaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorrentistaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(correntistaService.buscarPorId(id));
    }
}