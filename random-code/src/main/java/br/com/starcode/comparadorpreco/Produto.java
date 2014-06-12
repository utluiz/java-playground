package br.com.starcode.comparadorpreco;

import java.math.BigDecimal;

/**
 * http://pt.stackoverflow.com/questions/20724/comparar-pre%C3%A7os-levando-em-considera%C3%A7%C3%A3o-as-unidades-kg-g-em-android
 */
public class Produto<T extends UnidadeProporcional> implements Comparable<Produto<T>> {

    private BigDecimal preco;
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
        this.preco = preco;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }
    
    public BigDecimal getPreco() {
        return preco;
    }
    
    /**
     * Retorna o preço na unidade básica para possibilitar a comparação em unidades diferentes.
     * Por exemplo, se a unidade for kg, o "preço base" será dividido por 1000, ou seja, o valor em gramas do produto.
     * Além disso, o resultado também será dividido pela quantidade, de forma que o resultado seja o valor por grama. 
     */
    public BigDecimal getPrecoBase() {
        return preco.divide(new BigDecimal(unidade.proporcao())).divide(new BigDecimal(quantidade));
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    
    public T getUnidade() {
        return unidade;
    }
    
    @Override
    public int compareTo(Produto<T> o) {
        if (o == null) {
            throw new IllegalArgumentException("Objeto nulo!");
        }
        return this.getPrecoBase().compareTo(o.getPrecoBase());
    }
    
}
