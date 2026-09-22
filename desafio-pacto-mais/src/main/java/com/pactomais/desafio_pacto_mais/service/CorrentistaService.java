package com.pactomais.desafio_pacto_mais.service;

import com.pactomais.desafio_pacto_mais.dto.CorrentistaDTO;
import com.pactomais.desafio_pacto_mais.entity.Correntista;
import com.pactomais.desafio_pacto_mais.exception.BusinessException;
import com.pactomais.desafio_pacto_mais.exception.ResourceNotFoundException;
import com.pactomais.desafio_pacto_mais.repository.CorrentistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorrentistaService {

    private final CorrentistaRepository correntistaRepository;

    public CorrentistaService(CorrentistaRepository correntistaRepository) {
        this.correntistaRepository = correntistaRepository;
    }

    @Transactional
    public CorrentistaDTO salvar(CorrentistaDTO dto) {
        if (correntistaRepository.existsByDocumento(dto.getDocumento())) {
            throw new BusinessException("Já existe um correntista cadastrado com este documento.");
        }

        Correntista correntista = new Correntista(dto.getNome(), dto.getDocumento(), dto.getContato());
        correntista = correntistaRepository.save(correntista);

        return toDTO(correntista);
    }

    @Transactional(readOnly = true)
    public List<CorrentistaDTO> listarTodos() {
        return correntistaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CorrentistaDTO buscarPorId(Long id) {
        Correntista correntista = correntistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Correntista não encontrado com o ID: " + id));
        return toDTO(correntista);
    }

    private CorrentistaDTO toDTO(Correntista correntista) {
        return new CorrentistaDTO(
                correntista.getId(),
                correntista.getNome(),
                correntista.getDocumento(),
                correntista.getContato()
        );
    }
}