package com.amazon.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties"
})public interface WebDriverConfig extends Config {

    @DefaultValue("chrome")
    String browser();

    @DefaultValue("91.0")
    String versionBrowser();

    @DefaultValue("1920x1080")
    String browserSize();

}