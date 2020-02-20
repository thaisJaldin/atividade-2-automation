package steps;

import definition.CRUDProcess;
import support.RESTSupport;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.json.internal.LazyMap;
import org.junit.Assert;

public class CRUDProcessSteps {

    @Given("^A user are on Process page$")
    public void aUserAreOnProcessPage() {
        CRUDProcess.clearFields();
    }

    @And("^field \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void fieldWithValue(String field, String value) {
        CRUDProcess.addFields(field, value);
    }

    @When("^user press Save$")
    public void userPressSave() {
        RESTSupport.executePost(CRUDProcess.getEndPoint(), CRUDProcess.getFields());
        CRUDProcess.setLastID(RESTSupport.key("id").toString());
    }

    @Then("^A message should be display \"([^\"]*)\"$")
    public void aMessageShouldBeDisplay(String msg) {
        LazyMap msgMap = new LazyMap();
        msgMap.put("The Item was added", 201);
        msgMap.put("Success", 200);
        msgMap.put("Not Found", 404);

        Assert.assertEquals(msgMap.get(msg), RESTSupport.getResponseCode());
    }


    @And("^The field \"([^\"]*)\" by value \"([^\"]*)\" should be return for user$")
    public void theFieldByValueShouldBeReturnForUser(String field, String value) {
        Assert.assertEquals(value, RESTSupport.key(field));
    }

    @And("^list of ids should be \"([^\"]*)\"$")
    public void listOfIdsShouldBe(String id) {
        CRUDProcess.setLastID(id);

    }

    @When("^user click on view$")
    public void userClickOnView() {
        RESTSupport.executeGet(CRUDProcess.getEndPoint() + CRUDProcess.getLastID() + ".json");
    }

    @And("^view the Item with id \"([^\"]*)\"$")
    public void viewTheItemWithId(String id) {
        CRUDProcess.setLastID(id);
    }

    @When("^user press Update$")
    public void userPressUpdate() {
        RESTSupport.executePut(CRUDProcess.getEndPoint() + CRUDProcess.getLastID() + ".json", CRUDProcess.getFields());
    }

    @When("^user press Delete$")
    public void userPressDelete() {
        RESTSupport.executeDelete(CRUDProcess.getEndPoint() + CRUDProcess.getLastID() + ".json");
    }
}
