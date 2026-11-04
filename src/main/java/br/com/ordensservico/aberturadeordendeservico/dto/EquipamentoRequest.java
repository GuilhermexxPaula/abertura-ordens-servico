package br.com.ordensservico.aberturadeordendeservico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipamentoRequest {

    private String nome;
    private String numeroPatrimonio;
    private Integer setorId;

    // Construtor vazio
    public EquipamentoRequest() {
    }

    // Getters e Setters
    @NotBlank (message = "O nome é obrigatório")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
@NotBlank (message = "O numero é obrigatório")
    public String getNumeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(String numeroPatrimonio) {
        this.numeroPatrimonio = numeroPatrimonio;
    }


  @NotNull (message ="Setor não existe")

    public Integer getSetorId() {
        return setorId;
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }
}
