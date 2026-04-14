package dto;

import java.math.BigDecimal;

public class FuncionarioDTO {
    private String nome;
    private String funcao;
    private String salarioFormatado;

    public FuncionarioDTO(String nome, String funcao, String salarioFormatado) {
        this.nome = nome;
        this.funcao = funcao;
        this.salarioFormatado = salarioFormatado;
    }

    // getters
}