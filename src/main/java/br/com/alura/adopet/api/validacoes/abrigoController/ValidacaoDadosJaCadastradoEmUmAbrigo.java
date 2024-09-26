package br.com.alura.adopet.api.validacoes.abrigoController;

import br.com.alura.adopet.api.dto.abrigo.AbrigoCadastroDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoDadosJaCadastradoEmUmAbrigo {

    @Autowired
    private AbrigoRepository repository;

    public void validar(AbrigoCadastroDto dto) {
        boolean jaCadastrado = repository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
