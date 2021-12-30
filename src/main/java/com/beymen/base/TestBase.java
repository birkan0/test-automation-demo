package com.beymen.base;

import com.beymen.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    public TestBase() {

        //properties dosyasından değer okusun
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/beymen/config/config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //inişılayz ederken yapacağı ayarlamalar
    public static void initialization() {

        String browserName = prop.getProperty("browser");
        String osName = prop.getProperty("os");


        if(browserName.equals("chrome")) {
            if(osName.startsWith("Windows")){
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
            }else if (osName.startsWith("Linux")){
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.");
                driver = new ChromeDriver();
            }
        }else if(browserName.equals("firefox")){
            if(osName.startsWith("Windows")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
            }else if (osName.startsWith("Linux")){
                //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/resources/geckodriver.");
                driver = new FirefoxDriver();
            }
        }





        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, TestUtil.IMPLICIT_WAIT);
        driver.get(prop.getProperty("url"));

    }
}
