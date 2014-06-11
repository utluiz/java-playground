public enum PointInTimeType implements CodeNameEnum {

	FIRST( "F" , "First" ) , START( "S" , "Start" ) , END( "E" , "End" ) , LAST(
			"L" , "Last" ) , MIN( "MI" , "Minimum" ) , MAX( "MX" , "Maximum" );

	private String code;
	private String name;

	private PointInTimeType( String code , String name ) {
		this.code = code;
		this.name = name;

	}

	public String getCode( ) {
		return code;
	}

	public String getName( ) {
		return name;
	}
}