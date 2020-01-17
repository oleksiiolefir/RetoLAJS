package launcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
		if(checksums!=null) {
			for(int i=0;i<urls.size();i++) {
				if(checksums.get(i)!=null) {
					String checksum = compareChecksums(urls.get(i), checksums.get(i));
					if(!checksum.equals(checksums.get(i)))
						refreshChecksum(checksum,i);
				}
			}
		}
	}
	
	private String compareChecksums(String url, String checksum) {
		Checksum checker = new Checksum();
		try {
			url = checker.getMD5Checksum(new URL(url));
		} catch (MalformedURLException e) {
			Logger.getInstance().log("URL mal formada. No se descargará el archivo", LogLevel.ERROR, getClass(), e.getClass());
			return checksum;
		}
		if(url.equalsIgnoreCase(checksum)) {
			Logger.getInstance().log("Checksums iguales. No se descargará el archivo", LogLevel.INFO, getClass(), null);
			return checksum;
		} else {
			Logger.getInstance().log("Checksums diferentes. Se descargará el archivo", LogLevel.INFO, getClass(), null);
			return url;
		}
	}
	
	private void refreshChecksum(String newChecksum, int index) {
		
	}
	
}
