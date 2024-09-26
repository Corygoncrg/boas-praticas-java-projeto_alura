package br.com.alura.adopet.api.validacoes.tutorController;

import br.com.alura.adopet.api.dto.tutor.TutorCadastroDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoDadosJaCadastradoEmUmTutor {

    @Autowired
    TutorRepository repository;

    public void validar(TutorCadastroDto dto) {
        boolean jaCadastrado = repository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }
    }
}
