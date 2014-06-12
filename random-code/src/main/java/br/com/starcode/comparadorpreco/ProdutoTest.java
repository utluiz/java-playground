package br.com.starcode.comparadorpreco;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class ProdutoTest {

    @Test
    public void comparacaoIgual() throws Exception {
        
        //testes básicos
        Produto<UnidadePeso> laranjaExtra = new Produto<UnidadePeso>(new BigDecimal("50"), 1, UnidadePeso.Kilograma);
        Assert.assertEquals(new BigDecimal("50"), laranjaExtra.getPreco());
        Assert.assertEquals(new BigDecimal("0.05"), laranjaExtra.getPrecoBase());
        Assert.assertEquals(1, laranjaExtra.getQuantidade());
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaCarrefour = new Produto<UnidadePeso>(new BigDecimal("50"), 1000, UnidadePeso.Grama);
        Assert.assertEquals(new BigDecimal("0.05"), laranjaCarrefour.getPrecoBase());
        Assert.assertEquals(1000, laranjaCarrefour.getQuantidade());
        
        //teste de comparação de mesmo valor
        Assert.assertEquals(0, laranjaExtra.compareTo(laranjaCarrefour));
        Assert.assertEquals(0, laranjaCarrefour.compareTo(laranjaExtra));
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaPaoDeAcucar = new Produto<UnidadePeso>(new BigDecimal("49.95"), 999, UnidadePeso.Grama);
        Assert.assertEquals(999, laranjaPaoDeAcucar.getQuantidade());
        Assert.assertEquals(new BigDecimal("0.05"), laranjaPaoDeAcucar.getPrecoBase());
        
        //teste de comparação de mesmo valor
        Assert.assertEquals(0, laranjaExtra.compareTo(laranjaPaoDeAcucar));
        Assert.assertEquals(0, laranjaPaoDeAcucar.compareTo(laranjaExtra));
        
    }
    
    @Test
    public void comparacaoDiferente() throws Exception {
        
        //testes básicos
        Produto<UnidadePeso> laranjaExtra = new Produto<UnidadePeso>(new BigDecimal("50"), 1, UnidadePeso.Kilograma);
        
        //outros testes com unidade diferente
        Produto<UnidadePeso> laranjaCarrefour = new Produto<UnidadePeso>(new BigDecimal("49.95"), 1000, UnidadePeso.Grama);
        Assert.assertEquals(new BigDecimal("0.04995"), laranjaCarrefour.getPrecoBase());
        
        //teste de comparação de valor diferente
        Assert.assertEquals(1, laranjaExtra.compareTo(laranjaCarrefour)); //extra > carrefour (1)
        Assert.assertEquals(-1, laranjaCarrefour.compareTo(laranjaExtra)); //carrefour < extra (-1)
        
    }
    
}
