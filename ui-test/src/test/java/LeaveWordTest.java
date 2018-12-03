import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LeaveWordTest {

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
    public void testLogin()throws Exception{
        driver.get(baseUrl + "/");

        //define username and password
        String username = "ding";
        String password = "12345";
        login(driver, username, password);
        //turn to url: http://localhost:8080/LeaveWord/words.html
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl.contains("words"), true);
    }

    @Test(dependsOnMethods = {"testLogin"})
    public void testLeaveWord()throws Exception{
        //define title and content
        String title = randomString();
        String content = randomString();

        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys(title);
        driver.findElement(By.id("content")).clear();
        driver.findElement(By.id("content")).sendKeys(content);
        driver.findElement(By.xpath("//button[text()='提交']")).click();
        Thread.sleep(5000);

        String titlePath = "//h2[contains(text(), " + title + ")]";
        String contentPath = "//h5[contains(text(), "+ content +")]";
        WebElement titleElement = driver.findElement(By.xpath(titlePath));
        Assert.assertEquals(null != titleElement, true);
        WebElement contentElement = driver.findElement(By.xpath(contentPath));
        Assert.assertEquals(null != contentElement, true);
    }

    public static void login(WebDriver driver,String username,String password){
        driver.findElement(By.id("inputUserName")).clear();
        driver.findElement(By.id("inputUserName")).sendKeys(username);
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='登录']")).click();
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
