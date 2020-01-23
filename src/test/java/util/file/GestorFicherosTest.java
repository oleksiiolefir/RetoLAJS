package util.file;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GestorFicherosTest {

	private static GestorFicheros fileManager;
	private Field fFile;
	private Field fReader;
	private Field fWriter;
	private static Field[] fields;
	private static Method[] methods;
	
	@BeforeClass
	public static void setup() throws IOException {
		fileManager = new GestorFicheros();
		fields = GestorFicheros.class.getDeclaredFields();
		methods = GestorFicheros.class.getDeclaredMethods();
	}
	
	@Before
	public void set() {
		
	}
	
	@After
	public void clean() {
		
	}
	
	@AfterClass
	public static void cleanup() throws IOException {
		fileManager.deleteFile();
	}
	
	@Test
	public void testGestorFicheros() {
		assertNull(fileManager.file);
		assertNull(fileManager.bfReader);
		assertNull(fileManager.bfWriter);
	}

	@Test
	public void testOpenFileExists() throws IOException {
		assertTrue(fileManager.openFile("test/test.txt"));
	}
	@Test
	public void testOpenFileNotExistsTrue() {
		assertTrue(fileManager.openFile("test/test.txt"));
	}
	@Test
	public void testOpenFileNotExistsFalse() {
		assertTrue(fileManager.openFile("test/test.txt"));
	}
}
