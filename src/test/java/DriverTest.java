import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import static org.junit.Assert.assertTrue;

public class DriverTest {
    @Test
    public void driverTest () throws InterruptedException {
        String downloadFilePath = "C:" + File.separator + "downloads";
        String sendFilePath = "C:\\test\\";
        String fileName = "TestTextFile.txt";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", downloadFilePath);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("safebrowsing.enabled", false);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        //options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("start-maximized", "disable-popup-blocking");
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions action = new Actions(driver);


        String mailField = "//input[@type='email']";
        String nextBtn = "//span[contains(text(), 'Next')]";
        String passwordField = "//input[@type='password']";

        String login = "chudogashevilya@gmail.com";
        String password = "Ac22081966.";



        String recipientMailFieldFinder = "//*[contains(text(), 'Кому')]"; // //input[@id=':tw'] //*[contains(text(), 'Кому')] //*[contains(text(), 'Получатели')]
        String recipientMailField = "//*[contains(text(), 'Кому')]/following::input[contains(@aria-label, 'Кому')]";
        // //*[contains(text(), 'Кому')]/following::input[contains(@aria-label, 'Кому')]
        String recipientOption = "//div[@role='option' and contains(@pkd-target, '"+login+"')]";
        String subjectBoxField = "//input[@name='subjectbox']";
        String addFile = "//input[@type='file']";
        String sendBtn = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']";
        String checkThatFileUploading = "//*[@role='progressbar']";

        String subjectText = "AttachTesting";

        File fileSend = new File(sendFilePath + fileName);

        String newMailBtn = "//div[@class='T-I T-I-KE L3']";
        String mailSendMsg = "//*[contains(text(), 'Сообщение отправлено')]";
        String inputBtn = "//a[contains(text(), 'Входящие')]";
        String findField = "//input[@class='gb_je aJh']";
        String mailBySubjectField = "//b[contains(text(),'"+subjectText+"')]";

        String attachField = "//span[contains(text(), 'Предварительный просмотр файла')]/parent::a";
        String downloadFile = "//button[starts-with(@aria-label,'Скачать')]";
        String downloadBtn = "//div[@role='tooltip' and contains(text(), 'Скачать')]/preceding-sibling::button";


        driver.get("https://mail.google.com/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mailField))).sendKeys(login); //pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextBtn))).click();//pass

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(passwordField))).sendKeys(password);//pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextBtn))).click();//pass

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newMailBtn))).click();//pass

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recipientMailFieldFinder)));//pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recipientMailField))).sendKeys(login);//pass
        WebElement recipient = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(recipientOption)));//pass
        action.click(recipient);//pass
        action.perform();//pass

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(subjectBoxField))).click();//pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(subjectBoxField))).sendKeys(subjectText);//pass
        driver.findElement(By.xpath(addFile)).sendKeys(sendFilePath + fileName);//pass
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(checkThatFileUploading)));//pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sendBtn))).click();//pass

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mailSendMsg)));//pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputBtn))).click();//pass
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(findField))).sendKeys(subjectText);//pass
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mailBySubjectField))).click();//pass

        String fileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attachField))).getAttribute("href");
        System.out.println(fileLink);
        WebElement downloadField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(attachField)));
        action.moveToElement(downloadField);
        action.perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(downloadBtn))).click();

//        URL url = new URL(fileLink);
        File fileDownload = new File(downloadFilePath, fileName);
        try {
//            FileUtils.copyURLToFile(url, fileDownload);
            FileUtils.waitFor(fileDownload, 10);
            assertTrue("The files differ", FileUtils.contentEquals(fileSend, fileDownload));
            FileUtils.delete(fileDownload);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
