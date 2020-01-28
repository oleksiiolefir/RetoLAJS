package util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import util.logger.LogLevel;
import util.logger.Logger;

public class GestorFicheros {

	protected File file;
	protected BufferedReader bfReader;
	protected BufferedWriter bfWriter;

	public GestorFicheros() {
	}

	protected void openFile(String filepath) throws IOException {
		file = new File(filepath);
		if (!file.exists()) {
			Logger.getInstance().log("El fichero " + file.getPath() + " no existe, se creará uno nuevo", LogLevel.INFO,
					getClass(), null);
			try {
				createFile();
			} catch (Throwable e) {
				file = null;
				throw new IOException("No se pudo abrir fichero", e);
			}
		}
	}

	private void createFile() throws IOException {
		try {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (Throwable e) {
			throw new IOException("Error al crear fichero " + file.getPath(), e);
		}
	}

	protected void loadURL(URL url) throws IOException {
		if (url != null)
			try {
				bfReader = new BufferedReader(new InputStreamReader(url.openStream()));
			} catch (Throwable e) {
				bfReader = null;
				throw new IOException("Error al cargar URL", e);
			}
	}

	protected void loadReader() throws IOException {
		try {
			bfReader = new BufferedReader(new FileReader(file));
		} catch (Throwable e) {
			bfReader = null;
			throw new IOException("Error al cargar reader. No se encuentra el fichero", e);
		}
	}

	protected void loadWriter(boolean append) throws IOException {
		try {
			bfWriter = new BufferedWriter(new FileWriter(file, append));
		} catch (Throwable e) {
			bfWriter = null;
			throw new IOException("Error al cargar writer", e);
		}
	}

	public boolean downloadFile(URL url, String filepath) throws IOException {
		try {
			loadURL(url);
			writeFile(filepath, false);
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al descargar archivo", LogLevel.WARNING, getClass(), e.getClass());
			return false;
		}
	}

	protected void writeFile(String filepath, boolean append) throws IOException {
		try {
			openFile(filepath);
			loadWriter(append);
			String line = null;
			while ((line = bfReader.readLine()) != null)
				bfWriter.write(line);
		} catch (Throwable e) {
			throw new IOException("Error al escribir fichero", e);
		} finally {
			bfReader.close();
			bfWriter.close();
		}
	}

	public boolean writeFile(String filepath, boolean append, String text) throws IOException {
		try {
			openFile(filepath);
			loadWriter(append);
			bfWriter.write(text);
			return true;
		} catch (Throwable e) {
			throw new IOException("Error al escribir fichero", e);
		} finally {
			bfWriter.close();
		}
	}

	public boolean writeFile(String pathTo, boolean append, ArrayList<String> lines) throws IOException {
		try {
			openFile(pathTo);
			loadWriter(append);
			for (String line : lines) {
				bfWriter.write(line);
				bfWriter.newLine();
			}
			return true;
		} catch (Throwable e) {
			throw new IOException("Error al escribir fichero", e);
		} finally {
			bfWriter.close();
		}
	}

	public ArrayList<String> readFile(String filePath) throws IOException {
		try {
			openFile(filePath);
			loadReader();
			ArrayList<String> lineas = new ArrayList<String>();
			String linea = null;
			while ((linea = bfReader.readLine()) != null)
				lineas.add(linea);
			return lineas;
		} catch (Throwable e) {
			throw new IOException("Error al escribir fichero", e);
		} finally {
			bfReader.close();
		}
	}

	public void closeFile() {
		file = null;
		bfReader = null;
		bfWriter = null;
	}

	public void deleteFile(String filepath) {
		file = new File(filepath);
		if (file.isDirectory()) 
			for (File file : file.listFiles()) {
				if (file.isDirectory())
					deleteFile(file.getPath());
				file.delete();
			}
		file.delete();	
	}
}