package launcher;

import java.util.ArrayList;

import util.file.GestorFicherosJSON;

public class JSONGenerator {
	
	private static final String PATH = "files/json";
	
	public void generateJsonFiles(ArrayList<?> list) {
		GestorFicherosJSON jsonFileManager = new GestorFicherosJSON();
		jsonFileManager.writeFile(PATH, list);		
	}
}
