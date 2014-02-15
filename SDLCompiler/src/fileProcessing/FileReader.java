package fileProcessing;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	public static String readFile(String file){
		String endOfLine = System.getProperty("line.separator");
		List<String> lines = new ArrayList<String>();
		
		try {
			lines = Files.readAllLines(Paths.get(file), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
		    sb.append(line).append(endOfLine);
		}
		return sb.toString();
	}
}
