package steps;

import Base.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SearchStep extends BaseUtil {

    private BaseUtil base;
    private String articleToSearch = "";

    public SearchStep(BaseUtil base) {
        this.base = base;
    }

    @Given("^I navigate to \"([^\"]*)\"$")
    public void navigateToFirstSite(String url) throws Throwable {
        System.out.println("Navigating " + url);
        Driver.navigate().to(url);
        Thread.sleep(1000);

        WebElement privacyCookie = Driver.findElement(By.xpath("//*[@class='site-message--first-pv-consent__actions']//button"));
        if (privacyCookie.isDisplayed()) {
            privacyCookie.click();
        }
    }

    @When("^I select the first news article$")
    public void firstArticle() throws Throwable {
        List<WebElement> articleList = Driver.findElements(By.xpath("//ul//li[contains(@class,'fc-slice__item')]//h3"));

        System.out.println("Total Articles:::" + articleList.size());
        for (WebElement article : articleList) {
            System.out.println(article.getText());
            System.out.println("========================================================");
        }
        articleToSearch = articleList.get(0).getText();
        System.out.println("First Article Selected: " + articleToSearch);
    }

    @And("^I navigate to second site \"([^\"]*)\"$")
    public void navigateToSecondSite(String url) throws Throwable {
        System.out.println("Navigating " + url);
        Driver.navigate().to(url);
        Thread.sleep(1000);
    }

    @And("^I search for the article$")
    public void theArticle() throws Throwable {
        Driver.findElement(By.name("q")).sendKeys(articleToSearch);
        Driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        Thread.sleep(1000);
    }

    @Then("^I should see the search result on \"([^\"]*)\"$")
    public void iShouldSeeTheSearchResultOn(String url) throws Throwable {
        Driver.findElements(By.xpath("//div[@class='r']")).get(1).getText();
        List<WebElement> searchResult = Driver.findElements(By.xpath("//div[@class='r']"));
        Assert.assertTrue("This seems to be a fake news as article NOT found!!!",
                searchResult.size() > 1);
    }

}







