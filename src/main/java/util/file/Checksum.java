package util.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	private boolean buildMessageDigest(String algorithm) {
		try {
			md = MessageDigest.getInstance(algorithm);
			return true;
		} catch (NoSuchAlgorithmException e) {
			Logger.getInstance().log("El algoritmo " + algorithm + " no existe", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	private boolean loadStream(String path) {
		try {
			stream = new FileInputStream(path);
			return true;
		} catch (FileNotFoundException e) {
			Logger.getInstance().log("Error al cargar stream. No se encuentra el fichero", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	private boolean loadStream(URL url) {
		try {
			stream = url.openStream();
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cargar stream. No se pudo abrir la URL", LogLevel.ERROR, getClass(),
					e.getClass());
			return false;
		}
	}

	private boolean closeStream() {
		try {
			stream.close();
			return true;
		} catch (IOException e) {
			Logger.getInstance().log("Error al cerrar stream de lectura", LogLevel.ERROR, getClass(), e.getClass());
			return false;
		}
	}

	private byte[] crearChecksum() {
		try {
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer)) != -1) {
				if (bytesRead > 0)
					md.update(buffer, 0, bytesRead);
			}
			return md.digest();
		} catch (IOException e) {
			Logger.getInstance().log("Error al leer stream de lectura", LogLevel.ERROR, getClass(), e.getClass());
			return null;
		} finally {
			closeStream();
		}
	}

	public String getMD5Checksum(String path) {
		if (buildMessageDigest("MD5")) {
			if (loadStream(path))
				return getMD5Checksum();
		}
		return null;
	}

	public String getMD5Checksum(URL url) {
		if (buildMessageDigest("MD5")) {
			if (loadStream(url))
				return getMD5Checksum();
		}
		return null;
	}

	private String getMD5Checksum() {
		byte[] bytes = crearChecksum();
		String md5Checksum = "";
		for (byte b : bytes)
			md5Checksum += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
		return md5Checksum;
	}

}
