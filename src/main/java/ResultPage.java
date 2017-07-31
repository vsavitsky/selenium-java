import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by VladimirSavitski on 7/14/2017.
 */
public class ResultPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div/h3/a")
    List<WebElement> links;

    public ResultPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
        System.out.println("Links:");
        for (WebElement e : links) {
            System.out.println("    - " + e.getText());
        }
    }

    public Integer getLinksCount() {
        return links.size();
    }

    public void clickLinkByText (String linkText) {
        for (WebElement e : links) {
            if (e.getText().equals(linkText)) {
                e.click();
                return;
            }
        }
        System.out.println("! - Link with text '" + linkText + "' - NOT FOUND !");
    }
}
