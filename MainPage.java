import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainPage {
    private WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        driver = new FirefoxDriver();
        driver.get("https://allhockey.ru/");
    }
    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
    @Test
    public void currentURL() {
        String expURL = "https://allhockey.ru/";
        String actURL = driver.getCurrentUrl();
        Assert.assertEquals(actURL, expURL);
    }
    @Test
    public void homeTitle() {
        String expTitle = "Allhockey.ru - главный хоккейный информационный сайт России";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle, actTitle);
    }
    @Test
    public void aboutUs() {
        driver.findElement(By.linkText("О нас")).click();
        String expTitle = "О нас";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle, actTitle);
    }
    @Test
    public void login(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(3) > .icon")).click();
        driver.findElement(By.linkText("Вход")).click();
        driver.findElement(By.cssSelector("#userName")).sendKeys("RockStar");
        driver.findElement(By.cssSelector("#userPass")).sendKeys("766638");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @Test
    public void invalidLogin(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(3) > .icon")).click();
        driver.findElement(By.linkText("Вход")).click();
        driver.findElement(By.cssSelector("#userName")).sendKeys("invalidLogin");
        driver.findElement(By.cssSelector("#userPass")).sendKeys("766638");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //String expText = "Ошибка входа. Имя пользователя или пароль указаны неверно.";
        //String actText = driver.findElement(By.cssSelector(".errorMessage")).getText();
        //Assert.assertEquals(actText, expText);
    }
    @Test
    public void invalidPassword(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(3) > .icon")).click();
        driver.findElement(By.linkText("Вход")).click();
        driver.findElement(By.cssSelector("#userName")).sendKeys("RockStar");
        driver.findElement(By.cssSelector("#userPass")).sendKeys("invalidLogin");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expText = "Ошибка входа. Имя пользователя или пароль указаны неверно.";
        String actText = driver.findElement(By.cssSelector(".errorMessage")).getText();
        Assert.assertEquals(actText, expText);
    }
    @Test
    public void emptyLogin(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(3) > .icon")).click();
        driver.findElement(By.linkText("Вход")).click();
        driver.findElement(By.cssSelector("#userName")).sendKeys("");
        driver.findElement(By.cssSelector("#userPass")).sendKeys("766638");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expText = "Логин: необходимо заполнить";
        String actText = driver.findElement(By.cssSelector(".ui > .list > li")).getText();
        Assert.assertEquals(actText, expText);
    }
    @Test
    public void emptyPassword(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(3) > .icon")).click();
        driver.findElement(By.linkText("Вход")).click();
        driver.findElement(By.cssSelector("#userName")).sendKeys("RockStar");
        driver.findElement(By.cssSelector("#userPass")).sendKeys("");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expText = "Логин: необходимо заполнить"; //Баг - сообщение не соответствует
        String actText = driver.findElement(By.cssSelector(".ui > .list > li")).getText();
        Assert.assertEquals(actText, expText);
    }
    @Test
    public void tournaments(){
        driver.findElement(By.cssSelector(".right > .ui > .item:nth-child(2)")).click();
        String expURL = "https://allhockey.ru/stat/list";
        String actURL = driver.getCurrentUrl();
        Assert.assertEquals(actURL, expURL);
    }
    @Test
    public void matches(){
        driver.findElement(By.cssSelector(".right > .ui > .item:nth-child(1)")).click();
        String expURL = "https://allhockey.ru/stat/center";
        String actURL = driver.getCurrentUrl();
        Assert.assertEquals(actURL, expURL);
    }
    @Test
    public void searchRU(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(1) > .icon")).click();
        driver.findElement(By.cssSelector(".ui:nth-child(2) .site-main-search")).sendKeys("Овечкин");
        driver.findElement(By.cssSelector(".ui:nth-child(2) .ui .inverted")).click();
        String expTitle = "Результаты поиска";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle, actTitle);
    }
    @Test
    public void searchEN(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(1) > .icon")).click();
        driver.findElement(By.cssSelector(".ui:nth-child(2) .site-main-search")).sendKeys("Jagr");
        driver.findElement(By.cssSelector(".ui:nth-child(2) .ui .inverted")).click();
        String expTitle = "Результаты поиска";
        String actTitle = driver.getTitle();
        Assert.assertEquals(expTitle, actTitle);
    }
    @Test
    public void searchEmpty(){
        driver.findElement(By.cssSelector(".popmenu-right:nth-child(1) > .icon")).click();
        driver.findElement(By.cssSelector(".ui:nth-child(2) .site-main-search")).sendKeys("");
        driver.findElement(By.cssSelector(".ui:nth-child(2) .ui .inverted")).click();
        String expText = "Материалов, по заданным условиям поиска, не найдено.";
        String actText = driver.findElement(By.cssSelector(".sixteen > .row")).getText();
        Assert.assertEquals(expText, actText);
    }
    @Test
    public void linkVK(){
        driver.findElement(By.cssSelector(".vk")).click();
        String expURL = "https://vk.com/club21848866";
        String actURL = driver.getCurrentUrl();
        Assert.assertEquals(actURL, expURL);
    }
}
