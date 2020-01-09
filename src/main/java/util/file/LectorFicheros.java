package util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class LectorFicheros {

	protected File fichero;
	protected BufferedReader bReader;
	protected BufferedWriter bWriter;

	public LectorFicheros() {
		fichero = null;
	}

	public boolean abrirFichero(String ruta) {
		if (fichero != null) {
			fichero = new File(ruta);
			if (!fichero.exists())
				if (crearFichero())
					return true;
				else
					return false;
		}
		return false;
	}

	private boolean crearFichero() {
		try {
			fichero.createNewFile();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean cerrarFichero() {
		if (fichero != null) {
			fichero = null;
			return true;
		}
		return false;
	}
}
