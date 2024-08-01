package pages.base;

import common.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constans.Constant.TimeoutVariables.EXPLICIT_WAIT;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * The method for navigating to a specific URL
     */
    public void goToUrl(String url) {
        driver.get(url);
    }

    /**
     * Wait for visibility element in DOM model
     */
    public WebElement waitElementIsVisible (By by) {
        WebElement element = new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    public boolean waitElementIsInvisible (By by) {
        boolean invisible = new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.invisibilityOfElementLocated(by));
        return invisible;
    }
}
