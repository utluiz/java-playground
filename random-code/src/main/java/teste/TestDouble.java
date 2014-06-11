package teste;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class TestDouble {

	public static void main( String[ ] args ) throws ParseException {
		String salario = "2.689,35";
		NumberFormat nf = NumberFormat.getInstance( new Locale( "pt" , "BR" ) );
		System.out.println( nf.parse( salario ) );
	}

}
