package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {

        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void incial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de funcionário deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Vizualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {

        System.out.println("Digite o nome funcionario");
        String nome = scanner.next();

        System.out.println("Digite CPF funcionario");
        String cpf = scanner.next();

        System.out.println("Digite salario do funcionario");
        BigDecimal salario = scanner.nextBigDecimal();

        System.out.println("Digite o codigo cargo");
        Integer cargoid = scanner.nextInt();
        Optional<Cargo> cargo = cargoRepository.findById(cargoid);

        List<UnidadeTrabalho> unidades = unidade(scanner);


        Funcionario funcionario = new Funcionario();
        funcionario.setNomeFuncionario(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario Salvo");

    }

    private void atualizar(Scanner scanner) {

        System.out.println("Digite o ID do funcionario");
        int id = scanner.nextInt();

        System.out.println("O que deseja atualizar?");
        System.out.println("0 - Nome");
        System.out.println("1 - CPF");
        System.out.println("2 - Salario");
        System.out.println("3 - Cargo");
        System.out.println("4 - Unidade de Trabalho");

        int action = scanner.nextInt();

        switch (action){
            case 0:
                System.out.println("Digite o nome do funcionario");
                String nome = scanner.next();

                Funcionario funcionarionome = new Funcionario();
                funcionarionome.setId(id);
                funcionarionome.setNomeFuncionario(nome);

                funcionarioRepository.save(funcionarionome);
                System.out.println("Nome atualizado com sucesso");
                break;
            case 1:
                System.out.println("Digite o CPF do funcionario");
                String cpf = scanner.next();

                Funcionario funcionariocpf = new Funcionario();
                funcionariocpf.setId(id);
                funcionariocpf.setCpf(cpf);

                funcionarioRepository.save(funcionariocpf);
                System.out.println("CPF atualizado com sucesso");
                break;
            case 2:
                System.out.println("Digite o salario do funcionario");
                BigDecimal salario = scanner.nextBigDecimal();

                Funcionario funcionariosalario = new Funcionario();
                funcionariosalario.setId(id);
                funcionariosalario.setSalario(salario);

                funcionarioRepository.save(funcionariosalario);
                System.out.println("Salario atualizado com sucesso");
                break;
            case 3:
                System.out.println("Digite o cargo do funcionario");
                Integer cargoid = scanner.nextInt();
                Optional<Cargo> cargo = cargoRepository.findById(cargoid);

                Funcionario funcionariocargo = new Funcionario();
                funcionariocargo.setId(id);
                funcionariocargo.setCargo(cargo.get());


                funcionarioRepository.save(funcionariocargo);
                System.out.println("Cargo atualizado com sucesso");
                break;
            case 4:
                List<UnidadeTrabalho> unidades = unidade(scanner);


                Funcionario funcionariounidade = new Funcionario();
                funcionariounidade.setId(id);
                funcionariounidade.setUnidadeTrabalhos(unidades);

                funcionarioRepository.save(funcionariounidade);
                System.out.println("Unidade Trabalho atualizada com sucesso");
            default:
                system = false;
                break;
        }


    }

    private void visualizar() {
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionarios));
    }

    private void deletar(Scanner scanner) {

        System.out.println("Digite o id do funcionario a deletar: ");
        int id = scanner.nextInt();

        cargoRepository.deleteById(id);
        System.out.println("Funcionario deletado com sucesso!");
    }

    private List<UnidadeTrabalho> unidade(Scanner scanner){
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite a unidade trabalho id (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if (unidadeId != 0) {
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }




}
