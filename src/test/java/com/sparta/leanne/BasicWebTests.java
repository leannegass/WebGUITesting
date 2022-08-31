package com.sparta.leanne;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class BasicWebTests {
    private static ChromeDriver driver;
    private static ChromeDriverService service;

    private static ChromeOptions options;

    @BeforeAll
    static void setUpAll(){
        service = new ChromeDriverService
                .Builder()
                .usingDriverExecutable(new File("chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        try {
            service.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(service, options);
        //driver = new RemoteWebDriver();
        driver.get("https://www.bbc.com");


    }
    @Test
    void checkThatTheWebDriverWorks(){

        Assertions.assertEquals("BBC - Homepage", driver.getTitle());
    }

    @Test
    void checkThatTheUrlIsCorrect(){
        Assertions.assertEquals("https://www.bbc.com", driver.getCurrentUrl());
    }

    @AfterEach
    void teardown(){
        //driver.close(); //closes browser window after each tests

    }

    @AfterAll
    static void tearDownAll(){
        service.stop(); //quits the webdriver

    }

}
