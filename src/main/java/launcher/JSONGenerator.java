package launcher;

import java.io.IOException;
import java.util.ArrayList;

import util.file.GestorFicherosJSON;

public class JSONGenerator {
	
	private static final String PATH = "files/json/";
	
	public void generateJsonFile(ArrayList<?> list, String filename) {
		GestorFicherosJSON jsonFileManager = new GestorFicherosJSON();
		try {
			jsonFileManager.writeFile(PATH + filename, list);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
