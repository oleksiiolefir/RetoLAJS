package util.file;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class GestorFicherosJSON extends GestorFicheros {

	private Gson gson;

	public boolean writeFile(String pathTo, ArrayList<?> list) throws IOException {
		gson = new Gson();
		String json = gson.toJson(list);
		if (writeFile(pathTo, false, json))
			return true;
		else
			return false;
	}

}
