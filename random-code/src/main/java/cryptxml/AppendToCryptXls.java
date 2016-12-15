package cryptxml;

import java.text.MessageFormat;


public class AppendToCryptXls {

	public static void main( String[ ] args ) {
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();
		new AppendToCryptXls().executeQuery();

	}

	public void executeQuery( ) {

		System.out.println( "---------" );
		String query = QueryUtil.loadQuery( getClass() , "QUERY1" );
		query = MessageFormat.format( query , "0, 1, 2, 3" );

		System.out.println( query );
	}

}
