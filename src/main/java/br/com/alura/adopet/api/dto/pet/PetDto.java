package br.com.alura.adopet.api.dto.pet;

import br.com.alura.adopet.api.model.TipoPet;

public record PetDto(
        Long id,

        TipoPet tipo,

        String nome,

        String raca,

        Integer idade,

        String cor,

        Float peso,

        Boolean adotado) {
}
