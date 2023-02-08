public interface ServiceNowProcess
{
    void launchSalesForceURL();
    void login(String userName, String Password);
    void navigateToIncidents();
    void navigateToNewIncidentCreation();
    void fillInDetails(String shortDescription);
    String captureNumber();
    void createIncident();
    boolean verifyIncidentCreation(String numberInCreationForm);
}
