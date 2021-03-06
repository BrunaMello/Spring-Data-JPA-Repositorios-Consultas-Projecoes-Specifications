package br.com.alura.spring.data;

import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataJpaRepositoriosConsultasProjecoesSpecificationsApplication implements CommandLineRunner {

    private final CrudCargoService cargoService;
    private final CrudFuncionarioService funcionarioService;
    private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
    private final RelatoriosService relatoriosService;
    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

    private Boolean system = true;

    public SpringDataJpaRepositoriosConsultasProjecoesSpecificationsApplication(
            CrudCargoService cargoService,
            CrudFuncionarioService funcionarioService,
            CrudUnidadeTrabalhoService unidadeTrabalhoService,
            RelatoriosService relatoriosService,
            RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
        this.cargoService = cargoService;
        this.funcionarioService = funcionarioService;
        this.unidadeTrabalhoService = unidadeTrabalhoService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaRepositoriosConsultasProjecoesSpecificationsApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionário");
            System.out.println("3 - Unidade de Trabalho");
            System.out.println("4 - Relatorio Funcionarios");
            System.out.println("5 - Relatorio Funcionarios Dinamico");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    cargoService.inicial(scanner);
                    break;
                case 2:
                    funcionarioService.incial(scanner);
                    break;
                case 3:
                    unidadeTrabalhoService.inicial(scanner);
                    break;
                case 4:
                    relatoriosService.incial(scanner);
                case 5:
                    relatorioFuncionarioDinamico.inicial(scanner);
                default:
                    system = false;
                    break;
            }

        }

    }
}
