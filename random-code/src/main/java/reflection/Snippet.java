package reflection;

/**
 * To answer
 * http://pt.stackoverflow.com/questions/16647/via-reflexao-em-java-setar-e-pegar-valores-de-variaveis-herdadas
 */
public class Snippet {
    public static void main(String[] args) {
	POSTRequestHTTP p = new POSTRequestHTTP();
	p.loadHeaders("campo1=valor1\r\ncampo2=valor2");
	System.out.println(p.getCampo1());
	System.out.println(p.getCampo2());
    }
}

