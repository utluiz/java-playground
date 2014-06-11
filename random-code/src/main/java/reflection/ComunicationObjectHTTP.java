package reflection;

import java.lang.reflect.Field;

public class ComunicationObjectHTTP {

    protected Field getHeaderField(String fieldName) {
	Class current = this.getClass();
	boolean keepSearch = true;
	while (keepSearch) {
	    try {
		return current.getDeclaredField(fieldName);

	    } catch (Exception ex) {
	    }
	    if ((current = current.getSuperclass()) == null) {
		keepSearch = false;
	    }
	}
	return null;

    }

    private String lineSeparator = "\r\n";
    private String headers;
    private String nameAndValueSeparator = "=";

    public void loadHeaders(String protocol) {
	if (protocol != null) {
	    if (protocol != "") {
		this.headers = protocol;
		String[] lines = protocol.split(lineSeparator);
		for (String line : lines) {
		    if (line == null) {
			break;
		    }
		    String[] nameAndValue = line.split(nameAndValueSeparator);
		    if (nameAndValue.length >= 2) {
			try {
			    Field field = this.getHeaderField(nameAndValue[0]);
			    field.setAccessible(true);
			    field.set(this, nameAndValue[1]);
			} catch (Exception ex) {
			}
		    }
		}
	    }
	}
    }
}
