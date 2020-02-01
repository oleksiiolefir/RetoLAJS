package security;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import file.FileManager;

public class ChecksumTest {

	@BeforeClass
	public static void setup() throws Exception {
	}

	@Before
	public void set() {

	}

	@After
	public void clean() {

	}

	@AfterClass
	public static void cleanup() throws IOException {
		new FileManager().deleteFile("test");
	}

	@Test
	public void testGetMD5Checksum() throws Exception {
		String expected = "3CE35752AF8B291E5EDE2EDD8ED3E98A";
		String actual = Checksum.getMD5Checksum("test\\testFile.txt");
		assertEquals(expected, actual.toUpperCase());
	}

}
