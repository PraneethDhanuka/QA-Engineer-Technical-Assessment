# QA Engineer Technical Assessment

This repository contains my completed QA Engineer Technical Assessment.

## 1. Manual Testing
Manual test cases are available in the `Manual_Testing` folder.

## 2. API Testing
API testing was completed using Postman.

The Postman collection includes test scenarios for:
- Successful login
- Invalid credentials
- Missing username
- Update booking

The exported Postman collection is available in the `API_Testing` folder.

## 3. Load Testing
Load testing was performed using Apache JMeter.

The `Load_Testing` folder contains:
- JMeter test plan
- Load testing report
- Test execution evidence

## 4. UI Automation
UI automation was implemented using:
- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Microsoft Edge

The automated test covers the SauceDemo login and checkout flow.

## 5. CI/CD
GitHub Actions is configured to automatically execute the Selenium automation tests.

The workflow runs on push and pull requests using Java 17 and Maven.
