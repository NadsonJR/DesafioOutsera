package org.desafio.steps;

import com.itextpdf.layout.Document;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.desafio.config.CucumberHooks;
import org.desafio.logic.APILogic;
import org.desafio.logic.HomeLogic;
import org.desafio.logic.LoginLogic;
import org.desafio.utils.DriverManager;
import org.desafio.utils.Utilities;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class APISteps {

    private APILogic apiLogic;
    private Response response;
    private String scenarioName;
    private Utilities utilities;
    private Document documentEvidence;
    public APISteps() {
        apiLogic = new APILogic();
        documentEvidence = CucumberHooks.getDocumentEvidence();
    }

    @Given("I get users")
    public void i_get_users() {
        response = apiLogic.getUsers(documentEvidence);
    }
    @Then("I should see the status code {int}")
    public void i_should_see_the_status_code(Integer statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
    }

    @Given("I get user by id {int}")
    public void i_get_user_by_id(Integer int1) {
        response = apiLogic.getUserById(int1,documentEvidence);
    }
    @Given("I create a user")
    public void i_create_a_user() {
        response = apiLogic.createUser("eve.holt@reqres.in", "pistol", documentEvidence);
    }
    @Given("I update a user")
    public void i_update_a_user() {
        response = apiLogic.updateUser(4, "morpheus", "zion resident", documentEvidence);
    }

    @Given("I delete a user")
    public void i_delete_a_user() {
        response = apiLogic.deleteUser(4, documentEvidence);
    }

}
