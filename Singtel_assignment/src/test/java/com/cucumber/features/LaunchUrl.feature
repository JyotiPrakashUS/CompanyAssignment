Feature: ToDoMVC Url Launch

  Scenario: launch url for ToDoMVC
    Given user navigates to url provided
    When user handles advanced button
    Then user is displayed Home screen
    
  Scenario Outline: User creates todo list on ToDoMVC
  
    Given User is on ToDoMVC application Home Page.
    When User enter new ToDo item As "<ToDoItemlist>" in Textbox and submit it
    Then System Validate with Message As "<Validation>" list"

    Examples: 
      | ToDoItemlist                    | Validation                                           |
      | Create a framework | ToDo Item is added to the List |
      
  Scenario Outline: User completes 1st ToDo Item from list
  
    Given User is on ToDoMVC application Home Page.
    When User choose "<ToDoItems>" from list to mark it completed
    Then Selected Item is marked as completed with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework | ToDo Item is marked completed successful |
      
  Scenario Outline: User wants to clear completed ToDo Item from list
  
    Given User is on ToDoMVC application Home Page.
    When User click on clear complete button for "<ToDoItems>" from list
    Then Completed Item is deleted with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework | ToDo Item is marked completed successful |