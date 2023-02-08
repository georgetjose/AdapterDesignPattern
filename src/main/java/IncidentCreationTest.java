public class IncidentCreationTest
{
    public static void main(String[] args) {
        SeleniumAdapter seleniumAdapter = new SeleniumAdapter();
        RestAssuredAdapter restAssuredAdapter = new RestAssuredAdapter();

        seleniumAdapter.launchSalesForceURL();
        seleniumAdapter.login("admin", "x+!E9h9OMqbQ");
        seleniumAdapter.navigateToIncidents();
        seleniumAdapter.navigateToNewIncidentCreation();
        seleniumAdapter.fillInDetails("Test Description - Adapter Design");
        String number = seleniumAdapter.captureNumber();
        seleniumAdapter.createIncident();
        //seleniumAdapter.verifyIncidentCreation(number);
        restAssuredAdapter.verifyIncidentCreation(number);
    }
}
