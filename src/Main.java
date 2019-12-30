import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);

        String baseUrl = "https://www.mytek.tn/";
        String productName = "Clé USB ADATA AUV240 16Go - Blanc";

        try {
            driver.get(baseUrl);

            // authentification

            String loginLinkXpath = "//*[@id=\'header\']/div[1]/div/div/nav/div[1]/a[1]";
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginLinkXpath)));
            driver.findElement(By.xpath(loginLinkXpath)).click();
            By loginFormEmailInput = By.xpath("//*[@id=\'email\']");
            By loginFormPasswordInput= By.xpath("//*[@id=\'passwd']");
            By loginFormSubmitButton = By.xpath("//*[@id='SubmitLogin']");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(loginFormEmailInput));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(loginFormPasswordInput));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(loginFormSubmitButton));

            System.out.println("Form is loaded");

            driver.findElement(loginFormEmailInput).sendKeys("mralaajerbi@gmail.com");
            driver.findElement(loginFormPasswordInput).sendKeys("123456789");
            driver.findElement(loginFormSubmitButton).click();

            By informatiqueProductCategory = By.partialLinkText("INFORMATIQUE");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(informatiqueProductCategory));
            driver.findElement(informatiqueProductCategory).click();

            // go through the pages until you find the desired product
            WebElement productElement;
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("columns")));

            By loadingElement = By.cssSelector("#center_column > ul > p > img");
            while (true) {
                try {
                    productElement = driver.findElement(By.partialLinkText(productName));
                    System.out.println("Found!!!");
                    System.out.println(productElement.getText());
                    break;
                }
                catch (NoSuchElementException e) {
                    System.out.println("Not found in this page");
                    driver.findElement(By.xpath("//*[@id=\'pagination_next\']/a")).click();
                    webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingElement));
                }
            }

            productElement.click();

            By addToCartButton = By.xpath("//*[@id='add_to_cart']/button");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));

            driver.findElement(addToCartButton).click();

            By checkoutConfirmationButton = By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a");

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(checkoutConfirmationButton));

            driver.findElement(checkoutConfirmationButton).click();

            // find the classical guitar product link and click it
            /*driver.findElement(By.xpath("//*[@id=\'center_column\']/div/div[5]/div[1]/a")).click();

            By addToCartButton = By.xpath("//*[@id='add_to_cart']/button");
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));*/


/*
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"center_column\"]/div/div[5]/div[1]/a")));

            driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div[5]/div[1]/a")).click();

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='add_to_cart']/button")));
            String addToCartButtonText = driver.findElement(By.xpath("//*[@id='add_to_cart']/button"));
            System.out.println(addToCartButtonText);*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }


    }
}
