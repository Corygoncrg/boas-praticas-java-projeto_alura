package br.com.alura.adopet.api.validacoes.adocaoController;

import br.com.alura.adopet.api.dto.adocao.AdocaoSolicitacaoDto;

public interface ValidacaoSolicitacaoAdocao {
    void validar(AdocaoSolicitacaoDto dto);
}
