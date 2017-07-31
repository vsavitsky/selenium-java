import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by VladimirSavitski on 7/13/2017.
 */
public class testExampleSimple {

    WebDriver webDriver;
    WebDriverWait wait;

    @Test
    public void testGoogleSearch() throws InterruptedException {

        System.out.println("testExampleSimple > Started");
        System.setProperty("webdriver.chrome.driver","F:\\Selenium Java\\chromedriver.exe");

        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 30, 1000); // WebDriverWait
        webDriver.get("http://google.com/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#lst-ib"))); // ExpectedConditions
        WebElement searchField = webDriver.findElement(By.cssSelector("#lst-ib"));
        searchField.sendKeys("Searching - Wikipedia" + Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/h3/a"))); // ExpectedConditions

        List<WebElement> links = webDriver.findElements(By.xpath("//div/h3/a")); // List<WebElement> // visible for mobile duplicates
        System.out.println("links count = " + links.size());
        Assert.assertTrue(links.size() == 10);

        for (WebElement e : links) {
            if (e.getText().equals("Searching - Wikipedia")) {
                e.click();
                return;
            }
        }

        Assert.assertTrue(webDriver.getCurrentUrl().contains("wikipedia.org"));
        Thread.sleep(5000); // just pause

        webDriver.quit();

        System.out.println("testExample > Finished");
    }
}
