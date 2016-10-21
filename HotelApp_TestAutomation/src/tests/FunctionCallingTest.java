package tests;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import HotelApp_BusinessFunctions.HotelApp_BusinessFunctions;

public class FunctionCallingTest extends HotelApp_BusinessFunctions {
 // private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  //public Properties prop;

  @Before
  public void setUp() throws Exception {
    prop = new Properties();
    prop.load(new FileInputStream("./SharedUIMap/SharedUIMap.properties"));
	  driver = new FirefoxDriver();
    baseUrl = "http://adactin.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelWebDriverLogin() throws Exception {
    driver.get(baseUrl + "/HotelApp/index.php");
    //driver.findElement(By.cssSelector(prop.getProperty("Txt_Login_Username"))).clear();
    //driver.findElement(By.cssSelector(prop.getProperty("Txt_Login_Username"))).sendKeys("ramya1309");
    //driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).clear();
    //driver.findElement(By.xpath(prop.getProperty("Txt_Login_Password"))).sendKeys("Ramya3018");
    //driver.findElement(By.id(prop.getProperty("Btn_Login_Login"))).click();
    
    HA_BF_Login(driver, "ramya1309","Ramya3018");
    HA_Search_Hotel (driver, "Sydney","Hotel Creek");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
