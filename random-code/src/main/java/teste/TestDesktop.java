package teste;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class TestDesktop {
	public static void main( String[ ] args ) {
		try {
			Desktop.getDesktop().open( new File( "h:\\" ) );
		} catch( IOException e ) {
			e.printStackTrace();
		}
	}
}
