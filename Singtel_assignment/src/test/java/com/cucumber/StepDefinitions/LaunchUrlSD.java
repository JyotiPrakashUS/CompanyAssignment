package com.cucumber.StepDefinitions;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

import com.cucumber.Selenium.SeleniumFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LaunchUrlSD {
	private By advancedButton=By.xpath("//*[@id=\"details-button\"]");
	private By proceedlink=By.xpath("//*[@id=\"proceed-link\"]");
	private By homaPagevalidation=By.xpath("(//*[.=\"Vue.js\"])[1]");
	private By newToDoTextBox=By.xpath("//*[@class=\"new-todo\"]");
	public static String baseURL = "https://todomvc.com/examples/vue/";
    SeleniumFunctions sf = new SeleniumFunctions();

    @Given("^user navigates to url provided$")
    public void user_is_on_chome_browser() throws MalformedURLException,
            InterruptedException {
    	sf.createDriver();
    	sf.launchUrl(baseURL);
    }

    @When("^user handles advanced button$")
    public void user_navigates_to_url() throws Exception {
    	sf.handleAdvancedButton(advancedButton,proceedlink);
    }

    @Then("^user is displayed Home screen$")
    public void user_is_displayed_home_screen() {
    	sf.ishomepageDisplayed(homaPagevalidation);
        
    }
    
    @Given("^User is on ToDoMVC application Home Page\\.$")
	public void user_is_on_ToDoMVC_Home_Page(){
	    // Write code here that turns the phrase above into concrete actions
		sf.ishomepageDisplayed(homaPagevalidation);
	}

	@When("^User enter new ToDo item As \"(.*?)\" in Textbox and submit it$")
	public void user_Enter_ToDo_item_As_in_Textbox(String newToDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
	    sf.addNewToDoItem(newToDoTextBox, newToDoItem);
	}

	@Then("^System Validate with Message As \"(.*?)\" list$")
	public void system_Validate_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
	    sf.validateNewlyAddedItem(validationMsg);
	    sf.teardown();
	}
	
	@When("^User choose \"(.*?)\" from list to mark it completed$")
	public void user_select_from_list_to_mark_it_completed(String toDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickCheckboxToSelectToDoItem(toDoItem);
	}

	@Then("^Selected Item is marked as completed with Message As \"(.*?)\"$")
	public void selected_Item_is_marked_completed_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateCompletedToDoItems(validationMsg);
	}
	
	@When("^User click on clear complete button for \"(.*?)\" from list$")
	public void user_click_on_clear_complete_from_list(String toDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickClearCompletedToRemoveToDoItem(toDoItem);
	}

	@Then("^Completed Item is deleted with Message As \"(.*?)\"$")
	public void completed_item_is_deleted_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateClearCompletedToDoItems(validationMsg);
		sf.teardown();
	}

}
