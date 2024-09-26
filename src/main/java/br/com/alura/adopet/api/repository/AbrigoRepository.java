package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {

    @Query("SELECT p FROM Pet p WHERE p.id = :id OR p.nome = :nome")
    List<Pet> encontrarPetPorIdOuNome(@Param("id") Long id, @Param("nome") String nome);

    @Query("SELECT a FROM Abrigo a WHERE a.id = :id OR a.nome = :nome")
    Abrigo encontrarAbrigoPorIdOuNome(@Param("id") Long id, @Param("nome") String nome);

    boolean existsByNome(String nome);

    boolean existsByTelefone(String telefone);

    boolean existsByEmail(String email);

    Abrigo findByNome(String nome);

    boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email);
}
