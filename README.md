# Selenium Test framework

Introduction: 
---------------
This Test Automation Framework is created using Java + Selenium Web Driver + TestNG + POM, which can be used across different web based applications.
In this approach, the endeavor is to build a lot of applications independent reusable keyword components so that they can directly used for another web application without spending any extra effort. 
With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch, but use the application independent keyword components to the extent possible and create application specific components for the specific needs.

Prerequisites:
---------------
*	Java jdk-1.8 or higher
*	Apache Maven 3 or higher
*	Please refer for any help in Maven. 
* 	http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* 	http://www.tutorialspoint.com/maven/maven_environment_setup.html

Maven Depencies/Plugins:
-------------------------
Dependencies/versions to be included for using this project
*	Selenium - 4.12
*	Testng - 7.7.0
*	Webdriver Manager - 5.5.3
* Jackson Databind - 2.13.3
* Extent reports - 5.1.1
* Json simple - 1.1.1

Test Data configuration:
-------------------------
*	Test data is configured in src\test\java\Planit\TestData as JSON files.
*	Browser and URL is passed via \src\main\java\Planit\Resources\config.properties

Execution:
---------------
*	Clone the repository.
*	Go to src/test/java/Tests for Testcases.
*	For local Execution, Add Tests under Class section in TestNG.XML file located in <testSuites> folder.
*	To build project and execution, use command ....\.\SeleniumFrameworkAssessment> **mvn clean test**

Screenshot:
---------------
*	Most of the time we think to Capture Screenshot in WebDriver when some kind of error or exception surfaces while practicing testing, to resolve the same the framework has a method. 
*	getScreenshot() is used to indicate driver to capture a screenshot and store it in //reports directory.

Reporting:
-------------
*  The framework produce index.html report. It resides in the same 'target\surefire-reports' folder. This reports gives the link to all the different component of the TestNG reports like Groups & Reporter Output. On clicking these will display detailed descriptions of execution.
*  You can find emailable-report.html from target\surefire-reports to email the test reports. As this is a html report you can open it with browser.
*  For test execution logs, Extent report logs are configured
*  Extent reports can be found in \reports\TestReport.html

Enhancements/Improvements in Framework:
-----------------------------------------
*  Wait time is hardcoded and can be defined based on the project requirements. Also it is to be defined globally.
*  Test methods are to be simplified and fine tuned even further.
*  Test data is currently configured in JSON format as it is light weight, structured and scalable. Depending on the project goals and data sourcing/complexity, the input data formatting can be changed to excel,xml etc.
*  Reporting can be enhanced further to make it more dynamic in nature.
