package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FileManager {

	protected File file;
	protected BufferedReader bfReader;
	protected BufferedWriter bfWriter;

	protected void openFile(String filepath) throws IOException {
		if (filepath != null) {
			file = new File(filepath);
			if (!file.exists())
				createFile();
		}
	}

	private void createFile() throws IOException {
		try {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			throw new IOException("Error al crear archivo " + file.getPath(), e);
		}
	}

	protected void loadReader(URL url) throws IOException {
		if (url != null && file != null)
			try {
				bfReader = new BufferedReader(new InputStreamReader(url.openStream()));
			} catch (IOException e) {
				bfReader = null;
				throw new IOException("Error al cargar URL en bfReader.", e);
			}
	}

	protected void loadFileReader() throws IOException {
		if (file != null)
			try {
				bfReader = new BufferedReader(new FileReader(file));
			} catch (IOException e) {
				bfReader = null;
				throw new IOException("Error al cargar bfReader", e);
			}
	}

	protected void loadFileWriter(boolean append) throws IOException {
		if (file != null)
			try {
				bfWriter = new BufferedWriter(new FileWriter(file, append));
			} catch (IOException e) {
				bfWriter = null;
				throw new IOException("Error al cargar bfWriter", e);
			}
	}

	public void downloadFile(String filepath, URL url) throws IOException {
		openFile(filepath);
		loadReader(url);
		loadFileWriter(false);
		try {
			String line = null;
			while ((line = bfReader.readLine()) != null)
				bfWriter.write(line);
		} catch (IOException e) {
			throw new IOException("Error al escribir fichero descargado", e);
		} finally {
			bfReader.close();
			bfWriter.close();
		}
	}

	public void closeFile() {
		file = null;
		bfReader = null;
		bfWriter = null;
	}

	public void deleteFile(String filepath) throws IOException {
		file = new File(filepath);
		if (file.exists()) {
			try {
				if (file.isDirectory())
					deleteDirFiles(filepath);
				file.delete();
			} catch (IOException e) {
				throw new IOException("Error al borrar fichero " + file.getPath(), e);
			}
		}
	}

	private void deleteDirFiles(String filepath) throws IOException {
		File tempFile = new File(filepath);
		for (File file : tempFile.listFiles()) {
			if (file.isDirectory())
				deleteDirFiles(file.getPath());
			file.delete();
		}
	}

}