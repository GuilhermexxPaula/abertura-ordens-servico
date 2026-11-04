
package br.com.ordensservico.aberturadeordendeservico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ordensservico.aberturadeordendeservico.dto.EquipamentoRequest;
import br.com.ordensservico.aberturadeordendeservico.model.Equipamento;
import br.com.ordensservico.aberturadeordendeservico.model.Setor;
import br.com.ordensservico.aberturadeordendeservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturadeordendeservico.repository.SetorRepository;

@Service
public class ServiceEquipamento {

    private final EquipamentoRepository equipamentoRepository;
    private final SetorRepository setorRepository;

    public ServiceEquipamento(
            EquipamentoRepository equipamentoRepository,
            SetorRepository setorRepository) {

        this.equipamentoRepository = equipamentoRepository;
        this.setorRepository = setorRepository;
    }

    // Cadastrar equipamento
    public Optional<Equipamento> cadastrar(EquipamentoRequest equipamento) {

        if (equipamento == null
                || equipamento.getNome() == null
                || equipamento.getNome().isBlank()
                || equipamento.getNumeroPatrimonio() == null
                || equipamento.getNumeroPatrimonio().isBlank()
                || equipamento.getSetorId() == null) {

            return Optional.empty();
        }

        Optional<Setor> setorEncontrado =
                setorRepository.findById(equipamento.getSetorId());

        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento novoEquipamento = new Equipamento();

        novoEquipamento.setNome(equipamento.getNome());
        novoEquipamento.setNumeroPatrimonio(
                equipamento.getNumeroPatrimonio());
        novoEquipamento.setSetor(setorEncontrado.get());

        return Optional.of(
                equipamentoRepository.save(novoEquipamento));
    }

    // Listar todos os equipamentos
    public List<Equipamento> listarTodos() {
        return equipamentoRepository.findAll();
    }

    // Buscar equipamento por ID
    public Optional<Equipamento> buscarPorId(Integer id) {

        if (id == null) {
            return Optional.empty();
        }

        return equipamentoRepository.findById(id);
    }

    // Atualizar equipamento
    public Optional<Equipamento> atualizar(
            Integer id, Equipamento dados) {

        if (id == null
                || dados == null
                || dados.getNome() == null
                || dados.getNome().isBlank()
                || dados.getNumeroPatrimonio() == null
                || dados.getNumeroPatrimonio().isBlank()
                || dados.getSetor() == null
                ) {

            return Optional.empty();
        }

        Optional<Equipamento> equipamentoEncontrado =
                equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Optional<Setor> setorEncontrado =
                setorRepository.findById(dados.getSetor().getId());

        if (setorEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();

        equipamento.setNome(dados.getNome());

        equipamento.setNumeroPatrimonio(
                dados.getNumeroPatrimonio());

        equipamento.setSetor(setorEncontrado.get());

        return Optional.of(
                equipamentoRepository.save(equipamento));
    }

    // Excluir equipamento
    public boolean excluir(Integer id) {

        if (id == null
                || !equipamentoRepository.existsById(id)) {

            return false;
        }

        equipamentoRepository.deleteById(id);

        return true;
    }
}


