package tatacliq;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LearnAdvanceSel {

	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        ChromeDriver driver=new ChromeDriver(option);
		
		
		//Maximize the window
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//1. Load the url as https://www.tatacliq.com/
		driver.get("https://www.tatacliq.com/ ");
		
		//driver.findElement(By.id("wzrk-confirm")).click();
		
		//2. MouseHover on 'Brands'
		 Actions brands= new Actions(driver);
		 WebElement hoverElement = driver.findElement(By.xpath("//div[text()='Brands']"));
		 brands.moveToElement(hoverElement).perform();
		   
		//3. MouseHover on 'Watches & Accessories' 
		 Actions watches= new Actions(driver);
		 WebElement hoverElement2 = driver.findElement(By.xpath("//div[text()='Watches & Accessories']"));
		 watches.moveToElement(hoverElement2).perform();
		
		//4. Choose the first option from the 'Featured brands'.
		 driver.findElement(By.xpath("(//div[text()='Casio'])[1]")).click();
		
		//5. Select sortby: New Arrivals 
		 WebElement sourceDropdown = driver.findElement(By.className("SelectBoxDesktop__hideSelect"));
         Select dd1=new Select(sourceDropdown);
         dd1.selectByValue("isProductNew"); 
         
		//6. choose men from catagories filter.
         driver.findElement(By.xpath("(//div[@class='CheckBox__base'])[1]")).click();
         
         Thread.sleep(3000);
		//7. print all price of watches 
         driver.findElement(By.xpath("(//div[@class='Accordion__iconPlus'])[3]")).click();
        
        // List<String> allprices=new ArrayList<String>();
         //allprices.add( driver.findElement(By.xpath("//div[@class='PriceFilterTabDesktop__priceList']")).getText());
         //System.out.println("Price of all watches :"+allprices);
         List<WebElement> allprices = driver.findElements(By.xpath("//h3[@class='ProductDescription__boldText']")); 
         System.out.println(allprices.size());
         for(int i=0;i<allprices.size();i++)
         {
        	 System.out.println(allprices.get(i).getText());
         }
		//8. click on the first resulting watch. 
         driver.findElement(By.xpath("(//div[@class='ProductModule__dummyDiv'])[1]")).click();
         
		//9. Handle Alert Pop Up 9. compare two price are similar 
        Set<String> allWindowAddress = driver.getWindowHandles();
		List<String> allWindows=new ArrayList<String>(allWindowAddress);
		driver.switchTo().window(allWindows.get(1));
		
		//10. click Add to cart and get count from the cart icon. 
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		String count = driver.findElement(By.xpath("//span[text()='1']")).getText();
		System.out.println("Count on the bag :" +count);
		
		//11. Click on the cart 
		driver.findElement(By.xpath("//div[@class='DesktopHeader__myBagShow']")).click();
		
		//12. Take a snap of the resulting page. 
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./screenshot/tatacliqcom.png");
		FileUtils.copyFile(source, destination);
		
		//13. Close All the opened windows one by one. 
		driver.close();
		
		
		
		

	}

}
