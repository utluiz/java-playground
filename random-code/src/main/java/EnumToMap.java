import java.util.HashMap;
import java.util.Map;

public class EnumToMap {

	public static Map < String , String > getMapFromEnum(
			CodeNameEnum[] codeNameEnumArray ) {

		Map < String , String > map = new HashMap < String , String >();
		for( CodeNameEnum codeNameEnum : codeNameEnumArray ) {
			map.put( codeNameEnum.getCode() , codeNameEnum.getName() );
		}
		return map;

	}

	public static void main( String[ ] args ) {
		Map < String , String > map = getMapFromEnum( PointInTimeType.values() );
		System.out.println( map );
	}

}
