import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class Login {

	public static WebDriver dr;
	public static String DEV_KEY = "441b106fbc9ec8537fa53f4a64ae4a7d";

	public static String SERVER_URL = "http://localhost/testlink-1.9.16/lib/api/xmlrpc/v1/xmlrpc.php";
	public static String PROJECT_NAME = "testlinkproject_poc";
	public static String PLAN_NAME = "testplan";
	public static String BUILD_NAME = "sample-build";
	public static String TESTCASE   ="ID-1";
	
	// private String exception;
	String exception = null;

	@Test

	public void logindata() throws Exception {

		String result = "";
		String exception = null;
		String fireFoxDriverPath = getPath() + File.separator + "drivers" + File.separator
				+ "geckodriver_64Bit.exe";
		System.setProperty("webdriver.gecko.driver", fireFoxDriverPath);
		
		try {
			WebDriver driver = new FirefoxDriver();
			driver.get("https://accounts.google.com");
			//driver.manage().window().maximize();
		/*	result = TestLinkAPIResults.TEST_PASSED;
		    driver.findElement(By.id("Email")).sendKeys("your gmail login id");
			Thread.sleep(2000);
			driver.findElement(By.id("next")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("Passwd")).sendKeys("*********");
			Thread.sleep(1000);
			driver.findElement(By.id("signIn")).click();*/
			result = TestLinkAPIResults.TEST_PASSED;
			updateTestLinkResult(TESTCASE, null, result);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			result = TestLinkAPIResults.TEST_FAILED;
			exception = e.getMessage();
			updateTestLinkResult(TESTCASE, exception, result);
		}

	}

	private void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException {
		TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
		testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME, TESTCASE, BUILD_NAME, exception, result);

	}
	
	public String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");
		return path;
	}

}
