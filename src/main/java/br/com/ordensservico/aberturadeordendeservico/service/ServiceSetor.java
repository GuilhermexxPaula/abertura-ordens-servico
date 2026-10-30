
package br.com.ordensservico.aberturadeordendeservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturadeordendeservico.model.Setor;
import br.com.ordensservico.aberturadeordendeservico.repository.SetorRepository;

@Service
public class ServiceSetor {

    private final SetorRepository repository;

    public ServiceSetor(SetorRepository repository) {
        this.repository = repository;
    }

    // Cadastrar
    public Setor cadastrar(Setor setor) {
        
        return repository.save(setor);
    }

    // Listar todos
    public List<Setor> listarTodos() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Setor> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    // Atualizar
    public Optional<Setor> atualizar(Integer id, Setor dados) {
        return repository.findById(id).map(setor -> {
            setor.setNome(dados.getNome());
            return repository.save(setor);
        });
    }

    // Excluir
    public boolean excluir(Integer id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
    
