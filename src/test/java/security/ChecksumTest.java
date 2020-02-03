package security;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChecksumTest {
	
	static File testFile = new File("testFile.txt");

	@BeforeClass
	public static void setup() throws IOException { 
		testFile.createNewFile();
	}

	@AfterClass
	public static void cleanup() throws IOException {
		testFile.delete();
	}

	@Test
	public void testGetMD5Checksum() throws Exception {
		String expected = "D41D8CD98F00B204E9800998ECF8427E";
		String actual = Checksum.getMD5Checksum("testFile.txt");
		assertEquals(expected, actual.toUpperCase());
	}

}
