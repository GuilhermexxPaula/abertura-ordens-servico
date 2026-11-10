
package br.com.ordensservico.aberturadeordendeservico.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import br.com.ordensservico.aberturadeordendeservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturadeordendeservico.model.OrdemServico;
import br.com.ordensservico.aberturadeordendeservico.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(
            OrdemServicoService ordemServicoService) {

        this.ordemServicoService = ordemServicoService;
    }

    // POST - CADASTRAR
    @PostMapping
    public ResponseEntity<OrdemServico> cadastrar(
            @Valid @RequestBody OrdemServicoRequest request) {

        OrdemServico ordem = ordemServicoService.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ordem);
    }

    // GET - LISTAR TODAS
    @GetMapping
    public ResponseEntity<List<OrdemServico>> listarTodas() {

        return ResponseEntity.ok(
                ordemServicoService.listarTodas()
        );
    }

    // GET - BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                ordemServicoService.buscarPorId(id)
        );
    }
}
