package br.com.starcode.comparadorpreco;

public enum UnidadePeso implements UnidadeProporcional {
    Grama(1), Kilograma(1000), Tonelada(1000000);
    
    private int proporcao;
    
    private UnidadePeso(int proporcao) {
        this.proporcao = proporcao;
    }

    @Override
    public int proporcao() {
        return proporcao;
    }
}
