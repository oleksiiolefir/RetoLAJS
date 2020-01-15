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
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import util.logger.LogLevel;
import util.logger.Logger;

public class GestorFicheros {

	protected File file;
	protected ArrayList<File> ficheros;
	protected BufferedReader reader;
	protected BufferedWriter writer;

	public GestorFicheros() {
	}

	public boolean openFile(String path) throws SecurityException {
		if (path == null) {
			Logger.getInstance().log("Error al crear fichero, path es null", LogLevel.ERROR, getClass());
			return false;
		} else {
			file = new File(path);
			if (!file.exists() && file.isFile()) {
				Logger.getInstance().log("El fichero no existe, se creará uno nuevo", LogLevel.INFO, getClass());
				if (create())
					return true;
				else
					return false;
			}
			return true;
		}
	}

	private boolean create() throws SecurityException {
		boolean dirsCreated = true;
		if (file.getParentFile() == null) {
			Logger.getInstance().log("El(los) directorio(s) padre no existen, se crearán", LogLevel.INFO, getClass());
			dirsCreated = createDirs();
		}
		if (dirsCreated) {
			if (createFile())
				return true;
			else
				return false;
		} else
			return false;
	}

	private boolean createFile() {
		try {
			file.createNewFile();
			Logger.getInstance().log("Fichero creado con éxito", LogLevel.INFO, getClass());
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al crear el fichero " + file.getAbsolutePath(), LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	private boolean createDirs() throws SecurityException {
		if (file.mkdirs()) {
			Logger.getInstance().log("Directorio(s) padre del fichero creado(s) con éxito", LogLevel.INFO, getClass());
			return true;
		} else {
			Logger.getInstance().log("Error al crear el(los) directorio(s)", LogLevel.ERROR, getClass());
			return false;
		}
	}

	protected void loadReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	protected boolean loadReader() {
		try {
			reader = new BufferedReader(new FileReader(file));
			return true;
		} catch (FileNotFoundException e) {
			Logger.getInstance().log("Error al cargar reader. No se encuentra el fichero", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	protected boolean loadWriter(boolean append) {
		try {
			writer = new BufferedWriter(new FileWriter(file, append));
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cargar reader. No se encuentra el fichero", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	protected boolean closeReader() {
		try {
			reader.close();
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cerrar reader", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		}
	}

	protected boolean closeWriter() {
		try {
			writer.close();
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cerrar writer", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		}
	}

	public boolean downloadFile(URL url, String pathTo) {
		try {
			loadReader(url.openStream());
		} catch (IOException e) {
			Logger.getInstance().log("Error al abrir URL", LogLevel.WARNING, getClass(), e.getClass());
			return false;
		}
		if (loadWriter(false)) {
			if (writeFile(pathTo, false))
				return true;
			else {
				return false;
			}
		}
		return false;
	}

	protected boolean writeFile(String pathTo, boolean append) {
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				try {
					writer.write(line);
				} catch (IOException e) {
					Logger.getInstance().log("Error de escritura en buffer", LogLevel.ERROR, getClass(), e.getClass());
					return false;
				}
			}
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error de lectura en buffer", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		} finally {
			closeReader();
			closeWriter();
		}
	}

	public boolean writeFile(String pathTo, boolean append, String cadena) {
		if (loadWriter(append)) {
			try {
				writer.write(cadena);
				return true;
			} catch (IOException e) {
				Logger.getInstance().log("Error de escritura en buffer", LogLevel.ERROR, getClass(), e.getClass());
				return false;
			} finally {
				closeWriter();
			}
		}
		return false;
	}

	public ArrayList<String> readFile(String pathFrom) {
		if (loadReader()) {
			ArrayList<String> lineas = new ArrayList<String>();
			try {
				String linea;
				while ((linea = reader.readLine()) != null) {
					lineas.add(linea);
				}
				return lineas;
			} catch (IOException e) {
				Logger.getInstance().log("Error de lectura en buffer", LogLevel.ERROR, getClass(), e.getClass());
				return null;
			} finally {
				closeReader();
			}
		} return null;
	}

}
