
package br.com.ordensservico.aberturadeordendeservico.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.ordensservico.aberturadeordendeservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturadeordendeservico.model.OrdemServico;
import br.com.ordensservico.aberturadeordendeservico.model.Equipamento;
import br.com.ordensservico.aberturadeordendeservico.repository.OrdemServicoRepository;
import br.com.ordensservico.aberturadeordendeservico.repository.EquipamentoRepository;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;
    private final EquipamentoRepository equipamentoRepository;

    public OrdemServicoService(
            OrdemServicoRepository ordemServicoRepository,
            EquipamentoRepository equipamentoRepository) {

        this.ordemServicoRepository = ordemServicoRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    // CADASTRAR ORDEM DE SERVIÇO
    public OrdemServico cadastrar(OrdemServicoRequest request) {

        Equipamento equipamento = equipamentoRepository
                .findById(request.getEquipamentoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Equipamento não encontrado."
                ));

        OrdemServico ordem = new OrdemServico();

        ordem.setDescricao(request.getDescricao());
        ordem.setEquipamento(equipamento);
        ordem.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordem);
    }

    // LISTAR TODAS AS ORDENS
    public List<OrdemServico> listarTodas() {

        return ordemServicoRepository.findAll();
    }

    // BUSCAR ORDEM POR ID
    public OrdemServico buscarPorId(Integer id) {

        return ordemServicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ordem de serviço não encontrada."
                ));
    }
}
