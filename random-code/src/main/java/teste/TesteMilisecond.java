package teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TesteMilisecond {

	public static String msToHourSecond( int ms ) {
		Calendar c = Calendar.getInstance();
		c.set( Calendar.HOUR , 0 );
		c.set( Calendar.MINUTE , 0 );
		c.set( Calendar.SECOND , 0 );
		c.set( Calendar.MILLISECOND , 0 );
		c.add( Calendar.MILLISECOND , ms );
		return new SimpleDateFormat( "HH:mm" ).format( c.getTime() );
	}

	public static void main( String[ ] args ) {
		System.out.println( msToHourSecond( 1000 * 60 * 11 ) ); // 11 minutos
		System.out.println( msToHourSecond( 1000 * 60 * ( 15 + 60 * 2 ) ) ); // 2
	}

}
