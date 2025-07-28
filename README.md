# AutomationTestDemo
Automation using Selenium with Java and TestNG for https://automationteststore.com/

The automation framework is designed using the Page Object Model (POM) architecture to promote scalability and maintainability.

  BasePage serves as the superclass for all page objects, centralizing shared functionality.
  
  Web elements are managed via BaseElement, which encapsulates built-in wait strategies to reduce test flakiness and enhance stability.
  
  BaseTest acts as the foundation for all test classes, handling driver initialization and teardown through standardized setup and cleanup methods.
  
  Tests are organized and executed using TestNG, leveraging its grouping and annotation capabilities for efficient test management.
