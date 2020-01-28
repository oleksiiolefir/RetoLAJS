package util.file;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChecksumTest {

	private static Checksum tester;

	@BeforeClass
	public static void setup() throws Exception {
		tester = new Checksum();
		new GestorFicheros().writeFile("test\\testFile.txt", false, "testText");

	}

	@Before
	public void set() {

	}

	@After
	public void clean() {

	}

	@AfterClass
	public static void cleanup() {
		new GestorFicheros().deleteFile("test");
	}

	@Test
	public void testGetMD5Checksum() throws Exception {
		String expected = "3CE35752AF8B291E5EDE2EDD8ED3E98A";
		String actual = tester.getMD5Checksum("test\\testFile.txt");
		assertEquals(expected, actual.toUpperCase());
	}

}
