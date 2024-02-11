import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pageobject.CustomerFormPageObject;
import pageobject.ScooterRentFormPageObject;

import java.time.LocalDate;

@RunWith(Parameterized.class)
public class RentScooterTest extends AbstractUiTestParent {

    private final By orderButtonLocator;

    public RentScooterTest(By orderButtonLocator) {
        this.orderButtonLocator = orderButtonLocator;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { By.xpath("//div[@class='Header_Nav__AGCXC']/button[contains(@class, 'Button_Button__ra12g')]") },
                { By.xpath("//div[@class='Home_FinishButton__1_cWm']/button[contains(@class, 'Button_Button__ra12g')]") }
        };
    }

    @Test
    public void rentScooterTest() throws InterruptedException {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        WebElement orderButtonElement = driver.findElement(orderButtonLocator);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({ behaviour: \"instant\", block: \"center\" });",
                orderButtonElement
        );

        orderButtonElement.click();

        CustomerFormPageObject customerFormPageObject = new CustomerFormPageObject(driver);

        customerFormPageObject.fillFirstName("Гарик");
        customerFormPageObject.fillLastName("Харламов");
        customerFormPageObject.fillAddress("Пушкина, 72");
        customerFormPageObject.fillSubwayStation("ВДНХ");
        customerFormPageObject.fillPhoneNumber("77076213225");
        customerFormPageObject.proceed();

        ScooterRentFormPageObject scooterRentFormPageObject = new ScooterRentFormPageObject(driver);
        scooterRentFormPageObject.fillDeliveryDate(LocalDate.of(2024, 2, 5));
        scooterRentFormPageObject.chooseRentalPeriod("двое суток");
        scooterRentFormPageObject.chooseScooterColor("чёрный жемчуг");
        scooterRentFormPageObject.fillCommentForCourier("побыстрее, пожалуйста");
        scooterRentFormPageObject.clickOrderButton();
        scooterRentFormPageObject.confirmOrder();
        scooterRentFormPageObject.checkOrderConfirmation();
    }

}
