package brand.automation;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PreferenceCenter {

  private WebDriver driver;
  ExtentReports extent;
  ExtentTest logger; 
  String actual,actualText,id,name;
  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
  @BeforeClass
  public void setUp() throws Exception {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browser", "Chrome");
    caps.setCapability("browser_version", "62.0");
    caps.setCapability("os", "Windows");
    caps.setCapability("os_version", "7");
    caps.setCapability("resolution", "1024x768");

    // setup reports
    
    extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
    
    extent
    .addSystemInfo("Host Name", "SoftwareTestingMaterial")
    .addSystemInfo("Environment", "Automation Testing")
    .addSystemInfo("User Name", "Rajkumar SM");
    //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
    //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
    extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
    
    driver = new RemoteWebDriver(
      new URL("https://zaqwsx1:zqybk75kc8SQJmSXFKvg@hub-cloud.browserstack.com/wd/hub"),
      caps
    );
  }

  @Test(priority=1)
  public void loginLink() throws Exception {
	  name = "loginPage";  
	  logger = extent.startTest("Login Link");
	logger.log(LogStatus.INFO, "Browser started ");
    driver.get("http://www.puffs.com");
    
  
    assertEquals(driver.findElement(By.linkText("Login")).getText(), "Login");
    
    logger.log(LogStatus.PASS, "Login link");
   
    
    //ashot Library used on Browserstack
    
    
   
    
    
    driver.findElement(By.linkText("Login")).click();
    assertEquals(driver.getTitle(), "Login Form");
    logger.log(LogStatus.PASS, "Login Link Clicked", getImage(name));

    
  }

  @Test(priority=2)
  
  public void invalidLogin() throws IOException, InterruptedException{
	  name = "invalidLogin";  
	  logger = extent.startTest("Invalid Login");
  	System.out.println("Let me click on login");
   driver.manage().timeouts().implicitlyWait(450000, TimeUnit.SECONDS);
   
  
   
   driver.manage().timeouts().implicitlyWait(450000, TimeUnit.SECONDS);
  driver.findElement(By.id("phdesktopbody_0_username")).clear();
  driver.findElement(By.id("phdesktopbody_0_username")).sendKeys("puck2stick@gma22il.com");
  driver.findElement(By.id("phdesktopbody_0_password")).clear();
  driver.findElement(By.id("phdesktopbody_0_password")).sendKeys("zaq12wsx");
  driver.findElement(By.id("phdesktopbody_0_submit")).click();

  id="phdesktopbody_0_Message";
  actualText=driver.findElement(By.id(id)).getText();
  actual="The email and password combination you entered is incorrect. Please try again, or click the link below to create an account.";
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  assertEquals(actual,actualText);
  
  logger.log(LogStatus.PASS, "Invalid Login");
  logger.log(LogStatus.PASS, "Invalid Login", getImage(name));
  
  }
  
  
  @Test(priority=3)
  public void validLogin() throws IOException{
	  name = "validLogin";  
	  logger = extent.startTest("Valid Login");
	  System.out.println("Let me check on valid login");
	   driver.manage().timeouts().implicitlyWait(450000, TimeUnit.SECONDS);
	   driver.findElements(By.className("event_profile_login"));

	  driver.findElement(By.id("phdesktopbody_0_username")).clear();
	  driver.findElement(By.id("phdesktopbody_0_username")).sendKeys("albert.golubev@pkt.com");
	  driver.findElement(By.id("phdesktopbody_0_password")).clear();
	  driver.findElement(By.id("phdesktopbody_0_password")).sendKeys("zaq12wsx");
	  driver.findElement(By.id("phdesktopbody_0_submit")).click();

	  id="phdesktopbody_0_lblDOBValue";
	  actualText=driver.findElement(By.id(id)).getText();
	  actual="12/1962";
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  assertEquals(actual,actualText);
	  logger.log(LogStatus.PASS, "Valid Login", getImage(name));
	  
  }
  
  @Test(priority=4)
  public void resetPassword() throws IOException{
	  logger = extent.startTest("Reset Password");
	  System.out.println("Let me reset the password");
	  
	    name="invalid_reset_password.png";
	  System.out.println("Let me reset password");
	  driver.findElement(By.id("phdesktopbody_0_HlinkEdit")).click();
	   driver.manage().timeouts().implicitlyWait(450000, TimeUnit.SECONDS);
		  driver.findElement(By.id("phdesktopbody_0_HlinkReset")).click();
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 
	 
	 

	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.findElement(By.name("phdesktopbody_0$username")).clear();
	  driver.findElement(By.name("phdesktopbody_0$username")).sendKeys("asfsaf2qao@sdasd.com");
	  driver.findElement(By.name("phdesktopbody_0$zipcode")).clear();
	  driver.findElement(By.name("phdesktopbody_0$zipcode")).sendKeys("11235");
	  driver.findElement(By.name("phdesktopbody_0$LoginSubmitBtn")).click();
	  
	  id="phdesktopbody_0_Message";
	  actualText=driver.findElement(By.id(id)).getText();
	  actual="We could not find an account with that Email address and Birthday/ZIP code.";
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  assertEquals(actual,actualText);
	  logger.log(LogStatus.PASS, "Password Reset", getImage(name));
        name="valid_reset_password.png";
      driver.findElement(By.name("phdesktopbody_0$username")).clear();
	  driver.findElement(By.name("phdesktopbody_0$username")).sendKeys("albert.golubev@pkt.com");
	  driver.findElement(By.name("phdesktopbody_0$zipcode")).clear();
	  driver.findElement(By.name("phdesktopbody_0$zipcode")).sendKeys("11235");
	  driver.findElement(By.name("phdesktopbody_0$LoginSubmitBtn")).click();
	  
	  id="phdesktopbody_0_Header";
	  actualText=driver.findElement(By.id(id)).getText();
	  actual="SET PASSWORD";
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  assertEquals(actual,actualText);
	  logger.log(LogStatus.PASS, "Updated the password", getImage(name));
      
     
  
  }
  
  @Test(priority=5)
  public void setPassword() throws IOException{
	  
	    name="set_password.png";
	    logger = extent.startTest("Let me set new password");
	  System.out.println("Let me set new password");
	   driver.manage().timeouts().implicitlyWait(450000, TimeUnit.SECONDS);
	 
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 
	  driver.findElement(By.name("phdesktopbody_0$showpassword")).click();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  
	  driver.findElement(By.name("phdesktopbody_0$password")).clear();
	  driver.findElement(By.name("phdesktopbody_0$password")).sendKeys("alb");
	  driver.findElement(By.name("phdesktopbody_0$ResetSubmitBtn")).click();
	  
	  id="phdesktopbody_0_revpassword";
	  actualText=driver.findElement(By.id(id)).getText();
	  actual="The password must be minimum 8 characters, including at least 1 letter and 1 number.";
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  assertEquals(actual,actualText);
	  logger.log(LogStatus.PASS, "Updated the password", getImage(name));
        name="valid_set_password.png";
        
        id="phdesktopbody_0_revpassword";
		  actualText=driver.findElement(By.id(id)).getText();
		  actual="The password must be minimum 8 characters, including at least 1 letter and 1 number.";
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  assertEquals(actual,actualText);
		  logger.log(LogStatus.PASS, "Invalid password", getImage(name));
	        name="blank_password.png";
	        driver.findElement(By.name("phdesktopbody_0$password")).sendKeys("");
			  driver.findElement(By.name("phdesktopbody_0$ResetSubmitBtn")).click();
			  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        id="phdesktopbody_0_revpassword";
			  actualText=driver.findElement(By.id(id)).getText();
			  actual="The password must be minimum 8 characters, including at least 1 letter and 1 number.";
			 
			  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			  assertEquals(actual,actualText);
			  logger.log(LogStatus.PASS, "Invalid password", getImage(name));
		        name="valid_set_password.png";
		        
		        
      driver.findElement(By.name("phdesktopbody_0$password")).clear();
	  driver.findElement(By.name("phdesktopbody_0$password")).sendKeys("zaq12wsx");
	  driver.findElement(By.name("phdesktopbody_0$ResetSubmitBtn")).click();
	  id="phdesktopbody_0_Header";
	  actualText=driver.findElement(By.id(id)).getText();
	  actual="YOUR PASSWORD HAS BEEN RESET";
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  assertEquals(actual,actualText);
	  logger.log(LogStatus.PASS, "Password Has Been Reset", getImage(name));
      
       name="after_reset_password.png";
       
     
      driver.findElement(By.name("phdesktopbody_0$username")).sendKeys("albert.golubev@pkt.com");
	  driver.findElement(By.name("phdesktopbody_0$password")).clear();
	  driver.findElement(By.name("phdesktopbody_0$password")).sendKeys("zaq12wsx");
	  driver.findElement(By.name("phdesktopbody_0$submit")).click();

	  id="phdesktopbody_0_labelDOB";
	  actualText=driver.findElement(By.id(id)).getText();
	  actual="Birthday";
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  assertEquals(actual,actualText);
	  logger.log(LogStatus.PASS, "Updated the password", getImage(name));
      
     
  
  }
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}
	@AfterTest
	public void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report. 
              extent.flush();
              //Call close() at the very end of your session to clear all resources. 
              //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
              //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
              //Once this method is called, calling any Extent method will throw an error.
              //close() - To close all the operation
              extent.close();
  }
  @AfterClass
  public void tearDown() throws Exception {
    driver.quit();
  }
  
  public String getImage(String name) throws IOException
  {
	  Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	    ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") + "/" + name + ".png"));

	    String image= logger.addScreenCapture((System.getProperty("user.dir") + "/" + name + ".png"));
	    System.out.println("Image name is " + image);
	    
	    return image;
  }

}

