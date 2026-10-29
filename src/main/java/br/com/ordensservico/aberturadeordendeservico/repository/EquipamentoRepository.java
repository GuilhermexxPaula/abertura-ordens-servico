package br.com.ordensservico.aberturadeordendeservico.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ordensservico.aberturadeordendeservico.model.Equipamento;

public interface EquipamentoRepository
        extends JpaRepository<Equipamento, Integer> {

}

