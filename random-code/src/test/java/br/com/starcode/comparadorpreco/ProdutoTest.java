package br.com.starcode.comparadorpreco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProdutoTest {

    @Test
    public void comparacaoIgual() throws Exception {
        
        //testes básicos
        Produto<UnidadePeso> laranjaExtra = new Produto<UnidadePeso>(new BigDecimal("50"), 1, UnidadePeso.Kilograma);
        assertEquals(new BigDecimal("50"), laranjaExtra.getPreco());
        assertEquals(new BigDecimal("0.05"), laranjaExtra.getPrecoBase());
        assertEquals(1, laranjaExtra.getQuantidade());
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaCarrefour = new Produto<UnidadePeso>(new BigDecimal("50"), 1000, UnidadePeso.Grama);
        assertEquals(new BigDecimal("0.05"), laranjaCarrefour.getPrecoBase());
        assertEquals(1000, laranjaCarrefour.getQuantidade());
        
        //teste de comparação de mesmo valor
        assertEquals(0, laranjaExtra.compareTo(laranjaCarrefour));
        assertEquals(0, laranjaCarrefour.compareTo(laranjaExtra));
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaPaoDeAcucar = new Produto<UnidadePeso>(new BigDecimal("49.95"), 999, UnidadePeso.Grama);
        assertEquals(999, laranjaPaoDeAcucar.getQuantidade());
        assertEquals(new BigDecimal("0.05"), laranjaPaoDeAcucar.getPrecoBase());
        
        //teste de comparação de mesmo valor
        assertEquals(0, laranjaExtra.compareTo(laranjaPaoDeAcucar));
        assertEquals(0, laranjaPaoDeAcucar.compareTo(laranjaExtra));
        
    }
    
    @Test
    public void comparacaoDiferente() throws Exception {
        
        //testes básicos
        Produto<UnidadePeso> laranjaExtra = new Produto<UnidadePeso>(new BigDecimal("50"), 1, UnidadePeso.Kilograma);
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaCarrefour = new Produto<UnidadePeso>(new BigDecimal("49.95"), 1000, UnidadePeso.Grama);
        assertEquals(new BigDecimal("0.04995"), laranjaCarrefour.getPrecoBase());
        
        //teste de comparação de valor diferente
        assertEquals(1, laranjaExtra.compareTo(laranjaCarrefour)); //extra > carrefour (1)
        assertEquals(-1, laranjaCarrefour.compareTo(laranjaExtra)); //carrefour < extra (-1)
        
    }
    
}
