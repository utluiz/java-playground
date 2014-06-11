public class FloydCycleDetection {

	public static void main( String[ ] args ) {

		// periodo( 955 , "1110" );
		// periodo( 10 , "10" );
		// periodo( 627 , "10011" );

		// int[ ] v = { 6 , 6 , 0 , 1 , 4 , 3 , 3 , 4 };
		// fcd( v , 2 );

		System.out.println( periodo( 955 ) ); // experado: 4
		System.out.println( periodo( 10 ) ); // experado: 2
		System.out.println( periodo( 627 ) ); // experado: 5
		System.out.println( periodo( 102 ) ); // experado: -1
		System.out.println( periodo( 104 ) ); // experado: -1
		System.out.println( periodo( 3 ) ); // experado: 1

	}

	public static int periodo( int n ) {

		// validação inicial: precisa ser positivo e com pelo menos duas casas
		// binárias
		if( n < 2 ) return -1;

		// inicialização de variáveis
		String s = Integer.toBinaryString( n );
		byte[ ] bytes = s.getBytes();
		int t = bytes.length;

		// período inicial
		int p = t / 2;

		// verifica se o período se repete pelo menos uma vez
		boolean diferente;
		do {
			diferente = false;
			int qp = t / p; // quantiadde de períodos
			if( t % p > 0 ) qp++; // soma 1 se houver resto no final

			// verifica se a repetição ocorre
			for2: for( int k = 0 ; k < p ; k++ ) {
				for( int j = 1 ; j < qp ; j++ ) {
					if( k + p * j < t && bytes[ k ] != bytes[ k + p * j ] ) {
						diferente = true;
						break for2;
					}
				}
			}
			if( diferente ) {
				p--;
			}
		} while( diferente && p > 0 );

		return p <= 0 ? -1 : p;

	}

	public static int periodo2( int n , String periodoEsperado ) {

		String s = Integer.toBinaryString( n );
		System.out.println( "------" );
		System.out.println( "# Verificando: " + s );
		System.out.println( "# Esperado: " + periodoEsperado + " ("
				+ periodoEsperado.length() + ")" );

		int[ ] bits = new int[ s.length() ];
		for( int i = 0 ; i < bits.length ; i++ ) {
			bits[ i ] = new Integer( s.substring( i , i + 1 ) );
		}

		int p = fcd( bits , 0 );
		if( p > 0 && p < s.length() ) {
			System.out.println( "Período encontrado: " + s.substring( 0 , p ) );
		} else {
			System.out.println( "Retorno: " + p );
		}
		return p;

	}

	public static int fcd( int[ ] v , int x0 ) {

		int tartaruga = x0;
		int coelho = 2 * x0;
		int tam = v.length;
		while( v[ tartaruga % tam ] != v[ coelho % tam ] ) {
			tartaruga += 1;
			coelho += 2;
		}

		int mu = 0;
		tartaruga = x0;
		while( v[ tartaruga % tam ] != v[ coelho % tam ] ) {
			tartaruga += 1;
			coelho += 1;
			mu++;
		}

		int lam = 1;
		coelho = tartaruga + 1;
		while( v[ tartaruga % tam ] != v[ coelho % tam ] ) {
			coelho += 1;
			lam++;
		}

		System.out.println( "inicio periodo: " + mu );
		System.out.println( "periodo: " + lam );

		return lam;

		// 2, 0, 6, 3, 1, 6, 3, 1, 6, 3, 1, ....

	}

}