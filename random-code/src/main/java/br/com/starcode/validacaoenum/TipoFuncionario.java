package br.com.starcode.validacaoenum;

public enum TipoFuncionario {
    
    Celetista(new CeletistaValidator()), 
    QuadroPermanente(new QuadroPermanenteValidator()), 
    Surfista(new SurfistaValidator());
    
    private FuncionarioValidator validator;
    
    private TipoFuncionario(FuncionarioValidator validator) {
        this.validator = validator;
    }
    
    public FuncionarioValidator getValidator() {
        return validator;
    }
    
}
