import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UserLoginTest {

    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080/LeaveWord";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    //Test Flow Preserve Step 1: - Login
    public void testLogin()throws Exception{
        driver.get(baseUrl + "/");

        //define username and password
        String username = "ding";
        String password = "12345";

        //call function login
        login(driver,username,password);
        Thread.sleep(1000);

        //turn to url: http://localhost:8080/LeaveWord/words.html
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl.contains("words"), true);
    }

    public static void login(WebDriver driver,String username,String password){
        driver.findElement(By.id("inputUserName")).clear();
        driver.findElement(By.id("inputUserName")).sendKeys(username);
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='登录']")).click();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
