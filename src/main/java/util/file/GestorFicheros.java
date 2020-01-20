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

import util.logger.LogLevel;
import util.logger.Logger;

public class GestorFicheros {

	protected File file;
	protected ArrayList<File> ficheros;
	protected BufferedReader bfReader;
	protected BufferedWriter bfWriter;

	public GestorFicheros() {
	}

	public boolean openFile(String path) throws IOException {
		file = new File(path);
		if (!file.exists()) {
			Logger.getInstance().log("El fichero " + file.getPath() + " no existe, se creará uno nuevo", LogLevel.INFO, getClass(), null);
			if (createPath())
				return true;
			else
				return false;
		}
		return true;
	}

	private boolean createPath() throws IOException {
		boolean dirsCreated = true;
		if (file.getParentFile() == null) {
			Logger.getInstance().log("El(los) directorio(s) padre no existen, se crearán", LogLevel.INFO, getClass(),
					null);
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
			Logger.getInstance().log("Fichero creado con éxito", LogLevel.INFO, getClass(), null);
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al crear el fichero " + file.getPath(), LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	private boolean createDirs() throws IOException {
		if (file.mkdirs()) {
			Logger.getInstance().log("Directorio(s) padre del fichero creado(s) con éxito", LogLevel.INFO, getClass(),
					null);
			return true;
		} else {
			Logger.getInstance().log("Error al crear el(los) directorio(s)", LogLevel.ERROR, getClass(), null);
			return false;
		}
	}

	protected void loadReader(InputStream stream) {
		bfReader = new BufferedReader(new InputStreamReader(stream));
	}

	protected boolean loadReader() {
		try {
			bfReader = new BufferedReader(new FileReader(file));
			return true;
		} catch (FileNotFoundException e) {
			Logger.getInstance().log("Error al cargar reader. No se encuentra el fichero", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	protected boolean loadWriter(boolean append) {
		try {
			bfWriter = new BufferedWriter(new FileWriter(file, append));
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cargar reader. No se encuentra el fichero", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	protected boolean closeReader() {
		try {
			bfReader.close();
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cerrar reader", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		}
	}

	protected boolean closeWriter() {
		try {
			bfWriter.close();
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cerrar writer", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		}
	}

	public boolean downloadFile(URL url, String pathTo) throws IOException {
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

	protected boolean writeFile(String pathTo, boolean append) throws IOException {
		if (openFile(pathTo)) {
			try {
				String line;
				while ((line = bfReader.readLine()) != null) {
					try {
						bfWriter.write(line);
					} catch (IOException e) {
						Logger.getInstance().log("Error de escritura en buffer", LogLevel.ERROR, getClass(),
								e.getClass());
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
		return false;
	}

	public boolean writeFile(String pathTo, boolean append, String text) throws IOException {
		if (openFile(pathTo)) {
			if (loadWriter(append)) {
				try {
					bfWriter.write(text);
					return true;
				} catch (IOException e) {
					Logger.getInstance().log("Error de escritura en buffer", LogLevel.ERROR, getClass(), e.getClass());
					return false;
				} finally {
					closeWriter();
				}
			}
		}
		return false;
	}

	public boolean writeFile(String pathTo, boolean append, ArrayList<String> lines) throws IOException {
		if (openFile(pathTo)) {
			if (loadWriter(append)) {
				try {
					for(String line:lines) {
						bfWriter.write(line);
						bfWriter.newLine();
					}
					return true;
				} catch (IOException e) {
					Logger.getInstance().log("Error de escritura en buffer", LogLevel.ERROR, getClass(), e.getClass());
					return false;
				} finally {
					closeWriter();
				}
			}
		}
		return false;
	}
	
	public ArrayList<String> readFile(String filePath) throws IOException {
		if (openFile(filePath)) {
			if (loadReader()) {
				ArrayList<String> lineas = new ArrayList<String>();
				try {
					String linea;
					while ((linea = bfReader.readLine()) != null)
						lineas.add(linea);
					return lineas;
				} catch (IOException e) {
					Logger.getInstance().log("Error de lectura en buffer", LogLevel.ERROR, getClass(), e.getClass());
					return null;
				} finally {
					closeReader();
				}
			}
		}
		return null;
	}

	public void deleteFile() throws IOException {
		if (file.exists()) {
			file.delete();
		}
	}
}