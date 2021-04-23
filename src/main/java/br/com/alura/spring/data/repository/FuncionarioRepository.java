package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.Query;
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
    List<Funcionario> findByNomeLike(String nome);

    //query menor quando o nome fica muito grande
    //JPQL
    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, BigDecimal salario, LocalDate data);

    //Native Query
    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findeDataContratacaoSuperior(LocalDate data);

    //projecoes
    @Query(value = "SELECT f.id, f.nome_funcionario, f.salario, f.cpf FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();



}
