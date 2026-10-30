
package br.com.ordensservico.aberturadeordendeservico.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.aberturadeordendeservico.model.Equipamento;
import br.com.ordensservico.aberturadeordendeservico.service.ServiceEquipamento;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final ServiceEquipamento service;

    public EquipamentoController(ServiceEquipamento service) {
        this.service = service;
    }

    // Cadastrar equipamento
    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
            @Valid @RequestBody Equipamento equipamento) {

        if (equipamento.getNome() == null
                || equipamento.getNome().isBlank()
                || equipamento.getNumeroPatrimonio() == null
                || equipamento.getNumeroPatrimonio().isBlank()
                || equipamento.getSetor() == null
                ) {

            return ResponseEntity.badRequest().build();
        }

        return service.cadastrar(equipamento)
                .map(novo -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(novo))
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Equipamento>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(
            @PathVariable Integer id) {

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Atualizar equipamento
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Equipamento dados) {

        if (dados.getNome() == null
                || dados.getNome().isBlank()
                || dados.getNumeroPatrimonio() == null
                || dados.getNumeroPatrimonio().isBlank()
                || dados.getSetor() == null
                ) {

            return ResponseEntity.badRequest().build();
        }

        return service.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Excluir equipamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        if (!service.excluir(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
