package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.TutorAtualizacaoDto;
import br.com.alura.adopet.api.dto.tutor.TutorCadastroDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.tutorController.ValidacaoDadosJaCadastradoEmUmTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private ValidacaoDadosJaCadastradoEmUmTutor validacao;

    public void cadastrar(TutorCadastroDto dto) {
        validacao.validar(dto);
            Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
            repository.save(tutor);

    }

    public void atualizar(TutorAtualizacaoDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizar(dto);
        repository.save(tutor);
    }
}
