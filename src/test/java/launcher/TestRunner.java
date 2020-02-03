package launcher;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import file.AllTestsFile;
import security.ChecksumTest;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllTestsFile.class, ChecksumTest.class);
		for (Failure failure : result.getFailures()) 
			System.out.println(failure.toString());
		
		System.out.println("Result==" + result.wasSuccessful());
	}

}
