package br.com.starcode.validacaoenum;

public class SurfistaValidator implements FuncionarioValidator {

    @Override
    public void validate(Funcionario f) throws Exception {
        System.out.println(f.getNome() + " -> " + getClass().getName());
    }

}
