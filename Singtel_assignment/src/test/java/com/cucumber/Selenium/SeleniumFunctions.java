package com.cucumber.Selenium;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunctions {

	public static WebDriver driver = null;
	public static WebDriverWait waitVar = null;
	public static int totalCountOfToDoItemListed = 0;
	public static int totalCountOfToDoItemCompleted = 0;
	public static int totalCountOfToDoItemRemoved = 0;
	public static String strToDoItem = null;

	public void createDriver() throws MalformedURLException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void launchUrl(String Url) {
		driver.get(Url);
		waitVar = new WebDriverWait(driver, 30);
	}

	public void teardown() {
		driver.quit();
	}

	public void handleAdvancedButton(By advancedButtonEle, By proceedlinkEle) throws Exception {
		waitVar.until(ExpectedConditions.presenceOfElementLocated(advancedButtonEle));
		WebElement advancedButton = driver.findElement(advancedButtonEle);
		advancedButton.isDisplayed();
		advancedButton.click();
		waitVar.until(ExpectedConditions.presenceOfElementLocated(proceedlinkEle));
		WebElement proceedlink = driver.findElement(proceedlinkEle);
		proceedlink.isDisplayed();
		proceedlink.click();
		takeSnapShot("Screenshots/homepage.png");
	}

	public void ishomepageDisplayed(By homaPagevalidationEle) {
		waitVar.until(ExpectedConditions.presenceOfElementLocated(homaPagevalidationEle));

		driver.findElement(homaPagevalidationEle).isDisplayed();
	}

	public void addNewToDoItem(By newToDoTextBoxEle, String strNewToDoItem) throws Exception {
		waitVar.until(ExpectedConditions.presenceOfElementLocated(newToDoTextBoxEle));

		WebElement newToDoItem = driver.findElement(newToDoTextBoxEle);
		newToDoItem.isDisplayed();
		newToDoItem.sendKeys(strNewToDoItem);
		newToDoItem.sendKeys(Keys.ENTER);
		// newToDoItem.submit();
		totalCountOfToDoItemListed++;
		strToDoItem = strNewToDoItem;
		takeSnapShot("Screenshots/ToDoItem" + totalCountOfToDoItemListed + ".png");
	}

	public void validateNewlyAddedItem(String validationMsg) {
		By newlyAddedToDoItem = By.xpath("//*[.=\"" + strToDoItem + "\"]");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(newlyAddedToDoItem));

		WebElement newToDoItem = driver.findElement(newlyAddedToDoItem);
		if (newToDoItem.isDisplayed()) {
			System.out.println("Test case Passed and Validation Is :" + validationMsg);
		} else {
			System.out.println("Actual is:" + "Item: " + strToDoItem + " not added");
			System.out.println("Expected is:" + validationMsg);
		}
	}

	public void clickCheckboxToSelectToDoItem(String strToDoItem) throws Exception {
		By existingToDoItemEle = By.xpath("//*[.=\"" + strToDoItem + "\"]/preceding-sibling::input");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(existingToDoItemEle));

		WebElement existingToDoItem = driver.findElement(existingToDoItemEle);
		existingToDoItem.click();
		totalCountOfToDoItemCompleted++;
		takeSnapShot("Screenshots/CompletedToDoItem" + totalCountOfToDoItemCompleted + ".png");
	}

	public void validateCompletedToDoItems(String validationMsg) {
		By toDoItemListCountEle = By.xpath("//*[@class=\"todo-count\"]/strong");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(toDoItemListCountEle));
		WebElement toDoItemListCount = driver.findElement(toDoItemListCountEle);
		int countOfToDoItemLeft = Integer.parseInt(toDoItemListCount.getText());
		if (countOfToDoItemLeft == totalCountOfToDoItemListed - totalCountOfToDoItemCompleted) {
			System.out.println("Test case Passed and Validation Is :" + validationMsg);
		} else {
			int Itemcompleted = totalCountOfToDoItemListed - countOfToDoItemLeft;
			System.out.println("Actual is:" + Itemcompleted);
			System.out.println("Expected is:" + totalCountOfToDoItemCompleted);
		}
	}

	public void clickDeleteToRemoveToDoItem(String strToDoItem) throws Exception {
		By existingToDoItemEle = By
				.xpath("//*[@class=\"todo completed\"]//label[.=\"" + strToDoItem + "\"]/following-sibling::button");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(existingToDoItemEle));

		WebElement existingToDoItem = driver.findElement(existingToDoItemEle);
		existingToDoItem.findElement(By.xpath("//*[.='" + strToDoItem + "']")).click();
		existingToDoItem.click();
		totalCountOfToDoItemRemoved++;
		takeSnapShot("Screenshots/DeletedToDoItem" + totalCountOfToDoItemRemoved + ".png");
	}

	public void validateDeletedToDoItems(String validationMsg) {
		By toDoItemListCountEle = By.xpath("//*[@class=\"todo-count\"]/strong");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(toDoItemListCountEle));
		WebElement toDoItemListCount = driver.findElement(toDoItemListCountEle);
		int countOfToDoItemLeft = Integer.parseInt(toDoItemListCount.getText());
		if (countOfToDoItemLeft == totalCountOfToDoItemListed - totalCountOfToDoItemRemoved) {
			System.out.println("Test case Passed and Validation Is :" + validationMsg);
		} else {
			int ItemActive = totalCountOfToDoItemListed - totalCountOfToDoItemRemoved;
			System.out.println("Actual is:" + countOfToDoItemLeft);
			System.out.println("Expected is:" + ItemActive);
		}
	}

	public void clickCompletedToDoItemFilter() throws Exception {
		By completedFilterToDoItemEle = By.xpath("//*[contains(@href,\"completed\")]");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(completedFilterToDoItemEle));

		WebElement existingToDoItem = driver.findElement(completedFilterToDoItemEle);
		existingToDoItem.click();
		takeSnapShot("Screenshots/CompletedToDoItemList.png");
	}

	public void validateCompletedToDoItemsList(String strCompletedToDoItem, String validationMsg) {
		By completedToDoItemListCountEle = By.xpath("//*[@class=\"todo-list\"]//li");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(completedToDoItemListCountEle));
		List<WebElement> toDoItemCompletedList = driver.findElements(completedToDoItemListCountEle);
		int countOfToDoItemCompleted = toDoItemCompletedList.size();
		if (countOfToDoItemCompleted == totalCountOfToDoItemCompleted) {
			for (WebElement toDoCompletedItem : toDoItemCompletedList) {
				if (strCompletedToDoItem.equalsIgnoreCase(toDoCompletedItem.getText())) {
					System.out.println("Test case Passed and Validation Is :" + validationMsg);
				} else {
					System.out.println("Actual is:" + toDoCompletedItem.getText());
					System.out.println("Expected is:" + strCompletedToDoItem);
				}
			}
		} else {
			System.out.println("Actual is:" + countOfToDoItemCompleted);
			System.out.println("Expected is:" + totalCountOfToDoItemCompleted);
		}
	}

	public void clickActiveToDoItemFilter() throws Exception {
		By activeFilterToDoItemEle = By.xpath("//*[contains(@href,\"active\")]");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(activeFilterToDoItemEle));

		WebElement existingToDoItem = driver.findElement(activeFilterToDoItemEle);
		existingToDoItem.click();
		takeSnapShot("Screenshots/ActiveToDoItemList.png");
	}

	public void validateActiveToDoItemsList(String strActiveToDoItem, String validationMsg) {
		By activeToDoItemListCountEle = By.xpath("//*[@class=\"todo-list\"]//li");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(activeToDoItemListCountEle));
		List<WebElement> toDoItemActiveList = driver.findElements(activeToDoItemListCountEle);
		int countOfToDoItemActive = toDoItemActiveList.size();
		By toDoItemListCountEle = By.xpath("//*[@class=\"todo-count\"]/strong");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(toDoItemListCountEle));
		WebElement toDoItemListCount = driver.findElement(toDoItemListCountEle);
		int countOfToDoItemLeft = Integer.parseInt(toDoItemListCount.getText());
		if (countOfToDoItemActive == countOfToDoItemLeft) {
			for (WebElement toDoActiveItem : toDoItemActiveList) {
				if (strActiveToDoItem.equalsIgnoreCase(toDoActiveItem.getText())) {
					System.out.println("Test case Passed and Validation Is :" + validationMsg);
					break;
				}
			}
		} else {
			System.out.println("Actual is:" + countOfToDoItemActive);
			System.out.println("Expected is:" + countOfToDoItemLeft);
		}
	}

	public void editToDoItemWithNewToDoItem(String existToDoItem, String newToDoItem) throws Exception {
		By existingAddedToDoItem = By.xpath("//*[.=\"" + existToDoItem + "\"]");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(existingAddedToDoItem));
		Actions action = new Actions(driver);

		WebElement existingToDoItem = driver.findElement(existingAddedToDoItem);
		if (existingToDoItem.isDisplayed()) {
			action.doubleClick(existingToDoItem).sendKeys(" " + newToDoItem + Keys.ENTER).build().perform();
			// existingToDoItem.sendKeys(Keys.ENTER);
			if (driver.findElement(By.xpath("//*[contains(text(),'" + newToDoItem + "')]")).isDisplayed()) {
				System.out.println("Test case Passed for ToDo Item edit");
			}
		} else {
			System.out.println("Test case failed for ToDo Item edit");
		}
		takeSnapShot("Screenshots/EditToDoItemList.png");
	}

	public void clickAllToDoItemFilter() throws Exception {
		By allFilterToDoItemEle = By.xpath("//*[contains(@href,\"all\")]");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(allFilterToDoItemEle));

		WebElement existingToDoItem = driver.findElement(allFilterToDoItemEle);
		existingToDoItem.click();
		takeSnapShot("Screenshots/AllToDoItemList.png");
	}

	public void validateAllToDoItemsList(String strToDoItem, String validationMsg) {
		By allToDoItemListCountEle = By.xpath("//*[@class=\"todo-list\"]//li");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(allToDoItemListCountEle));
		List<WebElement> toDoItemList = driver.findElements(allToDoItemListCountEle);
		int countOfToDoIteme = toDoItemList.size();
		for (WebElement toDoItem : toDoItemList) {
			if (strToDoItem.equalsIgnoreCase(toDoItem.getText())) {
				System.out
						.println("Test case Passed with Item: " + strToDoItem + " & Validation Msg: " + validationMsg);
			}
		}
		if (countOfToDoIteme == totalCountOfToDoItemListed) {
			System.out.println("Test case Passed for ToDo Item All Filter");
		} else {
			System.out.println("Test case Failed for ToDo Item All Filter");
		}
	}

	public void clickClearCompletedToRemoveToDoItem(String strToDoItem) throws Exception {
		By clearCompletedEle = By.xpath("//*[@class=\"clear-completed\"]");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(clearCompletedEle));

		WebElement existingToDoItem = driver.findElement(clearCompletedEle);
		existingToDoItem.click();
		takeSnapShot("Screenshots/DeleteCompletedToDoItem.png");
	}

	public void validateClearCompletedToDoItems(String validationMsg) {
		By toDoItemListCountEle = By.xpath("//*[@class=\"todo-count\"]/strong");
		waitVar.until(ExpectedConditions.presenceOfElementLocated(toDoItemListCountEle));
		System.out.println("Test case Passed and Validation Is :" + validationMsg);
	}

	public static void takeSnapShot(String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
}