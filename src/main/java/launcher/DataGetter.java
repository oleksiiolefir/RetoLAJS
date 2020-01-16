package launcher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import util.file.Checksum;
import util.file.GestorFicheros;
import util.logger.LogLevel;
import util.logger.Logger;

public class DataGetter {
	
	private static final String PATH_URLS = "files/urlsAlojamientos.txt";

	private GestorFicheros fileManager;
	private ArrayList<String> urls;
	
	public DataGetter() {
		fileManager = new GestorFicheros();
	}
	
	public void start() {
		loadUrls();
		readUrls();
	}
	
	private void loadUrls() {
		urls = fileManager.readFile(PATH_URLS);
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
			return true;
		} else {
			refreshChecksum(checksums[0]);
			return false;
		}

	}
	
	private void refreshChecksum(String newChecksum) {
		
	}
	
}
