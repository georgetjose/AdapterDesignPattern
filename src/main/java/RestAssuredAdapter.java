import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredAdapter implements ServiceNowProcess
{
    public void launchSalesForceURL() {

    }

    public void login(String userName, String Password) {

    }

    public void navigateToIncidents() {

    }

    public void navigateToNewIncidentCreation() {

    }

    @Override
    public void fillInDetails(String shortDescription) {

    }

    public String captureNumber() {
        return null;
    }

    public void createIncident() {

    }

    public boolean verifyIncidentCreation(String numberInCreationForm)
    {
        RestAssured.baseURI ="https://dev49355.service-now.com/";
        RestAssured.authentication = RestAssured.basic("admin", "x+!E9h9OMqbQ");
        Response getIncidentResponse =   RestAssured.given()
                                                    .queryParam("sysparm_query", "number="+numberInCreationForm)
                                                    .queryParam("sysparm_limit", "1")
                                                    .get("/api/now/table/incident");

        JsonPath getIncidentResponseJasonPath = getIncidentResponse.jsonPath();
        String number = getIncidentResponseJasonPath.getString("result[0].number");

        if(number!=null) {
            if (number.equals(numberInCreationForm)) {
                System.out.println("Incident Creation Verification Success!!");
                return true;
            } else {
                System.out.println("Incident creation Verification Failed!!!");
                return false;
            }
        }
        else {
        System.out.println("Incident creation Verification Failed!!!");
        return false;
    }
    }
}
