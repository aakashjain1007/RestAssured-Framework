# Rest Assured Java Framework

### Overview

This is a simple Java framework for API testing using the Rest Assured library, TestNG, Allure Report, and Lombok. The framework is designed to make API testing easy and provide clear and concise reports through Allure.

### **Dependencies**

The framework uses the following dependencies:

[Rest Assured](https://rest-assured.io/): A popular Java library for testing RESTful APIs.

[TestNG](https://testng.org/doc/): A testing framework for Java.

[Allure Report](http://allure.qatools.ru/): A flexible lightweight test report tool.

[Lombok](https://projectlombok.org/): A Java library that helps reduce boilerplate code.

[JsonPath](https://github.com/json-path/JsonPath): A library for working with JSON documents.

### **How to Run**

To execute the test suite, use the _**testng.xml**_ file. 
This file is configured to run the test classes in the src/test/java/tests package.

`$ mvn test -DsuiteXmlFile=testng.xml`

### **Reporting**

The framework generates Allure Reports, providing detailed insights into test execution. After running the tests, the Allure Report can be generated using the following command:

``allure generate allure-results --clean``

`allure serve allure-results`

The above commands will generate the Allure Report and open it in your default web browser.

### **Project Structure**

The project structure is organized as follows:
* **src/main/java**: Contains the all the Configuration require to Run the Tests like FilePath, ApiEndpoints, Common Utilities etc.
* **src/test/java**: Contains the test classes where API tests are implemented.
* **src/test/resources**: Contains the Schemas of the API Responses.
* **testNG.xml**: File to run the API Tests.
* **allure-results**: Directory where Allure test results are stored.
* **pom.xml**: Maven configuration file containing project dependencies and settings.


### **Important Notes**

* Update the config.ini file in src/main/java/configProperties with the appropriate values before running the tests.
* Modify the constants classes (ApiEndpoints and FilePath) in src/main/java/constantPaths if there are changes in API endpoints or file paths.
* Keep the POJOs in src/main/java/pojos updated with the structure of the actual API responses.
* Update the test cases in TodosTest.java and other test classes in src/test/java/tests based on changes to the API or application functionality.

### **Additional Notes**

* Make sure to configure your test environment and API endpoints in the appropriate configuration files.
* Check the documentation of Rest Assured, TestNG, Allure Report, and Lombok for detailed usage and customization options.

Feel free to contribute to this project by submitting issues.

Happy testing! 🚀
