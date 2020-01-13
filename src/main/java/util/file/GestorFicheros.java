package util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class GestorFicheros {

	protected File fichero;
	protected BufferedReader bReader;
	protected BufferedWriter bWriter;

	public GestorFicheros(File fichero) {
		this.fichero = fichero;
		if (!fichero.exists()) {
			System.out.println("El fichero no existe, se creará uno nuevo");
			crearFichero();
		}
	}

	public GestorFicheros(String ruta) {
		abrirFichero(ruta);
	}

	private boolean abrirFichero(String ruta) {
		fichero = null;
		if (fichero == null) {
			fichero = new File(ruta);
			if (!fichero.exists()) {
				System.out.println("El fichero no existe, se creará uno nuevo");
				if (crearFichero())
					return true;
				else
					return false;
			}
		}
		return false;
	}

	/*
	 * public boolean cerrarFichero() { if (fichero != null) { fichero = null;
	 * return true; } return false; }
	 */

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

	protected boolean cargarBufferedReader() {
		try {
			bReader = new BufferedReader(new FileReader(fichero));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
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

	protected boolean cerrarBufferedReader() {
		try {
			bReader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean cerrarBufferedWriter() {
		try {
			bWriter.close();
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
			if (escribirFichero(false, false))
				return true;
			else {
				System.out.println("Error al escribir fichero descargado");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean escribirFichero(boolean append, boolean bufferOpen) {
		if (bufferOpen) {
			cargarBufferedReader();
			cargarBufferedWriter(append);
		}
		
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
			cerrarBufferedReader();
			cerrarBufferedWriter();
		}
	}

	public boolean escribirFichero(String cadena) {
		cargarBufferedWriter(false);
		try {
			bWriter.write(cadena);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			cerrarBufferedWriter();
		}
	}

	public ArrayList<String> leerFichero() {
		ArrayList<String> lineas = new ArrayList<String>();
		cargarBufferedReader();
		try {
			String linea;
			while ((linea = bReader.readLine()) != null) {
				lineas.add(linea);
			}
			return lineas;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			cerrarBufferedReader();
		}
	}

}
