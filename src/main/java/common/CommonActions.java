package common;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import static common.Config.BROWSER_AND_PLATFORM;
import static constans.Constant.Directory.OUTPUT_PATH;
import static constans.Constant.TimeoutVariables.IMPLICIT_WAIT;

public class CommonActions {

    public static WebDriver createDriver() {
        WebDriver driver = null;
        switch (BROWSER_AND_PLATFORM) {
            case "CHROME_WINDOWS":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                String project_path = System.getProperty("user.dir") + "\\";
                project_path.replace("\\", File.separator);
                System.out.println(project_path);
                chromePrefs.put("download.default_directory", project_path + OUTPUT_PATH);
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("safebrowsing.enabled", false);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-blink-features=AutomationControlled");
                //options.addArguments("--safebrowsing-disable-download-protection");
                options.addArguments("start-maximized", "disable-popup-blocking");
                options.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(options);
                break;
            case "MOZILLA_WINDOWS":
                System.setProperty("webdriver.geckodriver.driver", "src/main/resources/geckodriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("INCORRECT BROWSER NAME - " + BROWSER_AND_PLATFORM);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        return driver;
    }
}
