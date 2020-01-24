package util.file;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GestorFicherosTest {

	private static GestorFicheros fileManager;
	private File testFile;
	private BufferedReader testReader;
	private BufferedWriter testWriter;
	private Method method;
	private Field fFile, fReader, fWriter;


	@BeforeClass
	public static void setup() {}

	@Before
	public void set() throws Exception {
		fileManager = new GestorFicheros();
		fFile = GestorFicheros.class.getDeclaredField("file");
		fFile.setAccessible(true);
		fReader = GestorFicheros.class.getDeclaredField("bfReader");
		fReader.setAccessible(true);
		fWriter = GestorFicheros.class.getDeclaredField("bfWriter");
		fWriter.setAccessible(true);
	}

	@After
	public void clean() {
		// borrar ficheros creados
	}

	@AfterClass
	public static void cleanup() throws IOException {}

	@Test
	public void testGestorFicheros() {
		assertNull(fileManager.file);
		assertNull(fileManager.bfReader);
		assertNull(fileManager.bfWriter);
	}

	@Test
	public void testOpenFile() {
		
	}

	@Test
	public void testCreateFileTrue() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("createFile");
		method.setAccessible(true);

		File testFile = new File("test/testCreateFile.txt");
		File testFile2 = new File("test/test");
		ArrayList<File> testFiles = new ArrayList<File>();
		testFiles.add(testFile);
		testFiles.add(testFile2);
		for (File file : testFiles) {
			fFile.set(fileManager, file);
			method.invoke(fileManager);
			assertTrue(file.exists());
		}
		// borrar archivos
	}
	
	@Test(expected=IOException.class)
	public void testCreateFileException() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("createFile");
		method.setAccessible(true);

		File testFile = new File("");
		fFile.set(fileManager, testFile);

		try {
			method.invoke(fileManager);
		} catch (Throwable e) {
			throw e.getCause();
		}
	}

	/*
	 * @Test public void testOpenFileExists() throws IOException {
	 * assertTrue(fileManager.openFile("test/test.txt")); }
	 * 
	 * @Test(expected=IOException.class) public void testOpenFileNotExistsTrue()
	 * throws IOException { assertTrue(fileManager.openFile("test/test.txt")); }
	 * 
	 * @Test public void testOpenFileNotExistsFalse() throws IOException {
	 * assertTrue(fileManager.openFile("test/test.txt")); }
	 */
}
