package br.com.starcode.comparadorpreco;

import java.math.BigDecimal;

/**
 * http://pt.stackoverflow.com/questions/20724/comparar-pre%C3%A7os-levando-em-considera%C3%A7%C3%A3o-as-unidades-kg-g-em-android
 */
public class Produto<T extends UnidadeProporcional> implements Comparable<Produto<T>> {

    private BigDecimal valorUnitario;
    private int quantidade;
    private T unidade;
    
    public Produto(BigDecimal preco, int quantidade, T unidade) {
        if (preco == null) {
            throw new IllegalArgumentException("Pre�o nulo!");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser um inteiro positivo!");
        }
        if (unidade == null) {
            throw new IllegalArgumentException("Informe a unidade!");
        }
        this.valorUnitario = preco;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }
    
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
    
    public BigDecimal getValorUnitarioUnidadeBase() {
        return valorUnitario.divide(new BigDecimal(unidade.proporcao()));
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    /**
     * @return Retorna a quantidade na unidade base
     */
    public int getQuantidadeUnidadeBase() {
        return quantidade * unidade.proporcao();
    }
    
    public T getUnidade() {
        return unidade;
    }
    
    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(new BigDecimal(quantidade));
    }
    
    @Override
    public int compareTo(Produto<T> o) {
        if (o == null) {
            throw new IllegalArgumentException("Objeto nulo!");
        }
        return this.getValorTotal().compareTo(o.getValorTotal());
    }
    
}
