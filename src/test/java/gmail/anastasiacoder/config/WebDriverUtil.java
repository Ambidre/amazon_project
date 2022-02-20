package gmail.anastasiacoder.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class WebDriverUtil {

    private static final WebDriverConfig CONFIG = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    private static final CredentialsConfig CREDENTIALS = ConfigFactory.create(CredentialsConfig.class);
    private static final String LOGIN = CREDENTIALS.login();
    private static final String PASSWORD = CREDENTIALS.password();
    private static final String SELENOID_URL = CREDENTIALS.remoteURL();
    private static final String REMOTE_URL = format("https://%s:%s@selenoid.autotests.cloud/wd/hub/", LOGIN, PASSWORD);

    public static void configure() {
        Configuration.browser = CONFIG.browser();
        Configuration.browserVersion = CONFIG.versionBrowser();
        Configuration.browserSize = CONFIG.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--enable-automation");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-gpu");

        if (!System.getProperty("remoteURL").equals("")) {
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.remote = REMOTE_URL;
            //Configuration.remote = System.getProperty("remoteURL");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        Configuration.browserCapabilities = capabilities;
//        Configuration.timeout = 20000;
//        Configuration.pageLoadTimeout = 20000;
    }
}