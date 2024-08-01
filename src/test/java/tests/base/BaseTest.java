package tests.base;

import common.CommonActions;
import common.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;
import pages.mail.MailBodyViewPage;
import pages.mail.MainMailPage;
import pages.mail.NewLetterPage;
import pages.mail.SignInPages;

import static common.Config.CLEAR_COOKIES;
import static common.Config.HOLD_BROWSER_OPEN;

@Execution(ExecutionMode.CONCURRENT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver = CommonActions.createDriver();
    protected BasePage basePage = new BasePage(driver);
    protected SignInPages signInPages = new SignInPages(driver);
    protected MainMailPage mainMailPage = new MainMailPage(driver);
    protected NewLetterPage newLetterPage  = new NewLetterPage(driver);
    protected MailBodyViewPage mailBodyViewPage  = new MailBodyViewPage(driver);
    protected Actions action = new Actions(driver);
    protected User user = new User("chudogashevilya@gmail.com", "Ac22081966.");

    @AfterEach
    void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    @AfterAll
    void close() {
        if (HOLD_BROWSER_OPEN) {
            driver.close();
        }
    }
}
