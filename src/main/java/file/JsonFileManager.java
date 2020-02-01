package file;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class JsonFileManager extends FileManager {

	public void writeFile(String filepath, ArrayList<?> data) throws IOException {
		openFile(filepath);
		loadFileWriter(false);	
		String json = new Gson().toJson(data);
		try {
			bfWriter.write(json);			
		} catch (IOException e) {
			throw new IOException("Error al escribir json en fichero " + file.getPath(), e);
		} finally {
			bfWriter.close();
		}
	}

}
