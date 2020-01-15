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
	protected ArrayList<File> ficheros;
	protected BufferedReader bReader;
	protected BufferedWriter bWriter;

	public GestorFicheros() {}
	
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

	public void setFichero(String path) {
		if(path==null) 
			System.out.println("path==null");
		else {
			fichero = new File(path);
			if(!fichero.exists()) {
				crearFichero();
			}
		}
	}
	
	public boolean abrirFichero(String ruta) {
		fichero = null;
		if (fichero == null) {
			fichero = new File(ruta);
			if (fichero.isFile() && !fichero.exists()) {
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
		System.out.println("El fichero no existe. Se creará uno nuevo");
		File parent = fichero.getParentFile();
		if(parent==null) {
			if(fichero.mkdirs()) {
				System.out.println("Directorios dependientes del fichero creados con exito");
				try {
					fichero.createNewFile();
					System.out.println("Fichero creado");
					return true;
				} catch (IOException e) {
					System.out.println("No se pudo crear el fichero");
					return false;
				}
			}else {
				System.out.println("No se pudieron crear los directorios");
				return false;
			}
		}return false;
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
		
		String caracter;
		try {
			while ((caracter = bReader.readLine()) != null) {
				try {
					bWriter.write(caracter);
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

	public boolean escribirFichero(String cadena, String path, boolean append) {
		
		cargarBufferedWriter(append);
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
