package Planit.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Planit.Resources.ExtentReport;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;

	//Initialising and Launching driver using this method
	@BeforeMethod(alwaysRun = true)
	public void initializeAndLaunchDriver() throws IOException {
		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Planit//Resources//config.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			// ChromeOptions options = new ChromeOptions();
			// options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().clearDriverCache().setup();
			driver = new ChromeDriver();
			driver.manage().window().fullscreen();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "pathforthedriver");
			// Note: Not mentioning the path here as of now
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Note: Not mentioning the path here as of now
			System.setProperty("webdriver.edge.driver", "pathforthedriver");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}

	//Configuration method for handling JSON test data and converting the data to List<HashMap>
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// String to HashMap- Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	//Configuration method for getting the json data to a JSON array
	public JSONArray getJsonData() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(
				System.getProperty("user.dir") + "//src//test//java//Planit//TestData//Products.json");
		// Read JSON file
		Object obj = jsonParser.parse(reader);
		JSONArray dataList = (JSONArray) obj;
		return dataList;
	}

	//Screenshot method used for capturing screenshots
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}
	

	//closing the driver using this method
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	//Extent Report initialization
	@BeforeSuite
	public void beforeSuite() {
		ExtentReport.initReports();
	}
	
	//Flushing the extent report after usage
	@AfterSuite
	public void afterSuite() {
		ExtentReport.flushReports();
	}
}
