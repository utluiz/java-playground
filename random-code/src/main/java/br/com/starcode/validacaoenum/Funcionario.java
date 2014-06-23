package br.com.starcode.validacaoenum;

public class Funcionario {
    
    private Integer id;
    private String nome;
    private TipoFuncionario tipo;
    
    public Funcionario(Integer id, String nome, TipoFuncionario tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public TipoFuncionario getTipo() {
        return tipo;
    }

}
