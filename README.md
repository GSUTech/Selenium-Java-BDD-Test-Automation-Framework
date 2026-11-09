
# Selenium Cucumber BDD Automation Framework

A UI test automation framework built using Java, Selenium WebDriver, Cucumber BDD, JUnit, and Maven, with GitHub source control and Jenkins CI integration.

## Technologies Used

- Java 21
- Selenium WebDriver
- Cucumber BDD / Gherkin
- JUnit
- Maven
- Page Object Model (POM)
- Git & GitHub
- Jenkins CI/CD

## Framework Architecture

- `pages/` — Page Object classes containing UI locators and reusable page actions.
- `stepDef/` — Cucumber step definitions and test hooks.
- `runners/` — Cucumber test runner configuration.
- `utilities/` — WebDriver management and configuration utilities.
- `features/` — Gherkin feature files containing test scenarios.

## Automated Test Scenarios

The framework currently includes five login test scenarios covering:

- Successful login with valid credentials
- Login failure with invalid credentials
- Data-driven login tests using Scenario Outline examples

Test application: https://the-internet.herokuapp.com/login

## Running Tests Locally

### Prerequisites

- Java 21
- Maven
- Google Chrome

### Configuration

Copy `config.properties.example` to `config.properties` in the project root.

Update the local configuration if needed. The `config.properties` file is excluded from Git.

### Execute Tests

Run the following command from the project directory:

```bash
mvn clean test
```

## Test Reports

After execution, Cucumber generates:

- HTML report: `target/cucumber-report.html`
- JSON report: `target/cucumber.json`
- JUnit XML report: `target/cucumber-results.xml`

## Jenkins CI Integration

The project is integrated with Jenkins for automated test execution.

CI workflow:

1. Changes are committed and pushed to the GitHub repository.
2. Jenkins polls the repository for changes.
3. A detected change triggers a Jenkins build.
4. Jenkins checks out the latest code.
5. Maven executes the automated Cucumber tests.
6. Jenkins publishes JUnit test results and the Cucumber HTML report.

## Current Test Results

- Total scenarios: 5
- Passed: 5
- Failed: 0

## Future Enhancements

- Cross-browser testing
- Screenshot capture on test failures
- Parallel test execution
- Additional UI test scenarios
- Improved reporting and logging
