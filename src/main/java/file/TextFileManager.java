package file;

import java.io.IOException;
import java.util.ArrayList;

public class TextFileManager extends FileManager {

	public void writeFile(String filepath, boolean append, String text) throws IOException {
		openFile(filepath);
		loadFileWriter(append);
		try {
			bfWriter.write(text);
		} catch(IOException e) {
			throw new IOException("Error al escribir texto en fichero" + file.getPath(), e);
		} finally {
			bfWriter.close();
		}
	}

	public void writeFile(String filepath, boolean append, ArrayList<String> lines) throws IOException {
		openFile(filepath);
		loadFileWriter(append);
		try {
			for (String line : lines) {
				bfWriter.write(line);
				bfWriter.newLine();
			}
		} catch (IOException e) {
			throw new IOException("Error al escribir lineas en fichero" + file.getPath(), e);
		} finally {
			bfWriter.close();
		}
	}

	public ArrayList<String> getFileLines(String filepath) throws IOException {
		openFile(filepath);
		loadFileReader();
		
		ArrayList<String> lines = new ArrayList<String>();
		String line = null;
		try {
			while ((line = bfReader.readLine()) != null)
				lines.add(line);
			return lines;
		} catch (IOException e) {
			throw new IOException("Error al leer lineas de fichero" + file.getPath(), e);
		} finally {
			bfReader.close();
		}
	}
}
