package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.pet.PetDto;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    PetRepository repository;

    public List<PetDto> listarTodosOsPetsDisponiveis() {
        return repository.findByAdotadoFalse().stream().map(pet -> new PetDto(pet.getId(),
                pet.getTipo(),
                pet.getNome(),
                pet.getRaca(),
                pet.getIdade(),
                pet.getCor(),
                pet.getPeso(),
                pet.getAdotado()
        )).toList();
    }
}
