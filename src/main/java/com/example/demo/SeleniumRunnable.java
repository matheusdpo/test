package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;


@Controller
@EnableScheduling
public class SeleniumRunnable {


    @PostConstruct
    public void run() {
        WebDriverManager.chromedriver().setup();

        System.setProperty("webdriver.chrome.verboseLogging", "false");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", "");

        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--disable-default-app");
        options.addArguments("--accept-notifications");
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        driver.get("http://www.google.com");

        System.out.println("Titulo da pagina: " + driver.getTitle());
    }
}
