import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class UserRegisterTest {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://10.141.211.177";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testRegister()throws Exception{
        driver.get(baseUrl + "/");

        //define username and password
        String username = randomString();
        String password = randomString();
        //register
        driver.findElement(By.xpath("//button[text()='注册']")).click();
        //turn to url: http://localhost:8080/LeaveWord/register.html
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl.contains("register"), true);

        register(driver,username,password);
        Thread.sleep(1000);
        //turn to url: http://localhost:8080/LeaveWord/words.html
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl.contains("words"), true);
    }

    public static void register(WebDriver driver,String username,String password){
        driver.findElement(By.id("inputUserName")).clear();
        driver.findElement(By.id("inputUserName")).sendKeys(username);
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='注册']")).click();
    }

    static String randomString(){
        RandomString rs = new RandomString();
        return rs.nextString();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
