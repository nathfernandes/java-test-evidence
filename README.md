# java-test-evidence
Simple test automation project using `Java`, `Selenium`, `TestNG` and `ExtentReports`. The main purpose of this project is to show how the generation of test evidences can be done using these technologies and also adding screenshots whenever a test fails.

# Running the tests
Tests can be run using the following commands:
```bash
set classpath=<path where you saved the project>\java-test-evidence\target\test-classes;<path where you saved the project>\java-test-evidence\target\dependency\*
```
```bash
java org.testng.TestNG <path where you saved the project>\java-test-evidence\testng.xml 
```
This command will run the tests and generate the HTML file with the test evidences using `ExtentReports`.

# Results
After the execution, you will be able to find the HTML file of the generated report inside the `reports` folder.