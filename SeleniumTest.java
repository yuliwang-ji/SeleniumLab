import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;


	@RunWith(Parameterized.class)
	public class SeleniumTest {
		
	    private static WebDriver driver;
	    private String StudentName;
	    private String StudentNumber;
	    
	    public SeleniumTest(String StudentName, String StudentNumber) {
	        this.StudentName = StudentName;
	        this.StudentNumber =StudentNumber;
	       
	    }
	 
	    
	    
	    @BeforeClass
	    public static void setUp() throws Exception {
	    	
	        driver = new ChromeDriver();
	        driver.get("http://118.178.137.170:8080/");
	        driver.manage().window().setSize(new Dimension(789, 825));
	    }
	    
	    @Before
	    public void clear() {
	    	driver.findElement(By.id("username")).clear();
	    }
	    
	    @After
	    public void back() {
	    	driver.navigate().back();
	    	
	    }
	    
	    @AfterClass
	    public static void tearDown() {
	        driver.quit();
	      }

	    @Parameterized.Parameters
	    public static Collection<Object[]> getData() {
	    	
	        List<String> content =null;
	        Collection<Object[]> resultArrays = new ArrayList<>();
	        try {
	            content = Files.readAllLines(Paths.get("E:\\课程\\三\\软件测试\\Lab-Selenium\\user_info.csv"));
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        for(int i = 1;i<content.size();i++) {
	        	String[] dataStrings = content.get(i).split(",");
	        	Object[] dataLine = new Object[2];
	        	dataLine[0] = dataStrings[1];
	        	dataLine[1] = dataStrings[0];
	        	resultArrays.add(dataLine);
	        	
	        }
	        return resultArrays;
	        
	    }

	    @Test
	    public void testMain() throws Exception {
	    	
	        driver.findElement(By.id("username")).click();
	        driver.findElement(By.id("username")).sendKeys(StudentName);
	        driver.findElement(By.cssSelector(".btn")).click();
	        WebElement expectedNumberString = driver.findElement(By.xpath("/html/body/section/div/div/div/div/div/table/tbody/tr/td[2]"));
	        assertEquals(StudentNumber, expectedNumberString.getText());
	    }

	   
	}
