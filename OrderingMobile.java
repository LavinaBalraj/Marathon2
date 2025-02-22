package servicenow;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class OrderingMobile {

	public static void main(String[] args) throws IOException {
		ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        ChromeDriver driver=new ChromeDriver(option);
		
		
		//Maximize the window
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//1. Launch ServiceNow application 
		driver.get("https://dev186929.service-now.com/");
		
		//2. Login with valid credentials username as admin and password
		//User name: admin Password: 2AqjN!lC2!Vy 
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("2AqjN!lC2!Vy");
		driver.findElement(By.id("sysverb_login")).click();
		
		//3. Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog 
		Shadow shadow=new Shadow(driver);
		shadow.setImplicitWait(10);
		shadow.findElementByXPath("//div[text()='All']").click();
		//shadow.findElementByXPath("//input[@id='filter']").click();
		//shadow.findElementByXPath("//input[@id='filter']").sendKeys("Service Catalog",Keys.ENTER);
		shadow.findElementByXPath("//span[text()='Service Catalog']").click();
	    
	    //step4:click on mobile
        WebElement serviceEle = shadow.findElementByXPath("//iframe[@title='Main Content']");
        driver.switchTo().frame(serviceEle);
        driver.findElement(By.xpath("//a[text()='Mobiles']")).click();
	  
        // 5. Select Apple iphone13pro 
        driver.findElement(By.xpath("//strong[text()='Apple iPhone 13 pro']")).click();
       
        //6. Choose yes option in lost or broken iPhone 
        driver.findElement(By.xpath("//label[text()='Yes']")).click();
	  
        //7. Enter phonenumber as 99 in original phonenumber field 
        driver.findElement(By.xpath("//input[@id='IO:4afecf4e9747011021983d1e6253af34']")).sendKeys("99");
        
        //8. Select Unlimited from the dropdown in Monthly data allowance 
        WebElement sourceele = driver.findElement(By.id("IO:ff1f478e9747011021983d1e6253af68"));
 	    Select dd1=new Select(sourceele);
	    dd1.selectByIndex(2);
        
        //9. Update color field to Blue and storage field to 512GB 
	    driver.findElement(By.xpath("//label[text()='Sierra Blue']")).click();
	    driver.findElement(By.xpath("//label[contains(text(),'512 GB')]")).click();
	    
        //10. Click on Order now option 
	    driver.findElement(By.id("oi_order_now_button")).click();
	    
        //11. Verify order is placed and copy the request number 
	       
	    String referencenumber = driver.findElement(By.id("requesturl")).getText();
	    System.out.println("Order is Placed :"+referencenumber );
	    String titleofpage = shadow.findElementByXPath("//div[@class='polaris-header-experience-title']").getText();
	    //String titleofpage = driver.getTitle();
	    System.out.println(titleofpage);
	    if(titleofpage.contains(referencenumber)) {
	    	System.out.println("Mobile Phone order is verified");
	    }
	    else {
	    	System.out.println("Not Placed");
	    }
	    
	    
        //12.Take a Snapshot of order placed page 
	    File source = driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./screenshot/orderphone.png");
		FileUtils.copyFile(source, destination);
	}

}
