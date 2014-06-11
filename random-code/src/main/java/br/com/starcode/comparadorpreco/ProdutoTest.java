package br.com.starcode.comparadorpreco;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class ProdutoTest {

    @Test
    public void comparacaoIgual() throws Exception {
        
        //testes básicos
        Produto<UnidadePeso> laranjaExtra = new Produto<UnidadePeso>(new BigDecimal("50"), 1, UnidadePeso.Kilograma);
        Assert.assertEquals(new BigDecimal("50"), laranjaExtra.getValorUnitario());
        Assert.assertEquals(new BigDecimal("0.05"), laranjaExtra.getValorUnitarioUnidadeBase());
        Assert.assertEquals(1, laranjaExtra.getQuantidade());
        Assert.assertEquals(1000, laranjaExtra.getQuantidadeUnidadeBase());
        Assert.assertEquals(new BigDecimal("50"), laranjaExtra.getValorTotal());
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaCarrefour = new Produto<UnidadePeso>(new BigDecimal("0.05"), 1000, UnidadePeso.Grama);
        Assert.assertEquals(new BigDecimal("0.05"), laranjaCarrefour.getValorUnitario());
        Assert.assertEquals(new BigDecimal("0.05"), laranjaCarrefour.getValorUnitarioUnidadeBase());
        Assert.assertEquals(1000, laranjaCarrefour.getQuantidade());
        Assert.assertEquals(1000, laranjaCarrefour.getQuantidadeUnidadeBase());
        Assert.assertEquals(new BigDecimal("50.00"), laranjaCarrefour.getValorTotal());
        
        //teste de comparação de mesmo valor
        Assert.assertEquals(0, laranjaExtra.compareTo(laranjaCarrefour));
        
    }
    
    @Test
    public void comparacaoDiferente() throws Exception {
        
        //testes básicos
        Produto<UnidadePeso> laranjaExtra = new Produto<UnidadePeso>(new BigDecimal("50"), 1, UnidadePeso.Kilograma);
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaCarrefour = new Produto<UnidadePeso>(new BigDecimal("0.05"), 999, UnidadePeso.Grama);
        Assert.assertEquals(999, laranjaCarrefour.getQuantidade());
        Assert.assertEquals(999, laranjaCarrefour.getQuantidadeUnidadeBase());
        Assert.assertEquals(new BigDecimal("49.95"), laranjaCarrefour.getValorTotal());
        
        //teste de comparação de mesmo valor
        Assert.assertEquals(1, laranjaExtra.compareTo(laranjaCarrefour));
        Assert.assertEquals(-1, laranjaCarrefour.compareTo(laranjaExtra));
        
    }
    
}
