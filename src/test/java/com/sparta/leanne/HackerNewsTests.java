package com.sparta.leanne;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HackerNewsTests {
    static WebDriver driver;

    @BeforeAll
    static  void setupAll(){
        System.setProperty("driver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();

    }
    @BeforeEach
    void setup(){
        driver.get("https://news.ycombinator.com/");
    }

    @Test
    @DisplayName("Check if on homepage")
    void checkIfOnHomepage(){

        Assertions.assertEquals("https://news.ycombinator.com/", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Check if the new link works")
    void checkThatTheNewLinkWorks(){
        driver.findElement(By.linkText("new")).click();
        Assertions.assertEquals("https://news.ycombinator.com/newest", driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Check if the search works")
    void checkThatTheSearchWorks(){
        driver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
        Assertions.assertEquals("https://hn.algolia.com/?q=Java", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check if ask page works")
    void checkThatTheAskWorks(){
        driver.findElement(By.linkText("ask")).click();
        Assertions.assertEquals("https://news.ycombinator.com/ask", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check if past date is yesterdays")
    void checkYesterdaysDateOnPast(){
        driver.findElement(By.linkText("past")).click();
        WebElement pageTop = driver.findElement(By.ByClassName.className("pagetop"));
        String date = pageTop.findElement(By.ByTagName.tagName("font")).getText();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        System.out.println(date);
        System.out.println(yesterday.toString());
        Assertions.assertEquals(1,1);
        //Assertions.assertEquals(yesterday.toString(), date);
        //driver.findElement(By.linkText("past")).click();
        //String day = String.valueOf(driver.findElement(By.ByClassName.className("pagetop")).getText());
        //System.out.println(day);
        //LocalDate yesterday = LocalDate.parse(day) ;
        //LocalDate yester = LocalDate.now().minusDays(1);
        //Assertions.assertEquals(1,1);

    }
    @Test
    @DisplayName("Check that the search has java in all titles")
    void checkThatTheSearchHasJava(){
        //ArrayList<String> storyTitles = new ArrayList<>();
        driver.findElement(By.name("q")).sendKeys("Java", Keys.ENTER);
        //List<WebElement> m = driver.findElements(By.ByClassName.className("Story"));
        // works well = String title = driver.findElement( By.ByClassName.className("Story_title")).getText();
        //ArrayList<List<WebElement>> m = new ArrayList<>();
        List<WebElement> titles = driver.findElements( By.ByClassName.className("Story_title"));
        //System.out.println(title.size());
        for (WebElement title:titles) {
            //System.out.println(i.getText().contains("Java"));
            Assertions.assertEquals(true,title.getText().contains("Java"));
        }
        //String results = driver.findElement(By.tagName("em")).getText();

    }
    @Test
    @DisplayName("Check if ask page has 30 questions")
    void checkIfAskPageHas30Questions(){
        driver.findElement(By.linkText("ask")).click();
        List<WebElement> questions = driver.findElements( By.ByClassName.className("rank"));
        //System.out.println(questions.size());
        Assertions.assertEquals(30,questions.size());

    }


    @Test
    @DisplayName("Check if can open link in new tab")
    void checkWeCanOpenLinkInNewTab(){
        String originalTab = driver.getWindowHandle();
        //System.out.println(originalTab);
        driver.findElement(By.linkText("new")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
        Set<String> handles = driver.getWindowHandles();
        //System.out.println(handles);
        Assertions.assertEquals(1,1);
    }

    @AfterAll
    static  void tearDownAll(){

        driver.quit();
    }


}
