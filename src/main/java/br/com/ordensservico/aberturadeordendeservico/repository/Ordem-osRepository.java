
package br.com.ordensservico.aberturadeordendeservico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordensservico.aberturadeordendeservico.model.OrdemServico;

public interface OrdemServicoRepository
        extends JpaRepository<OrdemServico, Integer> {

}
