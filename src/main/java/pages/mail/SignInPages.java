package pages.mail;

import common.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class SignInPages extends BasePage {

    public SignInPages(WebDriver driver) {
        super(driver);
    }

    private final By loginField = By.xpath("//input[@type='email']");
    private final By nextBtn = By.xpath("//span[contains(text(), 'Next')]");
    private final By passwordField = By.xpath("//input[@type='password']");

    public SignInPages pasteLogin(User user) {
        driver.findElement(loginField).sendKeys(user.login);
        return this;
    }

    public SignInPages pastePassword(User user) {
        WebElement passwordFieldElement = waitElementIsVisible(passwordField);
        passwordFieldElement.sendKeys(user.password);
        return this;
    }

    public SignInPages clickNext() {
        driver.findElement(nextBtn).click();
        return this;
    }
}
