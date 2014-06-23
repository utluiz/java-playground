package br.com.starcode.validacaoenum;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FuncionarioTest {

    @Test
    public void test() throws Exception {
        
        //cria lista de funcionários
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        funcionarios.add(new Funcionario(1, "João", TipoFuncionario.Celetista));
        funcionarios.add(new Funcionario(2, "José", TipoFuncionario.QuadroPermanente));
        funcionarios.add(new Funcionario(3, "Maria", TipoFuncionario.Surfista));
        
        for (Funcionario funcionario : funcionarios) {
            funcionario.getTipo().getValidator().validate(funcionario);
        }
        
    }

}
