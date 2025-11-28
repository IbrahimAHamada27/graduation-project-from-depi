package BaseTest;

import Pages.firstHomePage;
import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTestClass {

    public   WebDriver driver;
    public   WebDriverWait wait;
    public firstHomePage firstHomePage;
    public HomePage HomePage;
   //public secondHomePage secondHomePage = new secondHomePage(driver);


    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


//driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(){
        firstHomePage = new firstHomePage(driver);
        HomePage = new HomePage(driver);
        driver.get("https://seoudisupermarket.com/en/");
        selectLocationAndEnterHomePage();
    }


    public void selectLocationAndEnterHomePage() {

        try {
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button[aria-label='close']")
            ));
            closeButton.click();
        } catch (Exception e) {
            System.out.println("Pop-up مش موجود، نكمل...");
        }

        // city dropdown
        WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
        cityDropdown.click();
        WebElement cairoOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='city']//li[contains(text(),'Cairo')] | //option[contains(text(),'Cairo')]")
        ));
        cairoOption.click();

        driver.findElement(By.tagName("body")).click(); // يقفل القائمة

        // area dropdown
        WebElement areaDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("area")));
        areaDropdown.click();
        WebElement firstAreaOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='area']//li[1] | //select[@id='area']/option[2]")
        ));
        firstAreaOption.click();

        driver.findElement(By.tagName("body")).click(); // يقفل القائمة

        // district dropdown
        WebElement districtDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("district")));
        districtDropdown.click();
        WebElement firstDistrictOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='district']//li[1] | //select[@id='district']/option[2]")
        ));
        firstDistrictOption.click();

        driver.findElement(By.tagName("body")).click();

        // Continue to shop
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Continue to shop')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);

        // انتظار ظهور Search bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search Products']")
        ));

    }

    @AfterClass
    public void tearDown() {


        //  driver.quit();
    }
}
