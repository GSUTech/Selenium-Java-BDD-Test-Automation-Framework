
package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utilities.ConfigReader;

import org.junit.Assert;

import pages.LoginPage;
import utilities.Driver;

public class LoginSteps
{

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void userIsOnLoginPage()
    {

        Driver.getDriver().get(
                ConfigReader.getProperty("url")
        );

        loginPage = new LoginPage(Driver.getDriver());
    }

    @When("the user enters a valid username")
    public void enterValidUsername()
    {
        loginPage.enterUsername(ConfigReader.getProperty("username"));
    }

    @And("the user enters a valid password")
    public void enterValidPassword()
    {
        loginPage.enterPassword(ConfigReader.getProperty("password"));

    }

    @And("the user clicks the login button")
    public void clickLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the user should see a success message")
    public void verifySuccessMessage()
    {
        Assert.assertTrue(loginPage.getMessage().contains("You logged into a secure area!"));
    }

    @When("the user enters an invalid username")
    public void enterInvalidUsername() {
        loginPage.enterUsername("wronguser");
    }

    @And("the user enters an invalid password")
    public void enterInvalidPassword() {
        loginPage.enterPassword("wrongpassword");
    }

    @Then("the user should see an error message")
    public void verifyErrorMessage() {
        Assert.assertTrue(
                loginPage.getMessage().contains(
                        "Your username is invalid!"
                )
        );
    }






    @When("the user enters username {string}")
    public void enterUsername(String username)
    {
        loginPage.enterUsername(username);
    }

    @And("the user enters password {string}")
    public void enterPassword(String password)
    {
        loginPage.enterPassword(password);
    }

    @Then("the login message should contain {string}")
    public void verifyLoginMessage(String expectedMessage)
    {
        Assert.assertTrue("Expected message was not displayed: " + expectedMessage,
                loginPage.getMessage().contains(expectedMessage)
        );
    }
}
