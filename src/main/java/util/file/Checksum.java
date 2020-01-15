package util.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Checksum {

	private MessageDigest md;
	
	private byte[] crearChecksum(String path) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(path);
			md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while((bytesRead = stream.read(buffer)) != -1) {
				if(bytesRead>0)
					md.update(buffer, 0, bytesRead);
			}
			return md.digest();
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				stream.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	private byte[] crearChecksum(InputStream stream) {
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int bytesRead = 0;
			while((bytesRead = stream.read(buffer)) != -1) {
				if(bytesRead>0)
					md.update(buffer, 0, bytesRead);
			}
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		return md.digest();
	}
	
	public String getMD5Checksum(String path) {
		byte[] bytes = crearChecksum(path);
		String md5Checksum = "";
		for(byte by:bytes) 
			md5Checksum += Integer.toString( ( by & 0xff ) + 0x100, 16).substring( 1 );	
		return md5Checksum;
	}
	
	public String getMD5Checksum(InputStream stream) {
		byte[] bytes = crearChecksum(stream);
		String md5Checksum = "";
		for(byte by:bytes) 
			md5Checksum += Integer.toString( ( by & 0xff ) + 0x100, 16).substring( 1 );	
		return md5Checksum;
	}
}
