import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FullBuyProcess {
    WebDriver driver;

    @Test(priority = 1)
    void openBrowser() {

        System.out.println("Opening Browser...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 2)
    void selectComputersTab() throws InterruptedException {

//        driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div[2]/ul[1]/li[1]/a")).click();
    }

    @Test(priority = 3)
    void selectDesktopsSection() {
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[1]/div/div[1]/div/div/a/img")).click();
        System.out.println("Selected Desktops Section");
    }

    @Test(priority = 4)
    void selectProduct() {
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div[3]/div/div[2]/div[2]/div[2]/div/div/div[3]/div/div[2]/h2/a")).click();
        System.out.println("Selected Lenovo IdeaCentre 600 All-in-One PC");
    }

    @Test(priority = 5)
    void addToCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-button-3\"]")).click();
        System.out.println("Product Added to Cart");
        Thread.sleep(1000);
    }

    @Test(priority = 6)
    void cartSection() throws InterruptedException {
//        driver.findElement(By.className("cart-label")).click();
        driver.findElement(By.xpath("//*[@id=\"bar-notification\"]/div/p/a")).click();
        driver.findElement(By.className("qty-input")).clear();
        driver.findElement(By.className("qty-input")).sendKeys("3");
        driver.findElement(By.id("updatecart")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("termsofservice")).click();
        driver.findElement(By.id("checkout")).click();
        System.out.println("Shopping Cart updated to 3 units and Begin Checkout");
    }

    @Test(priority = 7)
    void signInPage(){
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div[1]/div[1]/div[3]/button[1]")).click();
        System.out.println("Continue checkout as Guest");
    }

    @Test(priority = 8)
    void billingAddress() throws InterruptedException {
        driver.findElement(By.id("BillingNewAddress_FirstName")).sendKeys("Test");
        driver.findElement(By.id("BillingNewAddress_LastName")).sendKeys("Check");
        driver.findElement(By.id("BillingNewAddress_Email")).sendKeys("Test123@gmail.com");
        driver.findElement(By.id("BillingNewAddress_CountryId")).sendKeys("United Kingdom");
        driver.findElement(By.id("BillingNewAddress_City")).sendKeys("London");
        driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("54 Rainy Avenue");
        driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("RA17 4AW");
        driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("12345678910");
        driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]")).click();
        System.out.println("Billing Details Filled");
        Thread.sleep(2000);
    }
    @Test(priority = 9)
    void shippingMethod(){
        driver.findElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']")).click();
        System.out.println("Shipping Method Selected");
    }
    @Test(priority = 10)
    void paymentMethod() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//label[text()='Credit Card']")).click();
        driver.findElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']")).click();
        System.out.println("Credit Card Selected");
    }

    @Test(priority = 11)
    void paymentInformation() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.id("CardholderName")).sendKeys("Mr B Saka");
        driver.findElement(By.id("CardNumber")).sendKeys("1234567891011111111111");
        driver.findElement(By.id("ExpireMonth")).sendKeys("06");
        driver.findElement(By.id("ExpireYear")).sendKeys("2027");
        driver.findElement(By.id("CardCode")).sendKeys("123");
        driver.findElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']")).click();
        System.out.println("Card Details Filled");
    }
    @Test(priority = 12)
    void confirmOrder() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button")).click();
        System.out.println("Order Confirmed");
    }

    @Test(priority = 13)
    void checkoutComplete() throws InterruptedException {
        Thread.sleep(1000);
        String ExpectedPageUrl ="https://demo.nopcommerce.com/checkout/completed";
        if(ExpectedPageUrl.equals(driver.getCurrentUrl())){
            System.out.println("Transaction Complete");
        }else{
            System.out.println("Transaction Incomplete");
        }
        driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[2]/div/div[3]/button")).click();
    }
}
