package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    //Locators

    private By productImageLocator = By.xpath("//img[@alt='V Super Soda Diet Cola Soft Drink - 300 ml']");

    private By addToCartButton = By.xpath("//button[@class='AppButton']");
    private By firstCheckoutButton = By.xpath("//button[@class='AppButton w-full mt-4 with-icon']");
    private By secondCheckoutButton = By.xpath("//button[@class='AppButton mt-11 block w-full font-bold with-icon']");

    private By signInButton = By.xpath("//span[@class='text-secondary-700 underline cursor-pointer']");
    private By phoneNumberTextBox = By.id("phone");
    private By passwordTextBox = By.id("password");
    private By loginButton = By.xpath("//span[@class='font-bold block w-full text-center']");

    private By mergeItemsButton = By.xpath("//button[@class='AppButton mb-6 lg:mb-0']");
    private By proceedToPaymentButton = By.xpath("//button[@class='AppButton mt-10 with-icon']");
    private By placeOrderButton = By.xpath("//button[@class='AppButton mt-10 with-icon']");

    private By thankYouMsg = By.xpath("//h1[normalize-space()='Thank you for your order!']");
    private String expectedMessage = "Thank you for your order!";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.actions = new Actions(driver);
    }



    //To Scroll Down
    public void scrollToElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);


        } catch (Exception e) {
            System.out.println("Could not scroll to element: " + e.getMessage());
        }
    }

    //1 Step
    public void scrollToProductImage() {
        scrollToElement(productImageLocator);
    }





    // Click on the product image (2 Step)
    public void clickOnProduct() {
        try {
            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(productImageLocator));

            try {
                product.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
            }

        } catch (Exception e) {
            System.out.println("Error while clicking product: " + e.getMessage());
        }
    }

    // Add the product to cart (3 Step)
    public void addToCart() {

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",btn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    // Click checkout first button then second (4 Step)
    // Click checkout button (always firstCheckoutButton)
    public void clickCheckoutButton() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(firstCheckoutButton));
            actions.moveToElement(btn).click().perform();
        } catch (Exception e) {
            System.out.println("Error while clicking checkout button: " + e.getMessage());
        }
    }

    // Login (5 Step)
    public void login(String phone, String pass) {

        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberTextBox)).sendKeys(phone);
        driver.findElement(passwordTextBox).sendKeys(pass);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // Merge items if pop-up appears (7 Step)
    public void mergeItems() {
        wait.until(ExpectedConditions.elementToBeClickable(mergeItemsButton)).click();
    }

    // Proceed to payment button (8 Step)
    public void proceedToPayment() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(proceedToPaymentButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    // Place Order (9 Step)
    public void placeOrder() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public String getActualResult() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouMsg));
        return msg.getText();
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }


}

