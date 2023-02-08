import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumAdapter implements ServiceNowProcess
{
    public ChromeDriver driver;
    JavascriptExecutor executor;

    public void launchSalesForceURL()
    {
        driver = new ChromeDriver();
        driver.get("https://dev49355.service-now.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void login(String userName, String password) {
        driver.findElement(By.id("user_name")).sendKeys(userName);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.id("sysverb_login")).click();
    }

    public void navigateToIncidents() {

        //driver.get("https://dev49355.service-now.com/now/nav/ui/classic/params/target/incident_list.do");
        try {
                Thread.sleep(25000);
                executor = (JavascriptExecutor)driver;
                WebElement homePage = (WebElement) executor.executeScript("return document.querySelector('body').firstElementChild");
                WebElement allButton = (WebElement) executor.executeScript("return document.querySelector('body').firstElementChild.shadowRoot.querySelector('div > sn-canvas-appshell-root > sn-canvas-appshell-layout > sn-polaris-layout').shadowRoot.querySelector('div.sn-polaris-layout.polaris-enabled > div.layout-main > div.header-bar > sn-polaris-header').shadowRoot.querySelector('div > div > div').nextSibling.querySelector('[aria-label= All]')");
                executor.executeScript("arguments[0].click();", allButton);
                Thread.sleep(2000);
                WebElement incidentButton = (WebElement) executor.executeScript("return document.querySelector('body').firstElementChild.shadowRoot.querySelector('div > sn-canvas-appshell-root > sn-canvas-appshell-layout > sn-polaris-layout').shadowRoot.querySelector('div.sn-polaris-layout.polaris-enabled > div.layout-main > div.header-bar > sn-polaris-header').shadowRoot.querySelector('nav > div > sn-polaris-menu:nth-child(1)').shadowRoot.querySelector('nav > div > div.sn-tree-menu.sn-polaris-nav-content > div > div > sn-collapsible-list:nth-child(1)').shadowRoot.querySelector('div > div > ul > li:nth-child(8) > span > a > span > span.label')");
                executor.executeScript("arguments[0].click();", incidentButton);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
    }

    public void navigateToNewIncidentCreation() {
        executor = (JavascriptExecutor)driver;
        WebElement serviceNowFrame = (WebElement) executor.executeScript("return document.querySelector('body').firstElementChild.shadowRoot.querySelector('#gsft_main')");
        driver.switchTo().frame(serviceNowFrame);
        driver.findElement(By.id("sysverb_new")).click();
        //driver.get("https://dev49355.service-now.com/now/nav/ui/classic/params/target/incident.do");
    }

    public void fillInDetails(String shortDescription)
    {
        //driver.findElement(By.id("sys_display.incident.caller_id")).sendKeys(caller);
        driver.findElement(By.id("incident.short_description")).sendKeys(shortDescription);
    }

    public String captureNumber() {

        return driver.findElement(By.id("incident.number")).getAttribute("value");
    }

    public void createIncident() {
        driver.findElement(By.id("sysverb_insert")).click();
    }


    public boolean verifyIncidentCreation(String numberInCreationForm) {
        driver.findElement(By.xpath("(//input[@type='search'][@placeholder='Search'])[1]")).sendKeys(numberInCreationForm, Keys.ENTER);
        String searchResult = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr[1]/td[3]/a")).getText();
        if(searchResult.equals(numberInCreationForm)) {
            System.out.println("Incident Creation Verification Success!!");
            return true;
        }
        else {
            System.out.println("Incident creation Verification Failed!!!");
            return false;
        }
    }
}
