package pages.mail;

import common.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;

import static constans.Constant.Directory.INPUT_PATH;
import static constans.Constant.TestData.TESTING_TEXT;

public class NewLetterPage extends BasePage {

    public NewLetterPage(WebDriver driver) {
        super(driver);
    }

    private final By recipientMailFieldFinder = By.xpath("//*[contains(text(), 'Кому')]");
    private final By recipientMailField = By.xpath("//*[contains(text(), 'Кому')]/following::input[contains(@aria-label, 'Кому')]");
    private final By recipientOption = By.xpath("//div[@role='option']");// and contains(@pkd-target, '"+login+"')]
    private final By subjectBoxField = By.xpath("//input[@name='subjectbox']");
    private final By addFile = By.xpath("//input[@type='file']");
    private final By sendBtn = By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']");
    private final By progressBar = By.xpath("//*[@role='progressbar']");

    public NewLetterPage checkRecipientMailFieldExist() {
        driver.findElement(recipientMailFieldFinder);
        return this;
    }

    public NewLetterPage sendLoginInRecipientMailField(User user) {
        driver.findElement(recipientMailField).sendKeys(user.login);
        return this;
    }

    public NewLetterPage clickActionToRecipientOption(Actions action) {
        WebElement recipient = driver.findElement(recipientOption);
        action.click(recipient);
        action.perform();
        return this;
    }

    public NewLetterPage sendSubjectIntoSubjectBox() {
        driver.findElement(subjectBoxField).sendKeys(TESTING_TEXT);
        return this;
    }

    public NewLetterPage addFileToLetter(String fileName) {
        driver.findElement(addFile).sendKeys(fileName);
        return this;
    }

    public NewLetterPage clickSendBtn() {
        driver.findElement(sendBtn).click();
        return this;
    }

    public NewLetterPage checkThatFileUpload() {
        boolean checkFileUpload = waitElementIsInvisible(progressBar);
        return this;
    }

}
