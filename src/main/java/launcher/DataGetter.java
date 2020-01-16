package launcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import util.file.Checksum;
import util.file.GestorFicheros;
import util.logger.LogLevel;
import util.logger.Logger;

public class DataGetter {
	
	private static final String PATH_URLS = "files/urls.txt";
	private static final String PATH_CHECKSUMS = "files/checksums.txt";

	private GestorFicheros fileManager;
	private ArrayList<String> urls;
	private ArrayList<String> checksums;
	
	public DataGetter() {
		fileManager = new GestorFicheros();
	}
	
	public void start() {
		loadUrls();
		readUrls();
	}
	
	public ArrayList<?> getData(){
		return null;
	}
	
	private void loadUrls() {
		try {
			urls = fileManager.readFile(PATH_URLS);
			checksums = fileManager.readFile(PATH_CHECKSUMS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readUrls() {
		Iterator<String> urlIterator = urls.iterator();
		if(urlIterator.hasNext()) {
			String[] url = urlIterator.next().split(" ");
			compareChecksums(url);
		}
	}
	
	private boolean compareChecksums(String[] checksums) {
		Checksum checker = new Checksum();
		try {
			checksums[0] = checker.getMD5Checksum(new URL(checksums[0]));
		} catch (MalformedURLException e) {
			Logger.getInstance().log("URL mal formada", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		}
		if(checksums[0].equalsIgnoreCase(checksums[1])) {
			Logger.getInstance().log("Checksums iguales. No se descargará el archivo", LogLevel.INFO, getClass(), null);
			return true;
		} else {
			Logger.getInstance().log("Checksums diferentes. Se descargará el archivo", LogLevel.INFO, getClass(), null);
			refreshChecksum(checksums[0]);
			return false;
		}
	}
	
	private void refreshChecksum(String newChecksum) {
		
	}
	
}
