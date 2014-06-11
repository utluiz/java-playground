package br.com.starcode.comparadorpreco;

public enum UnidadeVolume implements UnidadeProporcional {
    Mililitro(1), Litro(1000);
    
    private int proporcao;
    
    private UnidadeVolume(int proporcao) {
        this.proporcao = proporcao;
    }

    @Override
    public int proporcao() {
        return proporcao;
    }
}
