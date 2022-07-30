package com.cucumber.StepDefinitions;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

import com.cucumber.Selenium.SeleniumFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ManageToDoMVCItemsSD {
	private By advancedButton=By.xpath("//*[@id=\"details-button\"]");
	private By proceedlink=By.xpath("//*[@id=\"proceed-link\"]");
	private By homaPagevalidation=By.xpath("(//*[.=\"Vue.js\"])[1]");
	private By newToDoTextBox=By.xpath("//*[@class=\"new-todo\"]");
	public static String baseURL = "https://todomvc.com/examples/vue/";
	SeleniumFunctions sf = new SeleniumFunctions();

	@Given("^User Open Browser$")
	public void user_Open_Browser() throws MalformedURLException, InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		sf.createDriver();
	}

	@Given("^Navigate to URL Domain$")
	public void navigate_to_URL_Domain(){
	    // Write code here that turns the phrase above into concrete actions
	    sf.launchUrl(baseURL);
	}

	@Given("^User click on Advanced button$")
	public void user_click_on_Advanced_button() throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.handleAdvancedButton(advancedButton,proceedlink);
	}

	@Given("^User is on ToDoMVC Home Page\\.$")
	public void user_is_on_ToDoMVC_Home_Page(){
	    // Write code here that turns the phrase above into concrete actions
		sf.ishomepageDisplayed(homaPagevalidation);
	}

	@When("^User Enter ToDo item As \"(.*?)\" in Textbox and submit it$")
	public void user_Enter_ToDo_item_As_in_Textbox(String newToDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
	    sf.addNewToDoItem(newToDoTextBox, newToDoItem);
	}

	@Then("^System Validate with Message As \"(.*?)\"$")
	public void system_Validate_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
	    sf.validateNewlyAddedItem(validationMsg);
	}

	@When("^User select \"(.*?)\" from list to mark it completed$")
	public void user_select_from_list_to_mark_it_completed(String toDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickCheckboxToSelectToDoItem(toDoItem);
	}

	@Then("^Selected Item is marked completed with Message As \"(.*?)\"$")
	public void selected_Item_is_marked_completed_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateCompletedToDoItems(validationMsg);
	}

	@When("^User select completed \"(.*?)\" from list to delete it$")
	public void user_select_completed_from_list_to_delete_it(String toDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickDeleteToRemoveToDoItem(toDoItem);
	}

	@Then("^Selected Item is deleted with Message As \"(.*?)\"$")
	public void selected_Item_is_deleted_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateDeletedToDoItems(validationMsg);
	}
	
	@When("^User Click on Completed filter$")
	public void user_click_on_completed_filter() throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickCompletedToDoItemFilter();
	}

	@Then("^System Validate Completed \"(.*?)\" with Message As \"(.*?)\"$")
	public void system_validate_completed_with_Message_As(String toDoItem,String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateCompletedToDoItemsList(toDoItem,validationMsg);
	}
	
	@When("^User Click on Active filter$")
	public void user_click_on_active_filter() throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickActiveToDoItemFilter();
	}

	@Then("^System Validate Active \"(.*?)\" with Message As \"(.*?)\"$")
	public void system_Validate_Active_with_Message_As(String strActiveToDoItem, String validationMsg) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    sf.validateActiveToDoItemsList(strActiveToDoItem, validationMsg);
	}
	
	@When("^User click on \"(.*?)\" to edit it with \"(.*?)\"$")
	public void user_click_on_Item_to_edit_with_newItem(String existingToDoItem,String newToDoItem) throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.editToDoItemWithNewToDoItem(existingToDoItem,newToDoItem);
	}
	
	@Then("^System Validate after editing ToDO Item with Message As \"(.*?)\"$")
	public void system_validate_with_Message_As(String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateDeletedToDoItems(validationMsg);
		sf.teardown();
	}
	
	@When("^User Click on All filter$")
	public void user_click_on_all_filter() throws Exception{
	    // Write code here that turns the phrase above into concrete actions
		sf.clickAllToDoItemFilter();
	}

	@Then("^System Validate All filter for \"(.*?)\" with Message As \"(.*?)\"$")
	public void system_validate_all_filter(String strToDoItem, String validationMsg){
	    // Write code here that turns the phrase above into concrete actions
		sf.validateAllToDoItemsList(strToDoItem,validationMsg);
	}
}
