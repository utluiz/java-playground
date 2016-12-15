package br.com.starcode.validacaoenum;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * http://pt.stackoverflow.com/questions/22184/valida%C3%A7%C3%A3o-de-objeto-de-neg%C3%B3cio-evitando-reduzindo-uso-de-ifs-e-elses
 */
public class FuncionarioTest {

    @Test
    public void test() throws Exception {
        
        //cria lista de funcionários
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        funcionarios.add(new Funcionario(1, "João", TipoFuncionario.Celetista));
        funcionarios.add(new Funcionario(2, "José", TipoFuncionario.QuadroPermanente));
        funcionarios.add(new Funcionario(3, "Maria", TipoFuncionario.Surfista));
        
        //testa todos
        for (Funcionario funcionario : funcionarios) {
            funcionario.getTipo().getValidator().validate(funcionario);
        }
        
    }

}
