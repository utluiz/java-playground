package teste;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDatePattern {

	public static void main( String[ ] args ) {

		String s = new SimpleDateFormat(
 "'remittance_'ddMMyyyy" )
				.format( new Date() );
		System.out.println( s );

	}

}
