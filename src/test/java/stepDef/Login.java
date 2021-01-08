package stepDef;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public ChromeDriver driver;
	String incidentNumber;

	@Given("open the chrome browser and maximize the window")
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Given("load the service now application")
	public void loadAppUrl() {
		driver.get("https://dev68594.service-now.com/");

	}

	@Given("enter username as {string}")
	public void enter_username_as_admin(String username) {
		driver.switchTo().frame("gsft_main");
		driver.findElementById("user_name").sendKeys(username);
	}

	@Given("enter password as {string}")
	public void enter_password_as_india(String password) {
		driver.findElementById("user_password").sendKeys(password);
	}

	@When("click login button")
	public void click_login_button() {
		driver.findElementByName("not_important").click();
	}

	@Then("homepage should be displayed")
	public void homepage_should_be_displayed() {
		driver.switchTo().defaultContent();
		System.out.println("Home page should be displayed");
	}
	
	@But("error should be displayed")
	public void verifyError() {
		System.out.println("error is displayed");

	}

	@Given("enter {string} in the filter navigator")
	public void enter_in_the_filter_navigator(String string) {
		driver.switchTo().defaultContent();

		driver.findElementById("filter").sendKeys("incident");
		
	}

	@Given("click All under Incident")
	public void click_all_under_incident() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElementByXPath("(//div[text()='All'])[2]").click();
	    
	}

	@Given("click New button")
	public void click_new_button() {
		driver.switchTo().frame("gsft_main");

		driver.findElementByXPath("//button[text()='New']").click();
	   
	}

	@Given("read the incident number from Number and store it in variable for verification")
	public void read_the_incident_number_from_number_and_store_it_in_variable_for_verification() {
	    // Write code here that turns the phrase above into concrete actions
		
		incidentNumber = driver.findElementById("incident.number").getAttribute("value");
	    
	}

	@Given("click on look up icon for Caller")
	public void click_on_look_up_icon_for_caller() {
		driver.findElementByXPath("//span[text()='Caller']/following::span[@class='icon icon-search'][1]").click();

	   
	}

	@Given("search {string} in the lookup window")
	public void search_in_the_lookup_window(String string) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);

		String secondWin = listHandles.get(1);
		String firstWin = listHandles.get(0);
		driver.switchTo().window(secondWin);

		driver.findElementByXPath("//input[@placeholder='Search']").sendKeys("Abel", Keys.ENTER);

	   
	}

	@Given("click {string} from the results")
	public void click_from_the_results(String string) {
		driver.findElementByLinkText("Abel Tuter").click();

	   
	}

	@Given("enter the Short_description")
	public void enter_the_short_description() {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);

		//String secondWin = listHandles.get(0);
		String firstWin = listHandles.get(0);
		driver.switchTo().window(firstWin);

		driver.switchTo().frame("gsft_main");
		
		//String incidentNumber = driver.findElementById("incident.number").getAttribute("value");
		
		driver.findElementById("incident.short_description").sendKeys("Test");

	    
	}

	@When("click on Submit")
	public void click_on_submit() {
		driver.findElementById("sysverb_insert").click();
	    
	}

	@Then("search for the newly created incident and verify")
	public void search_for_the_newly_created_incident_and_verify() {
		WebElement searchBy = driver.findElementByXPath("//select[contains(@id,'select')]");

		Select dd = new Select(searchBy);

		dd.selectByVisibleText("Number");

		driver.findElementByXPath("//input[@placeholder='Search']").sendKeys(incidentNumber, Keys.ENTER);
	    
	}

	

}
