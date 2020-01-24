package util.file;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
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
	private Method method;
	private Field field;
	
	@BeforeClass
	public static void setup() {}
	
	@Before
	public void set() {
		fileManager = new GestorFicheros();
	}
	
	@After
	public void clean() {}
	
	@AfterClass
	public static void cleanup() throws IOException {
		//fileManager.deleteFile();
	}
	
	@Test
	public void testGestorFicheros() {
		assertNull(fileManager.file);
		assertNull(fileManager.bfReader);
		assertNull(fileManager.bfWriter);
	}

	@Test
	public void testCreateFile() throws Throwable {
		File testFile = new File("test/testCreateFile.txt");
		
		Field field1 = GestorFicheros.class.getDeclaredField("file");
		field1.setAccessible(true);
		field1.set(fileManager, testFile);
		
		Method method1 = GestorFicheros.class.getDeclaredMethod("createFile");
		method1.setAccessible(true);
		method1.invoke(fileManager);
		
		assertTrue(testFile.exists());
	}
	/*
	@Test
	public void testOpenFileExists() throws IOException {
		assertTrue(fileManager.openFile("test/test.txt"));
	}
	@Test(expected=IOException.class)
	public void testOpenFileNotExistsTrue() throws IOException {
		assertTrue(fileManager.openFile("test/test.txt"));
	}
	@Test
	public void testOpenFileNotExistsFalse() throws IOException {
		assertTrue(fileManager.openFile("test/test.txt"));
	}*/
}
