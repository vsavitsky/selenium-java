import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by VladimirSavitski on 7/13/2017.
 */
public class testExampleWithPageObject {

    WebDriver webDriver;
    WebDriverWait wait;
    SearchPage searchPage;
    ResultPage resultPage;

    @Before
    public void setUp() throws InterruptedException {
        System.out.println("testExample > Started");
        System.setProperty("webdriver.chrome.driver","F:\\Selenium Java\\chromedriver.exe");

        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 30, 1000);
        webDriver.get("http://google.com/");

        searchPage = new SearchPage(webDriver);
        searchPage.searchForString("Searching - Wikipedia");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/h3/a"))); // best practice -> wait in action methods
    }

    @Test
    public void testGoogleSearch() throws InterruptedException {
        resultPage = new ResultPage(webDriver);

        System.out.println("links count = " + resultPage.getLinksCount());
        Assert.assertTrue(resultPage.getLinksCount() == 10);

        resultPage.clickLinkByText("Searching - Wikipedia");

        Assert.assertTrue(webDriver.getCurrentUrl().contains("wikipedia.org"));
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }

        System.out.println("testExample > Finished");
    }
}
