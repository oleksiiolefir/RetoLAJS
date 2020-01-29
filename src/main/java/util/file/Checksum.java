package util.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import util.logger.LogLevel;
import util.logger.Logger;

public class Checksum {

	private MessageDigest md;
	private InputStream stream;

	private byte[] crearChecksum() throws IOException {
		try {
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer)) != -1) 
				md.update(buffer, 0, bytesRead);
			return md.digest();
		} catch (IOException e) {
			Logger.getInstance().log("Error al leer stream de lectura", LogLevel.ERROR, getClass(), e);
			return null;
		} finally {
			stream.close();
		}
	}

	public String getMD5Checksum(String filepath) {
		try {
			md = MessageDigest.getInstance("MD5");
			stream = new FileInputStream(filepath);
			return getMD5Checksum();
		} catch (IOException | NoSuchAlgorithmException e) {
			return null;
		}
	}

	public String getMD5Checksum(URL url) {
		try {
			md = MessageDigest.getInstance("MD5");
			stream = url.openStream();
			return getMD5Checksum();
		} catch (IOException | NoSuchAlgorithmException e) {
			return null;
		}
	}

	private String getMD5Checksum() throws IOException {
		byte[] bytes = crearChecksum();
		String md5Checksum = "";
		for (byte b : bytes)
			md5Checksum += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
		return md5Checksum;
	}

}
