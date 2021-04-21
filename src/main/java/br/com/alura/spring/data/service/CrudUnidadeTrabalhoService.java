package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Vizualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    vizualizar();
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

        System.out.println("Descricao Unidade Trabalho: ");
        String descricao = scanner.next();

        System.out.println("Endereco Cargo");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de Trabalho Salva");
    }

    private void atualizar(Scanner scanner) {

        System.out.println("Digite o Id da unidade de trabalho");
        int id = scanner.nextInt();

        System.out.println("Descricao Unidade Trabalho: ");
        String descricao = scanner.next();

        System.out.println("Endereco Cargo");
        String endereco = scanner.next();

        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(descricao);
        unidadeTrabalho.setEndereco(endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Unidade de Trabalho Atualizada");
    }

    private void vizualizar() {
        Iterable<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findAll();
        unidadeTrabalho.forEach(unidadeTrabalho1 -> System.out.println(unidadeTrabalho));
    }

    private void deletar(Scanner scanner) {

        System.out.println("Digite o Id da Unidade de Trabalho para deletar");
        int id = scanner.nextInt();

        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Unidade de Trabalho Deletada");

    }

}
