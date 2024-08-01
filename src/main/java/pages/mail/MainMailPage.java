package pages.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

import static constans.Constant.TestData.TESTING_TEXT;

public class MainMailPage extends BasePage {

    public MainMailPage(WebDriver driver) {
        super(driver);
    }
    private final By newLetterBtn = By.xpath("//div[@class='T-I T-I-KE L3']");
    private final By letterSendMsg = By.xpath("//*[contains(text(), 'Сообщение отправлено')]");
    private final By incomingBtn = By.xpath("//a[contains(text(), 'Входящие')]");
    private final By findField = By.xpath("//input[@class='gb_je aJh']");
    private final By letterBySubjectField = By.xpath("//b[contains(text(),'"+ TESTING_TEXT +"')]");

    public MainMailPage createNewLetter() {
        driver.findElement(newLetterBtn).click();
        return this;
    }

    public MainMailPage clickIncomingBtn() {
        driver.findElement(incomingBtn).click();
        return this;
    }

    public MainMailPage checkThatLetterSend() {
        WebElement checkMessageAboutSending = waitElementIsVisible(letterSendMsg);
        return this;
    }

    public MainMailPage sendTextIntoFindField() {
        WebElement findFieldElement = waitElementIsVisible(findField);
        findFieldElement.sendKeys(TESTING_TEXT);
        return this;
    }

    public MainMailPage clickLetterByFoundSubject() {
        WebElement letterBySubjectElement = waitElementIsVisible(letterBySubjectField);
        letterBySubjectElement.click();
        return this;
    }

}

