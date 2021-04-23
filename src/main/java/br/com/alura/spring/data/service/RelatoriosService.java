package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    Boolean system = true;

    //formatar a data que o cliente passar
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void incial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de funcionário deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca Funcionario por Nome");
            System.out.println("2 - Buscar por Nome, Data Contratacao, Salario");
            System.out.println("3 - Buscar por Data de Contratacao Superior");
            System.out.println("4 - Relatorio Salario");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaPorNome(scanner);
                    break;
                case 2:
                    buscarPorNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscarPorDataSuperior(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                default:
                    system = false;
                    break;
            }
        }
    }



    private void buscaPorNome(Scanner scanner) {

        System.out.println("Escreva o nome do funcionario: ");
        String nome = scanner.next();

        List<Funcionario> funcionarios = funcionarioRepository.findByNomeLike(nome);
        funcionarios.forEach(System.out::println);

    }

    private void buscarPorNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Escreva o nome do funcionario");
        String nome = scanner.next();

        System.out.println("Digite o Salario");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Data Contratacao");
        String data = scanner.next();

        //criando o formatador de datas
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscarPorDataSuperior(Scanner scanner) {

        System.out.println("Digite a data: ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findeDataContratacaoSuperior(localDate);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario() {
        System.out.println("Pesquisa Gerada");

        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println(
                "Funcionario Id: " + f.getId() +
                " Nome: " + f.getNome() +
                " Salario: " + f.getSalario() +
                "  CPF: " + f.getCpf()));
    }
}
