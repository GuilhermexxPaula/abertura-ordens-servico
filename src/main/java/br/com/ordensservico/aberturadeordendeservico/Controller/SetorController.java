
package br.com.ordensservico.aberturadeordendeservico.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ordensservico.aberturadeordendeservico.model.Setor;
import br.com.ordensservico.aberturadeordendeservico.service.ServiceSetor;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final ServiceSetor service;

    public SetorController(ServiceSetor service) {
        this.service = service;
    }

    // Cadastrar setor
    @PostMapping
    public ResponseEntity<Setor> cadastrar(
            @RequestBody Setor setor) {

        if (setor.getNome() == null ||
                setor.getNome().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Setor novoSetor = service.cadastrar(setor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoSetor);
    }

    // Listar todos os setores
    @GetMapping
    public ResponseEntity<List<Setor>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Buscar setor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Setor> buscarPorId(
            @PathVariable Integer id) {

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Atualizar setor
    @PutMapping("/{id}")
    public ResponseEntity<Setor> atualizar(
            @PathVariable Integer id,
            @RequestBody Setor dados) {

        if (dados.getNome() == null ||
                dados.getNome().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return service.atualizar(id, dados)
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());
    }

    // Excluir setor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        boolean excluido = service.excluir(id);

        if (!excluido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

