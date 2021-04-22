package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
// para fazer paginacao usar o PagingAndSortingRepository
@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {

    //query creations olhar a lista na documentacao
    //Derivated Queries
    List<Funcionario> findByNomeFuncionarioLike(String nome);

    //query menor quando o nome fica muito grande
    //JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nomeFuncionario = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate data);

    //Native Query
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findeDataContratacaoSuperior(LocalDate data);
}
