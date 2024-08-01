package pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

public class MailBodyViewPage extends BasePage {

    public MailBodyViewPage(WebDriver driver) {
        super(driver);
    }

    private final By attachField = By.xpath("//span[contains(text(), 'Предварительный просмотр файла')]/parent::a");
    private final By downloadBtn = By.xpath("//div[@role='tooltip' and contains(text(), 'Скачать')]/preceding-sibling::button");

    public MailBodyViewPage moveToAttachField(Actions action) {
        WebElement attachFieldElement = driver.findElement(attachField);
        action.moveToElement(attachFieldElement);
        action.perform();
        return this;
    }

    public MailBodyViewPage clickDownloadBtn() {
        WebElement downloadBtnElement = waitElementIsVisible(downloadBtn);
        downloadBtnElement.click();
        return this;
    }

}
