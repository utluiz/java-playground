public abstract class CodigoBarra {

    private final String codigoBarra;
    private final boolean valido;

    protected CodigoBarra(String codigoBarra) {
        if (codigoBarra == null) throw new IllegalArgumentException("Código não pode ser nulo");
        this.codigoBarra = codigoBarra;
        this.valido = validar(codigoBarra);
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public boolean valido() {
        return valido;
    }

    protected abstract boolean validar(String codigoBarra);


    static public class CodigoBarraEAN13 extends CodigoBarra {

        public CodigoBarraEAN13(String codigoBarra) {
            super(codigoBarra);
        }

        @Override
        protected boolean validar(String codigoBarra) {
            if (!codigoBarra.matches("^[0-9]{13}$")) {
                return false;
            }
            int[] numeros = codigoBarra.chars().map(Character::getNumericValue).toArray();
            int somaPares = numeros[1] + numeros[3] + numeros[5] + numeros[7] + numeros[9] + numeros[11];
            int somaImpares = numeros[0] + numeros[2] + numeros[4] + numeros[6] + numeros[8] + numeros[10];
            int resultado = somaImpares + somaPares * 3;
            int digitoVerificador = 10 - resultado % 10;
            return digitoVerificador == numeros[12];
        }

    }

    public static void main(String[] args) {
        String codigo = "9788576356127";
        CodigoBarra codigoBarra = new CodigoBarraEAN13(codigo);
        System.out.println("Número do código de barras: " + codigoBarra.getCodigoBarra());
        System.out.println("Código de barras é " + (codigoBarra.valido() ? "válido" : "inválido"));
    }
}
