package keywordLibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Keyword {
	static WebDriver browser;
	static String parentWindowHandle;
	static String message;
	
	public static void invokeKeyword(String methodName, String args1, String args2, String args3) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = Keyword.class.getDeclaredMethod(methodName, String.class, String.class, String.class);
		method.invoke(Keyword.class, args1, args2, args3);
	}
	
	public static void launchBrowser(String args1, String args2, String args3) throws Exception {
		
		if (args1.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\win10\\Documents\\chromedriver_win32_84\\chromedriver.exe");
			browser = new ChromeDriver();
		}

		else if (args1.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\win10\\Documents\\selenium program\\geckodriver-v0.27.0-win64\\geckodriver.exe");
			browser = new FirefoxDriver();

		} else {

			throw new Exception("invalidBrowserName");

		}
		browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.get(args2);
	}
	public static void enterText(String locator, String args2, String args3 ) {
		
		if(locator.equals("id")) {
			
			browser.findElement(By.id(args2)).sendKeys(args3);
		}else if(locator.equals("name")){
			browser.findElement(By.name(args2)).sendKeys(args3);
		}
		 else if(locator.equals("xpath")) {
				browser.findElement(By.xpath(args2)).sendKeys(args3);
			} else if(locator.equals("css")) {
				browser.findElement(By.cssSelector(args2)).sendKeys(args3);
			}
	}
	public static void click(String locator, String args2, String args3) {
		
		if(locator.equals("id")) {
			browser.findElement(By.id(args2)).click();
		}else if(locator.equals("name")){
			browser.findElement(By.name(args2)).click();;
		}else if(locator.equals("xpath")) {
			browser.findElement(By.xpath(args2)).click();
		}else if(locator.equals("css")) {
			browser.findElement(By.cssSelector(args2)).click();
		}
	}
		public static void selectValue(String locator , String args2, String args3) {
			
			if(locator.equals("id")) {
				browser.findElement(By.id(args2)).click();
			}else if(locator.equals("name")){
				browser.findElement(By.name(args2)).click();;
			}else if(locator.equals("xpath")) {
				browser.findElement(By.xpath(args2)).click();
			}else if(locator.equals("css")) {
				browser.findElement(By.cssSelector(args2)).click();
		}
		}
		public static void selectDate(String locator, String args2, String args3) {
			
			String mmToMonthYear =getMothAndYeatInString(args3);
			String monthYear = browser.findElement(By.cssSelector(".DayPicker-Month > div")).getText();
			
			while(! monthYear.equals( mmToMonthYear)){
				
				browser.findElement(By.cssSelector(".DayPicker-NavButton--next")).click();
				 monthYear = browser.findElement(By.cssSelector(".DayPicker-Month > div")).getText();
				}
			if(monthYear.equals(mmToMonthYear)) {
				browser.findElement(By.id("fare_"+args3)).click();
			}
			
		}
		public static String getMothAndYeatInString(String inputData) {
			
			HashMap<String, String> mmToMonth = new HashMap<String , String>();
			mmToMonth.put("01", "January");
			mmToMonth.put("02", "February");
			mmToMonth.put("03", "March");
			mmToMonth.put("04", "April");
			mmToMonth.put("05", "May");
			mmToMonth.put("06", "Jun");
			mmToMonth.put("07", "July");
			mmToMonth.put("08", "August");
			mmToMonth.put("09", "September");
			mmToMonth.put("10", "October");
			mmToMonth.put("11", "November");
			mmToMonth.put("12", "December");
			
			String mm = inputData.substring(4,6);
			String yr = inputData.substring(0,4);
			String month = mmToMonth.get(mm);
			
			return month + " " +yr;
			
			
		}
		public static void getParentWindowHandle(String locator, String args2 , String args3) {
			parentWindowHandle = browser.getWindowHandle();
		}
		public static void clickLink(String locator , String args2 , String args3) {	
			if(locator.equals("linkText")) {				
				browser.findElement(By.linkText(args2)).click();				
			}else if(locator.equals("partialLinkText")) {	
				browser.findElement(By.partialLinkText(args2)).click();
			}
		}
		
		public static void waitForElement(String locator, String args2, String args3) {
			
			WebDriverWait wait = new WebDriverWait(browser, 10);
			if(locator.equals("linkText")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(args2)));
			}else if(locator.equals("id")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.id(args2)));
			}
		}
		public static void switchToWindow(String locator, String args2, String args3) {
			
			Set<String> allWindow = browser.getWindowHandles();
			 for (String window : allWindow) {
				 
				 if(!window.equals(parentWindowHandle)) {
					 browser.switchTo().window(window);
					 if(browser.getTitle().contains(args2)) {
						 System.out.println("switched to window with title" +args2);
					 }
				 }
				
			}
				
			
		}
		public static void switchToParentWindow(String locator, String args2, String args3) {
			
			browser.switchTo().window(parentWindowHandle);
		}
		public static void fetchText(String locator, String args2, String args3) {
			if(locator.equals("id")) {
				message =	browser.findElement(By.id(args2)).getText();
			}else if(locator.equals("name")){
				message =	browser.findElement(By.name(args2)).getText();;
			}else if(locator.equals("xpath")) {
				message =	browser.findElement(By.xpath(args2)).getText();
			}else if(locator.equals("css")) {
				message =	browser.findElement(By.cssSelector(args2)).getText();
		}
			
		}
         public static void printMessage(String locator, String args2, String args3) {
			
			System.out.println("message" +message);
		}
		public static void closeBrowser(String locator, String args2, String args3) {
			
			browser.quit();
		}
		
		
		
		
		
		
		
		
		
		
	
	
	
	
		
	

}