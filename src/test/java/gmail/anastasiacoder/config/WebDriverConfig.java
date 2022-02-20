package gmail.anastasiacoder.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:/tmp/credentials.properties",
        "classpath:config/local.properties"
})public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("versionBrowser")
    @DefaultValue("96.0")
    String versionBrowser();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

}