package br.com.alura.adopet.api.validacoes.abrigoController;

import br.com.alura.adopet.api.dto.abrigo.AbrigoCadastroDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoDadosJaCadastradoEmUmAbrigo {

    @Autowired
    private AbrigoRepository repository;

    public void validar(AbrigoCadastroDto dto) {
        boolean nomeJaCadastrado = repository.existsByNome(dto.nome());
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
