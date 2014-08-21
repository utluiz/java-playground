package br.com.starcode.bigxlsrowcount;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BigXlsRowCount {

	public static void main(String[] args) throws IOException {
		
		test("test.xlsx");
		test("test-blank-middle.xlsx");
		test("test-blank-final.xlsx");
		
	}
	
	static void test(String file) throws IOException {
		
		System.out.println("#################");
		System.out.println("Testing file: " + file);
		
		ZipInputStream zin = readFromZip(BigXlsRowCount.class.getResourceAsStream(file), "xl/worksheets/sheet1.xml");
		if (zin == null) {
			throw new RuntimeException("File not found in zip!");
		}
		String xml = read(zin);
		zin.close();
		
		System.out.println(" --- XML ---");
		//System.out.println(xml);
		
		count1(xml);
		count2(xml);
		count3(xml);
		count4(xml);
		count5(xml);
		count6(xml);
		
	}
	
	static void count1(String xml) {
		Pattern p = Pattern.compile("<row", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(xml);
		int count = 0;
		while (m.find()) {
			count++;
		}
		System.out.println("linhas no arquivo (contando rows): " + count);
	}
	
	static void count2(String xml) {
		Pattern p = Pattern.compile("<row[^<]*<c", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(xml);
		int count = 0;
		while (m.find()) {
			count++;
		}
		System.out.println("linhas no arquivo (contando rows com cols): " + count);
	}
	
	static void count3(String xml) {
		Pattern p = Pattern.compile("<dimension\\sref=\"\\w+\\:[a-z]+(\\d+)\"", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(xml);
		int count = 0;
		while (m.find()) {
			String rows = m.group(1);
			count = new Integer(rows);
		}
		System.out.println("linhas no arquivo (dimension): " + count);
	}
	
	static void count4(String xml) {
		Pattern p = Pattern.compile("<row[^<]*<c[^<]*<v", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(xml);
		int count = 0;
		String content = null;
		while (m.find()) {
			content = m.group();
		}
		if (content != null) {
			//last row, get number
			//search for attribute "r" <row r="24040" spans="1:2" x14ac:dyDescent="0.25">
			Matcher m2 = Pattern.compile("r=\"(\\d+)\"", Pattern.CASE_INSENSITIVE).matcher(content);
			if (m2.find()) {
				count = new Integer(m2.group(1));
			}
		}
		System.out.println("linhas no arquivo (last row with col and value): " + count);
	}
	
	static void count5(String xml) {
		//<c r="A24040">
		Pattern p = Pattern.compile("<c\\s[^>]*>", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(xml);
		int count = 0;
		while (m.find()) {
			Matcher m2 = Pattern.compile("r=\"[a-z]+(\\d+)\"", Pattern.CASE_INSENSITIVE).matcher(m.group());
			if (m2.find()) {
				int newCount = new Integer(m2.group(1));
				if (newCount > count) {
					count = newCount;
				}
			}
		}
		System.out.println("linhas no arquivo (max cell row number): " + count);
	}
	
	static void count6(String xml) {
		//<c r="A24040">
		Pattern p = Pattern.compile("<c\\s[^>]*>[^>]*<v[\\s>]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(xml);
		int count = 0;
		while (m.find()) {
			Matcher m2 = Pattern.compile("r=\"[a-z]+(\\d+)\"", Pattern.CASE_INSENSITIVE).matcher(m.group());
			if (m2.find()) {
				int newCount = new Integer(m2.group(1));
				if (newCount > count) {
					count = newCount;
				}
			}
		}
		System.out.println("linhas no arquivo (max cell row number with value): " + count);
	}
	
	static ZipInputStream readFromZip(InputStream zip, String fileName) throws IOException {
		ZipInputStream zin = null;
		zin = new ZipInputStream(zip);
		ZipEntry entry;
		while ((entry = zin.getNextEntry()) != null) {
			if (entry.getName().equals(fileName)) {
				return zin;
			}
			zin.closeEntry();
		}
		return null;
	}
	
	
	
	/*static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	 */
	static String read(InputStream inputStream)
	        throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int length = 0;
	    while ((length = inputStream.read(buffer)) != -1) {
	        baos.write(buffer, 0, length);
	    }
	    return new String(baos.toByteArray(), "UTF-8");
	}
	

}
