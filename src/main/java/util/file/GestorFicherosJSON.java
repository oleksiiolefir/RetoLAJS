package util.file;

import java.io.File;
import java.util.ArrayList;

import com.google.gson.Gson;

import objetos.Alojamiento;

public class GestorFicherosJSON extends GestorFicheros {

	private Gson gson;
	
	public GestorFicherosJSON(File fichero) {
		super(fichero);
	}
	
	public GestorFicherosJSON(String ruta) {
		super(ruta);
	}
	
	public boolean escribirFichero(ArrayList<?> list) {
		gson = new Gson();
		String json = gson.toJson(list);
		if(escribirFichero(json))
			return true;
		else
			return false;
	}

}
