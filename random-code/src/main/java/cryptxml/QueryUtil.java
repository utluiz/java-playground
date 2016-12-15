package cryptxml;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QueryUtil {

	private static Map < String , String > cache = new HashMap < String , String >();

	public static String loadQuery( Class < ? > classe , String queryName ) {

		String result = cache.get( queryName );
		if( result == null ) {
			System.out.println( " ###  lendo arquivo  ###" );
			Scanner s = new Scanner( classe.getResourceAsStream( queryName
					+ ".sql" ) );
			StringBuilder sb = new StringBuilder();
			while( s.hasNextLine() ) {
				sb.append( s.nextLine() );
				sb.append( System.getProperty( "line.separator" ) );
			}
			s.close();
			result = sb.toString();
			cache.put( queryName , result );
		}
		return result;

	}

}
