Feature: Manage my todo list

Scenario:
	Given User Open Browser
    And Navigate to URL Domain
    And User click on Advanced button
  Scenario Outline: User creates todo list on ToDoMVC
  
    Given User is on ToDoMVC Home Page.
    When User Enter ToDo item As "<ToDoItems>" in Textbox and submit it
    Then System Validate with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework | ToDo Item is added to the List |
      | List Down All the test cases from user Story | ToDo Item is added to the List |
	  | Create feature file for all TCs | ToDo Item is added to the List |
	  | Implement feature file with selenium script | ToDo Item is added to the List |
	  
  Scenario Outline: User completes 1st ToDo Item from list
  
    Given User is on ToDoMVC Home Page.
    When User select "<ToDoItems>" from list to mark it completed
    Then Selected Item is marked completed with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework | ToDo Item is marked completed successful |

  Scenario Outline: User added 1 new item to todo list on ToDoMVC
    
    Given User is on ToDoMVC Home Page.
    When User Enter ToDo item As "<ToDoItems>" in Textbox and submit it
    Then System Validate with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Executes all the TCs developed     | ToDo Item is added to the List  |
      
  Scenario Outline: User wants to check completed todo list on ToDoMVC
    
    Given User is on ToDoMVC Home Page.
    When User Click on Completed filter
    Then System Validate Completed "<ToDoItems>" with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework     | ToDo Item is there in completed List  |
    
  Scenario Outline: User wants to check Active todo list on ToDoMVC
    
    Given User is on ToDoMVC Home Page.
    When User Click on Active filter
    Then System Validate Active "<ToDoItems>" with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | List Down All the test cases from user Story | ToDo Active Item is present in the List |
	  | Create feature file for all TCs | ToDo Active Item is present in the List |
	  | Implement feature file with selenium script | ToDo Active Item is present in the List |
      | Executes all the TCs developed     | ToDo Active Item is present in the List  |

  Scenario Outline: User wants to check All todo list on ToDoMVC
    
    Given User is on ToDoMVC Home Page.
    When User Click on All filter
    Then System Validate All filter for "<ToDoItems>" with Message As "<Validation>"
    
    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework     | ToDo Item is there in All List  |

  Scenario Outline: User wants to delete completed items from todo list on ToDoMVC
    
    Given User is on ToDoMVC Home Page.
    When User select completed "<ToDoItems>" from list to delete it
    Then Selected Item is deleted with Message As "<Validation>"

    Examples: 
      | ToDoItems                    | Validation                                           |
      | Create a framework | ToDo Item is deleted from the List |
      
      
  Scenario Outline: User wants to edit particular todo item from list on ToDoMVC
    
    Given User is on ToDoMVC Home Page.
    When User click on "<ExistingToDoItems>" to edit it with "<NewToDoItems>"
    Then System Validate after editing ToDO Item with Message As "<Validation>"

    Examples: 
      | ExistingToDoItems                  |NewToDoItems     | Validation                    |
      | Executes all the TCs developed     | in Cucumber| ToDo Item is edited successfully  |

 