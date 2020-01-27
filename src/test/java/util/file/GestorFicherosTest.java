package util.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GestorFicherosTest {

	private static GestorFicheros fileManager;
	private static Field fFile;
	private static Field fReader;
	private static Field fWriter;
	private Method method;

	private File testFile;
	private BufferedReader testReader;
	private BufferedWriter testWriter;

	@BeforeClass
	public static void setup() throws NoSuchFieldException, IOException{
		File newFile = new File("test\\testFileExists.txt");
		newFile.createNewFile();
		fFile = GestorFicheros.class.getDeclaredField("file");
		fFile.setAccessible(true);
		fReader = GestorFicheros.class.getDeclaredField("bfReader");
		fReader.setAccessible(true);
		fWriter = GestorFicheros.class.getDeclaredField("bfWriter");
		fWriter.setAccessible(true);
	}

	@Before
	public void set() {
		fileManager = new GestorFicheros();
	}

	@After
	public void clean() throws IOException {
		
	}

	@AfterClass
	public static void cleanup() throws IOException {
		//borrar carpeta 'test/'
	}

	@Test
	public void testGestorFicheros() {
		assertNull(fileManager.file);
		assertNull(fileManager.bfReader);
		assertNull(fileManager.bfWriter);
	}
	
	@Test(expected=IOException.class)
	public void testOpenFile() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("openFile", String.class);
		method.setAccessible(true);
		
		String filepath = "test\\testFileExists.txt";
		method.invoke(fileManager, filepath);
		assertEquals(fileManager.file.getPath(), filepath);
		
		filepath = "test\\testFileNotExists.txt";
		method.invoke(fileManager, filepath);
		assertEquals(fileManager.file.getPath(), filepath);
		
		filepath = "";
		try {			
			method.invoke(fileManager, filepath);
		} catch (Throwable e) {
			assertNull(fileManager.file);
			throw e.getCause();
		}
	}
	
	@Test(expected = IOException.class)
	public void testCreateFile() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("createFile");
		method.setAccessible(true);

		testFile = new File("test\\testCreateFile.txt");
		fFile.set(fileManager, testFile);
		method.invoke(fileManager);
		assertTrue(testFile.exists());
		
		testFile = new File("test\\test\\test.txt");
		fFile.set(fileManager, testFile);
		method.invoke(fileManager);
		assertTrue(testFile.exists());	
		
		testFile = new File("");
		fFile.set(fileManager, testFile);
		try {
			method.invoke(fileManager);
		} catch (Throwable e) {
			throw e.getCause();
		}
	}
	
	@Test(expected=IOException.class)
	public void testLoadURL() throws Throwable{
		method = GestorFicheros.class.getDeclaredMethod("loadURL", URL.class);
		method.setAccessible(true);
		
		URL testURL = null;
		method.invoke(fileManager, testURL);
		assertNull(fileManager.bfReader);
		
		testURL = new URL("http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/campings_de_euskadi/opendata/alojamientos.xml");
		method.invoke(fileManager, testURL);
		assertNotNull(fileManager.bfReader);
				
		testURL = new URL("http://exception.com/testException.xml");
		try {			
			method.invoke(fileManager, testURL);
		} catch (Throwable e) {
			assertNull(fileManager.bfReader);
			throw e.getCause();
		}
	}

	@Test(expected=IOException.class)
	public void testLoadReader() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("loadReader");
		method.setAccessible(true);
		
		testFile = new File("test\\testFileExists.txt");
		fFile.set(fileManager, testFile);
		method.invoke(fileManager);
		assertNotNull(fileManager.bfReader);
		
		fFile.set(fileManager, null);
		try {
			method.invoke(fileManager);
		} catch (Throwable e) {
			assertNull(fileManager.bfReader);
			throw e.getCause();
		}
	}
	
	@Test(expected=IOException.class)
	public void testLoadWriter() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("loadWriter", boolean.class);
		method.setAccessible(true);
		
		testFile = new File("test\\testFileExists.txt");
		fFile.set(fileManager, testFile);
		method.invoke(fileManager,true);
		assertNotNull(fileManager.bfWriter);
		
		fFile.set(fileManager, null);
		try {
			method.invoke(fileManager,false);
		} catch (Throwable e) {
			assertNull(fileManager.bfWriter);
			throw e.getCause();
		}
	}
	
	@Test
	public void testCloseReader() throws Throwable {
		method = GestorFicheros.class.getDeclaredMethod("closeReader");
		method.setAccessible(true);
		
		method.invoke(fileManager);
	}
}
