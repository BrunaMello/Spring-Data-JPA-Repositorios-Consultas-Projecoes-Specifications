package br.com.alura.spring.data.orm;

import java.math.BigDecimal;

//interface based projection
public interface FuncionarioProjecao {

    Integer getId();
    String getNome();
    BigDecimal getSalario();
    String getCpf();


}
