package teste;

import java.util.HashMap;
import java.util.Map;

public class TestGeneric {

	static abstract class Animal {

	}

	static class Gato extends Animal {
		void miar( ) {
			System.out.println( "Miau!" );
		}
	}

	static class Cachorro extends Animal {
		void latir( ) {
			System.out.println( "Au!" );
		}
	}

	static Map < String , Animal > mapa = new HashMap <>();

	static < T extends Animal > T call( String name , Class < T > classe ) {
		return ( T ) mapa.get( name );
	}

	public static void main( String[ ] args ) {
		mapa.put( "rex" , new Cachorro() );
		mapa.put( "miau" , new Gato() );
		
		/*
		 * Cachorro c = call( "rex" ); c.latir();
		 * 
		 * ( ( Gato ) call( "miau" ) ).miar();
		 */

		call( "rex" , Cachorro.class ).latir();
	}

}
