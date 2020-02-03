package file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

import file.FileManager;

public class GestorFicherosTest {

	private static FileManager fileManager;
	private static Field fFile;
	private Method method;

	static File testFile = new File("testFile.txt");

	@BeforeClass
	public static void setup() throws NoSuchFieldException, IOException {
		fileManager = new FileManager();
		testFile.createNewFile();

		fFile = FileManager.class.getDeclaredField("file");
		fFile.setAccessible(true);
	}

	@Before
	public void set() {
		fileManager = new FileManager();
	}

	@After
	public void clean() throws IOException {

	}

	@AfterClass
	public static void cleanup() throws IOException {
		testFile.delete();
	}

	@Test
	public void testGestorFicheros() {
		assertNull(fileManager.file);
		assertNull(fileManager.bfReader);
		assertNull(fileManager.bfWriter);
	}

	@Test(expected = IOException.class)
	public void testOpenFile() throws Throwable {
		method = FileManager.class.getDeclaredMethod("openFile", String.class);
		method.setAccessible(true);

		String filepath = "testFile.txt";
		method.invoke(fileManager, filepath);
		assertEquals(fileManager.file.getPath(), filepath);

		filepath = "test\\testFileNotExists.txt";
		method.invoke(fileManager, filepath);
		assertEquals(fileManager.file.getPath(), filepath);
	}

	@Test(expected = IOException.class)
	public void testCreateFile() throws Throwable {
		method = FileManager.class.getDeclaredMethod("createFile");
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

	@Test
	public void testLoadURL() throws Throwable {
		method = FileManager.class.getDeclaredMethod("loadReader", URL.class);
		method.setAccessible(true);

		URL testURL = null;
		method.invoke(fileManager, testURL);
		assertNull(fileManager.bfReader);

		testURL = new URL(
				"http://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/campings_de_euskadi/opendata/alojamientos.xml");
		method.invoke(fileManager, testURL);
		assertNotNull(fileManager.bfReader);
	}

	@Test
	public void testLoadReader() throws Throwable {
		method = FileManager.class.getDeclaredMethod("loadFileReader");
		method.setAccessible(true);

		testFile = new File("testFile.txt");
		fFile.set(fileManager, testFile);
		method.invoke(fileManager);
		assertNotNull(fileManager.bfReader);
	}

	@Test
	public void testLoadWriter() throws Throwable {
		method = FileManager.class.getDeclaredMethod("loadFileWriter", boolean.class);
		method.setAccessible(true);

		testFile = new File("testFile.txt");
		fFile.set(fileManager, testFile);
		method.invoke(fileManager, true);
		assertNotNull(fileManager.bfWriter);

		fFile.set(fileManager, null);
		method.invoke(fileManager, true);
		assertNull(fileManager.bfWriter);
	}
	
	@Test
	public void testCloseFile() {
		fileManager.closeFile();
		assertNull(fileManager.file);
		assertNull(fileManager.bfReader);
		assertNull(fileManager.bfWriter);
	}
}
