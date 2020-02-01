package security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

	private static InputStream stream;
	
	public static String getMD5Checksum(String filepath) throws IOException {	
		try {
			stream = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("El fichero " + filepath + " no existe");
		}		
		return getMD5Checksum();
	}

	public static String getMD5Checksum(URL url) throws IOException {
		try {
			stream = url.openStream();
		} catch (IOException e) {
			throw new IOException("Error al abrir stream de URL " + url.toString(), e);
		}		
		return getMD5Checksum();
	}
	
	private static byte[] getMD5Bytes() throws IOException  {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while ((bytesRead = stream.read(buffer)) != -1) 
				md.update(buffer, 0, bytesRead);
			return md.digest();
		} catch (IOException e) {
			throw new IOException("Error al leer bytes de stream", e);
		} catch (NoSuchAlgorithmException e) {
			return new byte[]{};
		} finally {
			stream.close();
		}
	}

	private static String getMD5Checksum() throws IOException {
		byte[] bytes = getMD5Bytes();
		String md5Checksum = "";
		for (byte b : bytes)
			md5Checksum += Integer.toString((b & 0xff) + 0x100, 16).substring(1);
		return md5Checksum;
	}

}
