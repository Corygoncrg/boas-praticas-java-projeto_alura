package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.AbrigoCadastroDto;
import br.com.alura.adopet.api.dto.abrigo.AbrigoDto;
import br.com.alura.adopet.api.dto.pet.PetCadastroDto;
import br.com.alura.adopet.api.dto.pet.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.abrigoController.ValidacaoDadosJaCadastradoEmUmAbrigo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private ValidacaoDadosJaCadastradoEmUmAbrigo validacao;

    public List<AbrigoDto> listar(){
        return repository.findAll()
                .stream()
                .map(abrigo -> new AbrigoDto(
                        abrigo.getId(),
                        abrigo.getNome(),
                        abrigo.getTelefone(),
                        abrigo.getEmail()
                )).toList();
    }

    public void cadastrar(AbrigoCadastroDto dto) {
        validacao.validar(dto);
        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        repository.save(abrigo);
    }

    public List<PetDto> listarPets(String idOuNome) {
        try {
            return repository.encontrarPetPorIdOuNome(Long.parseLong(idOuNome), idOuNome)
                    .stream()
                    .map(pet -> new PetDto(
                            pet.getId(),
                            pet.getTipo(),
                            pet.getNome(),
                            pet.getRaca(),
                            pet.getIdade(),
                            pet.getCor(),
                            pet.getPeso(),
                            pet.getAdotado()
                    )).toList();
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Nenhum pet encontrado!");
        }
    }

    public void cadastrarPet(String idOuNome, PetCadastroDto dto) {
        try {
            Abrigo abrigo = repository.encontrarAbrigoPorIdOuNome(Long.parseLong(idOuNome), idOuNome);
            Pet pet = new Pet(dto.tipo(), dto.nome(),dto.raca(), dto.idade(),dto.cor(), dto.peso());
            pet.setAbrigo(abrigo);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new ValidacaoException("Abrigo não encontrado");
        }
    }

}
