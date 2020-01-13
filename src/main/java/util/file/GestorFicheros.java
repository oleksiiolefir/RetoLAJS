package util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GestorFicheros {

	protected File fichero;
	protected BufferedReader bReader;
	protected BufferedWriter bWriter;
	
	public GestorFicheros(File fichero) {
		this.fichero = fichero;
	}
	
	public GestorFicheros(String ruta) {
		abrirFichero(ruta);
	}

	private boolean abrirFichero(String ruta) {
		fichero = null;
		if (fichero == null) {
			fichero = new File(ruta);
			if (!fichero.exists())
				System.out.println("El fichero no existe, se creará uno nuevo");
			if (crearFichero())
				return true;
			else
				return false;
		}
		return false;
	}

	public boolean cerrarFichero() {
		if (fichero != null) {
			fichero = null;
			return true;
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

	protected void cargarBufferedReader(InputStream stream) {
		bReader = new BufferedReader(new InputStreamReader(stream));
	}

	protected boolean cargarBufferedWriter(boolean append) {
		try {
			bWriter = new BufferedWriter(new FileWriter(fichero, append));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean descargarFichero(URL url) {
		try {
			cargarBufferedReader(url.openStream());
			cargarBufferedWriter(false);
			escribirFichero();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean escribirFichero() {
		String cadena;
		try {
			while ((cadena = bReader.readLine()) != null) {
				try {
					bWriter.write(cadena);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				bReader.close();
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	public boolean leerFichero() {
		return true;
	}*/

}
