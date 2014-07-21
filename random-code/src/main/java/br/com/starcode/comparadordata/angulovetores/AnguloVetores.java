package br.com.starcode.comparadordata.angulovetores;


/**
 * http://pt.stackoverflow.com/questions/25923/vetores-e-%C3%82ngulos-geometria-molecular
 * teoria: http://gradmat.ufabc.edu.br/disciplinas/listas/ga/notasdeaulas/geometriaanaliticaevetorial-SGD.pdf
 */
public class AnguloVetores {
    
    public static class Ponto {
        private double x, y, z;
        public Ponto(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public double x() {
            return x;
        }
        public double y() {
            return y;
        }
        public double z() {
            return z;
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }
    }
    
    public static class Vetor {
        private Ponto p, q;
        public Vetor(Ponto p, Ponto q) {
            this.p = p;
            this.q = q;
        }
        public Ponto p() {
            return p;
        }
        public Ponto q() {
            return q;
        }
        public double i() {
            return q.x() - p.x();
        }
        public double j() {
            return q.y() - p.y();
        }
        public double k() {
            return q.z() - p.z();
        }
        public double tamanho() {
            return Math.sqrt(
                    (q.x() - p.x()) * (q.x() - p.x()) +
                    (q.y() - p.y()) * (q.y() - p.y()) +
                    (q.z() - p.z()) * (q.z() - p.z()));
        }
        public Vetor produtoVetorial(Vetor other) {
            Ponto z = new Ponto(
                        p.x() + j() * other.k() - k() * other.j(),
                        p.y() + k() * other.i() - i() * other.k(),
                        p.z() + i() * other.j() - j() * other.i()
                    );
            return new Vetor(p, z); 
            
        }
        public Vetor normalizar() {
            double t = tamanho();
            return new Vetor(
                    new Ponto(0, 0, 0),
                    new Ponto(i() / t, j() / t, k() / t));
        }
        public double angulo(Vetor other) {
            return Math.acos(
                    (i() * other.i() + j() * other.j() + k() * other.k()) / (tamanho() * other.tamanho())
                ); 
        }
        @Override
        public String toString() {
            return "[" + p + " -> " + q + " = (" + i() + ", " + j() + ", " + k() + ")]";
        }
    }
    
    /**
     * http://en.wikipedia.org/wiki/Rotation_matrix
     */
    public static class MatrizRotacao {
        private double[][] matrix;
        public MatrizRotacao(Vetor v, double teta) {
            double cosTeta = Math.cos(teta);
            double oneMCT = 1 - cosTeta;
            double sinTeta = Math.sin(teta);
            v = v.normalizar();
            
            matrix = new double[3][3];
            matrix[0][0] = cosTeta + v.i() * v.i() * oneMCT;
            matrix[0][1] = v.i() * v.j() * oneMCT - v.k() * sinTeta;
            matrix[0][2] = v.i() * v.k() * oneMCT + v.j() * sinTeta;
            
            matrix[1][0] = v.i() * v.j() * oneMCT + v.k() * sinTeta;
            matrix[1][1] = cosTeta + v.j() * v.j() * oneMCT;
            matrix[1][2] = v.j() * v.k() * oneMCT - v.i() * sinTeta;
            
            matrix[2][0] = v.k() * v.i() * oneMCT - v.j() * sinTeta;
            matrix[2][1] = v.k() * v.j() * oneMCT + v.i() * sinTeta;
            matrix[2][2] = cosTeta + v.k() * v.k() * oneMCT;
        }
        public Vetor rotate(Vetor v) {
            Vetor vn = v.normalizar();
            double t = v.tamanho();
            return new Vetor(
                    v.p(),
                    new Ponto(
                        v.p().x() + t * (vn.q().x() * matrix[0][0] + vn.q().y() * matrix[0][1] + vn.q().z() * matrix[0][2]),
                        v.p().y() + t * (vn.q().x() * matrix[1][0] + vn.q().y() * matrix[1][1] + vn.q().z() * matrix[1][2]),
                        v.p().z() + t * (vn.q().x() * matrix[2][0] + vn.q().y() * matrix[2][1] + vn.q().z() * matrix[2][2])
                    )
                );
        }
        @Override
        public String toString() {
            return "[(" + matrix[0][0] + ", " + matrix[0][1] + ", " + matrix[0][2] + "), (" +
                    matrix[1][0] + ", " + matrix[1][1] + ", " + matrix[1][2] + "), " +
                    matrix[2][0] + ", " + matrix[2][1] + ", " + matrix[2][2] + ")]";
        }
    }
    
    /**
     * O5 = 12.350,5.420,12.480 
     * C1 = 13.290,4.510,13.090 
     * O1 = 14.461,5.261,13.253
     */
    public static void main(String[] args) {
        
        Ponto o5 = new Ponto(12.350, 5.420, 12.480);
        Ponto c1 = new Ponto(13.290, 4.510, 13.090);
        Ponto o1 = new Ponto(14.461, 5.261, 13.253);
        
        Vetor v1 = new Vetor(c1, o5);
        Vetor v2 = new Vetor(c1, o1);
        
        double teta = v1.angulo(v2);
        System.out.println("# Angulo v1 x v2");
        System.out.println(Math.toDegrees(teta));
        
        //cria um vetor rotacionado
        Vetor ortogonalAoPlano = v1.produtoVetorial(v2);
        
        double tetaOrtogonal = v1.angulo(ortogonalAoPlano);
        System.out.println("# Angulo v1 x ortogonal");
        System.out.println(Math.toDegrees(tetaOrtogonal));
        
        tetaOrtogonal = v2.angulo(ortogonalAoPlano);
        System.out.println("# Angulo v2 x ortogonal");
        System.out.println(Math.toDegrees(tetaOrtogonal));
        
        System.out.println();
        System.out.println("Calculando matriz");
        
        //matriz de rotação ao redor do eixo ortogonal
        MatrizRotacao m = new MatrizRotacao(
                ortogonalAoPlano, 
                Math.toRadians(109.45)
            );
        System.out.println(m);
        Vetor v3 = m.rotate(v1);
        
        System.out.println();
        
        double tetaNovo = v1.angulo(v3);
        System.out.println("Angulo v1 x v3");
        System.out.println(Math.toDegrees(tetaNovo));
        
        tetaNovo = v2.angulo(v3);
        System.out.println("Angulo v2 x v3");
        System.out.println(Math.toDegrees(tetaNovo));
        
        tetaNovo = ortogonalAoPlano.angulo(v3);
        System.out.println("Angulo ortogonalAoPlano x v3");
        System.out.println(Math.toDegrees(tetaNovo));
        
        System.out.println("----");
        System.out.println("v1 = " + v1);
        System.out.println("v2 = " + v2);
        System.out.println("v3 = " + v3);
        
    }

}
