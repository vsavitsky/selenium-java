import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by VladimirSavitski on 7/14/2017.
 */
public class SearchPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(css = "#lst-ib")
    WebElement searchField;

    public SearchPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    public void searchForString(String searchText) {
        System.out.println("Searching for '" + searchText + "' now ...");
        searchField.sendKeys(searchText + Keys.ENTER);
    }

}
